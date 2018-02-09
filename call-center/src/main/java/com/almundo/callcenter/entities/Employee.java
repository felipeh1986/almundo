package com.almundo.callcenter.entities;

import com.almundo.callcenter.utils.EmployeeTypeEnum;

/**
 * The Class Employee is an entity that contains the employee information.
 */
public class Employee {

	/** The id attribute contains the employee identifier. */
	private Integer id;
	
	/** The employee full name. */
	private String name;
	
	/** The employee type. */
	private EmployeeTypeEnum type;
	
	/**
	 * Instantiates a new employee.
	 */
	public Employee() {
		
	}

	/**
	 * Gets the employee identifier.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the employee identifier.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public EmployeeTypeEnum getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(EmployeeTypeEnum type) {
		this.type = type;
	}
	
	
}
