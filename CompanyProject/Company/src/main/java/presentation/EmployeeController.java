package presentation;

 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import model.Employee;
import service.EmployeeService;
 

 
@ManagedBean
public class EmployeeController {
 
	private Employee employee = new Employee();
 
	@EJB
	private EmployeeService service;
	
	
	void saveEmployee() {
		
	}
 
	public Employee getEmployee() {
		return employee;
	}
 
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
 
	public void saveEmployee(Employee emp) {
		service.addEmployee(emp);
	}
 
}