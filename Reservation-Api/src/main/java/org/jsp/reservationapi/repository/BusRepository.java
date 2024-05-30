package org.jsp.reservationapi.repository;

import java.util.Optional;

import org.jsp.reservationapi.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer>{
	
}
