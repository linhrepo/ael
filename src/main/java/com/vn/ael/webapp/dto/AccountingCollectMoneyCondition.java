package com.vn.ael.webapp.dto;


public class AccountingCollectMoneyCondition {

	private Long typeOfDocs;
	private String job = null;
	private Integer collectMoneyStatus;
	
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
	 * @return the collectMoneyStatus
	 */
	public Integer getCollectMoneyStatus() {
		return collectMoneyStatus;
	}
	/**
	 * @param collectMoneyStatus the collectMoneyStatus to set
	 */
	public void setCollectMoneyStatus(Integer collectMoneyStatus) {
		this.collectMoneyStatus = collectMoneyStatus;
	}
	
	
}
