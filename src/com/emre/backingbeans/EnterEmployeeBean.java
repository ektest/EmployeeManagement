package com.emre.backingbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.emre.staffmanagement.EmployeeManagementServiceLocal;
import com.emre.staffmanagement.SystemUnavaliableException;
import com.emre.staffmanagement.domain.Employee;

@ManagedBean(name="enterEmployee")
public class EnterEmployeeBean {

	private String firstName;
	private String surname;
	private String jobRole;
	private int Salary;

	@EJB
	private EmployeeManagementServiceLocal employeeService;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}

	public String createEmployee(){
		Employee newEmployee = new Employee(firstName, surname, jobRole, Salary);
		try {
			employeeService.registerEmployee(newEmployee);
			return "all-employees";
		} catch (SystemUnavaliableException e) {
			e.printStackTrace();
			return "systemDown";
		}
	}
}
