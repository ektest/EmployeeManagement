package com.emre.staffmanagement;

import java.util.List;

import javax.ejb.Local;
import com.emre.staffmanagement.domain.Employee;

@Local
public interface EmployeeManagementServiceLocal {

	public void registerEmployee(Employee newEmployee)
			throws SystemUnavaliableException;

	public List<Employee> getAllEmployees();

	public List<Employee> searchBySurname(String surname);

	void rollbackCheckedException(Employee newEmployee)
			throws SystemUnavaliableException;
}