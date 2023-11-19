package com.bookingapp.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookingapp.entity.Ware;
import com.bookingapp.entity.Warehouse;
import com.bookingapp.entity.WarehousingItem;
import com.bookingapp.service.WareService;
import com.bookingapp.service.WarehouseService;
import com.bookingapp.service.WarehousingService;


@Controller
public class WarehousingController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final String ALL = "... all ...";		// marker for selecting all items
	private Ware selectedWare = new Ware(ALL, "");
	private Warehouse selectedWarehouse = new Warehouse(ALL, "", "");
	
	private WareService wareService;
	private WarehouseService warehouseService;
	private WarehousingService warehousingService;
	
	
	@Autowired
	public void setWareService(WareService wareService) {
		this.wareService = wareService;
	}
	
	@Autowired
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}
	
	@Autowired
	public void setWarehousingService(WarehousingService warehousingService) {
		this.warehousingService = warehousingService;
	}
	
	
	// **************************************************
	
	// CREATING WAREHOUSE ITEMS on warehousing.html
	
	@GetMapping("/warehousing")
	public String warehousing(Model model) {
		model.addAttribute("wares", wareService.getWares());
		model.addAttribute("warehouses", warehouseService.getWarehouses());
		model.addAttribute("selectedWare", selectedWare);
		model.addAttribute("selectedWarehouse", selectedWarehouse);
		model.addAttribute("warehousingItem", new WarehousingItem());
		return "warehousing";
	}
	
	// THE BOOKING FORM GETS EVALUATED HERE... + store warehousing record in DB
	
	@PostMapping("/warehouse-record")
	public String warehouseRecord(@ModelAttribute("warehousingItem") WarehousingItem warehousingItem) {
		
		if (warehousingItem.getRegisteredAt() == null || warehousingItem.getQuantity() == 0)  return "redirect:/warehousing";
		
		log.info("datetime = " + warehousingItem.getRegisteredAt().toString());
		log.info("quantity = " + String.valueOf(warehousingItem.getQuantity()));
		log.info("ware = " + warehousingItem.getDescription());
		
		warehousingItem.setWare(selectedWare);				// connect the selected ware and warehouse to the warehousing object, then save it into DB
		warehousingItem.setWarehouse(selectedWarehouse);
		warehousingService.saveWarehousingItem(warehousingItem);
		
		return "redirect:/warehousing";
	}
	
	// WARE and WAREHOUSE chooser requests for updating page...
	
	@GetMapping("/ware/{name}")
	public String changeWare(@PathVariable("name") String ware) {
		
		log.info("ware changed to :  " + ware);
		
		selectedWare = wareService.findByName(ware);		// change ware object to the one queried from DB, acc. to the selection on form
		return "redirect:/warehousing";
	}
	
	@GetMapping("/warehouse/{name}")
	public String changeWarehouse(@PathVariable("name") String warehouse) {
		
		log.info("warehouse changed to :  " + warehouse);
		
		selectedWarehouse = warehouseService.findByName(warehouse);
		return "redirect:/warehousing";
	}
	
	
	// **************************************************************
	
	// RIPORTING WAREHOUSE ITEMS on warehouse-items.html
	
	@GetMapping("/warehouse-items")
	public String warehouseItems(Model model) {
		
		List<Ware> wares = wareService.getWares();
		wares.add( new Ware(Long.valueOf(0), ALL, "") );
		model.addAttribute("wares", wares);
		
		List<Warehouse> warehouses = warehouseService.getWarehouses();
		warehouses.add( new Warehouse(Long.valueOf(0), ALL, "", "") );
		model.addAttribute("warehouses", warehouses);
		
		model.addAttribute("selectedWare", selectedWare);
		model.addAttribute("selectedWarehouse", selectedWarehouse);
		
		model.addAttribute("message", "You can choose a warehouse to see which wares were stored into it...");		// parameter handover to a JS function: th:data-...
		
		model.addAttribute("somedata", "Data handed over...");		// parameter handover to a JS function 2: inline JS...
		
		List<WarehousingItem> warehousingItems = getSelectedList();		// invoke list of items selector below
		model.addAttribute("warehousingItems", warehousingItems);
		
		return "warehouse-items";
	}

	// WARE and WAREHOUSE chooser requests for updating page... - the only difference from the above ones is at the redirection after return... + void selection handling
	
	@GetMapping("/warehouse-items/ware/{name}")
	public String selectWare(@PathVariable("name") String ware) {
		
		log.info("ware changed to :  " + ware);
		
		if (ware.equals(ALL)) selectedWare = new Ware(ALL, "");	// clear selection
		else selectedWare = wareService.findByName(ware);		// change ware object to the one queried from DB, acc. to the selection on form
		
		return "redirect:/warehouse-items";
	}
	
	@GetMapping("/warehouse-items/warehouse/{name}")
	public String selectWarehouse(@PathVariable("name") String warehouse) {
		
		log.info("warehouse changed to :  " + warehouse);
		
		if (warehouse.equals(ALL)) selectedWarehouse = new Warehouse(ALL, "", "");	// clear selection
		else selectedWarehouse = warehouseService.findByName(warehouse);
		return "redirect:/warehouse-items";
	}
	
	
	// select warehousing items depending on whether there is a ware and/or a warehouse selected for filtering the items
	
	public List<WarehousingItem> getSelectedList() {
		
		String ware = selectedWare.getName();
		String warehouse = selectedWarehouse.getName();
		List<WarehousingItem> warehousingItems = new ArrayList<>();
		
		if (!ware.equals(ALL) && !warehouse.equals(ALL))
			warehousingItems = warehousingService.selectItemsByWareAndWarehouse(ware, warehouse);
		else
			if (!ware.equals(ALL))
				warehousingItems = warehousingService.selectItemsByWare(ware);
			else
				if (!warehouse.equals(ALL))
					warehousingItems = warehousingService.selectItemsByWarehouse(warehouse);
				else warehousingItems = warehousingService.getWarehousingItems();
		
		return warehousingItems;
	}
}
