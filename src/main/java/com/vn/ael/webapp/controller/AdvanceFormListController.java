package com.vn.ael.webapp.controller;

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
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.persistence.manager.UserManager;

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
        return new ModelAndView(URLReference.ADVANCE_REFUNDS,model.asMap());
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.ADVANCE_FORM_ACC)
    public ModelAndView handleRequestAcc(HttpServletRequest request) throws Exception {
    	Model model = new ExtendedModelMap();
    	model.addAttribute(advanceFormManager.getAll());
    	model.addAttribute(refundManager.getAll());
        return new ModelAndView(URLReference.ADVANCE_FORM_ACC,model.asMap());
    }
    
}

