/**
 * 
 */
package com.vn.ael.webapp.dto;

/**
 * @author hiendn1992
 *
 */
public class AccountingExhibitionItemExport {
	private String description;
	private String rate;
	private String amount;
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
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
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
	 * @param description
	 * @param type
	 * @param rate
	 * @param amount
	 */
	public AccountingExhibitionItemExport(String description,
			String rate, String amount) {
		super();
		this.description = description;
		this.rate = rate;
		this.amount = amount;
	}
}
