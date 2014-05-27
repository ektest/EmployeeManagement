package com.emre.backingbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;

import com.emre.staffmanagement.EmployeeManagementServiceLocal;
import com.emre.staffmanagement.domain.Employee;

@ManagedBean(name="allEmployeesPageBean")
public class AllEmployeesPageBean {

	@EJB
	private EmployeeManagementServiceLocal employeeService;
	// See the all-employees.xhtml and binding="#{allEmployeesPageBean.dataTable}"> 
	// that links it to here!
	private UIData dataTable;
	private Employee selectedEmployee;

	public  List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	public String showEmployee(){
		// This will get selected row information
		this.selectedEmployee = (Employee) dataTable.getRowData();
		return "employeeDetail";
	}
	
	public UIData getDataTable(){
		return this.dataTable;
	}
	
	public void setDataTable(UIData dataTable){
		this.dataTable = dataTable;
	}
	
	public Employee getSelectedEmployee(){
		return this.selectedEmployee;
	}
	
	public void setSelectedEmployee(Employee selectedEmployee){
		this.selectedEmployee = selectedEmployee;
	}
}
