package com.emre.client;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import com.emre.staffmanagement.EmployeeManagementServiceRemote;
import com.emre.staffmanagement.SystemUnavaliableException;
import com.emre.staffmanagement.domain.Employee;

public class ClientApplicationTest {

	public static void main(String[] args) {
		try {
			Context jndi = new InitialContext();
			// Below one for only Glassfish
			// EmployeeManagementService service = (EmployeeManagementService)
			// jndi.lookup("com.emre.staffmanagement.EmployeeManagementService");
			// Below will work on JAVAEE 6 or higher only...
			
			// Use below if you have only one interface (Remote or Local)
			//EmployeeManagementService service = (EmployeeManagementService) jndi
				//	.lookup("java:global/EmployeeManagement/EmployeeManagementImplementation");
			
			// Use below one if you have 2 interface (Remote and Local)
			// This is running the remote one :)
			EmployeeManagementServiceRemote service = (EmployeeManagementServiceRemote)
					jndi.lookup("java:global/EmployeeManagement/EmployeeManagementImplementation!com.emre.staffmanagement.EmployeeManagementServiceRemote");
			List<Employee> employees = service.getAllEmployees();

			for (Employee employee : employees) {
				System.out.println(employee);
			}

			employees = service.searchBySurname("B");

			for (Employee employee : employees) {
				System.out.println(employee);
			}
			
			// Chapter 11 : Rollback!
			try {
				service.rollbackCheckedException(new Employee("AA","BB","CC",10000));
			} catch (SystemUnavaliableException e) {
				System.out.println("Exception");
			}

		} catch (NamingException e) {
			System.out.println(e);
		}

	}

}
