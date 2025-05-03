package com.NagpurMetro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.NagpurMetro.Binding.MetroTicketBook;
import com.NagpurMetro.Binding.MetroTicketInfo;

public interface BookTicketRepository extends JpaRepository<MetroTicketInfo, Integer > {

	
}

