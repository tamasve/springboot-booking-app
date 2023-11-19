package com.bookingapp.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehousingitems")
public class WarehousingItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ware_id")
	private Ware ware;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "registration", nullable = false)
	private LocalDateTime registeredAt;
	
	@Column(name = "description", columnDefinition = "TEXT")		// huge text field instead of varchar
	private String description;

	
	public WarehousingItem() {}
	
	public WarehousingItem(Long id, Ware ware, Warehouse warehouse, int quantity, LocalDateTime registeredAt,
			String description) {
		super();
		this.id = id;
		this.ware = ware;
		this.warehouse = warehouse;
		this.quantity = quantity;
		this.registeredAt = registeredAt;
		this.description = description;
	}


	public WarehousingItem(Ware ware, Warehouse warehouse, int quantity, LocalDateTime registeredAt,
			String description) {
		super();
		this.ware = ware;
		this.warehouse = warehouse;
		this.quantity = quantity;
		this.registeredAt = registeredAt;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ware getWare() {
		return ware;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder desc = new StringBuilder();
		desc.append(quantity + " ");
		desc.append(ware.getUnit() + " ");
		desc.append(ware.getName());
		desc.append(" stored on " + registeredAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy[ [HH][:mm]]")));
		desc.append(" into " + warehouse.toString());
		desc.append(" -- info: " + description);
		return desc.toString();
	}

}
