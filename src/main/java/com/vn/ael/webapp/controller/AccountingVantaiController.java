package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.enums.FeeType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.persistence.manager.ContsealManager;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.dto.VantaiDTO;
import com.vn.ael.webapp.util.EntityUtil;

@Controller
@RequestMapping(URLReference.ACCOUNTING_VANTAI+"*")
public class AccountingVantaiController extends BaseFormController {
	
	private TruckingdetailRepository truckingdetailRepository;
	
	private ContsealManager contsealManager;
	
	private ExfeetableRepository exfeetableRepository;
	
	@Autowired
	public void setTruckingdetailRepository(
			TruckingdetailRepository truckingdetailRepository) {
		this.truckingdetailRepository = truckingdetailRepository;
	}
	
	@Autowired
	public void setContsealManager(ContsealManager contsealManager) {
		this.contsealManager = contsealManager;
	}

	@Autowired
	public void setExfeetableRepository(ExfeetableRepository exfeetableRepository) {
		this.exfeetableRepository = exfeetableRepository;
	}

	public AccountingVantaiController() {
    	setCancelView("redirect:"+URLReference.ACCOUNTING_VANTAI_LIST);
        setSuccessView("redirect:"+URLReference.ACCOUNTING_VANTAI_LIST);
	}
	
	@RequestMapping(method = RequestMethod.GET)
    protected ModelAndView showForm(HttpServletRequest request)
    throws Exception {
        ModelAndView mav = new ModelAndView(URLReference.ACCOUNTING_VANTAI);
        AccountingTransCondition accountingTransCondition = new AccountingTransCondition();
        
        //load condition
        String transId = request.getParameter("transId");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        Set<Customer> customers = new HashSet<>();
        List<VantaiDTO> vantaiDTOs = new ArrayList<>();
        Map<Long, List<Truckingdetail>> mapVantai = new HashMap<>();
        accountingTransCondition.setTransId(new Long(transId));
        accountingTransCondition.setMonth(Integer.parseInt(month));
        accountingTransCondition.setYear(Integer.parseInt(year));
        
        
        //Set up command
        List<Truckingdetail> truckingdetails = this.truckingdetailRepository.findAllByConditionVantai(ServicesType.fromValue(Integer.parseInt(transId)), accountingTransCondition.getMonth(), accountingTransCondition.getYear());
        List<Exfeetable> exfeetables = new ArrayList<>();
        
        if(truckingdetails != null && !truckingdetails.isEmpty()){        	
        	for (Truckingdetail truckingdetail : truckingdetails) {
        		List<Truckingdetail> truckdetails = new ArrayList<>();
        		//load fee
        		BigDecimal total = BigDecimal.ZERO;
        		exfeetables = exfeetableRepository.findByTruckingdetail(truckingdetail);
        		if(exfeetables != null && !exfeetables.isEmpty()){
        			for (Exfeetable exfeetable : exfeetables) {
        				if((exfeetable.getName().getId() == FeeType.NANG.getId() || exfeetable.getName().getId() == FeeType.HA.getId()) && exfeetable.getTotal() != null){
        					total = total.add(exfeetable.getTotal());
        				}
					}
        		}
        		truckingdetail.setTotal(total);
        		truckingdetail.setExfeetables(exfeetables);
				Truckingservice truckingservice = truckingdetail.getTruckingservice();
				if(truckingservice != null){
					Docsgeneral docsgeneral = truckingservice.getDocsgeneral();
					if(docsgeneral != null){
						List<Contseal> contseals = contsealManager.findByDocsgeneral(docsgeneral);
						docsgeneral.setContseals(contseals);
						EntityUtil.countCont(contseals, docsgeneral);
						truckingservice.setDocsgeneral(docsgeneral);
						truckingdetail.setTruckingservice(truckingservice);
						Customer customer = docsgeneral.getCustomer();
						truckdetails.add(truckingdetail);
						//put data into map depend on customer
						if(customer != null){
							if(mapVantai.get(customer.getId()) == null){
								mapVantai.put(customer.getId(), truckdetails);
							}
							else{
								mapVantai.get(customer.getId()).addAll(truckdetails);
							}
						}
					}
				}
			}
        }
        AccountingTrans accountingTrans = new AccountingTrans();
        accountingTrans.setCondition(accountingTransCondition);
        accountingTrans.setMap(mapVantai);        
        
        mav.addObject("accountingVantai", accountingTrans);
        mav.addObject("type", ServicesType.getUsageMapVT().get(Integer.parseInt(transId)));        
        mav.addObject("selections", configurationManager.createSelections(ConfigurationType.DOCS_SHIPPING_LINE, ConfigurationType.MASTER_FEE,ConfigurationType.FEE_NAMES));
        return mav;
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit()
    throws Exception {
		return getCancelView();
    }
}
