package com.vn.ael.webapp.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.persistence.entity.Attachment;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.manager.AttachmentManager;
import com.vn.ael.persistence.manager.OfferItemManager;
import com.vn.ael.persistence.manager.OfferPriceManager;

@Controller
public class AdvanceFormListController extends BaseFormController {

	private AdvanceFormManager advanceFormManager;
	
	@Autowired
	public void setAdvanceFormManager(AdvanceFormManager advanceFormManager){
		this.advanceFormManager = advanceFormManager;
		
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
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.OFFERPRICE_LIST)
    public ModelAndView handleRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute(advanceFormManager.getAll());
        return new ModelAndView(URLReference.OFFERPRICE_LIST, model.asMap());
    }
    
}

