package com.bookingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookingapp.entity.Partner;
import com.bookingapp.service.PartnerService;

@Controller
public class EntitiesController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PartnerService partnerService;
	
	@Autowired
	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}
	
	// /db: H2 db
	
	// Home page
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("user", "Thomas");		// user name into a message from the message.properties
		model.addAttribute("key", "home.welcome");	// the key for the message can come from a variable
		return "home";
	}
	
	// Show partners
	
	@GetMapping("/partners")
	public String showPartners(Model model) {
		model.addAttribute("partners", partnerService.getPartners());
		return "partners";
	}

	// Registrate new partner
	
	@GetMapping("/newpartner")
	public String newPartnerForm(Model model) {
		model.addAttribute("partner", new Partner("","",""));
		return "newpartner";
	}
	
	@PostMapping("/new")
	public String newPartnerSave(@ModelAttribute Partner partner) {
		System.out.println(partner);
		if (partner.getName() != "" && partner.getAddress() != "" && partner.getEmail() != "")
			partnerService.savePartner(partner);
		return "redirect:/partners";
	}
}
