package com.vn.ael.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.NumberFormat;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.FormatterPattern;

/**
 * The persistent class for the exfeetable database table.
 * 
 */
@Entity
@NamedQuery(name="Exfeetable.findAll", query="SELECT e FROM Exfeetable e")
public class Exfeetable extends BasedChildEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "name")
	private Configuration name;

	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal vat;

	@NumberFormat(pattern = FormatterPattern.NUMBER_HAS_EXTENSION)
	private BigDecimal total;
	
	//bi-directional many-to-one association to Exhibition
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="docsgeneral")
	private Docsgeneral docsgeneral;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="truckingdetail")
	private Truckingdetail truckingdetail;
	
	@ManyToOne
	@JoinColumn(name="masterFee")
	private Configuration masterFee;
	
	@JsonIgnore
	@OneToOne(mappedBy = "feeowner")
	private Extendfeeacc extendfeeacc;
	
	private Boolean approved;
	
	private Boolean checkByAdmin;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name= "exhibition")
	private Exhibition exhibition;
	
	private Date dateChange;
	
	private String invoiceNo;

	public Exfeetable() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Configuration getName() {
		return this.name;
	}

	public void setName(Configuration name) {
		this.name = name;
	}

	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public Docsgeneral getDocsgeneral() {
		return docsgeneral;
	}

	public void setDocsgeneral(Docsgeneral docsgeneral) {
		this.docsgeneral = docsgeneral;
	}

	public Configuration getMasterFee() {
		return masterFee;
	}

	public void setMasterFee(Configuration masterFee) {
		this.masterFee = masterFee;
	}

	public BigDecimal getTotal(){
		return this.total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Transient
	public BigDecimal getVatFee(){
		if (this.vat == null){
			return BigDecimal.ZERO;
		}
		if (this.amount == null){
			return BigDecimal.ZERO;
		}
		return this.vat.multiply(this.amount);
	}

	public Extendfeeacc getExtendfeeacc() {
		return extendfeeacc;
	}

	public void setExtendfeeacc(Extendfeeacc extendfeeacc) {
		this.extendfeeacc = extendfeeacc;
	}

	public Truckingdetail getTruckingdetail() {
		return truckingdetail;
	}

	public void setTruckingdetail(Truckingdetail truckingdetail) {
		this.truckingdetail = truckingdetail;
	}

	public Exhibition getExhibition() {
		return exhibition;
	}

	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	@Transient
	public String getApprovedText(){
		if (this.approved == null || ! this.approved){
			return AELConst.ACC_FEE_ISNOT_APPROVED;
		}
		return AELConst.ACC_FEE_IS_APPROVED;
	}
	
	@Transient
	public String getCheckByAdminText(){
		if (this.checkByAdmin == null || ! this.checkByAdmin){
			return AELConst.ACC_FEE_ISNOT_APPROVED;
		}
		return AELConst.ACC_FEE_IS_APPROVED;
	}

	public Date getDateChange() {
		return dateChange;
	}

	public void setDateChange(Date dateChange) {
		this.dateChange = dateChange;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Boolean getCheckByAdmin() {
		return checkByAdmin;
	}

	public void setCheckByAdmin(Boolean checkByAdmin) {
		this.checkByAdmin = checkByAdmin;
	}
	
}