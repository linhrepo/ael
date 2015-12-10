
package com.vn.ael.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.BookType;
import com.vn.ael.constants.SessionNames;
import com.vn.ael.constants.URLReference;
import com.vn.ael.constants.VoucherType;
import com.vn.ael.enums.CollectMoneyStatusType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;
import com.vn.ael.persistence.manager.AdvanceDetailManager;
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.manager.CustomerManager;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.ExfeetableManager;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.persistence.manager.PackageinfoManager;
import com.vn.ael.persistence.manager.RefundDetailManager;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.persistence.service.PermissionCheckingService;
import com.vn.ael.webapp.dto.AccountingCollectMoneyCondition;
import com.vn.ael.webapp.dto.Search;
import com.vn.ael.webapp.util.ControllerUtil;

@Controller
public class AccountingCollectMoneyController extends BaseFormController {
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
    
    private CustomerManager customerManager = null;
	 
    @Autowired
    public void setCustomerManager(final CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
	@Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_MANAGE_DEBIT)
    public ModelAndView manageDebitRequest(HttpServletRequest request) throws Exception {
    	ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT);
        //model.addAttribute(docsgeneralManager.findByDoAccounting(true));
    	AccountingCollectMoneyCondition searchAccFee = new AccountingCollectMoneyCondition();
        
        mav.addObject("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        mav.addObject("enumStatus", CollectMoneyStatusType.getLabelsMap());
        //mav.addObject("jobList", docsgeneralManager.getAllJob());
        mav.addObject("customers", customerManager.getAll()); 
        searchAccFee.setTypeOfDocs((long) ServicesType.DVTQ.getValue());
        mav.addObject("accountingCollectMoneyCondition", searchAccFee);
        //request.getSession().setAttribute(SessionNames.FORM_SEARCH_ACCOUNTING_COLLECT_MONEY, searchAccFee);
           
        List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchAccFee);
        mav.addObject(docsgenerals);
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = URLReference.ACCOUNT_SEARCH_DEBIT)
	public ModelAndView searchDebit(HttpServletRequest request, AccountingCollectMoneyCondition searchDebit, BindingResult errors)
			throws Exception {
		
		ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT);
		
		mav.addObject("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        mav.addObject("enumStatus", CollectMoneyStatusType.getLabelsMap());
        //mav.addObject("jobList", docsgeneralManager.getAllJob());
        mav.addObject("customers", customerManager.getAll()); 
		List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchDebit);
		mav.addObject(docsgenerals);

		return mav;
	}
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_COLLECT_MONEY_AJAX)
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
    
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_SAVE_MONEYBOOK_AJAX)
    public @ResponseBody String updateMoneyBook(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 
    	
    	String validate = ControllerUtil.validateForm(request, VoucherType.NTTK, this.accountingMoneyBookManager);
    	try {
	    	if (validate.length() == 0) {
		        String id = request.getParameter("jobId");
		        String feeType = request.getParameter("feeType");
		        int statusReturn = 0;
		        try {
		        	Long idLong = Long.parseLong(id);
		        	Integer feeTypeInt = Integer.parseInt(feeType);
			        MoneyBook mb = ControllerUtil.createMoneyBook(
			        		null,
			    			VoucherType.NTTK,
			    			BookType.BANKBOOK,
			    			request);
			    	MoneyBook moneyBook = this.accountingMoneyBookManager.insertMoneyBook(mb);
			    	statusReturn = this.docsgeneralManager.updateCollectMoneyStatus(idLong, feeTypeInt);
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		        return String.valueOf(statusReturn);
		        
	    	} else {
	    		return validate;
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return "error";
    }
}
