package com.app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private Integer id;
	
	private String name;
	
	private List<AddressDto> addresses;
	
}
