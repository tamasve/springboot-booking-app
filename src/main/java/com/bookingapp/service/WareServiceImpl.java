package com.bookingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapp.entity.Ware;
import com.bookingapp.repository.WareRepository;


@Service
public class WareServiceImpl implements WareService {
	
	private WareRepository wareRepository;
	
	@Autowired
	public void setWareRepository(WareRepository wareRepository) {
		this.wareRepository = wareRepository;
	}

	@Override
	public List<Ware> getWares() {
		return wareRepository.findAll();
	}

	@Override
	public Ware saveWare(Ware ware) {
		return wareRepository.save(ware);
	}

	@Override
	public Ware findByName(String name) {
		return wareRepository.findByName(name);
	}

}
