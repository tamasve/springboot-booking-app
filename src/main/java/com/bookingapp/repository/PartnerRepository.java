package com.bookingapp.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.bookingapp.entity.Partner;

@Repository
public interface PartnerRepository extends ListCrudRepository<Partner, Long> {
	
//	public List<Partner> findAll();   // we do not need it, because this interface returns List<> types instead of Iterable<> types where the return type is a collection

}
