package com.vn.ael.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.persistence.service.PermissionCheckingService;
import com.vn.ael.webapp.dto.DocsSelection;

@Controller
public class RefundFormController extends BaseFormController {

	private PermissionCheckingService permissionCheckingService;
	
	@Autowired
	public void setPermissionChecking(PermissionCheckingService permissionCheckingService){
		this.permissionCheckingService = permissionCheckingService;
		
	}
	
	private RefundManager refundManager;
	
	@Autowired
	public void setRefundManager(RefundManager refundManager){
		this.refundManager = refundManager;
		
	}
	
    public RefundFormController() {
        setCancelView("redirect:"+URLReference.ADVANCE_REFUNDS);
        setSuccessView("redirect:"+URLReference.ADVANCE_REFUNDS);
    }
 
    /**
     * Based on request parameter, load offers
     * @param request
     * @return
     */
    private Refund loadRefundByRequest(HttpServletRequest request){
    	String id = request.getParameter("id");
    	User customer = getUserManager().getLoggedUser(request);
    	Refund refund = null;
        if (!StringUtils.isBlank(id)) {
        	refund = refundManager.get(new Long(id));
        	if (refund == null || !(refund.getEmployee().getId().compareTo(customer.getId()) ==0  
        			|| permissionCheckingService.couldViewUserAdvance(customer))){
        		return null;
        	}
        }else{
        		 //load user
        		 if (customer != null){
        			 refund = new Refund();
        			 refund.setEmployee(customer);
        		 }
        }
        refundManager.updateChilds(refund);
        return refund;
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.REFUND_FORM)
    public ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        ModelAndView mav = new ModelAndView(URLReference.REFUND_FORM);
        Refund refund = this.loadRefundByRequest(request);
        if (refund == null){
        	Locale locale = request.getLocale();
        	saveMessage(request, getText("offerPrice.error.wrongCustomer", locale));
        }
        mav.addObject("refund", refund);
        //create selection
        DocsSelection docsSelection = 
        		configurationManager.loadSelectionForDocsPage(true);
        mav.addObject("docsSelection", docsSelection);
        return mav;
    }
 
    @RequestMapping(method = RequestMethod.POST, value=URLReference.REFUND_FORM)
    public String onSubmit(Refund refund, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(refund, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return URLReference.ADVANCE_FORM;
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
        	refund = refundManager.saveWholePackage(refund);
            String key = (isNew) ? "refund.added" : "refund.updated";
            saveMessage(request, getText(key, locale));
            success = "redirect:"+URLReference.REFUND_FORM+"?id=" + refund.getId();
        }
 
        return success;
    }
    
}
