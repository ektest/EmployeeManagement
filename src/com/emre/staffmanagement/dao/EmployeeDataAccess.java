package com.emre.staffmanagement.dao;

import java.util.List;

import javax.ejb.Local;

import com.emre.staffmanagement.domain.Employee;

@Local
public interface EmployeeDataAccess {
	
	public void insert (Employee newEmployee);
	public List<Employee> findAll();
	public List<Employee> findBySurname (String surname);
}
