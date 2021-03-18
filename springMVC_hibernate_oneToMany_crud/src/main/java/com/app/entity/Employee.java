package com.app.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL , mappedBy="employee", fetch=FetchType.LAZY)
	private List<Address> addresses;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", addresses=" + addresses + "]";
	}
	
	
	
}
