package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
 
	@Column(name = "first_name")
	private String firstName;
 
	@Column(name = "last_name")
	private String lastName;
 
	private double salary;
 
	public String getFirstName() {
		return firstName;
	}
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
 
	public String getLastName() {
		return lastName;
	}
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
	public double getSalary() {
		return salary;
	}
 
	public void setSalary(double salary) {
		this.salary = salary;
	}
 
	public int getEmployeeId() {
		return employeeId;
	}
 
}
