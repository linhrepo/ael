
package com.vn.ael.webapp.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.CollectMoneyStatusType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.enums.StatusType;
import com.vn.ael.persistence.entity.Docsgeneral;
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
import com.vn.ael.webapp.dto.Search;

@Controller
public class AccountingCollectionMoneyController extends BaseFormController {

	private CustomerManager customerManager;
	
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
	}
	
    private DocsgeneralManager docsgeneralManager;

    @Autowired
    public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
        this.docsgeneralManager = docsgeneralManager;
    }
       
    @Autowired
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

	public AccountingCollectionMoneyController() {
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
	}
    
	@Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_MANAGE_DEBIT)
    public ModelAndView manageDebitRequest() throws Exception {
        Model model = new ExtendedModelMap();
        //model.addAttribute(docsgeneralManager.findByDoAccounting(true));
        Search searchAccFee = new Search();
        model.addAttribute("search", searchAccFee);
        model.addAttribute("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        model.addAttribute("enumStatus", CollectMoneyStatusType.values());
        model.addAttribute("jobList", docsgeneralManager.getAllJob());
        searchAccFee.setTypeOfDocs((long) ServicesType.DVTQ.getValue());
        List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchAccFee);
        model.addAttribute(docsgenerals);
        return new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT, model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.POST, value = URLReference.DEBIT_SEARCH)
	public ModelAndView searchDebit(Search searchDebit)
			throws Exception {
		// Model model = new ExtendedModelMap();
		ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT);
		
		List<Docsgeneral> docsgenerals = docsgeneralManager.searchDebit(searchDebit);
		mav.addObject(docsgenerals);
        mav.addObject("typeOfDocs", ServicesType.getUsageMapSearchTruck());
        mav.addObject("enumStatus", CollectMoneyStatusType.values());
        mav.addObject("jobList", docsgeneralManager.getAllJob());
		return mav;
	}
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.DEBIT_APPROVE_COLLECT)
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
        model.addAttribute("enumStatus", CollectMoneyStatusType.values());
        model.addAttribute("jobList", docsgeneralManager.getAllJob());
        model.addAttribute("approve", approve);
        return new ModelAndView(URLReference.ACCOUNTING_MANAGE_DEBIT, model.asMap());
    }
}
