package com.emre.staffmanagement;

import java.util.List;

import javax.ejb.Remote;

import com.emre.staffmanagement.domain.Employee;

@Remote
public interface EmployeeManagementServiceRemote {
	
	public void registerEmployee(Employee newEmployee) 
			throws SystemUnavaliableException;
	public List<Employee> getAllEmployees();
	public List<Employee> searchBySurname (String surname);
	void rollbackCheckedException(Employee newEmployee)
			throws SystemUnavaliableException;
}
