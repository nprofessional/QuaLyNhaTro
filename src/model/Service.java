package model;

public class Service {

	private String code;
	private String name;
	private String unit;
	private long price;

	/**
	 *
	 * @param code
	 * @param name
	 * @param unit
	 * @param price
	 */
	public Service(String code, String name, String unit, long price) {
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
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
	 * @return unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 *
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 *
	 * @return price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 *
	 * @param price
	 */
	public void setPrice(long price) {
		this.price = price;
	}
}
