package com.vn.ael.webapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.BasicAdvance;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;

public class ControllerUtil {
	public static void voucherUpdateInfo(AccountingMoneyBookManager mbMa, HttpServletRequest request,  HttpServletResponse response)
    	    throws Exception {    	 
		String idParam = request.getParameter("id");
    	Long id = Long.parseLong(idParam);
    	String voucherNoParam = request.getParameter("voucherNo");
    	Long voucherNo = Long.parseLong(voucherNoParam);
        
	 	String reason = request.getParameter("reason");
        //insert payment form to moneybook
	 	System.out.println("voucherNo " + voucherNo + ", reason: " + reason);
	 	mbMa.updateMoneyBook(id, voucherNo, reason);
    }
	
	public static String createJsonObject(VoucherType type, BasicAdvance basicForm, AccountingMoneyBookManager mbMa, HttpServletRequest request,  HttpServletResponse response)
    	    throws Exception {    	 
		JSONObject obj = new JSONObject();
		
		Integer voucherNo = mbMa.getMaxVoucherNo(type);
		String prefix = (type.getValue() == 0 ? AELConst.VOUCHER_NO_PREFIX_PAYMENT : AELConst.VOUCHER_NO_PREFIX_RECEPT);
		String number = CommonUtil.addZero(String.valueOf(voucherNo), CommonUtil.LENGTH_OF_COUNTER);

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
