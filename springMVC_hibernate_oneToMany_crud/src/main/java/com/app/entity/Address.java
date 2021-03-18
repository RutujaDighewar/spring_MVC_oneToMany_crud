package com.app.entity;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String city;
	private Integer pincode;
	
	@ManyToOne
	@JoinColumn(name="eid")
	private Employee employee;

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", pincode=" + pincode + "]";
	}
	
	
	

}
