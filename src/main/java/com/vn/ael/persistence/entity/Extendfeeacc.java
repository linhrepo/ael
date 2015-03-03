package com.vn.ael.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the extendfeeacc database table.
 * 
 */
@Entity
@NamedQuery(name="Extendfeeacc.findAll", query="SELECT e FROM Extendfeeacc e")
public class Extendfeeacc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "description")
	private Configuration description;

	@OneToOne
	@JoinColumn(name= "feeowner")
	private Exfeetable feeowner;

	private String invoice;

	private String note;
	
	@ManyToOne
	@JoinColumn(name="accountingcus")
	private Accountingcus accountingcus;

	public Extendfeeacc() {
	}

	public Configuration getDescription() {
		return this.description;
	}

	public void setDescription(Configuration description) {
		this.description = description;
	}

	public Exfeetable getFeeowner() {
		return this.feeowner;
	}

	public void setFeeowner(Exfeetable feeowner) {
		this.feeowner = feeowner;
	}

	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}