package model;

public class ServiceDetail {
	
	private String contractNo;
	private String serviceCode;
	private String empCode;

	/**
	 * 
	 * @param contractNo
	 * @param serviceCode
	 * @param empCode
	 */
	public ServiceDetail(String contractNo, String serviceCode, String empCode) {
		super();
		this.contractNo = contractNo;
		this.serviceCode = serviceCode;
		this.empCode = empCode;
	}

	/**
	 * 
	 * @return contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * 
	 * @param contractNo
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * 
	 * @return serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * 
	 * @param serviceCode
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	/**
	 * 
	 * @return empCode
	 */
	public String getEmpCode() {
		return empCode;
	}

	/**
	 * 
	 * @param empCode
	 */
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
}
