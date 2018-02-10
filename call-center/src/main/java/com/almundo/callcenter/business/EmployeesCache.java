package com.almundo.callcenter.business;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.utils.EmployeeTypeEnum;

/**
 * The Class EmployeesCache is a singleton that contains the employees information in cache memory.
 */
public class EmployeesCache {

	/** The employees. */
	private Map<String, List<Employee>> employees = new Hashtable<>();
	
	/** The employees cache. */
	private static EmployeesCache employeesCache;
	
	/**
	 * Instantiates a new employees cache.
	 *
	 * @param employees the employees
	 */
	private EmployeesCache(List<Employee> employees) {
		this.employees = employees.stream().collect(Collectors.groupingBy(Employee :: getType));
	}
	
	/**
	 * Gets the single instance of EmployeesCache.
	 *
	 * @param employees the employees
	 * @return single instance of EmployeesCache
	 */
	public static EmployeesCache getInstance(List<Employee> employees) {
		if(employeesCache == null) {
			employeesCache = new EmployeesCache(employees);
		}
		
		return employeesCache;
	}
	
	/**
	 * Gets the employee available.
	 *
	 * @return the employee available
	 */
	public synchronized Employee getEmployeeAvailable() {
		
		if(!employees.get(EmployeeTypeEnum.OPERADOR.name()).isEmpty()) {
			return employees.get(EmployeeTypeEnum.OPERADOR.name()).remove(0);
		}
		
		if(!employees.get(EmployeeTypeEnum.SUPERVISOR.name()).isEmpty()) {
			return employees.get(EmployeeTypeEnum.SUPERVISOR.name()).remove(0);		
		}
		
		if(!employees.get(EmployeeTypeEnum.DIRECTOR.name()).isEmpty()) {
			return employees.get(EmployeeTypeEnum.DIRECTOR.name()).remove(0);
		}
		
		return null;
	}
	
	/**
	 * Free employee.
	 *
	 * @param employee the employee
	 */
	public synchronized void freeEmployee(final Employee employee) {
		employees.get(employee.getType()).add(employee);
	}
}
