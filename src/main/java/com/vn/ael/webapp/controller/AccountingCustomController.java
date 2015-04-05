package com.vn.ael.webapp.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.ReportTeamplates;
import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.Accountingcus;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.persistence.manager.AccountingcusManager;
import com.vn.ael.persistence.manager.CustomerManager;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.ExfeetableManager;
import com.vn.ael.persistence.manager.OfferPriceManager;
import com.vn.ael.webapp.util.EntityUtil;
import com.vn.ael.webapp.util.ReportUtil;

@Controller
/*@RequestMapping(URLReference.ACCOUNTING_CUSTOM+"*")*/
public class AccountingCustomController extends BaseFormController {

	private ExfeetableManager exfeetableManager = null;
	@Autowired
    public void setExfeetableManager(final ExfeetableManager exfeetableManager) {
        this.exfeetableManager = exfeetableManager;
    }
    
	private DocsgeneralManager docsgeneralManager = null;
	 
    @Autowired
    public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
        this.docsgeneralManager = docsgeneralManager;
    }
    
	private AccountingcusManager accountingcusManager = null;
	 
    @Autowired
    public void setAccountingcusManager(final AccountingcusManager accountingcusManager) {
        this.accountingcusManager = accountingcusManager;
    }
    
    public AccountingCustomController() {
        setCancelView("redirect:"+URLReference.ACCOUNTING_CUSTOM_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_CUSTOM_LIST);
    }
 
    @RequestMapping(method = RequestMethod.GET, value = URLReference.ACCOUNTING_CUSTOM)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("docId");
        ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_CUSTOM);
        Accountingcus accountingcus = null;
        if (!StringUtils.isBlank(id)) {
        	//load docsgeneral
        	Docsgeneral docsgeneral = docsgeneralManager.find(id);
        	accountingcus = accountingcusManager.createFromDocsgeneral(docsgeneral);
        	this.docsgeneralManager.updateContTruck(accountingcus.getDocsgeneral());
        }
        if (accountingcus == null){
        	Locale locale = request.getLocale();
        	saveMessage(request, getText("accountingcus.error.wrongDocsgenral", locale));
        }
        mav.addObject("accountingcus", accountingcus);
        //create selection
        mav.addObject("selections", configurationManager.createSelections(ConfigurationType.DEBIT_GENERAL_FEE, ConfigurationType.DEBIT_FEE_DESCRIPTION));
       
        return mav;
    }
 
    @RequestMapping(method = RequestMethod.POST,value = URLReference.ACCOUNTING_CUSTOM)
    public String onSubmit(Accountingcus accountingcus, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(accountingcus, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return URLReference.ACCOUNTING_CUSTOM;
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (accountingcus.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	success = "redirect:"+URLReference.ACCOUNTING_CUSTOM;
            accountingcusManager.remove(accountingcus.getId());
            saveMessage(request, getText("accountingcus.deleted", locale));
        } else {
        	getEntityService().checkUpdateInfo(accountingcus, isNew, request);
        	exfeetableManager.updateVats(accountingcus.getExtendfeeaccs());
        	accountingcusManager.saveWholePackage(accountingcus);
            String key = (isNew) ? "accountingcus.added" : "accountingcus.updated";
            saveMessage(request, getText(key, locale));
            success = "redirect:"+URLReference.ACCOUNTING_CUSTOM+"?docId=" + accountingcus.getDocsgeneral().getId();
        }
 
        return success;
    }
    
//    @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_CUSTOM_EXCEL)
//    public void getReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	 try {
//             response.setHeader("Content-disposition", "attachment; filename=test.xls");
//             HSSFWorkbook hssfWorkbook = exportEventsToCSV();
//             hssfWorkbook.write(response.getOutputStream());
//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//    }
//    
//    public HSSFWorkbook exportEventsToCSV() {
//        try {
//
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
//            HSSFCellStyle cellStyle = workbook.createCellStyle();
//            return workbook;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @RequestMapping(method=RequestMethod.GET, value =URLReference.AJAX_REPORT_DEBIT_NOTE)
    public void doDownload(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	String id = request.getParameter("docId");
        Accountingcus accountingcus = null;
        if (!StringUtils.isBlank(id)) {
        	//load docsgeneral
        	Docsgeneral docsgeneral = docsgeneralManager.find(id);
        	accountingcus = accountingcusManager.createFromDocsgeneral(docsgeneral);
        	this.docsgeneralManager.updateContTruck(accountingcus.getDocsgeneral());
        }
        if (accountingcus!=null) {
        	ReportUtil.dispatchReport(response, ReportTeamplates.ACCOUNTING_CUSTOM_ITEMS, ReportTeamplates.ACCOUNTING_CUSTOM_ITEMS_TEMPLATE, ReportUtil.prepareDataForOffers(accountingcus));
		}
    }
}

