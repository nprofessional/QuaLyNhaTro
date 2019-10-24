package model;

public class RoomStatus {

	private String code;
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	public RoomStatus(String code, String name) {
		super();
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
