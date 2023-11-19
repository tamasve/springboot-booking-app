package com.bookingapp.service;

import java.util.List;

import com.bookingapp.entity.WarehousingItem;


public interface WarehousingService {
	
	List<WarehousingItem> getWarehousingItems();
	
	WarehousingItem saveWarehousingItem(WarehousingItem warehousingItem);
	
	List<WarehousingItem> selectItemsByWare(String ware);

	List<WarehousingItem> selectItemsByWarehouse(String warehouse);

	List<WarehousingItem> selectItemsByWareAndWarehouse(String ware, String warehouse);

}
