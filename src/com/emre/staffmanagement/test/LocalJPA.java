package com.emre.staffmanagement.test;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.emre.staffmanagement.domain.Employee;
import com.emre.staffmanagement.domain.Note;

public class LocalJPA {

	@SuppressWarnings("unchecked")
	// Ignore warning!
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("employeeDbLocal");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		/*
		 * // Insert Employee emp1 = new Employee("e","k","e",1);
		 * em.persist(emp1);
		 * 
		 * // Select // 1 is ID in the Employee table (PK) Employee employee1 =
		 * em.find(Employee.class, 1); System.out.println(employee1);
		 * 
		 * // Update // em is open ;) employee1.setSurname("Koca");
		 * employee1.setSurname("Emre Koca");
		 * 
		 * //Delete em.remove(employee1);
		 */

		// #################################################################
		// Below ones: Please follow step by step...
		// You have to cleanup all the table before running!

		/*
		 * Employee emp; Note note;
		 * 
		 * //Step 1: emp = new Employee("A", "B", "C", 100000); em.persist(emp);
		 * note = new Note ("Testing!"); em.persist(note); emp.addNote(note);
		 * 
		 * 
		 * 
		 * //Step 2: // Id will change so look up for it before running below!
		 * note = new Note ("Testing! All Again!"); em.persist(note);
		 * emp.addNote(note);
		 * 
		 * 
		 * 
		 * //Step3 emp = em.find(Employee.class, 54); Set<Note> notes =
		 * emp.getAllNotes();
		 * 
		 * for(Note eachNote : notes){ System.out.println("Here is the ouput: "
		 * + eachNote); }
		 * 
		 * //#################################################################
		 * 
		 * emp = new Employee("A", "B", "C", 100000); em.persist(emp);
		 * 
		 * // Since I have cascading feature, I don't have to persite Note
		 * objects! emp.addNote("Test1"); emp.addNote("Test2");
		 */

		// Sample Query
		Query q = em.createQuery("SELECT employee FROM Employee employee");
		List<Employee> results = q.getResultList();

		for (Employee result : results) {
			System.out.println("Here is the employees: " + result);

			Set<Note> notes = result.getAllNotes();

			for (Note eachNote : notes) {
				System.out.println("Here is the notes: " + eachNote);
			}
		}

		// Where condition
		q = em.createQuery("SELECT employee FROM Employee employee "
				+ "WHERE employee.surname = 'BB'");
		results = q.getResultList();

		for (Employee result : results) {
			System.out.println("Where condition: " + result);
		}

		// With like (Wildcard)
		q = em.createQuery("SELECT employee FROM Employee employee "
				+ "WHERE employee.surname like 'BB%'");
		results = q.getResultList();

		for (Employee result : results) {
			System.out.println("Wildcard: " + result);
		}
		
		// Dynamic Parameters
		String requiredName = "BB";
		q = em.createQuery("SELECT employee FROM Employee employee "
				+ "WHERE employee.surname = :name");
		q.setParameter("name", requiredName);
		results = q.getResultList();

		for (Employee result : results) {
			System.out.println("Dynamic Parameters: " + result);
		}
		
		// Named Query
		results = em.createNamedQuery("searchByFirstName")
					.setParameter("name", "AA")
					.getResultList();
		for (Employee result : results) {
			System.out.println("Named Query: " + result);
		}
		
		// Report Query
		List<Object[]> resultsArray = em.createQuery("SELECT employee.firstName, employee surname FROM Employee employee")
					.getResultList();

		for (Object[] next : resultsArray) {
			System.out.println("Report Query - FirsName: " + next[0]);
			System.out.println("Report Query - Surname: " + next[1]);
		}
		
		// Aggregations
		Long numberOfEmployees = (Long) em.createQuery("SELECT count(employee) FROM Employee employee")
						.getSingleResult();
		System.out.println("Aggregations - Number Of Employees: " + numberOfEmployees);
		
		double averageSalary = (Double) em.createQuery("SELECT avg(employee.salary) FROM Employee employee")
				.getSingleResult();
		System.out.println("Aggregations - The Average Salary Of Employees: " + averageSalary);

		// Update Query will be ran in the below sentence (on commit)
		tx.commit();
		em.close();
	}

}
