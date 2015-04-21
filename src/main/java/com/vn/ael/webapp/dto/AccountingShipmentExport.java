package com.vn.ael.webapp.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountingShipmentExport {
	private Integer index;
	private String jobNo;
	private String shipper;
	private String poNo;
	private String invNo;
	private String billNo;
	private String contL;
	private Integer cont20;
	private Integer cont40;
	private Integer pks;
	private BigDecimal weight;
	private String shippingLine;
	private Date eta;
	private String arrival;
	private Date docOriginalDate;
	private Date decDate;
	private String soToKhai;
	private String colour;
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getContL() {
		return contL;
	}
	public void setContL(String contL) {
		this.contL = contL;
	}
	public Integer getCont20() {
		return cont20;
	}
	public void setCont20(Integer cont20) {
		this.cont20 = cont20;
	}
	public Integer getCont40() {
		return cont40;
	}
	public void setCont40(Integer cont40) {
		this.cont40 = cont40;
	}
	public Integer getPks() {
		return pks;
	}
	public void setPks(Integer pks) {
		this.pks = pks;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getShippingLine() {
		return shippingLine;
	}
	public void setShippingLine(String shippingLine) {
		this.shippingLine = shippingLine;
	}
	public Date getEta() {
		return eta;
	}
	public void setEta(Date eta) {
		this.eta = eta;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public Date getDocOriginalDate() {
		return docOriginalDate;
	}
	public void setDocOriginalDate(Date docOriginalDate) {
		this.docOriginalDate = docOriginalDate;
	}
	public Date getDecDate() {
		return decDate;
	}
	public void setDecDate(Date decDate) {
		this.decDate = decDate;
	}
	public String getSoToKhai() {
		return soToKhai;
	}
	public void setSoToKhai(String soToKhai) {
		this.soToKhai = soToKhai;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	
}
