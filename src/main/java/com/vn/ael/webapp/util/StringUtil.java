package com.vn.ael.webapp.util;

import java.util.Calendar;
import java.util.Date;

import com.vn.ael.constants.AELConst;

public class StringUtil {
	private static String ZERO = "0";
	private static int LENGTH_OF_MONTH_STRING = 2;
	public static int LENGTH_OF_COUNTER = 4;
	private static int YEAR_OFFSET = 1900;
	
	/**
	 * Get current year in String
	 * @return
	 */
	public static String getCurrentYear(){
		return String.valueOf(Calendar.getInstance().getTime().getYear() + YEAR_OFFSET);
	}
	
	/**
	 * Get 2 chars of current month
	 * @return
	 */
	public static String getCurrentMonth(){
		String month = String.valueOf(Calendar.getInstance().getTime().getMonth()+1);
		return addZero(month, LENGTH_OF_MONTH_STRING);
	}
	
	/**
	 * Add 0 at the beginning of input
	 * @param input
	 * @param len
	 * @return
	 */
	public static String addZero(String input, int len){
		if (input == null){
			input=AELConst.EMPTY_STRING;
		}
		
		while (input.length() < len){
			input=ZERO+input;
		}
		return input;
	}
	
	
	/**
	 * Return string for date, dd/mm/yyyy
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date){
		if (date != null){
			return addZero(String.valueOf(date.getDate()),2) +AELConst.SEPARATOR+addZero(String.valueOf(date.getMonth()+1),2)
					+AELConst.SEPARATOR+String.valueOf(date.getYear()+YEAR_OFFSET);
		}
		return AELConst.EMPTY_STRING;
	}

	/**
	 * 
	 * @param counting
	 * @param lENGTH_OF_COUNTER2
	 * @return
	 */
	public static String addZero(int counting, int len) {
		return addZero(String.valueOf(counting), len);
	}
	
}
