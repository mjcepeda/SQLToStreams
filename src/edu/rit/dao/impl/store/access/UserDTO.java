package edu.rit.dao.impl.store.access;

public class UserDTO {

	private String name;
	
	private Object dto;

	public UserDTO(String name, Object dto) {
		this.name = name;
		this.dto = dto;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dto
	 */
	public Object getDto() {
		return dto;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(Object dto) {
		this.dto = dto;
	}
}
