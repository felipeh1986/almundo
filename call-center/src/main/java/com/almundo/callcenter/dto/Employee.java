package com.almundo.callcenter.dto;

public class Employee {

private Integer id;
	
	/** The employee full name. */
	private String name;
	
	/** The employee type. */
	private String type;
	
	/** The chief id. */
	private Integer chief_id;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getChief_id() {
		return chief_id;
	}

	public void setChief_id(Integer chief_id) {
		this.chief_id = chief_id;
	}
	
	
}
