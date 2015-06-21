package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.ExfeetableManager;
import com.vn.ael.persistence.manager.TruckingserviceManager;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;
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
