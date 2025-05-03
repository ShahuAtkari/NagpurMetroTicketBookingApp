package com.NagpurMetro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NagpurMetro.Binding.PassengerInfo;

public interface RegisterPassengerRepository extends JpaRepository<PassengerInfo, Integer> {
	
	public PassengerInfo findByPassengerEmail(String email);

	
}
