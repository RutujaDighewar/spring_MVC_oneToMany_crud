package com.app.transformer;

import com.app.dto.AddressDto;
import com.app.entity.Address;

public class AddressTransformer {

	public static Address addressBeanToEntity(AddressDto addressDto) {

		Address add = new Address();

		if (addressDto.getId() != null)
			add.setId(addressDto.getId());

		add.setCity(addressDto.getCity());
		add.setPincode(addressDto.getPincode());

		return add;

	}

	public static AddressDto entityToaddressBean(Address address) {

		AddressDto dto = new AddressDto();

		dto.setId(address.getId());
		dto.setCity(address.getCity());
		dto.setPincode(address.getPincode());

		return dto;

	}

}
