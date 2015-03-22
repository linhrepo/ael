package com.vn.ael.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the advancedetail database table.
 * 
 */
@Entity
@NamedQuery(name="Advancedetail.findAll", query="SELECT a FROM Advancedetail a")
public class Advancedetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private BigDecimal amount;

	private String description;

	@ManyToOne
	@JoinColumn(name="docs")
	private Docsgeneral docs;

	//bi-directional many-to-one association to Advanceform
	@ManyToOne
	@JoinColumn(name="advanceForm")
	private Advanceform advanceform;

	public Advancedetail() {
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Docsgeneral getDocs() {
		return this.docs;
	}

	public void setDocs(Docsgeneral docs) {
		this.docs = docs;
	}

	public Advanceform getAdvanceform() {
		return this.advanceform;
	}

	public void setAdvanceform(Advanceform advanceform) {
		this.advanceform = advanceform;
	}

}