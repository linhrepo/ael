package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.persistence.manager.ContsealManager;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.formatter.FormatterUtil;
import com.vn.ael.webapp.util.EntityUtil;

@Controller
@RequestMapping(URLReference.ACCOUNTING_NHATHAU+"*")
public class AccountingNhathauController extends BaseFormController{
	
	private NhathauManager nhathauManager = null;
	
	private TruckingserviceManager truckingserviceManager;
	
	private ExfeetableRepository exfeetableRepository;
	
	private ContsealManager contsealManager;

    public AccountingNhathauController() {
    	setCancelView("redirect:"+URLReference.ACCOUNTING_NHATHAU_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_NHATHAU_LIST);
	}
        
    @Autowired
	public void setNhathauManager(NhathauManager nhathauManager) {
		this.nhathauManager = nhathauManager;
	}    
        
    @Autowired
	public void setTruckingserviceManager(
			TruckingserviceManager truckingserviceManager) {
		this.truckingserviceManager = truckingserviceManager;
	}
        
    @Autowired
	public void setExfeetableRepository(ExfeetableRepository exfeetableRepository) {
		this.exfeetableRepository = exfeetableRepository;
	}

    @Autowired
	public void setContsealManager(ContsealManager contsealManager) {
		this.contsealManager = contsealManager;
	}

	@RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request, AccountingTransCondition accountingTransCondition)
    throws Exception {
        ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_NHATHAU);        
        //Set up command
        List<Truckingdetail> truckdetails = new ArrayList<>();
        List<Exfeetable> exfeetables = new ArrayList<>();
        
        List<Truckingdetail> truckingdetails = this.truckingserviceManager.searchNhathau(accountingTransCondition);
        if(truckingdetails != null && !truckingdetails.isEmpty()){        	
        	for (Truckingdetail truckingdetail : truckingdetails) {
        		BigDecimal total = BigDecimal.ZERO;
        		//set phuthu into fee
        		if(truckingdetail.getPhuthu() != null){
        			total = total.add(truckingdetail.getPhuthu());
        		}
        		exfeetables = exfeetableRepository.findByTruckingdetail(truckingdetail);
        		if(exfeetables != null && !exfeetables.isEmpty()){
        			for (Exfeetable exfeetable : exfeetables) {
        				if(exfeetable.getTotal() != null){
        					total = total.add(exfeetable.getTotal());
        				}
					}
        		}
        		truckingdetail.setTotal(total);
        		truckingdetail.setExfeetables(exfeetables);
        		//set no cont
        		Truckingservice truckingservice = truckingdetail.getTruckingservice();
				if(truckingservice != null){
					Docsgeneral docsgeneral = truckingservice.getDocsgeneral();
					if(docsgeneral != null){
						List<Contseal> contseals = contsealManager.findByDocsgeneral(docsgeneral);
						docsgeneral.setContseals(contseals);
						EntityUtil.countCont(contseals, docsgeneral);
						truckingservice.setDocsgeneral(docsgeneral);
						truckingdetail.setTruckingservice(truckingservice);
					}
				}
        		truckdetails.add(truckingdetail);
			}
        }
        
        AccountingTrans accountingTrans = new AccountingTrans();
        accountingTrans.setNhathau(nhathauManager.get(accountingTransCondition.getNhathauId()));
        accountingTrans.setCondition(accountingTransCondition);
        accountingTrans.setTruckingdetails(truckdetails);
        
        mav.addObject("accountingNhathau", accountingTrans);
        mav.addObject("selections", configurationManager.createSelections(ConfigurationType.DOCS_SHIPPING_LINE, ConfigurationType.MASTER_FEE,ConfigurationType.FEE_NAMES));
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
        truckingserviceManager.saveTruckingdetail(accountingTrans.getTruckingdetails());
        
        String key = "accountingnhathau.updated";
        saveMessage(request, getText(key, locale));
        
        String startDate = FormatterUtil.formatDate(accountingTrans.getCondition().getStartDate());        
        String endDate = FormatterUtil.formatDate(accountingTrans.getCondition().getEndDate());
        startDate = startDate.replace("/", "%2F");
        endDate = endDate.replace("/", "%2F");
        success = "redirect:"+URLReference.ACCOUNTING_NHATHAU+"?nhathauId=" + accountingTrans.getCondition().getNhathauId() + 
        		"&customerId=" + accountingTrans.getCondition().getCustomerId() +
        		"&job=" + accountingTrans.getCondition().getJob() + 
        		"&startDate=" + startDate +
        		"&endDate=" + endDate;
//        nhathauId=46&customerId=&job=&startDate=01%2F04%2F2015&endDate=18%2F04%2F2015
        success = success.replace("null", "");
        return success;
    }
}
