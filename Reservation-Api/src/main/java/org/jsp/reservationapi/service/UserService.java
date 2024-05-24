package org.jsp.reservationapi.service;

import java.util.Optional;

import org.jsp.reservationapi.dao.UserDao;
import org.jsp.reservationapi.dto.ResponseStructure;
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
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user){
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(userDao.saveUser(user));
		structure.setMessage("User Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<User>> update(User user){
		Optional<User> recUser = userDao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if(recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(user.getName());
			dbUser.setAge(user.getAge());
			dbUser.setEmail(user.getEmail());
			dbUser.setGender(user.getGender());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			structure.setData(userDao.saveUser(user));
			structure.setMessage("User Updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new UserNotFoundException("Cannot Update User As Id Is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if(dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid User Id");
	}
	
	public ResponseEntity<ResponseStructure<User>> verify(long phone, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.verify(phone, password);
		if(dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("Verification Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid Phone Number or Password");
	}
	
	public ResponseEntity<ResponseStructure<User>> verify(String email, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.verify(email, password);
		if(dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("Verification Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new UserNotFoundException("Invalid Email Id or Password");
	}
	
	public ResponseEntity<ResponseStructure<String>> delete(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(id);
		if(dbUser.isPresent()) {
			userDao.delete(id);
			structure.setData("User Found");
			structure.setMessage("User Delete");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Cannot Delete User As Id is Invalid");
	}

}
