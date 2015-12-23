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
@NamedQuery(name="TruckingAccounting.findAll", query="SELECT d FROM TruckingAccounting d")
public class TruckingAccounting extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//profitloss here is ok!!!
	
	@OneToOne
	@JoinColumn(name = "truckingdetail")
	private Truckingdetail truckingdetail;
	
	private BigDecimal phiAel;
	private BigDecimal phiChiHo;
	
	private BigDecimal phiAelDaThu;
	private BigDecimal phiChiHoDaThu;
	
	private BigDecimal tienTrucking;
	private BigDecimal tienTruckingDaTra;
	
	private Integer collectMoneyStatus;
	private Integer payMoneyStatus;
	
	/**
	 * @return the phiAel
	 */
	public BigDecimal getPhiAel() {
		return phiAel;
	}
	/**
	 * @param phiAel the phiAel to set
	 */
	public void setPhiAel(BigDecimal phiAel) {
		this.phiAel = phiAel;
	}
	/**
	 * @return the phiChiHo
	 */
	public BigDecimal getPhiChiHo() {
		return phiChiHo;
	}
	/**
	 * @param phiChiHo the phiChiHo to set
	 */
	public void setPhiChiHo(BigDecimal phiChiHo) {
		this.phiChiHo = phiChiHo;
	}
	/**
	 * @return the phiAelDaThu
	 */
	public BigDecimal getPhiAelDaThu() {
		return phiAelDaThu;
	}
	/**
	 * @param phiAelDaThu the phiAelDaThu to set
	 */
	public void setPhiAelDaThu(BigDecimal phiAelDaThu) {
		this.phiAelDaThu = phiAelDaThu;
	}
	/**
	 * @return the phiChiHoDaThu
	 */
	public BigDecimal getPhiChiHoDaThu() {
		return phiChiHoDaThu;
	}
	/**
	 * @param phiChiHoDaThu the phiChiHoDaThu to set
	 */
	public void setPhiChiHoDaThu(BigDecimal phiChiHoDaThu) {
		this.phiChiHoDaThu = phiChiHoDaThu;
	}
	/**
	 * @return the tienTrucking
	 */
	public BigDecimal getTienTrucking() {
		return tienTrucking;
	}
	/**
	 * @param tienTrucking the tienTrucking to set
	 */
	public void setTienTrucking(BigDecimal tienTrucking) {
		this.tienTrucking = tienTrucking;
	}
	/**
	 * @return the tienTruckingDaTra
	 */
	public BigDecimal getTienTruckingDaTra() {
		return tienTruckingDaTra;
	}
	/**
	 * @param tienTruckingDaTra the tienTruckingDaTra to set
	 */
	public void setTienTruckingDaTra(BigDecimal tienTruckingDaTra) {
		this.tienTruckingDaTra = tienTruckingDaTra;
	}
	/**
	 * @return the collectMoneyStatus
	 */
	public Integer getCollectMoneyStatus() {
		return collectMoneyStatus;
	}
	/**
	 * @param collectMoneyStatus the collectMoneyStatus to set
	 */
	public void setCollectMoneyStatus(Integer collectMoneyStatus) {
		this.collectMoneyStatus = collectMoneyStatus;
	}
	/**
	 * @return the payMoneyStatus
	 */
	public Integer getPayMoneyStatus() {
		return payMoneyStatus;
	}
	/**
	 * @param payMoneyStatus the payMoneyStatus to set
	 */
	public void setPayMoneyStatus(Integer payMoneyStatus) {
		this.payMoneyStatus = payMoneyStatus;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
