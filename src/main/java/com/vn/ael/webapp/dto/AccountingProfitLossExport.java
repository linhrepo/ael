package com.vn.ael.webapp.dto;

import java.util.List;

public class AccountingProfitLossExport {
	
	private String cusName;
	private String jobNo;
	private String contNo;
	private String nhathau;
	private List<FeeExportItem> convertedFee;
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getNhathau() {
		return nhathau;
	}
	public void setNhathau(String nhathau) {
		this.nhathau = nhathau;
	}
	public List<FeeExportItem> getConvertedFee() {
		return convertedFee;
	}
	public void setConvertedFee(List<FeeExportItem> convertedFee) {
		this.convertedFee = convertedFee;
	}

}
