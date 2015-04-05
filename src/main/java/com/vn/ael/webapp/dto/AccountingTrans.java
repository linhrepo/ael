package com.vn.ael.webapp.dto;

import java.util.List;

import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Nhathau;
import com.vn.ael.persistence.entity.Truckingdetail;

public class AccountingTrans {
	
	private List<Docsgeneral> docs;
	private Customer customer;
	private AccountingTransCondition condition;
	private Nhathau nhathau;
	private List<Truckingdetail> truckingdetails;
	public List<Docsgeneral> getDocs() {
		return docs;
	}
	public void setDocs(List<Docsgeneral> docs) {
		this.docs = docs;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public AccountingTransCondition getCondition() {
		return condition;
	}
	public void setCondition(AccountingTransCondition condition) {
		this.condition = condition;
	}
	public Nhathau getNhathau() {
		return nhathau;
	}
	public void setNhathau(Nhathau nhathau) {
		this.nhathau = nhathau;
	}
	public List<Truckingdetail> getTruckingdetails() {
		return truckingdetails;
	}
	public void setTruckingdetails(List<Truckingdetail> truckingdetails) {
		this.truckingdetails = truckingdetails;
	}
	
	
}
