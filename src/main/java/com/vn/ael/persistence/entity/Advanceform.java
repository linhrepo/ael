package com.vn.ael.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the advanceform database table.
 * 
 */
@Entity
@NamedQuery(name="Advanceform.findAll", query="SELECT a FROM Advanceform a")
public class Advanceform implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private BigInteger creator;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private BigInteger employee;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	private String refNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timeRefund;

	private BigInteger updator;

	//bi-directional many-to-one association to Advancedetail
	@OneToMany(mappedBy="advanceform")
	private List<Advancedetail> advancedetails;

	public Advanceform() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigInteger getCreator() {
		return this.creator;
	}

	public void setCreator(BigInteger creator) {
		this.creator = creator;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigInteger getEmployee() {
		return this.employee;
	}

	public void setEmployee(BigInteger employee) {
		this.employee = employee;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

	public BigInteger getUpdator() {
		return this.updator;
	}

	public void setUpdator(BigInteger updator) {
		this.updator = updator;
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

}