package com.vn.ael.webapp.util;

import java.util.Calendar;

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
		String month = String.valueOf(Calendar.getInstance().getTime().getMonth());
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
			input="";
		}
		
		while (input.length() < len){
			input=ZERO+input;
		}
		return input;
	}
	
	

}
