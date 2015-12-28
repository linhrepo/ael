package com.vn.ael.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.appfuse.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.NumberFormat;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.FormatterPattern;
import com.vn.ael.enums.CollectMoneyStatusType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.webapp.formatter.FormatterUtil;


/**
 * The persistent class for the docsgeneral database table.
 * 
 */
@Entity
@NamedQuery(name="TruckAccounting.findAll", query="SELECT d FROM TruckAccounting d")
public class TruckAccounting extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "truckingdetail")
	private Truckingdetail truckingdetail;
	
	private BigDecimal phiAelChuaChi;
	private BigDecimal phiChiHoChuaChi;
	
	private BigDecimal phiAelDaChi;
	private BigDecimal phiChiHoDaChi;

	private Integer payMoneyStatus;
	
	public Truckingdetail getTruckingdetail() {
		return truckingdetail;
	}

	public void setTruckingdetail(Truckingdetail truckingdetail) {
		this.truckingdetail = truckingdetail;
	}

	
	public BigDecimal getPhiAelChuaChi() {
		return phiAelChuaChi;
	}

	public void setPhiAelChuaChi(BigDecimal phiAelChuaChi) {
		this.phiAelChuaChi = phiAelChuaChi;
	}

	public BigDecimal getPhiChiHoChuaChi() {
		return phiChiHoChuaChi;
	}

	public void setPhiChiHoChuaChi(BigDecimal phiChiHoChuaChi) {
		this.phiChiHoChuaChi = phiChiHoChuaChi;
	}

	public BigDecimal getPhiAelDaChi() {
		return phiAelDaChi;
	}

	public void setPhiAelDaChi(BigDecimal phiAelDaChi) {
		this.phiAelDaChi = phiAelDaChi;
	}

	public BigDecimal getPhiChiHoDaChi() {
		return phiChiHoDaChi;
	}

	public void setPhiChiHoDaChi(BigDecimal phiChiHoDaChi) {
		this.phiChiHoDaChi = phiChiHoDaChi;
	}

	public Integer getPayMoneyStatus() {
		return payMoneyStatus;
	}

	public void setPayMoneyStatus(Integer payMoneyStatus) {
		this.payMoneyStatus = payMoneyStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
