package model;

public class Customer extends Person {

	private int gender;

	/**
	 * 
	 * @param code
	 * @param fullName
	 * @param identityCard
	 * @param address
	 * @param phone
	 * @param birth
	 * @param gender
	 */
	public Customer(String code, String fullName, String identityCard, String address, String phone, String birth,
			int gender) {
		super(code, fullName, identityCard, address, phone, birth);
		this.gender = gender;
	}

	/**
	 * 
	 * @return gender
	 */
	public int getGender() {
		return gender;
	}

	/**
	 * 
	 * @param gender
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}

}
