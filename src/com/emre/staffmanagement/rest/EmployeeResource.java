package com.emre.staffmanagement.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.emre.staffmanagement.EmployeeManagementServiceLocal;
import com.emre.staffmanagement.SystemUnavaliableException;
import com.emre.staffmanagement.dao.EmployeeNotFoundException;
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
	public Employee getEmployeeById(@PathParam("id") int id){
		try {
			return employeeManagement.getEmployeeById(id);
		} catch (EmployeeNotFoundException e) {
			// return 404
			return null;
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteEmployeeById(@PathParam("id") int id){
		try {
			employeeManagement.deleteEmployeeById(id);
		} catch (EmployeeNotFoundException e) {
			// return 404
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().build();
	}

	@POST
	@Produces("application/xml")
	@Consumes("application/xml")
	public Response registerEmployee(Employee newEmployee){
		try {
			employeeManagement.registerEmployee(newEmployee);
		} catch (SystemUnavaliableException e) {
			// 503 Service Unavailable 
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.ok().entity(newEmployee).build();
	}
}
