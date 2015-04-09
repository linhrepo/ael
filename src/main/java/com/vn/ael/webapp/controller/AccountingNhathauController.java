package com.vn.ael.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.config.PersistenceConfiguration.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;

@Controller
@RequestMapping(URLReference.ACCOUNTING_NHATHAU+"*")
public class AccountingNhathauController extends BaseFormController{
	
	private NhathauManager nhathauManager = null;
	
	private TruckingserviceManager truckingserviceManager;
	
	private TruckingdetailRepository truckingdetailRepository;

    public AccountingNhathauController() {
    	setCancelView("redirect:"+URLReference.ACCOUNTING_NHATHAU_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_NHATHAU_LIST);
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
        
    @Autowired
	public void setTruckingserviceManager(
			TruckingserviceManager truckingserviceManager) {
		this.truckingserviceManager = truckingserviceManager;
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
        for (Truckingdetail truckingdetail : truckingdetails) {
			for (Exfeetable ewew : truckingdetail.getExfeetables()) {
				System.out.println(ewew.getMasterFee().getId());
			}
		}
        mav.addObject("selections", configurationManager.createSelections(ConfigurationType.DOCS_SHIPPING_LINE, ConfigurationType.MASTER_FEE,ConfigurationType.FEE_NAMES));
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
//        startDate=04%2F08%2F2015&endDate=04%2F09%2F2015
        truckingserviceManager.saveTruckingdetail(accountingTrans.getTruckingdetails());
        
        String key = "accountingnhathau.updated";
        saveMessage(request, getText(key, locale));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String startDate = dateFormat.format(accountingTrans.getCondition().getStartDate());        
        String endDate = dateFormat.format(accountingTrans.getCondition().getEndDate());
        startDate = startDate.replace("/", "%2F");
        endDate = endDate.replace("/", "%2F");
        success = "redirect:"+URLReference.ACCOUNTING_NHATHAU+"?nhathauId=" + accountingTrans.getCondition().getNhathauId()+
        		"&startDate="+ startDate+
        		"&endDate="+endDate;
 
        return success;
    }
}
