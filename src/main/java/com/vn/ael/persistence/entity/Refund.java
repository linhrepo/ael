package com.vn.ael.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.vn.ael.webapp.util.ConvertUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the refund database table.
 * 
 */
@Entity
@NamedQuery(name="Refund.findAll", query="SELECT r FROM Refund r")
public class Refund extends BasicAdvance implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Refunddetail
	@OneToMany(mappedBy="refund", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Refunddetail> refunddetails;
	
	private Boolean isPhieuThu;

	public Refund() {
	}

	public List<Refunddetail> getRefunddetails() {
		return this.refunddetails;
	}

	public void setRefunddetails(List<Refunddetail> refunddetails) {
		this.refunddetails = refunddetails;
	}

	public Refunddetail addRefunddetail(Refunddetail refunddetail) {
		getRefunddetails().add(refunddetail);
		refunddetail.setRefund(this);

		return refunddetail;
	}

	public Refunddetail removeRefunddetail(Refunddetail refunddetail) {
		getRefunddetails().remove(refunddetail);
		refunddetail.setRefund(null);
		return refunddetail;
	}
	
	@Transient
	private BigDecimal totalAmount;
	
	public BigDecimal getTotalAmount(){
		this.totalAmount = BigDecimal.ZERO;
		if (this.refunddetails != null && !this.refunddetails.isEmpty()){
			for (Refunddetail refunddetail : this.refunddetails){
				this.totalAmount = this.totalAmount.add(ConvertUtil.getNotNullValue(refunddetail.getAmount()));
			}
		}
		return this.totalAmount;
	}
	
	@Transient
	private BigDecimal totalOAmount;
	
	public BigDecimal getTotalOAmount(){
		this.totalOAmount = BigDecimal.ZERO;
		if (this.refunddetails != null && !this.refunddetails.isEmpty()){
			for (Refunddetail refunddetail : this.refunddetails){
				this.totalOAmount = this.totalOAmount.add(ConvertUtil.getNotNullValue(refunddetail.getOAmount()));
			}
		}
		return this.totalOAmount;
	}
	
	@Transient
	private BigDecimal total;
	
	public BigDecimal getTotal(){
		this.total = BigDecimal.ZERO;
		if (this.refunddetails != null && !this.refunddetails.isEmpty()){
			return this.getTotalAmount().add(this.getTotalOAmount());
		}
		return total;
	}

	/**
	 * @return the isPhieuThu
	 */
	public Boolean getIsPhieuThu() {
		return isPhieuThu;
	}

	/**
	 * @param isPhieuThu the isPhieuThu to set
	 */
	public void setIsPhieuThu(Boolean isPhieuThu) {
		this.isPhieuThu = isPhieuThu;
	}
	
}