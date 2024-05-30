package org.jsp.reservationapi.service;

import java.util.Optional;

import org.jsp.reservationapi.dao.AdminDao;
import org.jsp.reservationapi.dao.BusDao;
import org.jsp.reservationapi.dto.BusRequest;
import org.jsp.reservationapi.dto.BusResponse;
import org.jsp.reservationapi.dto.ResponseStructure;
import org.jsp.reservationapi.exception.AdminNotFoundException;
import org.jsp.reservationapi.exception.BusNotFoundException;
import org.jsp.reservationapi.model.Admin;
import org.jsp.reservationapi.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(BusRequest busRequest, int admin_id) {
		Optional<Admin> recdb = adminDao.findById(admin_id);
		if (recdb.isPresent()) {
			Admin admin = recdb.get();
			busRequest.setAdmin(admin);
			admin.getBus().add(mapToBus(busRequest));
			ResponseStructure<BusResponse> structure = new ResponseStructure<>();
			structure.setData(mapToBusResponse(busDao.saveBus(mapToBus(busRequest))));
			adminDao.saveAdmin(admin);
			structure.setMessage("Bus Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new AdminNotFoundException("Cannot Add Bus As Admin Id Is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(BusRequest busRequest,int id){
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> recdb = busDao.findById(id);
		if(recdb.isPresent()) {
			Bus db = recdb.get();
			db.setName(busRequest.getName());
			db.setBus_number(busRequest.getBus_number());
			db.setFrom_location(busRequest.getFrom_location());
			db.setTo_location(busRequest.getTo_location());
			db.setNumber_of_seats(busRequest.getNumber_of_seats());
			db.setDate_of_departure(busRequest.getDate_of_departure());
			structure.setData(mapToBusResponse(busDao.saveBus(db)));
			structure.setMessage("Bus Updated");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Cannot Update Bus As Id Is Invalid");
	}
	

	public ResponseEntity<ResponseStructure<BusResponse>> findById(int id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> recdb = busDao.findById(id);
		if(recdb.isPresent()) {
			structure.setData(mapToBusResponse(recdb.get()));
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Cannot Find Bus As Id Is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Bus> recdb = busDao.findById(id);
		if(recdb.isPresent()) {
			busDao.delete(id);
			structure.setData("Bus Found");
			structure.setMessage("Bus Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Cannot Delete The Bus As Id Is Invalid");
	}

	private Bus mapToBus(BusRequest busRequest) {
		return Bus.builder().name(busRequest.getName()).bus_number(busRequest.getBus_number())
				.from_location(busRequest.getFrom_location()).to_location(busRequest.getTo_location())
				.number_of_seats(busRequest.getNumber_of_seats()).date_of_departure(busRequest.getDate_of_departure())
				.admin(busRequest.getAdmin()).build();
	}
	
	private BusResponse mapToBusResponse(Bus bus) {
	return BusResponse.builder().id(bus.getId()).name(bus.getName()).bus_number(bus.getBus_number())
			.from_location(bus.getFrom_location()).to_location(bus.getTo_location())
			.number_of_seats(bus.getNumber_of_seats()).date_of_departure(bus.getDate_of_departure()).build();
}


}
