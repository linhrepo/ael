package com.vn.ael.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.persistence.manager.OfferPriceManager;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;

@Controller
@RequestMapping(URLReference.ACCOUNTING_NHATHAU+"*")
public class AccountingNhathauController extends BaseFormController{

	private DocsgeneralManager docsgeneralManager = null;
	
	private NhathauManager nhathauManager = null;
	
	private OfferPriceManager offerpriceManager;
	
	private TruckingdetailRepository truckingdetailRepository;

    public AccountingNhathauController() {
    	setCancelView("redirect:"+URLReference.ACCOUNTING_NHATHAU_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_NHATHAU_LIST);
	}

	@Autowired
    public void setOfferpriceManager(final OfferPriceManager offerpriceManager) {
        this.offerpriceManager = offerpriceManager;
    }
	 
    @Autowired
    public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
        this.docsgeneralManager = docsgeneralManager;
    }
        
    @Autowired
	public void setNhathauManager(NhathauManager nhathauManager) {
		this.nhathauManager = nhathauManager;
	}
        
    @Autowired
	public void setTruckingdetailRepository(
			TruckingdetailRepository truckingdetailRepository) {
		this.truckingdetailRepository = truckingdetailRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_NHATHAU);
        AccountingTransCondition accountingTransCondition = new AccountingTransCondition();
        
        //load condition
        String nhathauId = request.getParameter("nhathauId");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        accountingTransCondition.setNhathauId(new Long(nhathauId));
        accountingTransCondition.setStartDate(new Date(startDate));
        accountingTransCondition.setEndDate(new Date(endDate));
        
        
        //Set up command
        List<Truckingdetail> truckingdetails = this.truckingdetailRepository.findAllByConditionDateTime(new Date(startDate), new Date(endDate), new Long(nhathauId));
        
        AccountingTrans accountingTrans = new AccountingTrans();
        accountingTrans.setNhathau(nhathauManager.find(nhathauId));
        accountingTrans.setCondition(accountingTransCondition);
        accountingTrans.setTruckingdetails(truckingdetails);
        
        mav.addObject("accountingNhathau", accountingTrans);
//        mav.addObject("sales", offerpriceManager.findByCustomerAndTypeOfServiceAndIsValid(accountingTrans.getCustomer(), ServicesType.DVVT,true));
        return mav;
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(AccountingTrans accountingTrans, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(accountingTrans, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return URLReference.ACCOUNTING_NHATHAU;
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
//        accountingTransService.saveWholePackage(accountingTrans);
        String key = "accountingcus.updated";
        saveMessage(request, getText(key, locale));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = dateFormat.format(accountingTrans.getCondition().getStartDate());
        String endDate = dateFormat.format(accountingTrans.getCondition().getEndDate());
        success = "redirect:"+URLReference.ACCOUNTING_NHATHAU+"?nhathauId=" + accountingTrans.getCondition().getNhathauId()+
        		"&startDate="+ startDate+
        		"&endDate="+endDate;
 
        return success;
    }
}
