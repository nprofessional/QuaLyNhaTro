package model;

public class Room {
	private String code;
	private String name;
	private String status;
	private String type;
	private int floor;
	private String remark;
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param status
	 * @param type
	 * @param floor
	 * @param remark
	 */
	public Room(String code, String name, String status, String type, int floor, String remark) {
		super();
		this.code = code;
		this.name = name;
		this.status = status;
		this.type = type;
		this.floor = floor;
		this.remark = remark;
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

	/**
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return Floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * 
	 * @param floor
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
