package com.bookingapp.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bookingapp.entity.Warehouse;

@Repository
public interface WarehouseRepository extends ListCrudRepository<Warehouse, Long> {
	
	Warehouse findByName(String name);
}
