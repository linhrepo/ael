package com.vn.ael.enums;

public enum ReportMergeInfo {
	
	BANG_KE_CUOC_VAN_CHUYEN(16,0,1,3,4,5,6,7,10,11,12,13,14);
	
	private int startingRow;
	private int[] cols;
	
	ReportMergeInfo(int startingRow,int... cols){
		this.startingRow = startingRow;
		this.cols = cols;
	}

	public int getStartingRow() {
		return startingRow;
	}

	public void setStartingRow(int startingRow) {
		this.startingRow = startingRow;
	}

	public int[] getCols() {
		return cols;
	}

	public void setCols(int[] cols) {
		this.cols = cols;
	}
	
}
