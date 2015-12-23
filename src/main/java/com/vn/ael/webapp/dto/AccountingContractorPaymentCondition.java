package com.vn.ael.webapp.dto;



public class AccountingContractorPaymentCondition {

	private Long typeOfDocs;
	private String job = null;
	private Long nhathauId;
	private Integer payMoneyStatus;
	
	/**
	 * @return the typeOfDocs
	 */
	public Long getTypeOfDocs() {
		return typeOfDocs;
	}
	/**
	 * @param typeOfDocs the typeOfDocs to set
	 */
	public void setTypeOfDocs(Long typeOfDocs) {
		this.typeOfDocs = typeOfDocs;
	}
	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * @return the payMoneyStatus
	 */
	public Integer getPayMoneyStatus() {
		return payMoneyStatus;
	}
	/**
	 * @param payMoneyStatus the payMoneyStatus to set
	 */
	public void setPayMoneyStatus(Integer payMoneyStatus) {
		this.payMoneyStatus = payMoneyStatus;
	}
	
	public Long getNhathauId() {
		return nhathauId;
	}
	public void setNhathauId(Long nhathauId) {
		this.nhathauId = nhathauId;
	}
	
	
}
