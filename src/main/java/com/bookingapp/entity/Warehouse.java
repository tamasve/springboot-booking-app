package com.bookingapp.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "warehouses")
public class Warehouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@Column(name = "address", nullable = false, unique = true)
	private String address;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "warehouse")
	private Set<WarehousingItem> warehousingItems;
	
	
	public Warehouse() {
	}

	public Warehouse(Long id, String name, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
	}
	

	public Warehouse(String name, String address, String email) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	
	public Set<WarehousingItem> getWarehousingItems() {
		return warehousingItems;
	}

	public void setWareousingItems(Set<WarehousingItem> warehousingItems) {
		this.warehousingItems = warehousingItems;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return name + " _ " + address + " _ " + email;
	}
	
}
