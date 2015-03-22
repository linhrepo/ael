package com.vn.ael.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.appfuse.model.User;

import com.vn.ael.webapp.util.ConvertUtil;


/**
 * The persistent class for the advanceform database table.
 * 
 */
@Entity
@NamedQuery(name="Advanceform.findAll", query="SELECT a FROM Advanceform a")
public class Advanceform extends BasedEntityTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date date;

	@ManyToOne
	@JoinColumn(name="employee")
	private User employee;

	private String refNo;

	private Date timeRefund;

	//bi-directional many-to-one association to Advancedetail
	@OneToMany(mappedBy="advanceform", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Advancedetail> advancedetails;
	
	private Boolean doApproval;

	public Advanceform() {
	}

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

	public Date getTimeRefund() {
		return this.timeRefund;
	}

	public void setTimeRefund(Date timeRefund) {
		this.timeRefund = timeRefund;
	}

	public List<Advancedetail> getAdvancedetails() {
		return this.advancedetails;
	}

	public void setAdvancedetails(List<Advancedetail> advancedetails) {
		this.advancedetails = advancedetails;
	}

	public Advancedetail addAdvancedetail(Advancedetail advancedetail) {
		getAdvancedetails().add(advancedetail);
		advancedetail.setAdvanceform(this);

		return advancedetail;
	}

	public Advancedetail removeAdvancedetail(Advancedetail advancedetail) {
		getAdvancedetails().remove(advancedetail);
		advancedetail.setAdvanceform(null);

		return advancedetail;
	}

	public Boolean getDoApproval() {
		return doApproval;
	}

	public void setDoApproval(Boolean doApproval) {
		this.doApproval = doApproval;
	}
	
	@Transient
	private BigDecimal total;
	
	public BigDecimal getTotal(){
		this.total = BigDecimal.ZERO;
		if (this.advancedetails != null && !this.advancedetails.isEmpty()){
			for (Advancedetail advancedetail : this.advancedetails){
				this.total = this.total.add(ConvertUtil.getNotNullValue(advancedetail.getAmount()));
			}
		}
		return this.total;
	}

}