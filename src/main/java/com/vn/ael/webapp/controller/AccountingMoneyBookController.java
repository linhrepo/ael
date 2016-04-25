package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import com.vn.ael.constants.URLReference;
import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Bank;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.manager.AccountingMoneyBookManager;
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.manager.BankManager;
import com.vn.ael.persistence.manager.DocsAccountingManager;
import com.vn.ael.persistence.manager.DocsgeneralManager;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.persistence.manager.TruckingdetailManager;
import com.vn.ael.persistence.service.PermissionCheckingService;
import com.vn.ael.webapp.dto.AccountingMoneyBookCondition;


@Controller
public class AccountingMoneyBookController extends BaseFormController {
	protected final transient Log log = LogFactory.getLog(getClass());
	private AccountingMoneyBookManager mbManager;

	private final String ACCOUNTING_MONEY_BOOK = "/admin/accounting/moneyBook";
	
	private final String ACCOUNTING_MONEY_CASH_BOOK_SEARCH = "/admin/accounting/searchMoneyCashBook";
	
	private final String ACCOUNTING_MONEY_BANK_BOOK_SEARCH = "/admin/accounting/searchMoneyBankBook";
	
	private final String ACCOUNTING_MONEY_BOOK_SAVE_FIRST_BALANCE = "/admin/accounting/moneybook/saveFirstBalance";
	
	private final String SEARCH_MODEL = "accountingMoneyBookCondition";
	@Autowired
	public void setRefundManager(AccountingMoneyBookManager mbManager) {
		this.mbManager = mbManager;
	}
	
	private DocsAccountingManager docsAccountingManager;

    @Autowired
    public void setDocsAccountingManager(final DocsAccountingManager docsAccountingManager) {
        this.docsAccountingManager = docsAccountingManager;
    }
    
    private AdvanceFormManager advanceFormManager;

    @Autowired
    public void setAdvanceFormManager(final AdvanceFormManager advanceFormManager) {
        this.advanceFormManager = advanceFormManager;
    }
    
    private RefundManager refundManager;

    @Autowired
    public void setRefundManager(final RefundManager refundManager) {
        this.refundManager = refundManager;
    }
    
    private DocsgeneralManager docsgeneralManager;

    @Autowired
    public void setDocsgeneralManager(final DocsgeneralManager docsgeneralManager) {
        this.docsgeneralManager = docsgeneralManager;
    }
    
    private TruckingdetailManager truckingdetailManager;

    @Autowired
    public void setTruckingdetailManager(final TruckingdetailManager truckingdetailManager) {
        this.truckingdetailManager = truckingdetailManager;
    }
    
    private BankManager bankManager;

    @Autowired
    public void setBankManager(final BankManager bankManager) {
        this.bankManager = bankManager;
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
		ModelAndView mav = this.searchByDateCashBook(searchBook.getStartDate(), searchBook.getEndDate());
		
		MoneyBook cashBookFirst = mbManager.checkFirstBalance(0);
		MoneyBook bankBookFirst = mbManager.checkFirstBalance(1);
		
		if (cashBookFirst != null) {
			mav.addObject("firstCashBalance", cashBookFirst);
		}
		if (bankBookFirst != null) {
			mav.addObject("firstBankBalance", bankBookFirst);
		}
		
		List<Bank> banks = bankManager.getAll();
		mav.addObject("banks", banks);
		
		mav.addObject(SEARCH_MODEL, searchBook);
		
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = ACCOUNTING_MONEY_CASH_BOOK_SEARCH)
	public ModelAndView handlePostRequestForCashBook(
			HttpServletRequest request,
			@ModelAttribute(SEARCH_MODEL) AccountingMoneyBookCondition searchBook, 
			BindingResult errors)
			throws Exception {
		
		Date startDate = searchBook.getStartDate();
		Date endDate = searchBook.getEndDate();
		Calendar c = Calendar.getInstance(); 
		c.setTime(endDate); 
		c.add(Calendar.DATE, 1);
		endDate = c.getTime();
		
		return this.searchByDateCashBook(startDate, endDate);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = ACCOUNTING_MONEY_BANK_BOOK_SEARCH)
	public ModelAndView handlePostRequestForBankBook(
			HttpServletRequest request,
			@ModelAttribute(SEARCH_MODEL) AccountingMoneyBookCondition searchBook, 
			BindingResult errors)
			throws Exception {
		
		Date startDate = searchBook.getStartDate();
		Date endDate = searchBook.getEndDate();
		Calendar c = Calendar.getInstance(); 
		c.setTime(endDate); 
		c.add(Calendar.DATE, 1);
		endDate = c.getTime();
		
		Long bankId = searchBook.getBankId();
		return this.searchByDateBankBook(startDate, endDate, bankId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = ACCOUNTING_MONEY_BOOK_SAVE_FIRST_BALANCE)
	public @ResponseBody String saveFirstBalance(
			HttpServletRequest request)
			throws Exception {
		return "success";
	}
	
	private ModelAndView searchByDateCashBook(Date startDate, Date endDate) {
		ModelAndView mav = new ModelAndView(ACCOUNTING_MONEY_BOOK);
		List<MoneyBook> books = mbManager.findByDuration(startDate, endDate);
		List<MoneyBook> cashbooks = new ArrayList<MoneyBook>();
		for (MoneyBook mb : books) {
			if (mb.getTypeOfBook() == 0) {
				cashbooks.add(mb);
			}
		}

		List<Bank> banks = bankManager.getAll();
		mav.addObject("banks", banks);
		mav.addObject("cashbooks", cashbooks);
		mav.addObject("flag", 0);
		return mav;
	}
	
	private ModelAndView searchByDateBankBook(Date startDate, Date endDate, Long bankId) {
		ModelAndView mav = new ModelAndView(ACCOUNTING_MONEY_BOOK);
		List<MoneyBook> books = mbManager.findByDurationAndBank(startDate, endDate, bankId);
		List<MoneyBook> bankbooks = new ArrayList<MoneyBook>();
		for (MoneyBook mb : books) {
			if (mb.getTypeOfBook() == 1) {
				bankbooks.add(mb);
			}
		}
		
		List<Bank> banks = bankManager.getAll();
		mav.addObject("banks", banks);
		mav.addObject("bankbooks", bankbooks);
		mav.addObject("flag", 1);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_UPDATE_MONEYBOOK_AJAX)
    public @ResponseBody String updateMoneyBook(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 
		String idStr = request.getParameter("id");
		String date = request.getParameter("date");
		String reason = request.getParameter("reason");
		String bankId = request.getParameter("bankId");
		
		boolean voucherNoProblem = false;
		
		MoneyBook mb = null;
    	try {
    		Long id = Long.parseLong(idStr);
    		mb = mbManager.get(id);
    		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		mb.setDate(df.parse(date));
    		mb.setDescription(reason);
    		System.out.println("Bankid: " + bankId);
    		if (StringUtils.isNotEmpty(bankId)) {
    			Long bId = Long.parseLong(bankId);
    			Bank bank = bankManager.get(bId);
    			mb.setBank(bank);
    		}
    		mbManager.save(mb);
    	} catch (Exception e) {
    		e.printStackTrace();
    		voucherNoProblem = true;
		}
    	
    	return voucherNoProblem ? "notok" : "ok";
    	
    }
	
	@RequestMapping(method = RequestMethod.POST, value=URLReference.ACCOUNTING_DELETE_MONEYBOOK_AJAX)
    public @ResponseBody String deleteMoneyBook(HttpServletRequest request, HttpServletResponse response)
    	    throws Exception {    	 
		String idStr = request.getParameter("id");
		Long id = null;

    	boolean voucherNoProblem = false;
    	try {
    		id = Long.parseLong(idStr);
    		MoneyBook mb = mbManager.get(id);
    		List<Advanceform> list = advanceFormManager.findByMoneyBook(id);
			if (list != null && list.size() > 0) {
				for (Advanceform ba : list) {
					Advanceform af = advanceFormManager.get(ba.getId());
					af.setMoneyBook(null);
					af.setDoPrint(false);
					advanceFormManager.save(af);
				}
			} else {
				List<Refund> listRefund = refundManager.findByMoneyBook(id);
				if (listRefund != null && listRefund.size() > 0) {
					for (Refund ba : listRefund) {
						Refund rf = refundManager.get(ba.getId());
						rf.setMoneyBook(null);
						rf.setDoPrint(false);
						refundManager.save(rf);
					}
				} else if (mb.getRefNos() != null && !mb.getRefNos().contains("/")) {
					// update money back
					String[] moneyGroup = mb.getRefNos().split(",");
					for (String groupAmount : moneyGroup) {
						// docsId_feeType_amount
						if (!StringUtils.isEmpty(groupAmount)) {
							String[] amount = groupAmount.split("_");
							String jobOrTruckId = amount[0];
							String feeType = amount[1];
							BigDecimal amt = new BigDecimal(amount[2]);

							if (mb.getTypeOfVoucher() == VoucherType.PHIEUTHU
									|| mb.getTypeOfVoucher() == VoucherType.NTTK) {// accounting
								Docsgeneral docs = docsgeneralManager.get(Long.parseLong(jobOrTruckId));
								if ("0".equals(feeType)) {
									docsAccountingManager.revertDocsAccounting(docs, amt, null);
								} else if ("1".equals(feeType)) {
									docsAccountingManager.revertDocsAccounting(docs, null, amt);
								}
							} else if (mb.getTypeOfVoucher() == VoucherType.PHIEUCHI
									|| mb.getTypeOfVoucher() == VoucherType.UNC) {
								Truckingdetail truck = truckingdetailManager.get(Long.parseLong(jobOrTruckId));
								if ("0".equals(feeType)) {
									docsAccountingManager.revertTruckAccounting(truck, amt, null);
								} else if ("1".equals(feeType)) {
									docsAccountingManager.revertTruckAccounting(truck, null, amt);
								}
							}
						}
					}
				} else {
					voucherNoProblem = false;
				}
			}
    		
    		mbManager.remove(mb);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		voucherNoProblem = true;
		}
    	return voucherNoProblem? "notok" : "ok";
    }
}
