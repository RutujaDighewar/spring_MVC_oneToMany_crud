package com.app.repo.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.entity.Employee;
import com.app.repo.EmployeeRepo;

@Repository
public class EmployeereRepoImpl implements EmployeeRepo {

	private boolean flag = Boolean.FALSE;

	@Autowired
	SessionFactory sessionFactory;

	public Boolean saveOrUpdate(Employee employee) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(employee);
		session.beginTransaction().commit();
		flag = Boolean.TRUE;
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		return sessionFactory.openSession().createCriteria(Employee.class).list();
	}

	public Boolean deleteEmployee(Integer id) {
		Session session = sessionFactory.openSession();
		session.delete(session.get(Employee.class, id));
		session.beginTransaction().commit();
		flag = Boolean.TRUE;
		return flag;
	}

	public Employee getId(Integer id) {
		return (Employee) sessionFactory.openSession().get(Employee.class, id);
	}

}
