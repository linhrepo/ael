package com.vn.ael.webapp.controller;

import java.util.Locale;

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
import com.vn.ael.enums.NhathauType;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.ExhibitionManager;
import com.vn.ael.persistence.manager.NhathauManager;
import com.vn.ael.webapp.dto.DocsSelection;
import com.vn.ael.webapp.util.EntityUtil;

@Controller
@RequestMapping(URLReference.ACCOUNTING_EXHIBITION)
public class AccountingExhibitionController extends BaseFormController {

	private NhathauManager nhathauManager;
	
	@Autowired
	private void setNhauthauManager(NhathauManager nhathauManager){
		this.nhathauManager = nhathauManager;
	}
	
	private ExhibitionManager exhibitionManager = null;
	
	private DocsgeneralManager docsgeneralManager;
	
	@Autowired
	public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
		this.docsgeneralManager = docsgeneralManager;
	}

    @Autowired
    public void setExhibitionManager(final ExhibitionManager exhibitionManager) {
        this.exhibitionManager = exhibitionManager;
    }
    
    public AccountingExhibitionController() {
        setCancelView("redirect:"+URLReference.ACCOUNTING_EXHIBITION_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_EXHIBITION_LIST);
    }
 
    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        Exhibition exhibition = null;
        exhibition = exhibitionManager.findReportObjects(id);
        ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_EXHIBITION);
        mav.addObject("exhibition", exhibition);
        DocsSelection docsSelection = configurationManager.loadSelectionForDocsPage(
        		ConfigurationType.DOCS_TYPE_OF_TRANSPORT,
        		ConfigurationType.EXH_MASTER_FEE_REPORT,
        		ConfigurationType.EXH_FEE_NAMES_REPORT
        		);
        mav.addObject("nhathaus", EntityUtil.fromNhathauList2Map(nhathauManager.findByType(NhathauType.NHATHAU)));
        mav.addObject("docsSelection", docsSelection);
        return mav;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Exhibition exhibition, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
 
        if (validator != null) { // validator is null during testing
            validator.validate(exhibition, errors);
 
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return URLReference.ACCOUNTING_EXHIBITION;
            }
        }
 
        log.debug("entering 'onSubmit' method...");
 
        boolean isNew = (exhibition.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();
 
        if (request.getParameter("delete") != null) {
            exhibitionManager.removeWholeExh(exhibition);
            saveMessage(request, getText("exhibition.deleted", locale));
        } else {
        	exhibitionManager.saveWholeExhReport(exhibition);
            String key = (isNew) ? "exhibition.added" : "exhibition.updated";
            saveMessage(request, getText(key, locale));
 
            if (!isNew) {
                success = "redirect:"+URLReference.ACCOUNTING_EXHIBITION+"?id=" + exhibition.getId();
            }
        }
 
        return success;
    }

}

