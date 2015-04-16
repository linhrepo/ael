package com.vn.ael.webapp.dto;

import java.util.Date;

import com.vn.ael.enums.ServicesType;

public class Search {

	private Long customer;
	private Long typeOfImport;
	private Long typeOfContainer;
	private Boolean doDelivery;
	private Boolean doRelease;
	private Long typeOfEx;
	private Long typeOfDocs;
	private Boolean doAccounting;
	private ServicesType servicesType;
	private Long employee;
	private Date startDate;
	private Date endDate;
	private Date startTimeRefund;
	private Date endTimeRefund;
	private Boolean doApproval;
	
	public Long getCustomer() {
		return customer;
	}
	public void setCustomer(Long customer) {
		this.customer = customer;
	}
	public Long getTypeOfImport() {
		return typeOfImport;
	}
	public void setTypeOfImport(Long typeOfImport) {
		this.typeOfImport = typeOfImport;
	}
	public Long getTypeOfContainer() {
		return typeOfContainer;
	}
	public void setTypeOfContainer(Long typeOfContainer) {
		this.typeOfContainer = typeOfContainer;
	}
	public Boolean getDoDelivery() {
		return doDelivery;
	}
	public void setDoDelivery(Boolean doDelivery) {
		this.doDelivery = doDelivery;
	}
	public Boolean getDoRelease() {
		return doRelease;
	}
	public void setDoRelease(Boolean doRelease) {
		this.doRelease = doRelease;
	}
	public Long getTypeOfEx() {
		return typeOfEx;
	}
	public void setTypeOfEx(Long typeOfEx) {
		this.typeOfEx = typeOfEx;
	}
	public Long getTypeOfDocs() {
		return typeOfDocs;
	}
	public void setTypeOfDocs(Long typeOfDocs) {
		this.typeOfDocs = typeOfDocs;
	}
	public Boolean getDoAccounting() {
		return doAccounting;
	}
	public void setDoAccounting(Boolean doAccounting) {
		this.doAccounting = doAccounting;
	}
	public ServicesType getServicesType() {
		return servicesType;
	}
	public void setServicesType(ServicesType servicesType) {
		this.servicesType = servicesType;
	}
	public Long getEmployee() {
		return employee;
	}
	public void setEmployee(Long employee) {
		this.employee = employee;
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
	public Date getStartTimeRefund() {
		return startTimeRefund;
	}
	public void setStartTimeRefund(Date startTimeRefund) {
		this.startTimeRefund = startTimeRefund;
	}
	public Date getEndTimeRefund() {
		return endTimeRefund;
	}
	public void setEndTimeRefund(Date endTimeRefund) {
		this.endTimeRefund = endTimeRefund;
	}
	public Boolean getDoApproval() {
		return doApproval;
	}
	public void setDoApproval(Boolean doApproval) {
		this.doApproval = doApproval;
	}	
	
}
