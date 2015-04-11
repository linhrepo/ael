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
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;

@Controller
@RequestMapping(URLReference.ACCOUNTING_VANTAI+"*")
public class AccountingVantaiController extends BaseFormController {
	
	private TruckingdetailRepository truckingdetailRepository;
	
	@Autowired
	public void setTruckingdetailRepository(
			TruckingdetailRepository truckingdetailRepository) {
		this.truckingdetailRepository = truckingdetailRepository;
	}

	public AccountingVantaiController() {
    	setCancelView("redirect:"+URLReference.ACCOUNTING_VANTAI_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_VANTAI_LIST);
	}
	
	@RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_VANTAI);
        AccountingTransCondition accountingTransCondition = new AccountingTransCondition();
        
        //load condition
        String transId = request.getParameter("transId");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        accountingTransCondition.setTransId(new Long(transId));
        accountingTransCondition.setMonth(Integer.parseInt(month));
        accountingTransCondition.setYear(Integer.parseInt(year));
        
        
        //Set up command
//        List<Truckingdetail> truckingdetails = this.truckingdetailRepository.findAllByConditionVantai(accountingTransCondition.getTransId(), accountingTransCondition.getMonth(), accountingTransCondition.getYear());
        List<Truckingdetail> truckingdetails = this.truckingdetailRepository.findAllByConditionVantai();
        AccountingTrans accountingTrans = new AccountingTrans();
        accountingTrans.setCondition(accountingTransCondition);
        accountingTrans.setTruckingdetails(truckingdetails);
        
        mav.addObject("accountingVantai", accountingTrans);
        mav.addObject("selections", configurationManager.createSelections(ConfigurationType.DOCS_SHIPPING_LINE, ConfigurationType.MASTER_FEE,ConfigurationType.FEE_NAMES));
//        mav.addObject("sales", offerpriceManager.findByCustomerAndTypeOfServiceAndIsValid(accountingTrans.getCustomer(), ServicesType.DVVT,true));
        return mav;
    }
	
//	@RequestMapping(method = RequestMethod.POST)
//    public String onSubmit(AccountingTrans accountingTrans, BindingResult errors, HttpServletRequest request,
//                           HttpServletResponse response)
//    throws Exception {
//        if (request.getParameter("cancel") != null) {
//            return getCancelView();
//        }
// 
//        if (validator != null) { // validator is null during testing
//            validator.validate(accountingTrans, errors);
// 
//            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
//                return URLReference.ACCOUNTING_NHATHAU;
//            }
//        }
// 
//        log.debug("entering 'onSubmit' method...");
// 
//        String success = getSuccessView();
//        Locale locale = request.getLocale();
////        startDate=04%2F08%2F2015&endDate=04%2F09%2F2015
//        truckingserviceManager.saveTruckingdetail(accountingTrans.getTruckingdetails());
//        
//        String key = "accountingnhathau.updated";
//        saveMessage(request, getText(key, locale));
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        String startDate = dateFormat.format(accountingTrans.getCondition().getStartDate());        
//        String endDate = dateFormat.format(accountingTrans.getCondition().getEndDate());
//        startDate = startDate.replace("/", "%2F");
//        endDate = endDate.replace("/", "%2F");
//        success = "redirect:"+URLReference.ACCOUNTING_NHATHAU+"?nhathauId=" + accountingTrans.getCondition().getNhathauId()+
//        		"&startDate="+ startDate+
//        		"&endDate="+endDate;
// 
//        return success;
//    }
}
