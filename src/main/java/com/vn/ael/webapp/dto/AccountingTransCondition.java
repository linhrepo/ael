package com.vn.ael.webapp.dto;

import java.util.Date;

public class AccountingTransCondition {
	
	Long customerId;
	Integer month;
	Integer year;
	Date startDate;
	Date endDate;
	Long nhathauId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getNhathauId() {
		return nhathauId;
	}
	public void setNhathauId(Long nhathauId) {
		this.nhathauId = nhathauId;
	}

}
