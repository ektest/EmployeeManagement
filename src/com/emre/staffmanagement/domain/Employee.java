package com.emre.staffmanagement.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName;
	private String surname;
	private String jobRole;
	private int salary;

	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="fk_employee_id")
	private Set<Note> notes;
	
	public Employee(){
		//required by JPA, but not used by us!
	}
	
	public Employee(String firstName, String surname, String jobRole, int salary) {
		super();
		this.notes = new HashSet<Note>();
		this.firstName = firstName;
		this.surname = surname;
		this.jobRole = jobRole;
		this.salary = salary;
	}
	
	public void setSurname(String surname){
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", surname=" + surname
				+ ", jobRole=" + jobRole + ", salary=" + salary + "]";
	}
	
	public void addNote(Note note){
		this.notes.add(note);
	}
	
	public void addNote(String note){
		this.notes.add(new Note(note));
	}
	
	public Set<Note> getAllNotes(){
		return this.notes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getSurname() {
		return surname;
	}
}
