package com.vn.ael.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

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
	
	private String voucherNo;
	
	private BigDecimal paymentMoney;
	
	private BigDecimal receptMoney; 
	
	private BigDecimal balance;
	
	private String description;
	
	private String refNos;

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
	public String getVoucherNo() {
		return voucherNo;
	}

	/**
	 * @param voucherNo the voucherNo to set
	 */
	public void setVoucherNo(String voucherNo) {
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
	
	
}
