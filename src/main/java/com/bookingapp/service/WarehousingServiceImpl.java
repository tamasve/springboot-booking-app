package com.bookingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapp.entity.WarehousingItem;
import com.bookingapp.repository.WarehousingRepository;


@Service
public class WarehousingServiceImpl implements WarehousingService {
	
	private WarehousingRepository warehousingRepository;
	
	@Autowired
	public void setWarehousingRepository(WarehousingRepository warehousingRepository) {
		this.warehousingRepository = warehousingRepository;
	}

	@Override
	public List<WarehousingItem> getWarehousingItems() {
		return warehousingRepository.findAll();
	}

	@Override
	public WarehousingItem saveWarehousingItem(WarehousingItem warehousingItem) {
		return warehousingRepository.save(warehousingItem);
	}

	@Override
	public List<WarehousingItem> selectItemsByWare(String ware) {
		return warehousingRepository.findByWareName(ware);
	}

	@Override
	public List<WarehousingItem> selectItemsByWarehouse(String warehouse) {
		return warehousingRepository.findByWarehouseName(warehouse);
	}

	@Override
	public List<WarehousingItem> selectItemsByWareAndWarehouse(String ware, String warehouse) {
		return warehousingRepository.findByWareNameAndWarehouseName(ware, warehouse);
	}

}
