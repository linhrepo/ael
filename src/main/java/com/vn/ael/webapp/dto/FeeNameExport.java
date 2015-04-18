package com.vn.ael.webapp.dto;

import java.math.BigDecimal;
import java.util.List;

public class FeeNameExport {
	private String name;
	private List<BigDecimal> values;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BigDecimal> getValues() {
		return values;
	}
	public void setValues(List<BigDecimal> values) {
		this.values = values;
	}
	
}
