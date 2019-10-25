package model;

public class Person {

	private String code;
	private String fullName;
	private String identityCard;
	private String address;
	private String phone;
	private String birth;

	/**
	 * 
	 * @param code
	 * @param fullName
	 * @param identityCard
	 * @param address
	 * @param phone
	 * @param birth
	 */
	public Person(String code, String fullName, String identityCard, String address, String phone, String birth) {
		super();
		this.code = code;
		this.fullName = fullName;
		this.identityCard = identityCard;
		this.address = address;
		this.phone = phone;
		this.birth = birth;
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
	 * @return fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * @param fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 
	 * @return identityCard
	 */
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 
	 * @param identityCard
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	/**
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @return birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * 
	 * @param birth
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

}
