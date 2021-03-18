package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dto.AddressDto;
import com.app.dto.EmployeeDto;
import com.app.entity.Address;
import com.app.entity.Employee;
import com.app.repo.EmployeeRepo;
import com.app.transformer.AddressTransformer;
import com.app.transformer.EmployeeTransformer;
import com.app.validator.EmployeeValidator;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeRepo;

	@GetMapping(value = { "/", "edit" })
	public String viewHome(Model model, @RequestParam(value = "id", required = false) Integer id) {
		
		List<AddressDto> addressesDto = new ArrayList<AddressDto>();
		EmployeeDto employeeDto=null;
		
		if (id != null) {
			Employee employee = employeeRepo.getId(id);
			employeeDto = EmployeeTransformer.entityToemployeeBean(employee);
			for (Address address : employee.getAddresses()) {
				AddressDto addressDto = AddressTransformer.entityToaddressBean(address);
				addressesDto.add(addressDto);
			}
			employeeDto.setAddresses(addressesDto);
		} else {
			employeeDto = new EmployeeDto();
			AddressDto local = new AddressDto();
			AddressDto permanent = new AddressDto();
			addressesDto.add(local);
			addressesDto.add(permanent);
			employeeDto.setAddresses(addressesDto);

		}
		model.addAttribute("employeeForm", employeeDto);
		return "welcome";
	}

	@PostMapping("save")
	public String save(Model model, @ModelAttribute("employeeForm") @Validated EmployeeDto employeeDto,
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("employeeForm", employeeDto);
			return "welcome";
		} else {

			List<Address> addresses = new ArrayList<Address>();

			Employee employee = EmployeeTransformer.employeeBeanToEntity(employeeDto);

			for (AddressDto addressDto : employeeDto.getAddresses()) {
				Address address = AddressTransformer.addressBeanToEntity(addressDto);
				address.setEmployee(employee);
				addresses.add(address);
			}
			employee.setAddresses(addresses);

			Boolean flag = employeeRepo.saveOrUpdate(employee);
			if (flag) {
				redirectAttribute.addFlashAttribute("success", "Employee saved successfully");
			} else {
				redirectAttribute.addFlashAttribute("error", "Employee not saved...Try again");
			}
			return "redirect:/";
		}
	}

	@InitBinder("employeeForm")
	public void formBinding(WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new EmployeeValidator());
	}

	@ModelAttribute("employees")
	public List<Employee> listofEmp() {
		return employeeRepo.getEmployees();
	}

	@GetMapping(value = "delete")
	public String deleteEmployee(@RequestParam("id") Integer id, RedirectAttributes redirectAttribute) {

		Boolean flag = employeeRepo.deleteEmployee(id);
		if (flag) {
			redirectAttribute.addFlashAttribute("success", "Employee deleted successfully");
		} else {
			redirectAttribute.addFlashAttribute("error", "Employee not deleted...Try again");
		}
		return "redirect:/";

	}

}