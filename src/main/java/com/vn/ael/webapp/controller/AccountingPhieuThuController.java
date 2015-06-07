package com.vn.ael.webapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.ReportTeamplates;
import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.StatusType;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.persistence.service.PermissionCheckingService;
import com.vn.ael.webapp.dto.DocsSelection;
import com.vn.ael.webapp.dto.Search;
import com.vn.ael.webapp.util.ReportUtil;

@Controller
public class AccountingPhieuThuController extends BaseFormController {
	private RefundManager refundManager;

	@Autowired
	public void setRefundManager(RefundManager refundManager) {
		this.refundManager = refundManager;

	}

	private PermissionCheckingService permissionCheckingService;

	@Autowired
	public void setPermissionChecking(
			PermissionCheckingService permissionCheckingService) {
		this.permissionCheckingService = permissionCheckingService;

	}

	/**
	 * 
	 */
	public AccountingPhieuThuController() {
		setCancelView("redirect:" + URLReference.ACCOUNTING_PHIEUTHU_LIST);
		setSuccessView("redirect:" + URLReference.ACCOUNTING_PHIEUTHU_LIST);
	}

	@RequestMapping(method = RequestMethod.GET, value = URLReference.ACCOUNTING_PHIEUTHU_LIST)
	public ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		Model model = new ExtendedModelMap();
		model.addAttribute(refundManager.findAllPhieuThu());
		Search searchRefund = new Search();
		model.addAttribute("search", searchRefund);
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		model.addAttribute("docsSelection", docsSelection);
		model.addAttribute("enumStatus", StatusType.values());
		return new ModelAndView(URLReference.ACCOUNTING_PHIEUTHU_LIST, model.asMap());
	}

	private Refund loadPhieuThuByRequest(HttpServletRequest request) {
		String id = request.getParameter("id");
		User customer = getUserManager().getLoggedUser(request);
		Refund refund = null;
		if (!StringUtils.isBlank(id)) {
			refund = refundManager.get(new Long(id));
			if (refund == null
					|| !(refund.getEmployee().getId()
							.compareTo(customer.getId()) == 0 || permissionCheckingService
							.couldViewUserAdvance(customer))) {
				return null;
			}
		} else {
			// load user
			if (customer != null) {
				refund = new Refund();
				refund.setEmployee(customer);
			}
		}
		refundManager.updateChilds(refund);
		return refund;
	}

	@RequestMapping(method = RequestMethod.GET, value = URLReference.ACCOUNTING_PHIEUTHU)
	public ModelAndView showForm(HttpServletRequest request, @RequestParam(value="isAdmin", required= false) Boolean isAdmin) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_PHIEUTHU);
		Refund refund = this.loadPhieuThuByRequest(request);
		if (refund == null) {
			Locale locale = request.getLocale();
			saveMessage(request,
					getText("offerPrice.error.wrongCustomer", locale));
		}
		if (refund != null && refund.getId() == null){
			refund.setIsRAdmin(isAdmin);
		}
		mav.addObject("refund", refund);
		// create selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = URLReference.ACCOUNTING_PHIEUTHU_SEARCH)
	public ModelAndView searchRefund(Search searchRefund,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_PHIEUTHU_LIST);

		List<Refund> refunds = refundManager.searchPhieuThu(searchRefund);
		mav.addObject(refunds);
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		return mav;
	}
	
	 @RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_PHIEUTHU)
	    public String onSubmit(Refund refund, BindingResult errors, HttpServletRequest request,
	                           HttpServletResponse response)
	    throws Exception {
	        if (request.getParameter("cancel") != null) {
	            return getCancelView();
	        }
	 
	        if (validator != null) { // validator is null during testing
	            validator.validate(refund, errors);
	 
	            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
	                return URLReference.ACCOUNTING_PHIEUTHU_LIST;
	            }
	        }
	 
	        log.debug("entering 'onSubmit' method...");
	 
	        boolean isNew = (refund.getId() == null);
	        String success = getSuccessView();
	        Locale locale = request.getLocale();
	 
	        if (request.getParameter("delete") != null) {
	        	success = getSuccessView();
	            refundManager.remove(refund.getId());
	            saveMessage(request, getText("refund.deleted", locale));
	        } else {
	        	getEntityService().checkUpdateInfo(refund, isNew, request);
	        	refund.setIsPhieuThu(Boolean.TRUE);
	        	refund = refundManager.saveWholePackage(refund);
	            String key = (isNew) ? "refund.added" : "refund.updated";
	            saveMessage(request, getText(key, locale));
	            success = "redirect:"+URLReference.ACCOUNTING_PHIEUTHU+"?id=" + refund.getId();
	        }
	 
	        return success;
	    }
	 
	 @RequestMapping(method = RequestMethod.GET, value=URLReference.ACCOUNTING_PHIEUTHU_DOWNLOAD)
	    public void phieuThuDownload(HttpServletRequest request,  HttpServletResponse response)
	    	    throws Exception {    	 
	    	        Refund refundForm = this.loadPhieuThuByRequest(request);
	    	        if (refundForm != null){
	    	        	ReportUtil.dispatchReport(response, ReportTeamplates.PHIEU_THU_ITEMS,ReportTeamplates.PHIEU_THU_ITEMS_TEMPLATE, ReportUtil.prepareDataForPhieuThu(refundForm));
	    	        }
	    	    }
}
