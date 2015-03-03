package com.vn.ael.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.manager.ConfigurationManager;
import com.vn.ael.persistence.manager.CustomerManager;
import com.vn.ael.persistence.manager.OfferItemManager;
import com.vn.ael.persistence.manager.OfferPriceManager;
import com.vn.ael.webapp.util.ConvertUtil;

@Controller
@RequestMapping(URLReference.OFFERPRICE_FORM+"*")
public class OfferPriceFormController extends BaseFormController {

	private OfferItemManager offerItemManager = null;
	 
    @Autowired
    public void setOfferpriceManager(final OfferItemManager offerItemManager) {
        this.offerItemManager = offerItemManager;
    }
    
	private OfferPriceManager offerpriceManager = null;
	 
    @Autowired
    public void setOfferpriceManager(final OfferPriceManager offerpriceManager) {
        this.offerpriceManager = offerpriceManager;
    }
    
    private CustomerManager customerManager = null;
	 
    @Autowired
    public void setCustomerManager(final CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
 
    public OfferPriceFormController() {
        setCancelView("redirect:"+URLReference.OFFERPRICE_LIST);
        setSuccessView("redirect:"+URLReference.OFFERPRICE_LIST);
    }
 
    
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        ModelAndView mav = new ModelAndView(URLReference.OFFERPRICE_FORM);
        List<OfferItem> offerItems = new ArrayList<>();
        OfferPrice offerPrice = null;
        if (!StringUtils.isBlank(id)) {
        	offerPrice = offerpriceManager.get(new Long(id));
        }else{
        	id = request.getParameter("customerId");
        	 if (!StringUtils.isBlank(id)) {
        		 //load customer
        		 Customer customer = customerManager.find(id);
        		 if (customer != null){
        			 offerPrice = new OfferPrice();
        			 offerPrice.setCustomer(customer);
        		 }
        	 }
        }
        offerpriceManager.updateChilds(offerPrice);
        if (offerPrice == null){
        	Locale locale = request.getLocale();
        	saveMessage(request, getText("offerPrice.error.wrongCustomer", locale));
        }
        mav.addObject("offerPrice", offerPrice);
        //create selection
        mav.addObject("selections", configurationManager.createSelections(ConfigurationType.SALE_SERVICES,
        		ConfigurationType.UNIT, ConfigurationType.CURRENCY));
        mav.addObject("services", ServicesType.getUsageMap());
        return mav;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(OfferPrice offerPrice, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(offerPrice, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return URLReference.OFFERPRICE_FORM;
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (offerPrice.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
        	success = "redirect:"+URLReference.CUSTOMER_FORM+"?id=" + offerPrice.getCustomer().getId();
            offerpriceManager.remove(offerPrice.getId());
            saveMessage(request, getText("offerPrice.deleted", locale));
        } else {
        	getEntityService().checkUpdateInfo(offerPrice, isNew, request);
        	if (!isNew){
        		offerPrice.setCustomer(customerManager.get(offerPrice.getCustomer().getId()));
        	}
        	offerpriceManager.saveWholePackage(offerPrice);
            String key = (isNew) ? "offerPrice.added" : "offerPrice.updated";
            saveMessage(request, getText(key, locale));
            success = "redirect:"+URLReference.OFFERPRICE_FORM+"?id=" + offerPrice.getId();
        }
 
        return success;
    }

}

