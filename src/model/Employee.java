package model;

public class Employee extends Person {

	private int status;
	private String email;

	/**
	 * 
	 * @param code
	 * @param fullName
	 * @param identityCard
	 * @param address
	 * @param phone
	 * @param birth
	 * @param status
	 * @param email
	 */
	public Employee(String code, String fullName, String identityCard, String address, String phone, String birth,
			int status, String email) {
		super(code, fullName, identityCard, address, phone, birth);
		this.status = status;
		this.email = email;
	}

	/**
	 * 
	 * @return status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
