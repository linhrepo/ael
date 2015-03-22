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
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.service.PermissionCheckingService;
import com.vn.ael.webapp.dto.DocsSelection;

@Controller
public class AdvanceFormController extends BaseFormController {

	private PermissionCheckingService permissionCheckingService;
	
	@Autowired
	public void setPermissionChecking(PermissionCheckingService permissionCheckingService){
		this.permissionCheckingService = permissionCheckingService;
		
	}
	
	private AdvanceFormManager advanceFormManager;
	
	@Autowired
	public void setAdvanceFormManager(AdvanceFormManager advanceFormManager){
		this.advanceFormManager = advanceFormManager;
		
	}
	
    public AdvanceFormController() {
        setCancelView("redirect:"+URLReference.ADVANCE_LIST);
        setSuccessView("redirect:"+URLReference.ADVANCE_LIST);
    }
 
    /**
     * Based on request parameter, load offers
     * @param request
     * @return
     */
    private Advanceform loadAdvancesByRequest(HttpServletRequest request){
    	String id = request.getParameter("id");
    	User customer = getUserManager().getLoggedUser(request);
    	Advanceform advanceform = null;
        if (!StringUtils.isBlank(id)) {
        	advanceform = advanceFormManager.get(new Long(id));
        	if (advanceform == null || !(advanceform.getEmployee().getId().compareTo(customer.getId()) ==0  
        			|| permissionCheckingService.couldViewUserAdvance(customer))){
        		return null;
        	}
        }else{
        		 //load user
        		 if (customer != null){
        			 advanceform = new Advanceform();
        			 advanceform.setEmployee(customer);
        		 }
        }
        advanceFormManager.updateChilds(advanceform);
        return advanceform;
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ADVANCE_FORM)
    public ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        ModelAndView mav = new ModelAndView(URLReference.ADVANCE_FORM);
        Advanceform advanceform = this.loadAdvancesByRequest(request);
        if (advanceform == null){
        	Locale locale = request.getLocale();
        	saveMessage(request, getText("offerPrice.error.wrongCustomer", locale));
        }
        mav.addObject("advanceform", advanceform);
        //create selection
        DocsSelection docsSelection = 
        		configurationManager.loadSelectionForDocsPage(true);
        mav.addObject("docsSelection", docsSelection);
        return mav;
    }
 
    @RequestMapping(method = RequestMethod.POST, value=URLReference.ADVANCE_FORM)
    public String onSubmit(Advanceform advanceform, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(advanceform, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return URLReference.ADVANCE_FORM;
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (advanceform.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	success = getSuccessView();
            advanceFormManager.remove(advanceform.getId());
            saveMessage(request, getText("advanceform.deleted", locale));
        } else {
        	getEntityService().checkUpdateInfo(advanceform, isNew, request);
        	advanceform = advanceFormManager.saveWholePackage(advanceform);
            String key = (isNew) ? "advanceform.added" : "advanceform.updated";
            saveMessage(request, getText(key, locale));
            success = "redirect:"+URLReference.ADVANCE_FORM+"?id=" + advanceform.getId();
        }
 
        return success;
    }
    
}

