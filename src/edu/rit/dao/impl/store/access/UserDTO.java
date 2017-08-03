package edu.rit.dao.impl.store.access;

/**
 * The Class UserDTO.
 */
public class UserDTO {

	/** The name. */
	private String name;
	
	/** The dto. */
	private Object dto;

	/**
	 * Instantiates a new user DTO.
	 *
	 * @param name the name
	 * @param dto the dto
	 */
	public UserDTO(String name, Object dto) {
		this.name = name;
		this.dto = dto;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public Object getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the dto to set
	 */
	public void setDto(Object dto) {
		this.dto = dto;
	}
}
