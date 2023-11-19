package com.bookingapp.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bookingapp.entity.WarehousingItem;

@Repository
public interface WarehousingRepository extends ListCrudRepository<WarehousingItem, Long> {
	
	List<WarehousingItem> findByWareName(String name);

	List<WarehousingItem> findByWarehouseName(String name);

	List<WarehousingItem> findByWareNameAndWarehouseName(String ware, String warehouse);
}
