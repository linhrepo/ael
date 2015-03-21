package com.vn.ael.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the receiptacc database table.
 * 
 */
@Entity
@NamedQuery(name="Receiptacc.findAll", query="SELECT r FROM Receiptacc r")
public class Receiptacc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private BigDecimal amount;

	private String book;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private int doApproval;

	private BigInteger employee;

	private String ourRef;

	private String reason;

	private String refNo;

	private int type;

	public Receiptacc() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getBook() {
		return this.book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDoApproval() {
		return this.doApproval;
	}

	public void setDoApproval(int doApproval) {
		this.doApproval = doApproval;
	}

	public BigInteger getEmployee() {
		return this.employee;
	}

	public void setEmployee(BigInteger employee) {
		this.employee = employee;
	}

	public String getOurRef() {
		return this.ourRef;
	}

	public void setOurRef(String ourRef) {
		this.ourRef = ourRef;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRefNo() {
		return this.refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

}