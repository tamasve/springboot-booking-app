package com.bookingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapp.entity.Warehouse;
import com.bookingapp.repository.WarehouseRepository;


@Service
public class WarehouseServiceImpl implements WarehouseService {

	private WarehouseRepository warehouseRepository;
	
	@Autowired
	public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
		this.warehouseRepository = warehouseRepository;
	}

	@Override
	public List<Warehouse> getWarehouses() {
		return warehouseRepository.findAll();
	}

	@Override
	public Warehouse saveWarehouse(Warehouse warehouse) {
		return warehouseRepository.save(warehouse);
	}

	@Override
	public Warehouse findByName(String name) {
		return warehouseRepository.findByName(name);
	}
	


}
