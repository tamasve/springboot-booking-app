package com.bookingapp.service;

import java.util.List;

import com.bookingapp.entity.Ware;


public interface WareService {
	
	List<Ware> getWares();
	
	Ware saveWare(Ware ware);
	
	Ware findByName(String name);

}
