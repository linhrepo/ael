
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
    
	/*private CustomerManager customerManager;
	
	private ExfeetableManager exfeetableManager;
	
	private NhathauManager nhathauManager;
	
	private PackageinfoManager packageinfoManager;
	
	private TruckingserviceManager truckingserviceManager;
	
	private RefundManager refundManager;
	
	private RefundDetailManager refundDetailManager;
	
	private AdvanceDetailManager advanceDetailManager;
	
	private AdvanceFormManager advanceFormManager;
	
	@Autowired
	public void setExfeetableManager(ExfeetableManager exfeetableManager){
		this.exfeetableManager = exfeetableManager;
	}
	
	@Autowired
	public void setAdvanceDetailManager(AdvanceDetailManager advanceDetailManager){
		this.advanceDetailManager = advanceDetailManager;
	}
	
	@Autowired
	public void setRefundDetailManager(RefundDetailManager refundDetailManager){
		this.refundDetailManager = refundDetailManager;
	}
	
	@Autowired
	public void setCustomerManager(CustomerManager customerManager){
		this.customerManager = customerManager;
	}*/
	
    
       
   /* @Autowired
	public void setPackageinfoManager(PackageinfoManager packageinfoManager) {
		this.packageinfoManager = packageinfoManager;
	}

    @Autowired
	public void setTruckingserviceManager(
			TruckingserviceManager truckingserviceManager) {
		this.truckingserviceManager = truckingserviceManager;
	}
        
    @Autowired
	public void setRefundManager(RefundManager refundManager) {
		this.refundManager = refundManager;
	}

	public AccountingCollectMoneyController() {
        setCancelView("redirect:"+URLReference.HOME_PAGE);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_CUSTOM_LIST);
    }
        
    @Autowired
    public void setNhathauManager(NhathauManager nhathauManager) {
		this.nhathauManager = nhathauManager;
	}

    @Autowired
	public void setAdvanceFormManager(AdvanceFormManager advanceFormManager) {
		this.advanceFormManager = advanceFormManager;
	}*/
    
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
        mav.addObject("jobList", docsgeneralManager.getAllJob());
        
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
        mav.addObject("jobList", docsgeneralManager.getAllJob());
        
		List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchDebit);
		mav.addObject(docsgenerals);

		return mav;
	}
    
    /*@RequestMapping(method = RequestMethod.GET, value=URLReference.DEBIT_APPROVE_COLLECT)
    public ModelAndView approvalMoneyDetailRequest(@RequestParam(value="id") Long id, @RequestParam(value="approve") String approve) throws Exception {
    	Docsgeneral docsgeneral = docsgeneralManager.get(id);
    	if(docsgeneral != null){
    		if(docsgeneral.getIsCollectMoney() == null || !docsgeneral.getIsCollectMoney()){
    			docsgeneral.setIsCollectMoney(true);
    		}
    	}
    	this.docsgeneralManager.save(docsgeneral);
    	Model model = new ExtendedModelMap();
        model.addAttribute(docsgeneralManager.findByDoAccountingAndIsCollectMoney(true, false));
        Search searchAccFee = new Search();
        model.addAttribute("search", searchAccFee);
        model.addAttribute("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        model.addAttribute("enumStatus", CollectMoneyStatusType.getLabelsMap());
        model.addAttribute("jobList", docsgeneralManager.getAllJob());
        model.addAttribute("approve", approve);
        return new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT, model.asMap());
    }*/
    
    /*@RequestMapping(method = RequestMethod.GET, value=URLReference.DEBIT_LOAD_JOB_MONEY)
    public @ResponseBody String loadMoneyForFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Long id = Long.parseLong(request.getParameter("id"));
    	
    	Docsgeneral docsgeneral = docsgeneralManager.get(id);
    	//this.docsgeneralManager.updateTongChiPhi(docsgeneral);
		//this.docsgeneralManager.updateChiHo(docsgeneral,true);
    	//obj.put("phiAEL", docsgeneral.getTongChiPhi());
        //obj.put("chiho", docsgeneral.getChiho());
    	Model model = new ExtendedModelMap();
       // model.addAttribute(docsgeneralManager.findByDoAccountingAndIsCollectMoney(true, false));
   
        JSONObject obj = new JSONObject();
        return obj.toString(); 
    }*/
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_COLLECT_MONEY_AJAX)
    public @ResponseBody String phieuThuPrint(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 

        return ControllerUtil.createJsonObject(VoucherType.NTTK, this.accountingMoneyBookManager);
    }
    
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_SAVE_MONEYBOOK_AJAX)
    public @ResponseBody String updateMoneyBook(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 
    	
    	Refund refund = (Refund) request.getSession().getAttribute(SessionNames.REFUND_PRINT_PHIEU_THU);
    	String validate = ControllerUtil.validateForm(request, VoucherType.PHIEUTHU, this.accountingMoneyBookManager);
    	if (validate.length() == 0) {
	        if (refund != null) {
		        MoneyBook mb = ControllerUtil.createMoneyBook(
		        		refund,
		    			VoucherType.NTTK,
		    			BookType.BANKBOOK,
		    			request);
			    
		    	MoneyBook moneyBook = this.accountingMoneyBookManager.insertMoneyBook(mb);
		        this.accountingMoneyBookManager.updateBasicAdvance(refund, moneyBook);
		        refund.setMoneyBook(moneyBook);
		        return "ok";
	        }
    	} else {
    		return validate;
    	}
        return "error";
    }
}
