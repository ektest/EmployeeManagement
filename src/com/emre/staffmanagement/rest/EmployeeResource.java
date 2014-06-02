package com.emre.staffmanagement.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.emre.staffmanagement.EmployeeManagementServiceLocal;
import com.emre.staffmanagement.SystemUnavaliableException;
import com.emre.staffmanagement.domain.Employee;

@Path("/employees")
@Stateless
public class EmployeeResource {
	@EJB
	private EmployeeManagementServiceLocal employeeManagement;

	@GET
	@Produces("application/xml")
	public List<Employee> getAllEmployees() {
		return employeeManagement.getAllEmployees();
	}

	@GET
	@Produces("application/xml")
	@Path("{id}")
	public Employee findEmployeeById(@PathParam("id") String id){
		return new Employee("A", "B", "Trainer", 100000);
		//return employeeManagement.registerEmployee(new Employee("Test", "Testing", "Tester", 10000));
	}

	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Employee registerEmployee(Employee newEmployee){
		try {
			employeeManagement.registerEmployee(newEmployee);
		} catch (SystemUnavaliableException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return newEmployee;
	}
}
