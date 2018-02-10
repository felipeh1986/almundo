package com.almundo.callcenter.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The Class Employee is an entity that contains the employee information.
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity {

	/** The id attribute contains the employee identifier. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** The employee full name. */
	private String name;
	
	/** The employee type. */
	private String type;
	
	/** The available. */
	@Transient
	private boolean available;
	
	/** The chief id. */
	private Integer chief_id;
	
	/**
	 * Instantiates a new employee.
	 */
	public EmployeeEntity() {
		
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
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * Gets the chief id.
	 *
	 * @return the chief id
	 */
	public Integer getChiefId() {
		return chief_id;
	}

	/**
	 * Sets the chief id.
	 *
	 * @param chief_id the new chief id
	 */
	public void setChiefId(Integer chief_id) {
		this.chief_id = chief_id;
	}
	
}
