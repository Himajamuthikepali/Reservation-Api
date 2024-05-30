package org.jsp.reservationapi.service;

import java.util.Optional;

import org.jsp.reservationapi.dao.UserDao;
import org.jsp.reservationapi.dto.ResponseStructure;
import org.jsp.reservationapi.dto.UserRequest;
import org.jsp.reservationapi.dto.UserResponse;
import org.jsp.reservationapi.exception.AdminNotFoundException;
import org.jsp.reservationapi.exception.UserNotFoundException;
import org.jsp.reservationapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		structure.setMessage("User saved");
		User user = userDao.saveUser(mapToUser(userRequest));
		structure.setData(mapToUserResponse(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	public ResponseEntity<ResponseStructure<UserResponse>> update(UserRequest userRequest, int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = mapToUser(userRequest);
			dbUser.setName(userRequest.getName());
			dbUser.setEmail(userRequest.getEmail());
			dbUser.setPhone(userRequest.getPhone());
			dbUser.setGender(userRequest.getGender());
			dbUser.setAge(userRequest.getAge());
			dbUser.setPassword(userRequest.getPassword());
			structure.setData(mapToUserResponse(userDao.saveUser(dbUser)));
			structure.setMessage("User Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new UserNotFoundException("Cannot Update User As Id Is Invalid");
	}

	public ResponseEntity<ResponseStructure<UserResponse>> findById(int id) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) {
			structure.setData(mapToUserResponse(dbUser.get()));
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStructure<UserResponse>> verify(long phone, String password) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.verify(phone, password);
		if (dbUser.isPresent()) {
			structure.setData(mapToUserResponse(dbUser.get()));
			structure.setMessage("Verification Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid Phone Number or Password");
	}

	public ResponseEntity<ResponseStructure<UserResponse>> verify(String email, String password) {
		ResponseStructure<UserResponse> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.verify(email, password);
		if (dbUser.isPresent()) {
			structure.setData(mapToUserResponse(dbUser.get()));
			structure.setMessage("Verification Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid Email Id or Password");
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if (dbUser.isPresent()) {
			userDao.delete(id);
			structure.setData("User Found");
			structure.setMessage("User Delete");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Cannot Delete User As Id is Invalid");
	}

	private User mapToUser(UserRequest userRequest) {
		return User.builder().email(userRequest.getEmail()).name(userRequest.getName()).phone(userRequest.getPhone())
				.gender(userRequest.getGender()).age(userRequest.getAge()).password(userRequest.getPassword()).build();
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().name(user.getName()).email(user.getEmail())
				.phone(user.getPhone()).gender(user.getGender()).id(user.getId())
				.age(user.getAge()).password(user.getPassword()).build();
	}

}
