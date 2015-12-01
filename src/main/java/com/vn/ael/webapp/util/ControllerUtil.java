package com.vn.ael.webapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.BookType;
import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.BasicAdvance;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;

public class ControllerUtil {
	public static String validateForm(HttpServletRequest request, AccountingMoneyBookManager mbMa) {
		StringBuilder result = new StringBuilder();
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String dateStr=  request.getParameter("date");
    	try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			result.append("- Wrong date format \n");
		}*/
		boolean voucherNoProblem = false;
		String voucherNoParam = request.getParameter("voucherNo");
		if (voucherNoParam.length() < 3) {
			voucherNoProblem = true;
		}
    	String voucherNoStr = voucherNoParam.substring(2);

    	try {
    		Integer voucherNo = Integer.parseInt(voucherNoStr);
    		MoneyBook mb = mbMa.getMoneyBookByVoucherNoAndType(voucherNo, VoucherType.PHIEUCHI.getValue());
    		if(mb != null) {
    			voucherNoProblem = true;
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		voucherNoProblem = true;
		}
 
    	if (voucherNoProblem) {
    		result.append("- Sheet no is wrong format or existed \n");
    	}
    	
    	/*String reason = request.getParameter("reason");
    	if (reason == null || reason.trim().length() == 0) {
    		result.append("- Description shouldn't be null \n");
    	}*/
		return result.toString();
    }
	
	public static MoneyBook createMoneyBook(
			BasicAdvance form,
			VoucherType voucherType,
			BookType bookType,
			 
			HttpServletRequest request)
    	    throws Exception {    	
		
    	String voucherNoParam = request.getParameter("voucherNo");
    	String voucherNoStr = voucherNoParam.substring(2);
    	Integer voucherNo = Integer.parseInt(voucherNoStr);
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String dateStr=  request.getParameter("date");
    	Date date = sdf.parse(dateStr);
	 	String reason = request.getParameter("reason");
        //create moneybook
	 	MoneyBook moneyBook = new MoneyBook();
	 	moneyBook.setVoucherNo(voucherNo);
	 	moneyBook.setDate(date);
	 	moneyBook.setDescription(reason);
	 	
	    moneyBook.setRefNos(form.getMultipleRefCode());
	    moneyBook.setTypeOfBook(bookType.getValue());//cashbook
	    moneyBook.setTypeOfVoucher(voucherType.getValue());
	    if (voucherType.getValue() == 0) { 
	    	moneyBook.setPaymentMoney(form.getMultipleTotal());
	    } else {
	    	moneyBook.setReceptMoney(form.getMultipleTotal());
	    }
	    moneyBook.setBalance(null);
	    
	 	return moneyBook;
    }
	
	public static String createJsonObject(VoucherType type, BasicAdvance basicForm, AccountingMoneyBookManager mbMa, HttpServletRequest request)
    	    throws Exception {    	 
		JSONObject obj = new JSONObject();
		
		Integer voucherNo = mbMa.getMaxVoucherNo(type);
		String prefix = (type.getValue() == 0 ? AELConst.VOUCHER_NO_PREFIX_PAYMENT : AELConst.VOUCHER_NO_PREFIX_RECEPT);
		String number = CommonUtil.addZero(String.valueOf(voucherNo + 1), CommonUtil.LENGTH_OF_COUNTER);

		String voucherNoPrint = prefix + number ;
        obj.put("voucherNo", voucherNo);
        obj.put("voucherNoPrint", voucherNoPrint);
        obj.put("refCodes", basicForm.getRefCode());
        obj.put("reason", basicForm.getPayReason());
        obj.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        obj.put("amount", basicForm.getMultipleTotal());
        //System.out.println("payreason: " + advanceform.getPayReason());
        return obj.toString();
    }
	
}
