package com.vn.ael.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vn.ael.constants.ReportTeamplates;
import com.vn.ael.constants.URLReference;
import com.vn.ael.enums.StatusType;
import com.vn.ael.persistence.entity.Advancedetail;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Refunddetail;
import com.vn.ael.persistence.manager.AdvanceFormManager;
import com.vn.ael.persistence.manager.RefundManager;
import com.vn.ael.persistence.manager.UserManager;
import com.vn.ael.webapp.dto.AdvanceSumary;
import com.vn.ael.webapp.dto.DocsSelection;
import com.vn.ael.webapp.dto.Search;
import com.vn.ael.webapp.util.CommonUtil;
import com.vn.ael.webapp.util.ConvertUtil;
import com.vn.ael.webapp.util.EntityUtil;
import com.vn.ael.webapp.util.ReportUtil;

@Controller
public class AdvanceFormListController extends BaseFormController {

	private AdvanceFormManager advanceFormManager;

	@Autowired
	public void setAdvanceFormManager(AdvanceFormManager advanceFormManager) {
		this.advanceFormManager = advanceFormManager;

	}

	private RefundManager refundManager;

	@Autowired
	public void setRefundManager(RefundManager refundManager) {
		this.refundManager = refundManager;

	}

	public AdvanceFormListController() {
		setCancelView("redirect:" + URLReference.HOME_PAGE);
		setSuccessView("redirect:" + URLReference.OFFERPRICE_LIST);
	}

	@Override
	@InitBinder
	protected void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
		binder.setDisallowedFields("password", "confirmPassword");
	}

	@RequestMapping(method = RequestMethod.GET, value = URLReference.ADVANCE_REFUNDS)
	public ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		Model model = new ExtendedModelMap();
		model.addAttribute(advanceFormManager.findByEmpoyee(getUserManager()
				.getLoggedUser(request),false));
		model.addAttribute(refundManager.findByEmpoyee(getUserManager()
				.getLoggedUser(request),false));
		Search searchRefund = new Search();
		model.addAttribute("search", searchRefund);
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		model.addAttribute("docsSelection", docsSelection);
		model.addAttribute("enumStatus", StatusType.values());
		return new ModelAndView(URLReference.ADVANCE_REFUNDS, model.asMap());
	}

	@RequestMapping(method = RequestMethod.GET, value = URLReference.ADVANCE_FORM_ACC)
	public ModelAndView handleRequestAcc(HttpServletRequest request)
			throws Exception {
		Model model = new ExtendedModelMap();
		model.addAttribute(advanceFormManager.getAll());
		model.addAttribute(refundManager.getAll());
		Search searchRefund = new Search();
		model.addAttribute("search", searchRefund);
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		model.addAttribute("docsSelection", docsSelection);
		model.addAttribute("enumStatus", StatusType.values());
		return new ModelAndView(URLReference.ADVANCE_FORM_ACC, model.asMap());
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ADVANCE_SEARCH)
	public ModelAndView searchAdvanceForm(Search searchAdvanceForm,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_REFUNDS);
		// check role
		User user = getUserManager().getLoggedUser(request);
		if (!EntityUtil.isAdminOrAccountRole(user)) {
			searchAdvanceForm.setEmployee(user.getId());
		}
		List<Advanceform> advanceforms = advanceFormManager
				.searchAdvanceForm(searchAdvanceForm);
		mav.addObject(advanceforms);
		mav.addObject(refundManager.findByEmpoyee(getUserManager()
				.getLoggedUser(request),false));
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		mav.addObject("flag", 1);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.REFUND_SEARCH)
	public ModelAndView searchRefund(Search searchRefund,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_REFUNDS);
		// check role
		User user = getUserManager().getLoggedUser(request);
		if (!EntityUtil.isAdminOrAccountRole(user)) {
			searchRefund.setEmployee(user.getId());
		}
		List<Refund> refunds = refundManager.searchRefund(searchRefund);
		mav.addObject(refunds);
		mav.addObject(advanceFormManager.findByEmpoyee(getUserManager()
				.getLoggedUser(request),false));
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		mav.addObject("flag", 2);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ACC_ADVANCE_SEARCH)
	public ModelAndView searchAccAdvanceForm(Search searchAdvanceForm,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_FORM_ACC);
		List<Advanceform> advanceforms = advanceFormManager
				.searchAdvanceForm(searchAdvanceForm);
		mav.addObject(advanceforms);
		mav.addObject(refundManager.getAll());
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		mav.addObject("flag", 1);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ACC_REFUND_SEARCH)
	public ModelAndView searchAccRefund(Search searchRefund,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_FORM_ACC);

		List<Refund> refunds = refundManager.searchRefund(searchRefund);
		mav.addObject(refunds);
		mav.addObject(advanceFormManager.getAll());
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		mav.addObject("flag", 2);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ACC_ADVANCE_SUMARY_SEARCH, params = {
			"search", "!export" })
	public ModelAndView searchAccAdvanceSumary(Search searchAdvanceSumary,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_FORM_ACC);

		Calendar cal = Calendar.getInstance();
		Date startDate = searchAdvanceSumary.getStartDate() != null ? searchAdvanceSumary
				.getStartDate() : cal.getTime();
		Date endDate = searchAdvanceSumary.getEndDate() != null ? searchAdvanceSumary
				.getEndDate() : cal.getTime();
		List<AdvanceSumary> listSumary = new ArrayList<AdvanceSumary>();
		User employee = new User();
		try {
			employee = advanceFormManager.getUserById(searchAdvanceSumary
					.getEmployee());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("CAN'T GET USER: " + e.getMessage());
		}
		if (employee.getId() != null) {
			listSumary.add(this.getAdvanceSumaryForEmployee(employee,
					startDate, endDate));
		} else {
			List<User> employees = new ArrayList<User>();
			employees.addAll(getUserManager().getAllUser());
			if (!employees.isEmpty()) {
				for (User user : employees) {
					listSumary.add(this.getAdvanceSumaryForEmployee(user,
							startDate, endDate));
				}
			}
		}
		mav.addObject(listSumary);
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		mav.addObject("flag", 3);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ADVANCE_SUMARY_SEARCH, params = {
			"search", "!export" })
	public ModelAndView searchAdvanceSumary(Search searchAdvanceSumary,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_REFUNDS);
		// check role
		User loggedInUser = getUserManager().getLoggedUser(request);
		if (!EntityUtil.isAdminOrAccountRole(loggedInUser)) {
			searchAdvanceSumary.setEmployee(loggedInUser.getId());
		}
		Calendar cal = Calendar.getInstance();
		Date startDate = searchAdvanceSumary.getStartDate() != null ? searchAdvanceSumary
				.getStartDate() : cal.getTime();
		Date endDate = searchAdvanceSumary.getEndDate() != null ? searchAdvanceSumary
				.getEndDate() : cal.getTime();
		List<AdvanceSumary> listSumary = new ArrayList<AdvanceSumary>();
		User employee = new User();
		try {
			employee = advanceFormManager.getUserById(searchAdvanceSumary
					.getEmployee());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("CAN'T GET USER: " + e.getMessage());
		}
		if (employee.getId() != null) {
			listSumary.add(this.getAdvanceSumaryForEmployee(employee,
					startDate, endDate));
		} else {
			List<User> employees = new ArrayList<User>();
			employees.addAll(getUserManager().getAllUser());
			if (!employees.isEmpty()) {
				for (User user : employees) {
					listSumary.add(this.getAdvanceSumaryForEmployee(user,
							startDate, endDate));
				}
			}
		}
		mav.addObject(listSumary);
		// selection
		DocsSelection docsSelection = configurationManager
				.loadSelectionForDocsPage(true);
		mav.addObject("docsSelection", docsSelection);
		mav.addObject("enumStatus", StatusType.values());
		mav.addObject("flag", 3);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ADVANCE_SUMARY_SEARCH, params = {
			"export", "!search" })
	public void downloadAdvanceSumary(Search searchAdvanceSumary,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_REFUNDS);
		BigDecimal totalAdBefore = BigDecimal.ZERO;
		BigDecimal totalAdBetween = BigDecimal.ZERO;
		BigDecimal totalAdAfter = BigDecimal.ZERO;
		BigDecimal totalRefBefore = BigDecimal.ZERO;
		BigDecimal totalRefBetween = BigDecimal.ZERO;
		BigDecimal totalRefAfter = BigDecimal.ZERO;
		Map<String, Object> beans = new LinkedHashMap<>();
		// check role
		User loggedInUser = getUserManager().getLoggedUser(request);
		if (!EntityUtil.isAdminOrAccountRole(loggedInUser)) {
			searchAdvanceSumary.setEmployee(loggedInUser.getId());
		}
		Calendar cal = Calendar.getInstance();
		Date startDate = searchAdvanceSumary.getStartDate() != null ? searchAdvanceSumary
				.getStartDate() : cal.getTime();
		Date endDate = searchAdvanceSumary.getEndDate() != null ? searchAdvanceSumary
				.getEndDate() : cal.getTime();
		List<AdvanceSumary> listSumary = new ArrayList<AdvanceSumary>();
		User employee = new User();
		try {
			employee = advanceFormManager.getUserById(searchAdvanceSumary
					.getEmployee());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("CAN'T GET USER: " + e.getMessage());
		}
		if (employee.getId() != null) {
			listSumary.add(this.getAdvanceSumaryForEmployee(employee,
					startDate, endDate));
		} else {
			List<User> employees = new ArrayList<User>();
			employees.addAll(getUserManager().getAllUser());
			if (!employees.isEmpty()) {
				for (User user : employees) {
					listSumary.add(this.getAdvanceSumaryForEmployee(user,
							startDate, endDate));
				}
			}
		}
		if (!listSumary.isEmpty()) {
			for (AdvanceSumary advanceSumary : listSumary) {
				totalAdBefore = totalAdBefore.add(advanceSumary
						.getTotalAdvanceBefore());
				totalAdBetween = totalAdBetween.add(advanceSumary
						.getTotalAdvanceBetween());
				totalAdAfter = totalAdAfter.add(advanceSumary
						.getTotalAdvanceAfter());
				totalRefBefore = totalRefBefore.add(advanceSumary
						.getTotalRefundBefore());
				totalRefBetween = totalRefBetween.add(advanceSumary
						.getTotalRefundBetween());
				totalRefAfter = totalRefAfter.add(advanceSumary
						.getTotalRefundAfter());
			}

		}
		beans.put("totalAdBefore", totalAdBefore);
		beans.put("totalAdBetween", totalAdBetween);
		beans.put("totalAdAfter", totalAdAfter);
		beans.put("totalRefBefore", totalRefBefore);
		beans.put("totalRefBetween", totalRefBetween);
		beans.put("totalRefAfter", totalRefAfter);
		beans.put("advanceSumary", listSumary);
		beans.put("startDate", CommonUtil.getDateString(startDate));
		beans.put("endDate", CommonUtil.getDateString(endDate));
		ReportUtil.dispatchReport(response,
				ReportTeamplates.ADVANCE_SUMARY_ITEMS,
				ReportTeamplates.ADVANCE_SUMARY_ITEMS_TEMPLATE, beans);
	}

	@RequestMapping(method = RequestMethod.POST, value = URLReference.ACC_ADVANCE_SUMARY_SEARCH, params = {
			"export", "!search" })
	public void downloadAccAdvanceSumary(Search searchAdvanceSumary,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView(URLReference.ADVANCE_FORM_ACC);
		BigDecimal totalAdBefore = BigDecimal.ZERO;
		BigDecimal totalAdBetween = BigDecimal.ZERO;
		BigDecimal totalAdAfter = BigDecimal.ZERO;
		BigDecimal totalRefBefore = BigDecimal.ZERO;
		BigDecimal totalRefBetween = BigDecimal.ZERO;
		BigDecimal totalRefAfter = BigDecimal.ZERO;
		Calendar cal = Calendar.getInstance();
		Date startDate = searchAdvanceSumary.getStartDate() != null ? searchAdvanceSumary
				.getStartDate() : cal.getTime();
		Date endDate = searchAdvanceSumary.getEndDate() != null ? searchAdvanceSumary
				.getEndDate() : cal.getTime();
		Map<String, Object> beans = new LinkedHashMap<>();
		List<AdvanceSumary> listSumary = new ArrayList<AdvanceSumary>();
		User employee = new User();
		try {
			employee = advanceFormManager.getUserById(searchAdvanceSumary
					.getEmployee());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("CAN'T GET USER: " + e.getMessage());
		}
		if (employee.getId() != null) {
			listSumary.add(this.getAdvanceSumaryForEmployee(employee,
					startDate, endDate));
		} else {
			List<User> employees = new ArrayList<User>();
			employees.addAll(getUserManager().getAllUser());
			if (!employees.isEmpty()) {
				for (User user : employees) {
					listSumary.add(this.getAdvanceSumaryForEmployee(user,
							startDate, endDate));
				}
			}
		}
		if (!listSumary.isEmpty()) {
			for (AdvanceSumary advanceSumary : listSumary) {
				totalAdBefore = totalAdBefore.add(advanceSumary
						.getTotalAdvanceBefore());
				totalAdBetween = totalAdBetween.add(advanceSumary
						.getTotalAdvanceBetween());
				totalAdAfter = totalAdAfter.add(advanceSumary
						.getTotalAdvanceAfter());
				totalRefBefore = totalRefBefore.add(advanceSumary
						.getTotalRefundBefore());
				totalRefBetween = totalRefBetween.add(advanceSumary
						.getTotalRefundBetween());
				totalRefAfter = totalRefAfter.add(advanceSumary
						.getTotalRefundAfter());
			}

		}
		beans.put("totalAdBefore", totalAdBefore);
		beans.put("totalAdBetween", totalAdBetween);
		beans.put("totalAdAfter", totalAdAfter);
		beans.put("totalRefBefore", totalRefBefore);
		beans.put("totalRefBetween", totalRefBetween);
		beans.put("totalRefAfter", totalRefAfter);
		beans.put("advanceSumary", listSumary);
		beans.put("startDate", CommonUtil.getDateString(startDate));
		beans.put("endDate", CommonUtil.getDateString(endDate));
		ReportUtil.dispatchReport(response,
				ReportTeamplates.ADVANCE_SUMARY_ITEMS,
				ReportTeamplates.ADVANCE_SUMARY_ITEMS_TEMPLATE, beans);
	}

	private BigDecimal calculateTotalAdvance(List<Advanceform> listAdvanceForm) {
		BigDecimal result = BigDecimal.ZERO;
		try {

			if (!listAdvanceForm.isEmpty()) {
				for (Advanceform advanceform : listAdvanceForm) {
					List<Advancedetail> listDetails = new ArrayList<Advancedetail>();
					listDetails.addAll(advanceform.getAdvancedetails());
					if (!listDetails.isEmpty()) {
						for (Advancedetail advancedetail : listDetails) {
							result = result.add(advancedetail.getAmount());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("FAILED TO CALCULATE total advance before: "
					+ e.getMessage());
		}
		return result;
	}

	private BigDecimal calculateTotalRefund(List<Refund> listRefund) {
		BigDecimal result = BigDecimal.ZERO;
		try {

			if (!listRefund.isEmpty()) {
				for (Refund refund : listRefund) {
					List<Refunddetail> listDetails = new ArrayList<Refunddetail>();
					listDetails.addAll(refund.getRefunddetails());
					if (!listDetails.isEmpty()) {
						for (Refunddetail refundDetail : listDetails) {
							result = result.add(refundDetail.getAmount());
							result = result.add(refundDetail.getOAmount());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("FAILED TO CALCULATE total advance before: "
					+ e.getMessage());
		}
		return result;
	}

	private AdvanceSumary getAdvanceSumaryForEmployee(User employee,
			Date startDate, Date endDate) {
		BigDecimal totalAdvanceBefore = BigDecimal.ZERO;
		BigDecimal totalRefundBefore = BigDecimal.ZERO;
		BigDecimal totalAdvanceBetween = BigDecimal.ZERO;
		BigDecimal totalRefundBetween = BigDecimal.ZERO;
		BigDecimal totalAdvanceAfter = BigDecimal.ZERO;
		BigDecimal totalRefundAfter = BigDecimal.ZERO;
		List<Advanceform> listAdvance = new ArrayList<Advanceform>();
		listAdvance.addAll(advanceFormManager
				.findByEmpoyeeForAccounting(employee));
		List<Advanceform> listBefore = new ArrayList<Advanceform>();
		List<Advanceform> listBetween = new ArrayList<Advanceform>();
		if (!listAdvance.isEmpty()) {
			for (Advanceform advanceform : listAdvance) {
				if (advanceform.getDate() != null) {
					if (advanceform.getDate().before(startDate)) {
						listBefore.add(advanceform);
					} else if (advanceform.getDate().after(startDate)
							&& advanceform.getDate().before(endDate)) {
						listBetween.add(advanceform);
					}
				}
			}
		}
		totalAdvanceBefore = this.calculateTotalAdvance(listBefore);
		totalAdvanceBetween = this.calculateTotalAdvance(listBetween);
		totalAdvanceAfter = totalAdvanceBefore.add(totalAdvanceBetween);

		List<Refund> listRefund = new ArrayList<Refund>();
		listRefund.addAll(refundManager.findByEmpoyeeForAccounting(employee));
		List<Refund> listRefundBefore = new ArrayList<Refund>();
		List<Refund> listRefundBetween = new ArrayList<Refund>();
		if (!listRefund.isEmpty()) {
			for (Refund refund : listRefund) {
				if (refund.getDate() != null) {
					if (refund.getDate().before(startDate)) {
						listRefundBefore.add(refund);
					} else if (refund.getDate().after(startDate)
							&& refund.getDate().before(endDate)) {
						listRefundBetween.add(refund);
					}
				}
			}
			totalRefundBefore = this.calculateTotalRefund(listRefundBefore);
			totalRefundBetween = this.calculateTotalRefund(listRefundBetween);
			totalRefundAfter = totalRefundBefore.add(totalRefundBetween);
		}
		return new AdvanceSumary(employee.getFullName(), totalAdvanceBefore,
				totalRefundBefore, totalAdvanceBetween, totalRefundBetween,
				totalAdvanceAfter, totalRefundAfter);

	}
}
