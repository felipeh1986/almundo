package com.almundo.callcenter.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.almundo.callcenter.dto.Employee;
import com.almundo.callcenter.jpa.EmployeeEntity;

/**
 * The Class DataProviderTest is provides test data.
 */
public final class DataProviderTest {

	/** The Constant MIN_VALUE. */
	private static final int MIN_VALUE = 300000000;
	
	/** The Constant MAX_VALUE. */
	private static final int MAX_VALUE = 325000000;
	
	/**
	 * Builds the employees.
	 *
	 * @return the list
	 */
	public static List<Employee> buildEmployees(){
		final List<Employee> employees = new ArrayList<>();
		employees.add(buildEmployee(1, "Juan Perez Dir", EmployeeTypeEnum.DIRECTOR.name(), null));
		employees.add(buildEmployee(2, "Juan Perez Sup", EmployeeTypeEnum.SUPERVISOR.name(), 1));
		employees.add(buildEmployee(3, "Juan Perez 1", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(4, "Juan Perez 2", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(5, "Juan Perez 3", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(6, "Juan Perez 4", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(7, "Juan Perez 5", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(8, "Juan Perez 6", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(9, "Juan Perez 7", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployee(10, "Juan Perez 8", EmployeeTypeEnum.OPERADOR.name(), 2));
		return employees;
	}
	
	/**
	 * Builds the employees entities.
	 *
	 * @return the list
	 */
	public static List<EmployeeEntity> buildEmployeesEntities(){
		final List<EmployeeEntity> employees = new ArrayList<>();
		employees.add(buildEmployeeEntity(1, "Juan Perez Dir", EmployeeTypeEnum.DIRECTOR.name(), null));
		employees.add(buildEmployeeEntity(2, "Juan Perez Sup", EmployeeTypeEnum.SUPERVISOR.name(), 1));
		employees.add(buildEmployeeEntity(3, "Juan Perez 1", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(4, "Juan Perez 2", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(5, "Juan Perez 3", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(6, "Juan Perez 4", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(7, "Juan Perez 5", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(8, "Juan Perez 6", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(9, "Juan Perez 7", EmployeeTypeEnum.OPERADOR.name(), 2));
		employees.add(buildEmployeeEntity(10, "Juan Perez 8", EmployeeTypeEnum.OPERADOR.name(), 2));
		return employees;
	}
	
	/**
	 * Generate random number.
	 *
	 * @return the integer
	 */
	public static Integer generateRandomNumber() {
		final Random random = new Random();
		final Integer baseTime = MAX_VALUE - MIN_VALUE;
		return random.nextInt(baseTime) + MIN_VALUE;
	}
	
	/**
	 * Build employee.
	 *
	 * @param id the id
	 * @param name the name
	 * @param type the type
	 * @param chief the chief
	 * @return the employee
	 */
	private static Employee buildEmployee(int id, String name, String type, Integer chief) {
		final Employee employee = new Employee();
		employee.setId(id);
		employee.setName(name);
		employee.setType(type);
		employee.setChief_id(chief);
		
		return employee;
	}
	
	/**
	 * Builds the employee entity.
	 *
	 * @param id the id
	 * @param name the name
	 * @param type the type
	 * @param chief the chief
	 * @return the employee entity
	 */
	private static EmployeeEntity buildEmployeeEntity(int id, String name, String type, Integer chief) {
		final EmployeeEntity employee = new EmployeeEntity();
		employee.setId(id);
		employee.setName(name);
		employee.setType(type);
		employee.setChiefId(chief);
		
		return employee;
	}
}
