package com.vn.ael.webapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.BookType;
import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.BasicAdvance;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;

import net.sf.json.JSONObject;

public class ControllerUtil {
	public static MoneyBook createMoneyBook(
			BasicAdvance form,
			VoucherType voucherType,
			BookType bookType,
			AccountingMoneyBookManager mbMa, 
			HttpServletRequest request)
    	    throws Exception {    	
		
    	String voucherNoParam = request.getParameter("voucherNo");
    	String voucherNoStr = voucherNoParam.substring(2);
    	Long voucherNo = Long.parseLong(voucherNoStr);
    	
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
	    moneyBook.setTypeOfBook(voucherType.getValue());//cashbook
	    moneyBook.setTypeOfVoucher(bookType.getValue());
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
