/**
 * 
 */
package com.vn.ael.webapp.dto;

/**
 * @author hiendn1992
 *
 */
public class RefundRequestItem {
	private int index;
	private String fileCus;
	private String description;
	private String amount;
	private String oAmount;
	private String cont;
	/**
	 * @param index
	 * @param fileCus
	 * @param description
	 * @param amount
	 * @param oAmount
	 */
	public RefundRequestItem(int index, String fileCus, String description,
			String amount, String oAmount) {
		super();
		this.index = index;
		this.fileCus = fileCus;
		this.description = description;
		this.amount = amount;
		this.oAmount = oAmount;
	}
	public RefundRequestItem() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the fileCus
	 */
	public String getFileCus() {
		return fileCus;
	}
	/**
	 * @param fileCus the fileCus to set
	 */
	public void setFileCus(String fileCus) {
		this.fileCus = fileCus;
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
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the oAmount
	 */
	public String getoAmount() {
		return oAmount;
	}
	/**
	 * @param oAmount the oAmount to set
	 */
	public void setoAmount(String oAmount) {
		this.oAmount = oAmount;
	}
	/**
	 * @return the cont
	 */
	public String getCont() {
		return cont;
	}
	/**
	 * @param cont the cont to set
	 */
	public void setCont(String cont) {
		this.cont = cont;
	}
}
