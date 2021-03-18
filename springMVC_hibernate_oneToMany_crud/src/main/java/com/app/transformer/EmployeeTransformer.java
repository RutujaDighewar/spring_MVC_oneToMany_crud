package com.app.transformer;

import com.app.dto.EmployeeDto;
import com.app.entity.Employee;

public class EmployeeTransformer {

	public static Employee employeeBeanToEntity(EmployeeDto employeeDto) {

		Employee emp = new Employee();

		if (employeeDto.getId() != null)
			emp.setId(employeeDto.getId());

		emp.setName(employeeDto.getName());

		return emp;

	}

	public static EmployeeDto entityToemployeeBean(Employee employee) {

		EmployeeDto edto = new EmployeeDto();

		edto.setId(employee.getId());
		edto.setName(employee.getName());

		return edto;

	}
}
