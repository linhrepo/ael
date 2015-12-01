package com.vn.ael.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.vn.ael.constants.AELConst;
import com.vn.ael.webapp.util.CommonUtil;

@Entity
@NamedQuery(name="MoneyBook.findAll", query="SELECT mb FROM MoneyBook mb")
public class MoneyBook extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date date;
	
	private Integer typeOfBook;
	
	private Integer typeOfVoucher;//payment(chi): 0, recept(thu): 1
	
	private Integer voucherNo;
	
	@Transient
	private String voucherNoPrint;
	
	private BigDecimal paymentMoney;
	
	private BigDecimal receptMoney; 
	
	private BigDecimal balance;
	
	private String description;
	
	private String refNos;

	@OneToMany(mappedBy="moneyBook", cascade = CascadeType.ALL)
	private List<Advanceform> advanceForms;
	
	@OneToMany(mappedBy="moneyBook", cascade = CascadeType.ALL)
	private List<Refund> refunds;
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the typeOfBook
	 */
	public Integer getTypeOfBook() {
		return typeOfBook;
	}

	/**
	 * @param typeOfBook the typeOfBook to set
	 */
	public void setTypeOfBook(Integer typeOfBook) {
		this.typeOfBook = typeOfBook;
	}

	/**
	 * @return the typeOfVoucher
	 */
	public Integer getTypeOfVoucher() {
		return typeOfVoucher;
	}

	/**
	 * @param typeOfVoucher the typeOfVoucher to set
	 */
	public void setTypeOfVoucher(Integer typeOfVoucher) {
		this.typeOfVoucher = typeOfVoucher;
	}

	/**
	 * @return the voucherNo
	 */
	public Integer getVoucherNo() {
		return voucherNo;
	}
	
	public String getVoucherNoPrint() {
		String prefix = (typeOfVoucher == 0 ? AELConst.VOUCHER_NO_PREFIX_PAYMENT : AELConst.VOUCHER_NO_PREFIX_RECEPT);
		String number = CommonUtil.addZero(String.valueOf(voucherNo), CommonUtil.LENGTH_OF_COUNTER);

		String voucherNoPrint = prefix + number ;
		return voucherNoPrint;
	}

	/**
	 * @param voucherNo the voucherNo to set
	 */
	public void setVoucherNo(Integer voucherNo) {
		this.voucherNo = voucherNo;
	}

	/**
	 * @return the paymentMoney
	 */
	public BigDecimal getPaymentMoney() {
		return paymentMoney;
	}

	/**
	 * @param paymentMoney the paymentMoney to set
	 */
	public void setPaymentMoney(BigDecimal paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	/**
	 * @return the receptMoney
	 */
	public BigDecimal getReceptMoney() {
		return receptMoney;
	}

	/**
	 * @param receptMoney the receptMoney to set
	 */
	public void setReceptMoney(BigDecimal receptMoney) {
		this.receptMoney = receptMoney;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRefNos() {
		return refNos;
	}

	public void setRefNos(String refNos) {
		this.refNos = refNos;
	}

	public List<Advanceform> getAdvanceForms() {
		return advanceForms;
	}

	public void setAdvanceForms(List<Advanceform> advanceForms) {
		this.advanceForms = advanceForms;
	}

	public List<Refund> getRefunds() {
		return refunds;
	}

	public void setRefunds(List<Refund> refunds) {
		this.refunds = refunds;
	}
	
	
}
