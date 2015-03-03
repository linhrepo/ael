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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.manager.OfferPriceManager;
import com.vn.ael.persistence.manager.PackageinfoManager;

@Controller
public class PackageinfoListController extends BaseFormController {

    private PackageinfoManager packageinfoManager;

    @Autowired
    public void setPackageinfoManager(final PackageinfoManager packageinfoManager) {
        this.packageinfoManager = packageinfoManager;
    }

    public PackageinfoListController() {
        setCancelView("redirect:"+URLReference.HOME_PAGE);
        setSuccessView("redirect:"+URLReference.PACKAGEINFO_LIST);
    }

    @Override
    @InitBinder
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
        super.initBinder(request, binder);
        binder.setDisallowedFields("password", "confirmPassword");
    }
    
    @RequestMapping(method = RequestMethod.GET, value=URLReference.PACKAGEINFO_LIST)
    public ModelAndView handleRequest() throws Exception {
        Model model = new ExtendedModelMap();
        model.addAttribute(packageinfoManager.getAll());
        return new ModelAndView(URLReference.PACKAGEINFO_LIST, model.asMap());
    }
}

