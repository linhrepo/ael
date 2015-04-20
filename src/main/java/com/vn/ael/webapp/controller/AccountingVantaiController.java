package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vn.ael.constants.ReportTeamplates;
import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.util.ConvertUtil;
import com.vn.ael.webapp.util.ReportUtil;

@Controller
public class AccountingVantaiController extends BaseFormController {
	
	private TruckingserviceManager truckingserviceManager;
	
	private ExfeetableRepository exfeetableRepository;
	
	@Autowired
	public void setTruckingserviceManager(TruckingserviceManager truckingserviceManager) {
		this.truckingserviceManager = truckingserviceManager;
	}

	@Autowired
	public void setExfeetableRepository(ExfeetableRepository exfeetableRepository) {
		this.exfeetableRepository = exfeetableRepository;
	}

	public AccountingVantaiController() {
    	setCancelView("redirect:"+URLReference.ACCOUNTING_VANTAI_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_VANTAI_LIST);
	}
	
	@RequestMapping(method = RequestMethod.GET, value=URLReference.AJAX_REPORT_ACCOUNTING_KHVT)
    protected void doDownload(AccountingTransCondition accountingTransCondition, HttpServletRequest request,HttpServletResponse response)
    throws Exception {
		AccountingTrans accountingTrans = this.setUpDataKHVT(request, accountingTransCondition);
	       if (accountingTrans!=null) {
	    	   if (accountingTrans.getCondition().getTransId().intValue() == ServicesType.DVVT_SEALAND.getValue()){
	    		   ReportUtil.dispatchReport(response, ReportTeamplates.ACCOUNTING_KHVT_NOIDIA_ITEMS, ReportTeamplates.ACCOUNTING_KHVT_NOIDIA_ITEMS_TEMPLATE, ReportUtil.prepareDataForKHVTNoidia(accountingTrans),ConvertUtil.generateMergeIndexForKHVTNoidia(accountingTrans.getTruckingdetails())); 
	    	   }else{
	    		   ReportUtil.dispatchReport(response, ReportTeamplates.ACCOUNTING_KHVT_ITEMS, ReportTeamplates.ACCOUNTING_KHVT_ITEMS_TEMPLATE, ReportUtil.prepareDataForKHVTNoidia(accountingTrans),ConvertUtil.generateMergeIndexForKHVT(accountingTrans.getTruckingdetails()));
	    	   }
	    	   
	       }      
    }
	
	@RequestMapping(method = RequestMethod.GET, value=URLReference.AJAX_REPORT_ACCOUNTING_KHTQ)
    protected void doDownloadThongquan(AccountingTransCondition accountingTransCondition, HttpServletRequest request,HttpServletResponse response)
    throws Exception {
		AccountingTrans accountingTrans = this.setUpDataKHTQ(request, accountingTransCondition);
	       if (accountingTrans!=null) {
//	    	   if (accountingTrans.getCondition().getTransId().intValue() == ServicesType.DVVT_SEALAND.getValue()){
//	    		   ReportUtil.dispatchReport(response, ReportTeamplates.ACCOUNTING_KHVT_NOIDIA_ITEMS, ReportTeamplates.ACCOUNTING_KHVT_NOIDIA_ITEMS_TEMPLATE, ReportUtil.prepareDataForKHVTNoidia(accountingTrans),ConvertUtil.generateMergeIndexForKHVTNoidia(accountingTrans.getTruckingdetails())); 
//	    	   }else{
//	    		   ReportUtil.dispatchReport(response, ReportTeamplates.ACCOUNTING_KHVT_ITEMS, ReportTeamplates.ACCOUNTING_KHVT_ITEMS_TEMPLATE, ReportUtil.prepareDataForKHVTNoidia(accountingTrans),ConvertUtil.generateMergeIndexForKHVT(accountingTrans.getTruckingdetails()));
//	    	   }
	    	   
	       }      
    }
	
    private AccountingTrans setUpDataKHVT(HttpServletRequest request, AccountingTransCondition accountingTransCondition){
    	 Map<Long, List<Truckingdetail>> mapVantai = new HashMap<>();
         
         //Set up command
         List<Truckingdetail> truckingdetails = this.truckingserviceManager.searchVantai(accountingTransCondition);
         List<Exfeetable> exfeetables = new ArrayList<>();
         
         if(truckingdetails != null && !truckingdetails.isEmpty()){        	
         	for (Truckingdetail truckingdetail : truckingdetails) {
         		List<Truckingdetail> truckdetails = new ArrayList<>();
         		//load fee
         		BigDecimal total = BigDecimal.ZERO;
         		exfeetables = exfeetableRepository.findByTruckingdetail(truckingdetail);
         		if(exfeetables != null && !exfeetables.isEmpty()){
         			for (Exfeetable exfeetable : exfeetables) {
         					total = total.add(ConvertUtil.getNotNullValue(exfeetable.getTotal()));
 					}
         		}
         		truckingdetail.setTotal(total);
         		truckingdetail.setExfeetables(exfeetables);
 			}
         }
         AccountingTrans accountingTrans = new AccountingTrans();
         accountingTrans.setTruckingdetails(truckingdetails);
         accountingTrans.setCondition(accountingTransCondition);
         return accountingTrans;
    }
    
    private AccountingTrans setUpDataKHTQ(HttpServletRequest request, AccountingTransCondition accountingTransCondition){
        
        //Set up command
        List<Truckingdetail> truckingdetails = this.truckingserviceManager.searchPackageInfo(accountingTransCondition);
        List<Exfeetable> exfeetables = new ArrayList<>();
        
        if(truckingdetails != null && !truckingdetails.isEmpty()){        	
        	for (Truckingdetail truckingdetail : truckingdetails) {
        		//load fee
        		BigDecimal total = BigDecimal.ZERO;
        		exfeetables = exfeetableRepository.findByTruckingdetail(truckingdetail);
        		if(exfeetables != null && !exfeetables.isEmpty()){
        			for (Exfeetable exfeetable : exfeetables) {
        					total = total.add(ConvertUtil.getNotNullValue(exfeetable.getTotal()));
					}
        		}
        		truckingdetail.setTotal(total);
        		truckingdetail.setExfeetables(exfeetables);
			}
        }
        AccountingTrans accountingTrans = new AccountingTrans();
        accountingTrans.setTruckingdetails(truckingdetails);
        accountingTrans.setCondition(accountingTransCondition);
        return accountingTrans;
   }
}
