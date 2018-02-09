package com.almundo.callcenter.dto;

/**
 * The Class GeneralResponse defines a template that uses a  generic type.
 *
 * @param <T> the generic type
 */
public class GeneralResponse<T> {

	/** The code. */
	private int code;

	/** The description. */
	private String description;

	/** The detail. */
	private T detail;

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the detail.
	 *
	 * @return the detail
	 */
	public T getDetail() {
		return detail;
	}

	/**
	 * Sets the detail.
	 *
	 * @param detail the new detail
	 */
	public void setDetail(T detail) {
		this.detail = detail;
	}

}
