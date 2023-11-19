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
@Table(name = "wares")
public class Ware {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "unit", nullable = false)
	private String unit;
	
	@OneToMany(mappedBy = "ware")
	private Set<WarehousingItem> warehousingItems;
	
	

	public Ware() {
	}

	public Ware(Long id, String name, String unit) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;

	}
	

	public Ware(String name, String unit) {
		super();
		this.name = name;
		this.unit = unit;

	}
	
	
	public Set<WarehousingItem> getWarehousingItems() {
		return warehousingItems;
	}

	public void setWarehousingItems(Set<WarehousingItem> warehousingItems) {
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


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}



	@Override
	public String toString() {
		return unit == "" ? name : name + " _ measured in " + unit + " - id = " + id;
	}
	
}
