/**
 * 
 */
package com.vn.ael.webapp.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dbkp
 *
 */
public class LiabilityDetail {
	private Long employeeId;
	private Date date;
	private String type;
	private String explain;
	private BigDecimal debt;
	private BigDecimal refund;
	private BigDecimal remain;
	
	public LiabilityDetail() {};
	/**
	 * @param employeeName
	 * @param totalAdvanceBefore
	 * @param totalRefundBefore
	 * @param totalAdvanceBetween
	 * @param totalRefundBetween
	 * @param totalAdvanceAfter
	 * @param totalRefundAfter
	 */
	public LiabilityDetail(Long id, Date date, String type,
			String explain, BigDecimal debt,
			BigDecimal refund, BigDecimal remain) {
		super();
		this.employeeId = id;
		this.date = date;
		this.type = type;
		this.explain = explain;
		this.debt = debt;
		this.refund = refund;
		this.remain = remain;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getExplain() {
		return explain;
	}


	public void setExplain(String explain) {
		this.explain = explain;
	}


	public BigDecimal getDebt() {
		return debt;
	}


	public void setDebt(BigDecimal debt) {
		this.debt = debt;
	}


	public BigDecimal getRefund() {
		return refund;
	}


	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}


	public BigDecimal getRemain() {
		return remain;
	}


	public void setRemain(BigDecimal remain) {
		this.remain = remain;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
}
