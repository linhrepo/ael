package com.vn.ael.persistence.entity;

import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.appfuse.model.User;

@MappedSuperclass
public class BasicAdvance extends BasedEntityTracking{

	private static final long serialVersionUID = 1L;

	private Date date;

	@ManyToOne
	@JoinColumn(name="employee")
	private User employee;

	private String refNo;

	private Boolean doApproval = false;
	
	private String payReason;

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getEmployee() {
		return this.employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public String getRefNo() {
		return this.refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Boolean getDoApproval() {
		return doApproval;
	}

	public void setDoApproval(Boolean doApproval) {
		this.doApproval = doApproval;
	}
	
	public String getPayReason() {
		return payReason;
	}

	public void setPayReason(String payReason) {
		this.payReason = payReason;
	}
}
