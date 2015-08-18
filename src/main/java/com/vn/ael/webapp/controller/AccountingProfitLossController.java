package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.ReportTeamplates;
import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.ExfeetableManager;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.webapp.dto.AccountingProfitLossExport;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.dto.DocsSelection;
import com.vn.ael.webapp.util.ConvertUtil;
import com.vn.ael.webapp.util.ReportUtil;

@Controller
public class AccountingProfitLossController extends BaseFormController{

	private TruckingserviceManager truckingserviceManager;

	private DocsgeneralManager docsgeneralManager;
	
	private ExfeetableManager exfeetableManager;
	
	@Autowired
	public void setExfeetableManager(ExfeetableManager exfeetableManager) {
		this.exfeetableManager = exfeetableManager;
	}

	@Autowired
	public void setDocsgeneralManager(
			DocsgeneralManager docsgeneralManager) {
		this.docsgeneralManager = docsgeneralManager;
	}

	@Autowired
	public void setTruckingserviceManager(
			TruckingserviceManager truckingserviceManager) {
		this.truckingserviceManager = truckingserviceManager;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLReference.ACCOUNTING_PROFIT_LOSS)
	protected ModelAndView doSearchProfitLoss(
			@ModelAttribute AccountingTransCondition accountingTransCondition,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_PROFIT_LOSS);
		
		AccountingTrans accountingTrans = this.setUpDataProfitLoss(request,
				accountingTransCondition);
		
		if (accountingTrans != null) {
			Map<String,Object> parameter = this.prepareData(accountingTrans);
			mav.addObject("summary", parameter);
		}
		
		DocsSelection docsSelection = 
        		configurationManager.loadSelectionForDocsPage
        		(
        				ConfigurationType.DOCS_TYPE_OF_CONTAINER,
        				ConfigurationType.TYPE_OF_IMPORT,
        				ConfigurationType.DOCS_SHIPPING_CUSTOM_DEPT
        		);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("typeOfDocs", ServicesType.getUsageMapSearchTruck());
		mav.addObject("jobList", docsgeneralManager.getAllJob());
		mav.addObject("conditions", accountingTransCondition);
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = URLReference.AJAX_REPORT_ACCOUNTING_PROFIT_LOSS)
	protected void doDownloadProfitLoss(
			AccountingTransCondition accountingTransCondition,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AccountingTrans accountingTrans = this.setUpDataProfitLoss(request,
				accountingTransCondition);
		if (accountingTrans != null) {
			Map<String,Object> parameter = ReportUtil.prepareDataForProfitLoss(accountingTrans);
			//calculate dynamic column
			
			ReportUtil.dispatchReport(response,
					ReportTeamplates.ACCOUNTING_PROFITLOSS_ITEMS,
					ReportTeamplates.ACCOUNTING_PROFITLOSS_ITEMS_TEMPLATE,
					parameter,ConvertUtil.generateMergeIndexForProfitLoss(accountingTrans.getTruckingdetails()),ConvertUtil.generateDynamicsMergeIndexForProfitLoss(parameter));
		}
	}
	
	public Map<String, Object> prepareData(
			AccountingTrans accountingTrans) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startDate", accountingTrans.getCondition().getStartDate());
		parameterMap.put("endDate", accountingTrans.getCondition().getEndDate());
		List<AccountingProfitLossExport> profitLossExports = new ArrayList<>();
		Set<String> idHashSet = new HashSet<String>();
		if (accountingTrans.getTruckingdetails() != null && !accountingTrans.getTruckingdetails().isEmpty()){
			for (int i=0; i<accountingTrans.getTruckingdetails().size(); ++i){
				Truckingdetail truckingdetail = accountingTrans.getTruckingdetails().get(i);
				Docsgeneral docsgeneral = truckingdetail.getTruckingservice().getDocsgeneral(); 

				AccountingProfitLossExport profitLossExport = new AccountingProfitLossExport();
				profitLossExport.setJobNo(docsgeneral.getJobNo());
				profitLossExport.setCusName(docsgeneral.getCustomer().getName());
				//chi phi manifest
				profitLossExport.setTongChi(ConvertUtil.getNotNullValue(docsgeneral.getTongChiPhi()));
				//chi phi nha thau
				profitLossExport.setTongThu(ConvertUtil.getNotNullValue(docsgeneral.getTongThu()));
				//thu ho
				profitLossExport.setThuHo(truckingdetail.getTruckingservice().getDocsgeneral().getChiho());
				//debit
				profitLossExport.setDebit(truckingdetail.getTruckingservice().getDocsgeneral().getDebit());
				
				profitLossExport.setProfitLoss(profitLossExport.getThuHo().add(profitLossExport.getDebit()).subtract(profitLossExport.getTongThu().add(profitLossExport.getTongChi())));
				
				String rowId = docsgeneral.getJobNo() + docsgeneral.getCustomer().getId();
				if (!idHashSet.contains(rowId)) {
					profitLossExports.add(profitLossExport);
					idHashSet.add(rowId);
				}
			}
		}
		parameterMap.put("details", profitLossExports);
		return parameterMap;
	}
	
	private AccountingTrans setUpDataProfitLoss(HttpServletRequest request,
			AccountingTransCondition accountingTransCondition) {

		// Set up command
		List<Truckingdetail> truckingdetails = this.truckingserviceManager
				.searchProfitLoss(accountingTransCondition);
		List<Exfeetable> exfeetables = new ArrayList<>();

		if (truckingdetails != null && !truckingdetails.isEmpty()) {
			for (Truckingdetail truckingdetail : truckingdetails) {
				// load fee
				BigDecimal total = BigDecimal.ZERO;
				exfeetables = exfeetableManager
						.findByTruckingdetail(truckingdetail.getId());
				if (exfeetables != null && !exfeetables.isEmpty()) {
					for (Exfeetable exfeetable : exfeetables) {
						total = total.add(ConvertUtil
								.getNotNullValue(exfeetable.getTotal()));
					}
				}
				truckingdetail.setTotal(total);
				truckingdetail.setExfeetables(exfeetables);
				Docsgeneral docsgeneral = truckingdetail.getTruckingservice().getDocsgeneral();
				docsgeneralManager.updateTongChiPhi(docsgeneral);
				this.docsgeneralManager.updateChiHo(docsgeneral,true);
				if (docsgeneral.getTypeOfDocs() != ServicesType.DVVT_INLAND && docsgeneral.getTypeOfDocs()!= ServicesType.DVVT_SEALAND){
					this.docsgeneralManager.updateDebit(docsgeneral);
				}else{
					if (docsgeneral.getDebit() == null){
						docsgeneral.setDebit(BigDecimal.ZERO);
					}
					if (truckingdetail.getTransreportext() != null){
						docsgeneral.setDebit(docsgeneral.getDebit().add(ConvertUtil.getNotNullValue(truckingdetail.getTransreportext().getTotal())));
					}
						
				}
				docsgeneral.setTongThu(ConvertUtil.getNotNullValue(docsgeneral.getTongThu()).add(truckingdetail.getTotal()));
			}
		}
		AccountingTrans accountingTrans = new AccountingTrans();
		accountingTrans.setTruckingdetails(truckingdetails);
		accountingTrans.setCondition(accountingTransCondition);
		return accountingTrans;
	}
}
