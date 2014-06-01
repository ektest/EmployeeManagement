package com.emre.staffmanagement.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.emre.staffmanagement.domain.Employee;

@Path("/employees")
public class EmployeeResource {
	@GET
	@Produces("application/xml")
	public List<Employee> getAllEmployees()
	{
		List<Employee> emp = new ArrayList<Employee>();
		emp.add(new Employee("A", "B", "Trainer", 100000));
		return emp;
	}

}
