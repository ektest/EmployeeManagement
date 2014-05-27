package com.emre.staffmanagement;

import javax.ejb.Stateless;

import com.emre.staffmanagement.domain.Employee;

// This is a local not remote EJB so no interface needed!
@Stateless
public class ExternalPayrollSystem {

	public void enrollEmployee (Employee newEmployee) throws SystemUnavaliableException{
		if ( Math.random() < 0.5 ){
			throw new SystemUnavaliableException();
		}else{
			// do something here!
		}
	}
}
