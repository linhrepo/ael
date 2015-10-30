package com.vn.ael.webapp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;
import com.vn.ael.persistence.service.PermissionCheckingService;
import com.vn.ael.webapp.dto.AccountingMoneyBookCondition;

@Controller
public class AccountingMoneyBookController extends BaseFormController {
	protected final transient Log log = LogFactory.getLog(getClass());
	private AccountingMoneyBookManager mbManager;

	private final String ACCOUNTING_MONEY_BOOK = "/admin/accounting/moneyBook";
	
	private final String ACCOUNTING_MONEY_BOOK_SEARCH = "/admin/accounting/searchMoneyBook";
	
	private final String SEARCH_MODEL = "accountingMoneyBookCondition";
	@Autowired
	public void setRefundManager(AccountingMoneyBookManager mbManager) {
		this.mbManager = mbManager;

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
	public AccountingMoneyBookController() {
		setCancelView("redirect:" + ACCOUNTING_MONEY_BOOK);
		setSuccessView("redirect:" + ACCOUNTING_MONEY_BOOK);
	}

	@RequestMapping(method = RequestMethod.GET, value = ACCOUNTING_MONEY_BOOK)
	public ModelAndView handleGetRequest(HttpServletRequest request)
			throws Exception {

		ModelAndView mav = new ModelAndView(ACCOUNTING_MONEY_BOOK);
		AccountingMoneyBookCondition searchBook = new AccountingMoneyBookCondition();
		mav.addObject(SEARCH_MODEL, searchBook);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		searchBook.setStartDate(cal.getTime());
		searchBook.setEndDate(new Date());
		
		/*DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		model.addAttribute("docsSelection", docsSelection);
		model.addAttribute("enumStatus", StatusType.values());*/
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = ACCOUNTING_MONEY_BOOK_SEARCH)
	public ModelAndView handlePostRequest(
			HttpServletRequest request,
			@ModelAttribute(SEARCH_MODEL) AccountingMoneyBookCondition searchBook, 
			BindingResult errors)
			throws Exception {
		log.info("searching bankbook");
		System.out.println("handle post request" + searchBook.getStartDate() + " " + searchBook.getEndDate());
		ModelAndView mav = new ModelAndView(ACCOUNTING_MONEY_BOOK);
		System.out.println(searchBook.getStartDate()+ " " + searchBook.getEndDate());
		List<MoneyBook> cashbooks = mbManager.findByDuration(searchBook.getStartDate(), searchBook.getEndDate());
		System.out.println("size: " + cashbooks.size());
		//List<MoneyBook> cashbooks = new ArrayList<MoneyBook>();
		List<MoneyBook> bankbooks = new ArrayList<MoneyBook>();
		MoneyBook mb1 = new MoneyBook();
		mb1.setId(1L);
		mb1.setTypeOfBook(1);
		mb1.setTypeOfVoucher(2);
		mb1.setDate(new Date());
		mb1.setDescription("Description");
		//cashbooks.add(mb1);
		bankbooks.add(mb1);
		
		mav.addObject("cashbooks", cashbooks);
		mav.addObject("bankbooks", bankbooks);
		
		return mav;
	}
}
