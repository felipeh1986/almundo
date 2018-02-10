package com.almundo.callcenter.jpa;

import java.util.List;

import com.almundo.callcenter.dto.Employee;

/**
 * The Interface EmployeeRepository defines the persistence methods to implements.
 */
public interface EmployeeRepository {

	/**
	 * Method responsible to persist an employee.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	public Employee save(Employee employee);
	
	/**
	 * Method responsible to gets all employees from database.
	 *
	 * @return the list
	 */
	public List<Employee> allEmployees();
}
