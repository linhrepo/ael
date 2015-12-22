
package com.vn.ael.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.BookType;
import com.vn.ael.constants.URLReference;
import com.vn.ael.constants.VoucherType;
import com.vn.ael.enums.CollectMoneyStatusType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.webapp.dto.AccountingContractorPaymentCondition;
import com.vn.ael.webapp.util.ControllerUtil;

@Controller
public class AccountingContractorPaymentController extends BaseFormController {
	private AccountingMoneyBookManager accountingMoneyBookManager;

	@Autowired
	public void setAccountingMoneyBookManager(AccountingMoneyBookManager accountingMoneyBookManager){
		this.accountingMoneyBookManager = accountingMoneyBookManager;
		
	}

	private DocsgeneralManager docsgeneralManager;

    @Autowired
    public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
        this.docsgeneralManager = docsgeneralManager;
    }
    
    private NhathauManager nhathauManager = null;
	 
    @Autowired
    public void setnhathauManager(final NhathauManager nhathauManager) {
        this.nhathauManager = nhathauManager;
    }
	@Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_MANAGE_CONTRACTOR_PAYMENT)
    public ModelAndView manageDebitRequest(HttpServletRequest request) throws Exception {
    	ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_MANAGE_CONTRACTOR_PAYMENT);
        //model.addAttribute(docsgeneralManager.findByDoAccounting(true));
    	AccountingContractorPaymentCondition searchAccFee = new AccountingContractorPaymentCondition();
        
        mav.addObject("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        mav.addObject("enumStatus", CollectMoneyStatusType.getLabelsMap());
        mav.addObject("nhathauList", nhathauManager.getAll()); 
        searchAccFee.setTypeOfDocs((long) ServicesType.DVTQ.getValue());
        mav.addObject("accountingContractorPaymentCondition", searchAccFee);
        //request.getSession().setAttribute(SessionNames.FORM_SEARCH_ACCOUNTING_COLLECT_MONEY, searchAccFee);
           
        //List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchAccFee);
       // mav.addObject(docsgenerals);
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = URLReference.ACCOUNT_SEARCH_TRUCKING_FEE)
	public ModelAndView searchDebit(HttpServletRequest request, AccountingContractorPaymentCondition searchDebit, BindingResult errors)
			throws Exception {
		
		ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT);
		
		mav.addObject("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        mav.addObject("enumStatus", CollectMoneyStatusType.getLabelsMap());
        mav.addObject("nhathauList", nhathauManager.getAll()); 
		//List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchDebit);
		//mav.addObject(docsgenerals);

		return mav;
	}
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_PAY_MONEY_AJAX)
    public @ResponseBody String loadCollectMoney(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 
    	try {
    		String moneyType = request.getParameter("moneyType");
    		String result = "";
    		if (moneyType.equals("0")) {
    			result = ControllerUtil.createJsonObject(VoucherType.PHIEUTHU, this.accountingMoneyBookManager);
    		} else {
    			result = ControllerUtil.createJsonObject(VoucherType.NTTK, this.accountingMoneyBookManager);
    		}
    		
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_SAVE_TRUCKING_MONEYBOOK_AJAX)
    public @ResponseBody String updateMoneyBook(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 
    	String moneyType = request.getParameter("moneyType");
		VoucherType voucherType;
		BookType bookType;
		if (moneyType.equals("0")) {
			voucherType = VoucherType.PHIEUTHU;
			bookType = BookType.CASHBOOK;
		} else {
			voucherType = VoucherType.NTTK;
			bookType = BookType.BANKBOOK;
		}
		

    	String validate = ControllerUtil.validateForm(request, voucherType, this.accountingMoneyBookManager);
    	try {
	    	if (validate.length() == 0) {
		        String data = request.getParameter("jobNo");
		        //System.out.println(data);
		        //8_1, 9_1, 9_0,
		        String[] listMoney = data.split(",");
		        Map<Long, Integer> statusMap = new HashMap<Long, Integer>();
		        for (int i = 0; i < listMoney.length; i++) {
		        	String[] el = listMoney[i].split("_");
		        	Long jobNo = Long.parseLong(el[0]);
		        	Integer status = Integer.parseInt(el[1]);
		        	if (statusMap.get(jobNo) == null) {
		        		statusMap.put(jobNo, status);//0: ael, 1: chiHo
		        	} else {
		        		statusMap.put(jobNo, 2);//both
		        	}   	
		        }
		        
		        try {
		        	//Long idLong = Long.parseLong(id);
			        MoneyBook mb = ControllerUtil.createMoneyBook(
			        		null,
			    			voucherType,
			    			bookType,
			    			request);
			    	MoneyBook moneyBook = this.accountingMoneyBookManager.insertMoneyBook(mb);
			    	
			    	//statusReturn = this.docsgeneralManager.updateCollectMoneyStatus(idLong, feeTypeInt);
			    	this.docsgeneralManager.updateCollectMoneyStatus(statusMap);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		        return "ok";
		        
	    	} else {
	    		return validate;
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return "error";
    }
}
