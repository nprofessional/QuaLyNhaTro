package model;

public class RoomType {

	private String code;
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	public RoomType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
