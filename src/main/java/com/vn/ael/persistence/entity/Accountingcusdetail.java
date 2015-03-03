package com.vn.ael.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;

import com.vn.ael.constants.FormatterPattern;

import java.math.BigDecimal;


/**
 * The persistent class for the accountingcusdetail database table.
 * 
 */
@Entity
@NamedQuery(name="Accountingcusdetail.findAll", query="SELECT a FROM Accountingcusdetail a")
public class Accountingcusdetail extends BasedChildEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="description")
	private Configuration description;

	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal generalVat;

	private String invoice;

	@ManyToOne
	@JoinColumn(name="name")
	private Configuration name;

	private String note;

	private Integer quantity20;

	private Integer quantity40;

	private Integer quantityLCL;

	//bi-directional many-to-one association to Accountingcus
	@ManyToOne
	@JoinColumn(name="accountingcus")
	private Accountingcus accountingcus;

	public Accountingcusdetail() {
	}

	public BigDecimal getGeneralVat() {
		return this.generalVat;
	}

	public void setGeneralVat(BigDecimal generalVat) {
		this.generalVat = generalVat;
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

	public int getQuantity20() {
		return this.quantity20;
	}

	public void setQuantity20(int quantity20) {
		this.quantity20 = quantity20;
	}

	public int getQuantity40() {
		return this.quantity40;
	}

	public void setQuantity40(int quantity40) {
		this.quantity40 = quantity40;
	}

	public int getQuantityLCL() {
		return this.quantityLCL;
	}

	public void setQuantityLCL(int quantityLCL) {
		this.quantityLCL = quantityLCL;
	}

	public Accountingcus getAccountingcus() {
		return this.accountingcus;
	}

	public void setAccountingcus(Accountingcus accountingcusBean) {
		this.accountingcus = accountingcusBean;
	}

}