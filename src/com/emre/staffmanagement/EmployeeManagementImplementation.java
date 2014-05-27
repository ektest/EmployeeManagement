package com.emre.staffmanagement;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.emre.staffmanagement.dao.EmployeeDataAccess;
import com.emre.staffmanagement.domain.Employee;

@Stateless

// Transaction is not needed for this method (no db call) 
// By default for all methods
@TransactionAttribute(TransactionAttributeType.REQUIRED)
// This has 2 EJB implemantation (Remote and Local ones)
public class EmployeeManagementImplementation 
	implements EmployeeManagementServiceRemote, EmployeeManagementServiceLocal {
	// Dependency Injection
	@EJB
	private EmployeeDataAccess dao;
	@EJB
	private ExternalPayrollSystem payrollSystem;
	
	@Resource
	private SessionContext ctx;
	
	@Override
	public void registerEmployee(Employee newEmployee) throws SystemUnavaliableException {
		dao.insert(newEmployee);
		payrollSystem.enrollEmployee(newEmployee);
	}

	@Override
	public void rollbackCheckedException(Employee newEmployee) throws SystemUnavaliableException {
		dao.insert(newEmployee);
		payrollSystem.enrollEmployee(newEmployee);
		// Second way to handle rollback for checked Exception
		// Handle roolback here by adding try and catch!
		// This will rollback only in this case not for all case!
		/*
		 * try{
		 * 		payrollSystem.enrollEmployee(newEmployee);
		 * }catch (SystemUnavaliableException ex){
		 * 		ctx.setRollbackOnly();
		 * 		throw ex; // send it to client...
		 * }
		 * 
		 */
	}
	@Override
	public List<Employee> getAllEmployees() {
		return dao.findAll();
	}

	@Override
	public List<Employee> searchBySurname(String surname) {
		return dao.findAll();
	}
	
	// Overwriting the TransactionAttributeType for below method only
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public double dummy()
	{
		return 1.1;
	}

}
