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
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	private final String ACCOUNTING_MONEY_BOOK_SAVE_FIRST_BALANCE = "/admin/accounting/moneybook/saveFirstBalance";
	
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

		AccountingMoneyBookCondition searchBook = new AccountingMoneyBookCondition();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		searchBook.setStartDate(cal.getTime());
		searchBook.setEndDate(new Date());
		
		/*DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		model.addAttribute("docsSelection", docsSelection);
		model.addAttribute("enumStatus", StatusType.values());*/
		ModelAndView mav = this.searchByDate(searchBook.getStartDate(), searchBook.getEndDate());
		
		MoneyBook cashBookFirst = mbManager.checkFirstBalance(0);
		MoneyBook bankBookFirst = mbManager.checkFirstBalance(1);
		
		if (cashBookFirst != null) {
			mav.addObject("firstCashBalance", cashBookFirst);
		}
		if (bankBookFirst != null) {
			mav.addObject("firstBankBalance", bankBookFirst);
		}
		
		mav.addObject(SEARCH_MODEL, searchBook);
		
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = ACCOUNTING_MONEY_BOOK_SEARCH)
	public ModelAndView handlePostRequest(
			HttpServletRequest request,
			@ModelAttribute(SEARCH_MODEL) AccountingMoneyBookCondition searchBook, 
			BindingResult errors)
			throws Exception {
		System.out.println("searching bankbook");
		
		Date startDate = searchBook.getStartDate();
		Date endDate = searchBook.getEndDate();
		Calendar c = Calendar.getInstance(); 
		c.setTime(endDate); 
		c.add(Calendar.DATE, 1);
		endDate = c.getTime();
		
		return this.searchByDate(startDate, endDate);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = ACCOUNTING_MONEY_BOOK_SAVE_FIRST_BALANCE)
	public @ResponseBody String saveFirstBalance(
			HttpServletRequest request)
			throws Exception {
		return "success";
	}
	
	private ModelAndView searchByDate(Date startDate, Date endDate) {
		ModelAndView mav = new ModelAndView(ACCOUNTING_MONEY_BOOK);
		List<MoneyBook> books = mbManager.findByDuration(startDate, endDate);
		List<MoneyBook> cashbooks = new ArrayList<MoneyBook>();
		List<MoneyBook> bankbooks = new ArrayList<MoneyBook>();
		for (MoneyBook mb : books) {
			if (mb.getTypeOfBook() == 0) {
				cashbooks.add(mb);
			} else {
				bankbooks.add(mb);
			}
		}

		mav.addObject("cashbooks", cashbooks);
		mav.addObject("bankbooks", bankbooks);
		
		return mav;
	}
}
