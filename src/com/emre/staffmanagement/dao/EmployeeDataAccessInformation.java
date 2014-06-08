package com.emre.staffmanagement.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.emre.staffmanagement.domain.Employee;

@Stateless
public class EmployeeDataAccessInformation implements EmployeeDataAccess {

	@PersistenceContext(unitName="employeeDb")
	private EntityManager em;
	
	@Override
	public void insert(Employee newEmployee) {
		em.persist(newEmployee);
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> results = em.createNamedQuery("getAllEmployeeRecords")
				.getResultList();
		return results;
	}

	@Override
	public List<Employee> findBySurname(String surname) {
		List<Employee> results = em.createNamedQuery("searchBySurname")
				.setParameter("surname", surname)
				.getResultList();
		return results;
	}

	@Override
	public Employee findById(int id) throws EmployeeNotFoundException{
		/*
		Employee result = (Employee) em.createNamedQuery("searchById")
				.setParameter("id", id)
				.getSingleResult();
		*/
		// or
		Employee result = em.find(Employee.class, id);
		
		if(result == null){
			throw new EmployeeNotFoundException();
		}
		return result;
	}

	@Override
	public void deleteById(int id) throws EmployeeNotFoundException {
		
		Employee result = findById(id);
		//or
						//em.find(Employee.class, id);
		em.remove(result);
	}
}
