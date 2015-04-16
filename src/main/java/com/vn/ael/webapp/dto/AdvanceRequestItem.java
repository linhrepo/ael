/**
 * 
 */
package com.vn.ael.webapp.dto;

/**
 * @author hiendn1992
 *
 */
public class AdvanceRequestItem {
	private Integer index;
	private String jobNo;
	private String packageDetail;
	private String advanceReason;
	private String amount;
	private String remainAdvance;
	/**
	 * @return the jobNo
	 */
	public String getJobNo() {
		return jobNo;
	}
	/**
	 * @param jobNo the jobNo to set
	 */
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	/**
	 * @return the packageDetail
	 */
	public String getPackageDetail() {
		return packageDetail;
	}
	/**
	 * @param packageDetail the packageDetail to set
	 */
	public void setPackageDetail(String packageDetail) {
		this.packageDetail = packageDetail;
	}
	/**
	 * @return the advanceReason
	 */
	public String getAdvanceReason() {
		return advanceReason;
	}
	/**
	 * @param advanceReason the advanceReason to set
	 */
	public void setAdvanceReason(String advanceReason) {
		this.advanceReason = advanceReason;
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
	 * @return the remainAdvance
	 */
	public String getRemainAdvance() {
		return remainAdvance;
	}
	/**
	 * @param remainAdvance the remainAdvance to set
	 */
	public void setRemainAdvance(String remainAdvance) {
		this.remainAdvance = remainAdvance;
	}
	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	/**
	 * @param jobNo
	 * @param packageDetail
	 * @param advanceReason
	 * @param amount
	 * @param remainAdvance
	 */
	public AdvanceRequestItem(Integer index, String jobNo, String packageDetail,
			String advanceReason, String amount, String remainAdvance) {
		super();
		this.index = index;
		this.jobNo = jobNo;
		this.packageDetail = packageDetail;
		this.advanceReason = advanceReason;
		this.amount = amount;
		this.remainAdvance = remainAdvance;
	}
	public AdvanceRequestItem() {
		// TODO Auto-generated constructor stub
	}
	

}
