
package com.vn.ael.webapp.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.NhathauType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.manager.CustomerManager;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.ExfeetableManager;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.persistence.manager.OfferItemManager;
import com.vn.ael.webapp.dto.AccountingTransCondition;

@Controller
public class AccountingController extends BaseFormController {

	private CustomerManager customerManager;
	
	private OfferItemManager offerItemManager;
	
	private ExfeetableManager exfeetableManager;
	
	private NhathauManager nhathauManager;
	
	@Autowired
	public void setExfeetableManager(ExfeetableManager exfeetableManager){
		this.exfeetableManager = exfeetableManager;
	}
	
	@Autowired
	public void setCustomerManager(CustomerManager customerManager){
		this.customerManager = customerManager;
	}
	
	@Autowired
	public void setOfferItemManager(OfferItemManager offerItemManager){
		this.offerItemManager = offerItemManager;
	}
	
    private DocsgeneralManager docsgeneralManager;

    @Autowired
    public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
        this.docsgeneralManager = docsgeneralManager;
    }

    public AccountingController() {
        setCancelView("redirect:"+URLReference.HOME_PAGE);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_CUSTOM_LIST);
    }
        
    @Autowired
    public void setNhathauManager(NhathauManager nhathauManager) {
		this.nhathauManager = nhathauManager;
	}

	@Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_CUSTOM_LIST)
    public ModelAndView handleRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute(docsgeneralManager.findByDoAccountingAndTypeOfDocs(true,ServicesType.DVTQ));
        return new ModelAndView(URLReference.ACCOUNTING_CUSTOM_LIST, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_TRANSPORT_LIST)
    public ModelAndView handleTrabsRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute("customers", customerManager.getAll());
        model.addAttribute("conditions",new AccountingTransCondition());
        return new ModelAndView(URLReference.ACCOUNTING_TRANSPORT_LIST, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_EXHIBITION_LIST)
    public ModelAndView handleExhRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute(docsgeneralManager.findByDoAccountingAndTypeOfDocs(true,ServicesType.EXHS));
        return new ModelAndView(URLReference.ACCOUNTING_EXHIBITION_LIST, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_FEE_LIST)
    public ModelAndView handleFeeRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute(docsgeneralManager.findByDoAccounting(true));
        return new ModelAndView(URLReference.ACCOUNTING_FEE_LIST, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_FEE_LIST_ADMIN)
    public ModelAndView handleFeeAdminRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute(docsgeneralManager.findByDoAccounting(true));
        return new ModelAndView(URLReference.ACCOUNTING_FEE_LIST_ADMIN, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_FEE_LIST_DETAIL)
    public @ResponseBody List<Exfeetable> handleFeeDetailRequest(@RequestParam(value="docId") Long id) throws Exception {
    	return this.exfeetableManager.findByDocsgeneral(id);
    }
    
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_FEE_CHANGE_APPROVAL)
    public @ResponseBody String approvalFeeDetailRequest(@RequestParam(value="id") Long id) throws Exception {
    	Exfeetable exfee = this.exfeetableManager.get(id);
    	if (exfee == null){
    		return AELConst.AJAX_ERROR;
    	}
    	if (exfee.getApproved() == null || !exfee.getApproved()){
    		exfee.setApproved(true);
    		exfee.setDateChange(Calendar.getInstance().getTime());
    	}
    	else if(exfee.getCheckByAdmin() == null || !exfee.getCheckByAdmin()){
    		exfee.setApproved(false);
    	}
    	this.exfeetableManager.save(exfee);
    	return AELConst.AJAX_SUCCESS;
    }
    
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_FEE_CHANGE_APPROVAL_ADMIN)
    public @ResponseBody String approvalAdminFeeDetailRequest(@RequestParam(value="id") Long id) throws Exception {
    	Exfeetable exfee = this.exfeetableManager.get(id);
    	if (exfee == null){
    		return AELConst.AJAX_ERROR;
    	}
    	if ((exfee.getCheckByAdmin() == null || !exfee.getCheckByAdmin()) && exfee.getApproved() != null && exfee.getApproved()){
    		exfee.setCheckByAdmin(true);
    		exfee.setDateChange(Calendar.getInstance().getTime());
    	}
    	else{
    		exfee.setCheckByAdmin(false);
    	}
    	this.exfeetableManager.save(exfee);
    	return AELConst.AJAX_SUCCESS;
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_NHATHAU_LIST)
    public ModelAndView handleNhathauRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute("nhathauList", nhathauManager.getAll());
        model.addAttribute("conditions",new AccountingTransCondition());
        return new ModelAndView(URLReference.ACCOUNTING_NHATHAU_LIST, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_VANTAI_LIST)
    public ModelAndView handleVantaiRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute("vantaiList", ServicesType.getUsageMapVT());
        model.addAttribute("conditions",new AccountingTransCondition());
        return new ModelAndView(URLReference.ACCOUNTING_VANTAI_LIST, model.asMap());
    }
}




