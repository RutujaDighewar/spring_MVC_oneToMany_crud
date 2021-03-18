package com.app.repo;

import java.util.List;

import com.app.entity.Employee;

public interface EmployeeRepo {
	
	public Boolean saveOrUpdate(Employee employee);
	
	public List<Employee> getEmployees();
	
	public Boolean deleteEmployee(Integer id);
	
	public Employee getId(Integer id);
	

}
