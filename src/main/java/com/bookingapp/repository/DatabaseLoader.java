package com.bookingapp.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.bookingapp.entity.Partner;
import com.bookingapp.entity.Ware;
import com.bookingapp.entity.Warehouse;
import com.bookingapp.entity.WarehousingItem;


// *** It is a component only for loading initial data for the entities ***

@Component
public class DatabaseLoader implements CommandLineRunner {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PartnerRepository partnerRepository;
	private WarehouseRepository warehouseRepository;
	private WareRepository wareRepository;
	private WarehousingRepository warehousingRepository;
	
	private BufferedReader partnerReader;
	private BufferedReader warehouseReader;
	private BufferedReader wareReader;
	private BufferedReader warehousingReader;
	
	@Value("classpath:partners.txt")		// files under /resources! - https://elvisciotti.medium.com/spring-boot-load-text-file-lines-at-bean-creation-time-4a28b224a99d
	Resource partnersDataFile;

	@Value("classpath:warehouses.txt")
	Resource warehousesDataFile;
	
	@Value("classpath:wares.txt")
	Resource waresDataFile;
	
	@Value("classpath:warehousingitems.txt")
	Resource warehousingDataFile;
	
	
	@Autowired
	public DatabaseLoader(PartnerRepository pr, WarehouseRepository whr, WareRepository wr, WarehousingRepository whgr) {
		partnerRepository = pr;
		warehouseRepository = whr;
		wareRepository = wr;
		warehousingRepository = whgr;
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		partnerReader = new BufferedReader(new InputStreamReader( partnersDataFile.getInputStream() ));
		partnerReader.lines()
			.map(line -> line.split(" _ "))						// the common splitting char sequence
			.map(arr -> new Partner(arr[0], arr[1], arr[2]))
			.forEach(p -> {
				log.info("new Partner record >> DB: " + p.toString());
				partnerRepository.save(p);
				});
		
		warehouseReader = new BufferedReader(new InputStreamReader( warehousesDataFile.getInputStream() ));
		warehouseReader.lines()
			.map(line -> line.split(" _ "))
			.map(arr -> new Warehouse(arr[0], arr[1], arr[2]))
			.forEach(w -> {
				log.info("new Warehouse record >> DB: " + w.toString());
				warehouseRepository.save(w);
				});
		
		wareReader = new BufferedReader(new InputStreamReader( waresDataFile.getInputStream() ));
		wareReader.lines()
			.map(line -> line.split(" _ "))
			.map(arr -> new Ware(arr[0], arr[1]))
			.forEach(w -> {
				log.info("new Ware record >> DB: " + w.toString());
				wareRepository.save(w);
				});
		
		warehousingReader = new BufferedReader(new InputStreamReader( warehousingDataFile.getInputStream() ));
//		log.info("Warehousing items: " + warehousingReader.lines().count());
		warehousingReader.lines()
			.map(line -> line.split(" _ "))
			.map(arr -> {
				Ware ware = wareRepository.findById(Long.valueOf(arr[0])).orElse(null);
				Warehouse warehouse = warehouseRepository.findById(Long.valueOf(arr[1])).orElse(null);
				return new WarehousingItem(
						ware, 
						warehouse, 
						Integer.valueOf(arr[2]), 
						LocalDateTime.parse(arr[3], DateTimeFormatter.ofPattern("dd-MM-yyyy[ [HH][:mm]]")), 
						arr[4]);
				})
			.forEach(w -> {
				log.info("new Warehousing record >> DB: " + w.toString());
				warehousingRepository.save(w);
				});
	}

}
