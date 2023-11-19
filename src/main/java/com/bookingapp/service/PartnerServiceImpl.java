package com.bookingapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapp.entity.Partner;
import com.bookingapp.repository.PartnerRepository;


@Service
public class PartnerServiceImpl implements PartnerService {
	
	private PartnerRepository partnerRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public void setPartnerRepository(PartnerRepository partnerRepository) {
		this.partnerRepository = partnerRepository;
	}

	@Override
	public List<Partner> getPartners() {
		return partnerRepository.findAll();
	}

	@Override
	public Partner savePartner(Partner partner) {
//		partner.setId( partnerRepository.count() + 1 );
		log.info(String.valueOf(partner.getId()));
		log.info(partner.getName());
		return partnerRepository.save(partner);
		
	}

}
