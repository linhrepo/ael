package com.vn.ael.webapp.dto;

public class Search {

	private Long customer;
	private Long typeOfImport;
	private Long typeOfContainer;
	private Boolean doDelivery;
	private Boolean doRelease;
	private Long typeOfEx;
	private Long typeOfDocs;
	private Boolean doAccounting;
	
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
	
}
