package com.vn.ael.webapp.dto;

import java.util.List;

import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;

public class AccountingTrans {
	
	private List<Docsgeneral> docs;
	private Customer customer;
	private AccountingTransCondition condition;
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
	
}
