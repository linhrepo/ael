package com.vn.ael.webapp.dto;

import java.util.List;
import java.util.Map;

import org.appfuse.model.User;

import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;

public class DocsSelection {
	private Map<String,Map<Long,String>> selections;
	
	private List<Customer> customers;
	
	private List<User> staff;
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<User> getStaff() {
		return staff;
	}

	public void setStaff(List<User> staff) {
		this.staff = staff;
	}

	public Map<String,Map<Long,String>> getSelections() {
		return selections;
	}

	public void setSelections(Map<String,Map<Long,String>> selections) {
		this.selections = selections;
	}

}
