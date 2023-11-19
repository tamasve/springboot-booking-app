package com.bookingapp.service;

import java.util.List;

import com.bookingapp.entity.Warehouse;


public interface WarehouseService {
	
	List<Warehouse> getWarehouses();
	
	Warehouse saveWarehouse(Warehouse warehouse);
	
	Warehouse findByName(String name);
 
}
