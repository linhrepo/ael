package com.vn.ael.webapp.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.vn.ael.constants.FormatterPattern;

public class FormatterUtil {
	
	/**
	 * Return a String which represents the BigDecimal
	 * @param bigDecimal
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal bigDecimal){
		DecimalFormat decimalFormat = new DecimalFormat(FormatterPattern.NUMBER_HAS_EXTENSION);
		return decimalFormat.format(bigDecimal);
	}
	
	/**
	 * Return a String which represents the Integer
	 * @param bigDecimal
	 * @return
	 */
	public static String formatInteger(Integer integer){
		DecimalFormat decimalFormat = new DecimalFormat(FormatterPattern.NUMBER);
		return decimalFormat.format(integer);
	}

}
