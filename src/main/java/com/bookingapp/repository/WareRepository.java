package com.bookingapp.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bookingapp.entity.Ware;

@Repository
public interface WareRepository extends ListCrudRepository<Ware, Long> {
	
	Ware findByName(String name);

}
