package com.vn.ael.persistence.entity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.appfuse.model.User;

import com.vn.ael.constants.AELConst;
import com.vn.ael.webapp.util.CommonUtil;
import com.vn.ael.webapp.util.ConvertUtil;

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
	
	@Transient
	private String refCode;
	
	public String getRefCode(){
		if(this.getId() != null && this.getEmployee() != null){
			this.refCode = this.employee.getUsername()+AELConst.SPLASH +String.valueOf(this.getId());
		}
		return this.refCode;
	}
}
