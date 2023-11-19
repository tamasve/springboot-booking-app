package com.bookingapp.service;

import java.util.List;

import com.bookingapp.entity.Partner;


public interface PartnerService {
	
	List<Partner> getPartners();
	
	Partner savePartner(Partner partner);

}
