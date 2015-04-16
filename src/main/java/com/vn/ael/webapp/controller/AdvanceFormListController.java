package com.vn.ael.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.enums.StatusType;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.webapp.dto.DocsSelection;
import com.vn.ael.webapp.dto.Search;

@Controller
public class AdvanceFormListController extends BaseFormController {

	private AdvanceFormManager advanceFormManager;
	
	@Autowired
	public void setAdvanceFormManager(AdvanceFormManager advanceFormManager){
		this.advanceFormManager = advanceFormManager;
		
	}
	
	private RefundManager refundManager;
	
	@Autowired
	public void setRefundManager(RefundManager refundManager){
		this.refundManager = refundManager;
		
	}
	
    public AdvanceFormListController() {
        setCancelView("redirect:"+URLReference.HOME_PAGE);
        setSuccessView("redirect:"+URLReference.OFFERPRICE_LIST);
    }

    @Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ADVANCE_REFUNDS)
    public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
    	Model model = new ExtendedModelMap();
    	model.addAttribute(advanceFormManager.findByEmpoyee(getUserManager().getLoggedUser(request)));
    	model.addAttribute(refundManager.findByEmpoyee(getUserManager().getLoggedUser(request)));
    	Search searchRefund = new Search();
        model.addAttribute("search", searchRefund);
    	DocsSelection docsSelection = 
        		configurationManager.loadSelectionForDocsPage(true);
    	model.addAttribute("docsSelection", docsSelection);
    	model.addAttribute("enumStatus", StatusType.values());
        return new ModelAndView(URLReference.ADVANCE_REFUNDS,model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ADVANCE_FORM_ACC)
    public ModelAndView handleRequestAcc(HttpServletRequest request) throws Exception {
    	Model model = new ExtendedModelMap();
    	model.addAttribute(advanceFormManager.getAll());
    	model.addAttribute(refundManager.getAll());
        return new ModelAndView(URLReference.ADVANCE_FORM_ACC,model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.POST, value = URLReference.ADVANCE_SEARCH)
	public ModelAndView searchAdvanceForm(Search searchAdvanceForm)
			throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_REFUNDS);
		List<Advanceform> advanceforms = advanceFormManager.searchAdvanceForm(searchAdvanceForm);
		mav.addObject(advanceforms);
		
		//selection
		DocsSelection docsSelection = 
        		configurationManager.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		return mav;
	}
    
}

