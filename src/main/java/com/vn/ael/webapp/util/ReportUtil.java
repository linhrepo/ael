package com.vn.ael.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.ReportTeamplates;
import com.vn.ael.constants.TypeOfContainer;
import com.vn.ael.enums.ReportMergeInfo;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Accountingcus;
import com.vn.ael.persistence.entity.Accountingcusdetail;
import com.vn.ael.persistence.entity.Advancedetail;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.entity.Extendfeeacc;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.entity.Packageinfo;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Refunddetail;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.webapp.dto.AccountingExhibitionItemExport;
import com.vn.ael.webapp.dto.AccountingNhathauExport;
import com.vn.ael.webapp.dto.AccountingProfitLossExport;
import com.vn.ael.webapp.dto.AccountingShipmentExport;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransportExport;
import com.vn.ael.webapp.dto.AdvanceRequestItem;
import com.vn.ael.webapp.dto.CustomFeeExportModel;
import com.vn.ael.webapp.dto.ExhibitionFeetable;
import com.vn.ael.webapp.dto.FeeExportItem;
import com.vn.ael.webapp.dto.FeeNameExport;
import com.vn.ael.webapp.dto.KeHoachVanTaiExport;
import com.vn.ael.webapp.dto.KeHoachVanTaiThongQuanExport;
import com.vn.ael.webapp.dto.OfferItemExportModel;
import com.vn.ael.webapp.dto.RefundRequestItem;
import com.vn.ael.webapp.formatter.FormatterUtil;

public class ReportUtil {
	private static final transient Log log = LogFactory
			.getLog(ReportUtil.class);

	/**
	 * Prepare data for offerprices report
	 * 
	 * @param offerPrice
	 * @return
	 */
	public static Map<String, Object> prepareDataForOffers(OfferPrice offerPrice) {
		List<OfferItemExportModel> offerPriceExport = new ArrayList<>();
		if (offerPrice.getOfferItems() != null) {
			int i = 0;
			for (OfferItem offerItem : offerPrice.getOfferItems()) {
				if (offerItem.getIsAdded() != null
						&& offerItem.getIsAdded() == false) {
					i++;
					offerPriceExport.add(new OfferItemExportModel(i, offerItem
							.getNameOfService().getValue(), ConvertUtil
							.getNotNullValue(offerItem.getFeeWithVAT())
							.toEngineeringString(), ConvertUtil
							.getNotNullValue(offerItem.getFeeNoVAT())
							.toEngineeringString(), offerItem.getCurrency()
							.getValue(), offerItem.getFeeUnit().getValue()));
				}
			}
		}
		Properties prop = new Properties();
		String typeOfService = "";
		// Get file from resources folder
					ClassLoader classLoader = ReportUtil.class.getClassLoader();
					File file = new File(classLoader.getResource(AELConst.APP_PROPERTIES)
							.getFile());
					try {
						InputStream targetStream = new FileInputStream(file);
						if (targetStream!=null) {
							prop.load(targetStream);
							if (prop.containsKey(offerPrice
						.getTypeOfService().getTextKey())) {
								typeOfService=prop.getProperty(offerPrice
						.getTypeOfService().getTextKey());
							}
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.error(e.getMessage());
					}
		
		Map<String, Object> beans = new LinkedHashMap<>();
		Customer cust = offerPrice.getCustomer();
		beans.put("customerCode", cust.getCode());
		beans.put("customerName", cust.getName());
		beans.put("offerType", typeOfService);
		beans.put("offerDate",
				CommonUtil.getDateString(offerPrice.getDateOffer()));
		beans.put("offerItem", offerPriceExport);
		return beans;
	}

	/**
	 * Compose report data
	 * 
	 * @param response
	 * @param reportName
	 * @param reportTemplates
	 * @param data
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public static void dispatchReport(HttpServletResponse response,
			String reportName, String reportTemplates,
			Map<String, Object> data, 
			Map<ReportMergeInfo,List<Integer>> dataMegres,Map<ReportMergeInfo,List<Integer>> dynamicMergeInfos) {
		try {
			Workbook workbook = generateWorkbook(reportTemplates, data);
			if (workbook != null) {
				Sheet sheet = workbook.getSheetAt(0);
				if (dataMegres != null){
					for (Entry<ReportMergeInfo, List<Integer>> merge : dataMegres.entrySet()){
						List<Integer> mergeIndexs = merge.getValue();
						ReportMergeInfo mergeInfo = merge.getKey(); 
						if (mergeIndexs != null) {
							int startRow = mergeInfo.getStartingRow();
							for (Integer height : mergeIndexs) {
								List<Integer> cols = ConvertUtil.createListFromArray(mergeInfo.getCols());
								if (dynamicMergeInfos != null && dynamicMergeInfos.containsKey(mergeInfo)){
									cols.addAll(dynamicMergeInfos.get(mergeInfo));
								}
								for (Integer index : cols) {
									if (height > 0) {
										sheet.addMergedRegion(new CellRangeAddress(
												startRow,startRow + height - 1,
												index, index));
									}
								}
								startRow = startRow + height;
							}
						}
					}
				}
				generateOutputInfo(response, reportName, workbook);
			}

		} catch (Exception e) {
			log.error("Could not write report to response", e);
		}
	}

	/**
	 * Compose report data
	 * 
	 * @param response
	 * @param reportName
	 * @param reportTemplates
	 * @param data
	 * @throws IOException
	 * @throws InvalidFormatException
	 * @throws ParsePropertyException
	 */
	public static void dispatchReport(HttpServletResponse response,
			String reportName, String reportTemplates, Map<String, Object> data) {
		generateOutputInfo(response, reportName,
				generateWorkbook(reportTemplates, data));
	}

	/**
	 * Generate workbook from data and template
	 * 
	 * @return
	 */
	public static Workbook generateWorkbook(String reportTemplates,
			Map<String, Object> data) {
		try {
			XLSTransformer transformer = new XLSTransformer();
			// Get file from resources folder
			ClassLoader classLoader = ReportUtil.class.getClassLoader();
			File file = new File(classLoader.getResource(reportTemplates)
					.getFile());
			InputStream targetStream = new FileInputStream(file);
			return transformer.transformXLS(targetStream, data);
		} catch (Exception e) {
			log.error("Could not generate XLS data", e);
		}
		return null;
	}

	/**
	 * Write a workbook to response stream
	 * 
	 * @param response
	 * @return
	 */
	public static void generateOutputInfo(HttpServletResponse response,
			String reportName, Workbook workbook) {
		try {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ reportName);
			ServletOutputStream out = response.getOutputStream();
			if (workbook != null) {
				workbook.write(out);
				out.flush();
			}
			out.close();
		} catch (IOException e) {
			log.error("Could not write report to response", e);
		}
	}

	/**
	 * Create data for bienbangiaohang report
	 * 
	 * @param packageInfo
	 * @return
	 */
	public static Map<String, Object> prepareDataForBienbangiaohang(
			Packageinfo packageInfo) {
		Map<String, Object> beans = new LinkedHashMap<>();
		Customer cust = packageInfo.getDocsgeneral().getCustomer();
		beans.put("cusCode", cust.getCode());
		beans.put("cusName", cust.getName());
		beans.put("invoiceNo", packageInfo.getInvoice());
		beans.put("contract", packageInfo.getPo());
		beans.put("description", packageInfo.getDocsgeneral()
				.getProductDescription());
		beans.put("weight", FormatterUtil.formatBigDecimal(packageInfo
				.getDocsgeneral().getWeigth()));
		beans.put("pkgs", packageInfo.getDocsgeneral().getNoOfPkgsText());
		beans.put(
				"jobTK",
				AELConst.PREFFIX_TK + AELConst.COLON
						+ packageInfo.getCusDecOnNo() + AELConst.SEPARATOR
						+ packageInfo.getDocsgeneral().getJobNo());

		return beans;

	}

	/**
	 * Prepare data for accounting custom report
	 * 
	 * @param accountingcus
	 * @return
	 */
	public static Map<String, Object> prepareDataForCust(
			Accountingcus accountingcus) {
		BigDecimal cusFeeTotal = BigDecimal.ZERO;
		BigDecimal cusFeeVat = BigDecimal.ZERO;

		BigDecimal chihoTotal = BigDecimal.ZERO;
		BigDecimal chihoVat = BigDecimal.ZERO;

		List<CustomFeeExportModel> customFee = new ArrayList<>();
		int index = 0;
		if (accountingcus.getAccountingcusdetails() != null) {
			for (Accountingcusdetail accountingCusDetailItem : accountingcus
					.getAccountingcusdetails()) {
				if (accountingCusDetailItem.getIsAdded() != null
						&& accountingCusDetailItem.getIsAdded() == false) {
					CustomFeeExportModel item = new CustomFeeExportModel(
							accountingCusDetailItem.getName() != null ? accountingCusDetailItem
									.getName().getValue()
									: AELConst.EMPTY_STRING,
							accountingCusDetailItem.getDescription() != null ? accountingCusDetailItem
									.getDescription().getValue()
									: AELConst.EMPTY_STRING,
							accountingCusDetailItem.getQuantity20(),
							accountingCusDetailItem.getQuantity40(),
							accountingCusDetailItem.getQuantityOt(),
							accountingCusDetailItem.getQuantityLCL(),
							ConvertUtil.getNotNullValue(accountingCusDetailItem
									.getTotal()),
							ConvertUtil.getNotNullValue(accountingCusDetailItem
									.getGeneralVat()),
							accountingCusDetailItem.getNote(),
							accountingCusDetailItem.getInvoice());
					index++;
					item.setIndex(index);
					customFee.add(item);
					cusFeeTotal = cusFeeTotal.add(accountingCusDetailItem
							.getTotal());
					cusFeeVat = cusFeeVat.add(accountingCusDetailItem
							.getFeevat());
				}
			}
		}
		BigDecimal cusFinalVal = cusFeeTotal.add(cusFeeVat);

		List<CustomFeeExportModel> fee = new ArrayList<>();
		if (accountingcus.getExtendfeeaccs() != null) {
			index = 0;
			for (Extendfeeacc extendFeeAcc : accountingcus.getExtendfeeaccs()) {
				CustomFeeExportModel item = new CustomFeeExportModel(
						extendFeeAcc.getFeeowner().getName().getValue(),
						extendFeeAcc.getDescription().getValue(),
						extendFeeAcc.getQuantity20(),
						extendFeeAcc.getQuantity40(),
						extendFeeAcc.getQuantityLCL(),
						ConvertUtil.getNotNullValue(extendFeeAcc.getFeeowner()
								.getAmount()),
						ConvertUtil.getNotNullValue(extendFeeAcc.getFeeowner()
								.getVat()), extendFeeAcc.getNote(),
						extendFeeAcc.getInvoice());
				index++;
				item.setIndex(index);
				fee.add(item);
				chihoTotal = chihoTotal.add(extendFeeAcc.getFeeowner()
						.getAmount());
				chihoVat = chihoVat.add(extendFeeAcc.getFeeowner().getVatFee());
			}
		}
		BigDecimal chihoFinalVal = chihoTotal.add(chihoVat);

		Map<String, Object> beans = new LinkedHashMap<>();
		beans.put("chihoFinalVal", chihoFinalVal);
		beans.put("chihoTotal", chihoTotal);
		beans.put("chihoVat", chihoVat);

		beans.put("cusFinalVal", cusFinalVal);
		beans.put("cusFeeTotal", cusFeeTotal);
		beans.put("cusFeeVat", cusFeeVat);

		beans.put("total", chihoFinalVal.add(cusFeeTotal));

		beans.put("customFees", customFee);
		beans.put("fees", fee);
		beans.put("updateDate",
				FormatterUtil.formatDate(accountingcus.getLastUpdateDate()));
		Customer cust = accountingcus.getDocsgeneral().getCustomer();
		Docsgeneral doc = accountingcus.getDocsgeneral();
		beans.put("refNo", accountingcus.getRefNo());
		beans.put("custName", cust.getName());
		beans.put("custAddress", cust.getAddress());
		beans.put("custTaxNo", cust.getTaxno());
		beans.put("custTel", cust.getTel());
		beans.put("custFax", cust.getFax());
		beans.put("bill", doc.getPackageinfo().getBillOfLading());
		beans.put("cusDecOnNo", doc.getPackageinfo().getCusDecOnNo());
		beans.put("placeDelivery", doc.getPlaceDelivery());
		beans.put("tentau", doc.getNameVehicle());
		beans.put("cmb", doc.getCmbText());
		beans.put("aelcmb", doc.getCmbText());
		beans.put("noOfPkgs", doc.getNoOfPkgsText());
		beans.put("kg", doc.getWeigthText());
		beans.put("colourApplying",
				doc.getPackageinfo().getColourApplying() != null ? doc
						.getPackageinfo().getColourApplying().getFullInfo()
						: AELConst.EMPTY_STRING);
		beans.put("po", doc.getPackageinfo().getPo());
		beans.put("PTVT", doc.getPTVT());
		beans.put("vol", doc.getCmbText());
		return beans;
	}

	/**
	 * Prepare data for ACCOUNTING TRANSPORT report
	 * 
	 * @param year
	 * @param month
	 * @param offerPrice
	 * @return
	 */
	public static Map<String, Object> prepareDataForAccountingTransport(
			AccountingTrans accountingTrans) {
		List<AccountingTransportExport> accountingTransExport = new ArrayList<>();
		BigDecimal chihoTotal = BigDecimal.ZERO;
		BigDecimal giacaTotal = BigDecimal.ZERO;
		BigDecimal otherTotal = BigDecimal.ZERO;

		if (accountingTrans.getDocs() != null) {
			int i = 0;
			for (Docsgeneral doc : accountingTrans.getDocs()) {
				++i;
				if (doc.getTruckingservice() != null
						&& doc.getTruckingservice().getTruckingdetails() != null) {
					for (Truckingdetail truckingdetail : doc
							.getTruckingservice().getTruckingdetails()) {
						AccountingTransportExport item = new AccountingTransportExport();
						item.setJobNo(doc.getJobNo());
						item.setDateDev(FormatterUtil.formatDate(truckingdetail
								.getDateDev()));
						item.setPlaceRev1(doc.getPlaceRev());
						item.setPlaceDelivery1(doc.getPlaceDelivery());
						item.setNoOf20Cont(doc.getNoOf20Cont());
						item.setNoOf40Cont(doc.getNoOf40Cont());
						item.setNoOfOthCont(doc.getOtCont());
						item.setIsLCL(doc.getIsLCL() ? AELConst.HAVE_ONE : AELConst.EMPTY_STRING);
						item.setVehicleNo(truckingdetail.getVehicleNo());
						item.setNoOfCont(truckingdetail.getConsteal() != null ? truckingdetail
								.getConsteal().getNoOfCont()
								: AELConst.EMPTY_STRING);
						item.setVolumn(doc.getCmbText());
						item.setKg(doc.getWeigthText());
						item.setPlacegetcont(doc.getTruckingservice()
								.getArrival());
						item.setPlaceputcont(doc.getTruckingservice()
								.getDeparture());
						item.setChiho(truckingdetail.getChiho());
						chihoTotal = chihoTotal.add(ConvertUtil
								.getNotNullValue(truckingdetail.getChiho()));
						item.setAccountingPrice(truckingdetail
								.getAccountingPrice());
						giacaTotal = giacaTotal.add(ConvertUtil
								.getNotNullValue(truckingdetail
										.getAccountingPrice()));
						item.setOtherfee(truckingdetail.getOtherFees());
						otherTotal = otherTotal
								.add(ConvertUtil.getNotNullValue(truckingdetail
										.getOtherFees()));
						item.setIndex(i);
						item.setTotal(truckingdetail.getTotalTransReport());
						accountingTransExport.add(item);
					}
				}
			}
		}

		Map<String, Object> beans = new LinkedHashMap<>();
		Customer cust = accountingTrans.getCustomer();
		beans.put("custCode", cust.getCode());
		beans.put("custName", cust.getName());
		beans.put("custAddress", cust.getAddress());
		beans.put("custTaxNo", cust.getTaxno());
		beans.put("custPhone", cust.getTel());
		beans.put("custFax", cust.getFax());
		beans.put("trans", accountingTransExport);
		beans.put("month", accountingTrans.getYear());
		beans.put("year", accountingTrans.getMonth());
		beans.put("refNo", accountingTrans.getRefNo());
		beans.put("chihoTotal", chihoTotal);
		beans.put("giacaTotal", giacaTotal);
		beans.put("otherTotal", otherTotal);
		beans.put("total", chihoTotal.add(giacaTotal).add(otherTotal));
		return beans;
	}
	
	/**
	 * Prepare data for Bang Ke Nha Thau
	 * @param accountingTrans
	 * @return
	 */
	public static Map<String, Object> prepareDataForBangKeNhaThau(AccountingTrans accountingTrans){
		Map<String, Object> beans = new LinkedHashMap<>();
		//general info
		beans.put("name", accountingTrans.getNhathau().getName());
		beans.put("start",accountingTrans.getCondition().getStartDate());
		beans.put("end",accountingTrans.getCondition().getEndDate());
		List<List<Exfeetable>> feesList = new ArrayList<>();
		
		List<AccountingNhathauExport> accountingNhathauExports = new ArrayList<>();
		if (accountingTrans.getTruckingdetails() != null && !accountingTrans.getTruckingdetails().isEmpty()){
			for (int i=0; i<accountingTrans.getTruckingdetails().size(); ++i){
				Truckingdetail truckingdetail = accountingTrans.getTruckingdetails().get(i);
				feesList.add(truckingdetail.getExfeetables());
				Docsgeneral docsgeneral = truckingdetail.getTruckingservice().getDocsgeneral(); 
				AccountingNhathauExport accountingNhathauExport = new AccountingNhathauExport();
				accountingNhathauExport.setIndex(i+1);
				accountingNhathauExport.setDateDev(truckingdetail.getDateDev());
				accountingNhathauExport.setJobNo(docsgeneral.getJobNo());
				accountingNhathauExport.setCusName(docsgeneral.getCustomer().getName());
				
				accountingNhathauExport.setImportType(docsgeneral.getTypeOfImport()!= null ? docsgeneral.getTypeOfImport().getValue() : AELConst.EMPTY_STRING);
				accountingNhathauExport.setPlaceGetCont(docsgeneral.getPlaceRev());
				accountingNhathauExport.setPlacePutCont(docsgeneral.getPlaceDelivery());
				if (truckingdetail.getConsteal() != null && truckingdetail.getConsteal().getTypeOfCont()!= null){
					accountingNhathauExport.setContNo(truckingdetail.getConsteal().getNoOfCont());
					if (truckingdetail.getConsteal().getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_20_START)){
						accountingNhathauExport.setCont20(AELConst.HAVE_ONE);
					}else if (truckingdetail.getConsteal().getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_40_START)){
						accountingNhathauExport.setCont40(AELConst.HAVE_ONE);
					} else{
						accountingNhathauExport.setContO(AELConst.HAVE_ONE);
					}
				}else{
					accountingNhathauExport.setContO(AELConst.HAVE_ONE);
				}
				
				accountingNhathauExport.setDeparture(truckingdetail.getTruckingservice().getDeparture());
				accountingNhathauExport.setArrival(truckingdetail.getTruckingservice().getArrival());
				accountingNhathauExport.setPhuthu(truckingdetail.getPhuthu());
				
				accountingNhathauExports.add(accountingNhathauExport);
			}
		}
		FeeNameExport  feeNameExport = ConvertUtil.fromFeeListToFeeExport(feesList);
		beans.put("feeNames", feeNameExport.getName());
		if (!accountingNhathauExports.isEmpty() && !feeNameExport.getValues().isEmpty()){
			for (int i=0; i<accountingNhathauExports.size(); ++i){
				accountingNhathauExports.get(i).setConvertedFee(feeNameExport.getValues().get(i));
				//calculate total
				BigDecimal total = ConvertUtil.getNotNullValue(accountingNhathauExports.get(i).getPhuthu());
				if (feeNameExport.getValues() != null && !feeNameExport.getValues().isEmpty()){
					for (FeeExportItem exportItem : feeNameExport.getValues().get(i)){
						total = total.add(exportItem.getFeeVal());
					}
					accountingNhathauExports.get(i).setTotal(total);
				}
			}
		}
		beans.put("details", accountingNhathauExports);
		return beans;
	}

	public static void dispatchExhibitionReport(HttpServletResponse response,
			String accountingExhibitionItems,
			String accountingExhibitionItemsTemplate, Exhibition exhibition,
			List<Configuration> masterFee) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ accountingExhibitionItems);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		JasperReport report = null;
		FileInputStream template = null;
		File logo = null;
		try {
			/* template = new FileInputStream("resources/reportTemplate.jrxml"); */
			ClassLoader classLoader = ReportUtil.class.getClassLoader();
			File file = new File(classLoader.getResource(
					accountingExhibitionItemsTemplate).getFile());
			logo = new File(classLoader.getResource(
					ReportTeamplates.ACCOUNTING_EXHIBITION_ITEMS_AEL_LOGO)
					.getFile());
			template = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found on filesystem");
		} catch (IOException ioe) {
			System.out.println("Exception while reading the file" + ioe);
		}
		JasperDesign design = null;
		try {
			design = JRXmlLoader.load(template);
		} catch (JRException e) {
			e.printStackTrace();
		}
		// compile the report from the stream
		try {
			report = JasperCompileManager.compileReport(design);
			report.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		try {
			Customer cust = exhibition.getDocsgeneral().getCustomer();
			parameterMap.put("companyName", cust != null ? cust.getName()
					: AELConst.EMPTY_STRING);
			parameterMap.put("address", cust != null ? cust.getAddress()
					: AELConst.EMPTY_STRING);
			parameterMap.put("fax", cust != null ? cust.getFax()
					: AELConst.EMPTY_STRING);
			parameterMap.put("tel", cust != null ? cust.getTel()
					: AELConst.EMPTY_STRING);
			parameterMap.put("attn", exhibition.getAttn());
			parameterMap.put("exhibitor", exhibition.getExhibitor());
			parameterMap.put("exName", exhibition.getExName());
			parameterMap.put("from_to", exhibition.getExhPlace());
			parameterMap.put("cmb",
					exhibition.getDocsgeneral() != null ? exhibition
							.getDocsgeneral().getCmbText()
							: AELConst.EMPTY_STRING);
			parameterMap.put("weight",
					exhibition.getDocsgeneral() != null ? exhibition
							.getDocsgeneral().getNoOfPkgs().toString()
							+ "/"
							+ exhibition.getDocsgeneral().getWeigthText()
									.toString() : AELConst.EMPTY_STRING);
			parameterMap.put("invoiceNo",
					exhibition.getDocsgeneral() != null ? exhibition
							.getDocsgeneral().getJobNo()
							: AELConst.EMPTY_STRING);
			parameterMap.put("accountNo", exhibition.getAccountNo());
			parameterMap.put("mode", AELConst.EMPTY_STRING);
			parameterMap.put("logo", logo != null ? logo.getAbsolutePath()
					: AELConst.EMPTY_STRING);
			parameterMap.put("date",
					CommonUtil.getDateString(exhibition.getDateRe()));
			parameterMap.put("albw", exhibition.getAlbwNo());

			// Calculate total
			double grandTotal = 0;
			if (!exhibition.getExfeetables().isEmpty()) {
				for (Exfeetable fee : exhibition.getExfeetables()) {
					grandTotal += fee.getTotal() != null ? fee.getTotal()
							.doubleValue() : 0;
				}
			}
			parameterMap.put("grandTotal", Double.toString(grandTotal));

			JasperPrint print = JasperFillManager.fillReport(report,
					parameterMap,
					getDataSource(exhibition.getExfeetables(), masterFee));
			if (out != null) {
				JasperExportManager.exportReportToPdfStream(print, out);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		if (!exhibition.getExfeetables().isEmpty()) {

		}
	}

	// Get datasource for export accounting exhibition
	private static JRDataSource getDataSource(List<Exfeetable> fees,
			List<Configuration> masterFee) {
		Collection<ExhibitionFeetable> coll = new ArrayList<ExhibitionFeetable>();
		for (Configuration master : masterFee) {
			List<AccountingExhibitionItemExport> tmpEx = new ArrayList<AccountingExhibitionItemExport>();
			for (Exfeetable fee : fees) {
				if (fee.getMasterFee().getId().toString()
						.equals(master.getId().toString())) {
					AccountingExhibitionItemExport item = new AccountingExhibitionItemExport(
							fee.getName().getValue(), "",
							fee.getTotal() != null ? fee.getTotal().toString()
									: "0");
					tmpEx.add(item);
				}
			}
			if (!tmpEx.isEmpty()) {
				ExhibitionFeetable bean = new ExhibitionFeetable(tmpEx,
						master.getValue());
				coll.add(bean);
			}

		}
		return new JRBeanCollectionDataSource(coll);
	}

	public static Map<String, Object> prepareDataForAdvanceRequest(
			Advanceform advanceForm,
			Map<Integer, BigDecimal> listRemainAdvancebyJob) {
		Map<String, Object> beans = new LinkedHashMap<>();
		double total = 0;
		BigDecimal totalRemainAdv = BigDecimal.ZERO;
		int index = 0;
		List<AdvanceRequestItem> listAdvance = new ArrayList<AdvanceRequestItem>();
		List<Advancedetail> listAdvanceDetail = new ArrayList<Advancedetail>();
		listAdvanceDetail.addAll(advanceForm.getAdvancedetails());
		if (!listAdvanceDetail.isEmpty()) {
			for (Advancedetail advancedetail : listAdvanceDetail) {
				try {
					if (listRemainAdvancebyJob.containsKey(advancedetail.getDocs().getId().intValue())) {
						BigDecimal tmp = listRemainAdvancebyJob.get(advancedetail.getDocs().getId().intValue()).subtract(advancedetail.getAmount());
						if (tmp.doubleValue()>=0) {
							listRemainAdvancebyJob.put(advancedetail.getDocs().getId().intValue(), tmp);
						}						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			Map<Integer, BigDecimal> usedRemain = new HashMap<Integer, BigDecimal>();
			for (Advancedetail advancedetail : listAdvanceDetail) {
				AdvanceRequestItem item = new AdvanceRequestItem();
				index++;
				item.setJobNo(advancedetail.getDocs() != null ? advancedetail
						.getDocs().getJobNo() : AELConst.EMPTY_STRING);
				item.setPackageDetail(advancedetail.getGoodDes());
				item.setAdvanceReason(advancedetail.getDescription());
				item.setAmount(advancedetail.getAmount().toString());
				item.setRemainAdvance(listRemainAdvancebyJob.get(
						advancedetail.getDocs().getId().intValue()).toString());
				if (!usedRemain.containsKey(advancedetail.getDocs().getId().intValue())) {
					totalRemainAdv = totalRemainAdv.add(listRemainAdvancebyJob.get(
							advancedetail.getDocs().getId().intValue()));
					usedRemain.put(advancedetail.getDocs().getId().intValue(), totalRemainAdv);
				}
				
				item.setIndex(index);
				total += advancedetail.getAmount().doubleValue();
				listAdvance.add(item);
			}
		}
		beans.put("advanceDetails", listAdvance);
		beans.put("total", total);
		beans.put("advanceDate",
				CommonUtil.getDateString(advanceForm.getDate()));
		beans.put("employee", advanceForm.getEmployee());
		beans.put("refundDate",
				CommonUtil.getDateString(advanceForm.getTimeRefund()));
		beans.put("totalRemainAdv", totalRemainAdv.toString());
		return beans;
	}

	public static Map<String, Object> prepareDataForRefundRequest(Refund refund) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<Refunddetail> refundDetails = new ArrayList<Refunddetail>();
		if (refund.getRefunddetails() != null) {
			refundDetails.addAll(refund.getRefunddetails());
		}
		List<RefundRequestItem> listItem = new ArrayList<RefundRequestItem>();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalOAmount = BigDecimal.ZERO;
		BigDecimal grandTotal = BigDecimal.ZERO;
		int index = 0;
		if (!refund.getRefunddetails().isEmpty()) {
			for (Refunddetail refunddetail : refundDetails) {
				RefundRequestItem item = new RefundRequestItem();
				index++;
				item.setIndex(index);
				item.setFileCus(refunddetail.getDocs() != null ? refunddetail
						.getDocs().getJobNo() : AELConst.EMPTY_STRING);
				item.setDescription(refunddetail.getDescription());
				item.setAmount(refunddetail.getAmount().toString());
				item.setoAmount(refunddetail.getOAmount().toString());
				totalAmount = totalAmount.add(refunddetail.getAmount());
				totalOAmount = totalOAmount.add(refunddetail.getOAmount());
				listItem.add(item);
			}
			grandTotal = grandTotal.add(totalAmount);
			grandTotal = grandTotal.add(totalOAmount);
			parameterMap.put("refundDetails", listItem);
			parameterMap.put("totalAmount", totalAmount.toString());
			parameterMap.put("totalOAmount", totalOAmount.toString());
			parameterMap.put("employee", refund.getEmployee());
			parameterMap.put("grandTotal", grandTotal.toString());
			parameterMap.put("refundDate",
					CommonUtil.getDateString(refund.getDate()));
			parameterMap.put("refNo", refund.getRefNo());
		}
		return parameterMap;
	}

	public static Map<String, Object> prepareDataForPhieuChi(
			Advanceform advanceForm) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<Advancedetail> listAdvanceDetail = new ArrayList<Advancedetail>();
		listAdvanceDetail.addAll(advanceForm.getAdvancedetails());
		BigDecimal total = BigDecimal.ZERO;
		if (!listAdvanceDetail.isEmpty()) {
			for (Advancedetail advancedetail : listAdvanceDetail) {
				total = total.add(advancedetail.getAmount());
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(advanceForm.getDate());

		parameterMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
		parameterMap.put("month", cal.get(Calendar.MONTH) + 1);
		parameterMap.put("year", cal.get(Calendar.YEAR));
		parameterMap.put("refNo", advanceForm.getRefNo());
		parameterMap.put("employee", advanceForm.getEmployee());
		parameterMap.put("reason", advanceForm.getPayReason());
		parameterMap.put("amount", total);
		parameterMap.put("amountVND", ConvertUtil.convertToVND(total));
		return parameterMap;
	}

	public static Map<String, Object> prepareDataForPhieuThu(Refund refund) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(refund.getDate());
		BigDecimal total = BigDecimal.ZERO;

		List<Refunddetail> listRefundDetail = new ArrayList<Refunddetail>();
		listRefundDetail.addAll(refund.getRefunddetails());
		if (!listRefundDetail.isEmpty()) {
			for (Refunddetail refunddetail : listRefundDetail) {
				total = total.add(refunddetail.getAmount());
				total = total.add(refunddetail.getOAmount());

			}
		}

		parameterMap.put("day", cal.get(Calendar.DAY_OF_MONTH));
		parameterMap.put("month", cal.get(Calendar.MONTH) + 1);
		parameterMap.put("year", cal.get(Calendar.YEAR));
		parameterMap.put("refNo", refund.getRefNo());
		parameterMap.put("employee", refund.getEmployee());
		parameterMap.put("reason", refund.getPayReason());
		parameterMap.put("amount", total);
		parameterMap.put("amountVND", ConvertUtil.convertToVND(total));
		return parameterMap;
	}

	public static Map<String, Object> prepareDataForKHVTNoidia(
			AccountingTrans accountingTrans) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startDate", accountingTrans.getCondition().getStartDate());
		parameterMap.put("endDate", accountingTrans.getCondition().getEndDate());
		List<KeHoachVanTaiExport> hoachVanTaiExports = new ArrayList<>();
		List<List<Exfeetable>> feesList = new ArrayList<>();
		if (accountingTrans.getTruckingdetails() != null && !accountingTrans.getTruckingdetails().isEmpty()){
			for (int i=0; i<accountingTrans.getTruckingdetails().size(); ++i){
				Truckingdetail truckingdetail = accountingTrans.getTruckingdetails().get(i);
				feesList.add(truckingdetail.getExfeetables());
				Docsgeneral docsgeneral = truckingdetail.getTruckingservice().getDocsgeneral(); 
				KeHoachVanTaiExport keHoachVanTaiExport = new KeHoachVanTaiExport();
				keHoachVanTaiExport.setDateDev(truckingdetail.getDateDev());
				keHoachVanTaiExport.setJobNo(docsgeneral.getJobNo());
				keHoachVanTaiExport.setCusName(docsgeneral.getCustomer().getName());
				keHoachVanTaiExport.setTotal(ConvertUtil.getNotNullValue(truckingdetail.getTotal()));
				keHoachVanTaiExport.setNhathau(truckingdetail.getNhathau() != null ? truckingdetail.getNhathau().getName() : AELConst.EMPTY_STRING);
				if (truckingdetail.getConsteal() != null && truckingdetail.getConsteal().getTypeOfCont()!= null){
					keHoachVanTaiExport.setContNo(truckingdetail.getConsteal().getNoOfCont());
					if (truckingdetail.getConsteal().getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_20_START)){
						keHoachVanTaiExport.setCont20(AELConst.HAVE_ONE);
					}else if (truckingdetail.getConsteal().getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_40_START)){
						keHoachVanTaiExport.setCont40(AELConst.HAVE_ONE);
					} else{
						keHoachVanTaiExport.setContO(AELConst.HAVE_ONE);
					}
				}else{
					keHoachVanTaiExport.setContL(AELConst.HAVE_ONE);
				}
				keHoachVanTaiExport.setDeparture(truckingdetail.getTruckingservice().getDeparture());
				keHoachVanTaiExport.setArrival(truckingdetail.getTruckingservice().getArrival());
				keHoachVanTaiExport.setShipName(truckingdetail.getVehicleNo()+AELConst.SPLASH+truckingdetail.getNoOfVehicle());
				keHoachVanTaiExport.setEta(truckingdetail.getTruckingservice().getDocsgeneral().getInland().getEta());
				keHoachVanTaiExport.setEtd(truckingdetail.getTruckingservice().getDocsgeneral().getInland().getEtd());
				keHoachVanTaiExport.setDocReceiveDate(truckingdetail.getTruckingservice().getDocsgeneral().getDocReceiveDate());
				hoachVanTaiExports.add(keHoachVanTaiExport);
			}
		}
		 if (accountingTrans.getCondition().getTransId().intValue() == ServicesType.DVVT_SEALAND.getValue()){
			 parameterMap.put("detail", hoachVanTaiExports);
		 }else{
			    FeeNameExport  feeNameExport = ConvertUtil.fromFeeListToFeeExport(feesList);
			    parameterMap.put("feeNames", feeNameExport.getName());
				if (!hoachVanTaiExports.isEmpty() && !feeNameExport.getValues().isEmpty()){
					for (int i=0; i<hoachVanTaiExports.size(); ++i){
						hoachVanTaiExports.get(i).setConvertedFee(feeNameExport.getValues().get(i));
						//calculate total
						BigDecimal total = BigDecimal.ZERO;
						if (feeNameExport.getValues() != null && !feeNameExport.getValues().isEmpty()){
							for (FeeExportItem exportItem : feeNameExport.getValues().get(i)){
								total = total.add(exportItem.getFeeVal());
							}
							hoachVanTaiExports.get(i).setTotal(total);
						}
					}
				}
			 parameterMap.put("details", hoachVanTaiExports);
		 }
		return parameterMap;
	}

	public static Map<String, Object> prepareDataForKHVTThongquan(
			AccountingTrans accountingTrans) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startDate", accountingTrans.getCondition().getStartDate());
		parameterMap.put("endDate", accountingTrans.getCondition().getEndDate());
		
		List<KeHoachVanTaiThongQuanExport> hoachVanTaiExports = new ArrayList<>();
		if (accountingTrans.getTruckingdetails() != null && !accountingTrans.getTruckingdetails().isEmpty()){
			for (int i=0; i<accountingTrans.getTruckingdetails().size(); ++i){
				Truckingdetail truckingdetail = accountingTrans.getTruckingdetails().get(i);
				Docsgeneral docsgeneral = truckingdetail.getTruckingservice().getDocsgeneral(); 
				KeHoachVanTaiThongQuanExport keHoachVanTaiExport = new KeHoachVanTaiThongQuanExport();
				keHoachVanTaiExport.setDateDev(truckingdetail.getDateDev());
				keHoachVanTaiExport.setJobNo(docsgeneral.getJobNo());
				keHoachVanTaiExport.setCusName(docsgeneral.getCustomer().getName());
				keHoachVanTaiExport.setNhathau(truckingdetail.getNhathau() != null ? truckingdetail.getNhathau().getName() : AELConst.EMPTY_STRING);
				if (truckingdetail.getConsteal() != null && truckingdetail.getConsteal().getTypeOfCont()!= null){
					keHoachVanTaiExport.setContNo(truckingdetail.getConsteal().getNoOfCont());
					if (truckingdetail.getConsteal().getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_20_START)){
						keHoachVanTaiExport.setCont20(AELConst.HAVE_ONE);
					}else if (truckingdetail.getConsteal().getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_40_START)){
						keHoachVanTaiExport.setCont40(AELConst.HAVE_ONE);
					} else{
						keHoachVanTaiExport.setContO(AELConst.HAVE_ONE);
					}
				}else{
					keHoachVanTaiExport.setPks(truckingdetail.getTruckingservice().getDocsgeneral().getNoOfPkgsText());
					keHoachVanTaiExport.setWeight(truckingdetail.getTruckingservice().getDocsgeneral().getWeigthText());
				}
				keHoachVanTaiExport.setDeparture(truckingdetail.getTruckingservice().getDeparture());
				keHoachVanTaiExport.setArrival(truckingdetail.getTruckingservice().getArrival());
				keHoachVanTaiExport.setEta(docsgeneral.getPackageinfo().getEta());
				keHoachVanTaiExport.setBill(docsgeneral.getPackageinfo().getBillOfLading());
				keHoachVanTaiExport.setImportType(docsgeneral.getTypeOfImport().getValue());
				keHoachVanTaiExport.setFreeTime(ConvertUtil.getNotNullValue(docsgeneral.getPackageinfo().getFreeDemDate())+AELConst.SPLASH
						+ConvertUtil.getNotNullValue(docsgeneral.getPackageinfo().getFreeDetDate()));
				keHoachVanTaiExport.setCusDate(docsgeneral.getPackageinfo().getCustomsDate());
				keHoachVanTaiExport.setSotkhq(docsgeneral.getPackageinfo().getCusDecOnNo());
				keHoachVanTaiExport.setShipper(docsgeneral.getPackageinfo().getShipper());
				hoachVanTaiExports.add(keHoachVanTaiExport);
			}
			 parameterMap.put("detail", hoachVanTaiExports);
		}
		
		return parameterMap;
	}

	public static Map<String, Object> prepareDataForShipmentControl(
			AccountingTrans accountingTrans) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startDate", accountingTrans.getCondition().getStartDate());
		parameterMap.put("endDate", accountingTrans.getCondition().getEndDate());
		List<AccountingShipmentExport> hoachVanTaiExports = new ArrayList<>();
		if (accountingTrans.getDocs() != null) {
			int i = 0;
			for (Docsgeneral doc : accountingTrans.getDocs()) {
				++i;
				AccountingShipmentExport accountingShipmentExport = new AccountingShipmentExport();
				accountingShipmentExport.setIndex(i);
				accountingShipmentExport.setJobNo(doc.getJobNo());
				accountingShipmentExport.setShipper(doc.getPackageinfo().getShipper());
				accountingShipmentExport.setPoNo(doc.getPackageinfo().getPo());
				accountingShipmentExport.setInvNo(doc.getPackageinfo().getInvoice());
				accountingShipmentExport.setBillNo(doc.getPackageinfo().getBillOfLading());
				if (doc.getTypeOfContainer().getId() == TypeOfContainer.FCL){
					accountingShipmentExport.setContL(TypeOfContainer.FCL_CHAR);
					accountingShipmentExport.setCont20(doc.getNoOf20Cont());
					accountingShipmentExport.setCont40(doc.getNoOf40Cont());
					
				}else{
					accountingShipmentExport.setContL(TypeOfContainer.LCL_CHAR);
				}
				
				accountingShipmentExport.setPks(doc.getNoOfPkgs());
				accountingShipmentExport.setWeight(doc.getWeigth());
				accountingShipmentExport.setShippingLine(doc.getShippingLine().getName());
				accountingShipmentExport.setEta(doc.getPackageinfo().getEta());
				accountingShipmentExport.setArrival(doc.getPackageinfo().getPortOfArrival());
				accountingShipmentExport.setDocOriginalDate(doc.getDocReceiveDate());
				accountingShipmentExport.setDecDate(doc.getPackageinfo().getCustomsDate());
				accountingShipmentExport.setSoToKhai(doc.getPackageinfo().getCusDecOnNo());
				accountingShipmentExport.setColour(doc.getPackageinfo().getColourApplying().getDescription());
				hoachVanTaiExports.add(accountingShipmentExport);
			}
		}
		
	    parameterMap.put("detail", hoachVanTaiExports);
		
		return parameterMap;
	}

	public static Map<String, Object> prepareDataForProfitLoss(
			AccountingTrans accountingTrans) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("startDate", accountingTrans.getCondition().getStartDate());
		parameterMap.put("endDate", accountingTrans.getCondition().getEndDate());
		List<AccountingProfitLossExport> profitLossExports = new ArrayList<>();
		List<List<Exfeetable>> feesList = new ArrayList<>();
		List<List<Exfeetable>> feesListThu = new ArrayList<>();
		if (accountingTrans.getTruckingdetails() != null && !accountingTrans.getTruckingdetails().isEmpty()){
			for (int i=0; i<accountingTrans.getTruckingdetails().size(); ++i){
				Truckingdetail truckingdetail = accountingTrans.getTruckingdetails().get(i);
				Docsgeneral docsgeneral = truckingdetail.getTruckingservice().getDocsgeneral(); 
				feesList.add(docsgeneral.getExfeetables());
				feesListThu.add(truckingdetail.getExfeetables());
				AccountingProfitLossExport profitLossExport = new AccountingProfitLossExport();
				profitLossExport.setJobNo(docsgeneral.getJobNo());
				profitLossExport.setCusName(docsgeneral.getCustomer().getName());
				profitLossExport.setContNo(truckingdetail.getConsteal() != null ? truckingdetail.getConsteal().getNoOfCont() : AELConst.EMPTY_STRING);
				profitLossExport.setNhathau(truckingdetail.getNhathau() != null ? truckingdetail.getNhathau().getName() : AELConst.EMPTY_STRING);
				profitLossExport.setDateDev(truckingdetail.getDateDev());
				profitLossExport.setTongChi(ConvertUtil.getNotNullValue(docsgeneral.getTongChiPhi()));
				profitLossExport.setTongThu(ConvertUtil.getNotNullValue(docsgeneral.getTongThu()));
				profitLossExport.setProfitLoss(profitLossExport.getTongThu().subtract(profitLossExport.getTongChi()));
				profitLossExports.add(profitLossExport);
			}
		}
		//chi phí chi
			    FeeNameExport  feeNameExport = ConvertUtil.fromFeeListToFeeExportFeeType(feesList);
			    parameterMap.put("feeNames", feeNameExport.getName());
				if (!profitLossExports.isEmpty() && !feeNameExport.getValues().isEmpty()){
					for (int i=0; i<profitLossExports.size(); ++i){
						profitLossExports.get(i).setConvertedFee(feeNameExport.getValues().get(i));
					}
				}
				//chi phí thu
				 FeeNameExport  feeNameExportThu = ConvertUtil.fromFeeListToFeeExport(feesListThu);
				    parameterMap.put("feeNamesThu", feeNameExportThu.getName());
					if (!profitLossExports.isEmpty() && !feeNameExportThu.getValues().isEmpty()){
						for (int i=0; i<profitLossExports.size(); ++i){
							profitLossExports.get(i).setConvertedFeeThu(feeNameExportThu.getValues().get(i));
							//calculate total
							BigDecimal total = BigDecimal.ZERO;
							if (feeNameExportThu.getValues() != null && !feeNameExportThu.getValues().isEmpty()){
								for (FeeExportItem exportItem : feeNameExportThu.getValues().get(i)){
									total = total.add(exportItem.getFeeVal());
								}
								profitLossExports.get(i).setTotal(total);
							}
						}
					}
			 parameterMap.put("details", profitLossExports);
		return parameterMap;
	}
}
