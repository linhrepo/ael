package com.vn.ael.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import com.vn.ael.constants.AELConst;
import com.vn.ael.persistence.entity.Accountingcus;
import com.vn.ael.persistence.entity.Accountingcusdetail;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Extendfeeacc;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.entity.Packageinfo;
import com.vn.ael.webapp.dto.CustomFeeExportModel;
import com.vn.ael.webapp.dto.OfferItemExportModel;
import com.vn.ael.webapp.formatter.FormatterUtil;

public class ReportUtil {
	private static final transient Log log = LogFactory.getLog(ReportUtil.class);
	
	/**
	 * Prepare data for offerprices report
	 * @param offerPrice
	 * @return
	 */
	public static Map<String,Object> prepareDataForOffers(OfferPrice offerPrice){
		List<OfferItemExportModel> offerPriceExport	 = new ArrayList<>();
		if (offerPrice.getOfferItems()!=null) {
			int i=0;
			for (OfferItem offerItem : offerPrice.getOfferItems()) {
				if (offerItem.getIsAdded() != null && offerItem.getIsAdded() == false){
					i++;
					offerPriceExport.add(new OfferItemExportModel(i,offerItem.getNameOfService().getValue(),ConvertUtil.getNotNullValue(offerItem.getFeeWithVAT()).toEngineeringString(),ConvertUtil.getNotNullValue(offerItem.getFeeNoVAT()).toEngineeringString(),offerItem.getCurrency().getValue(),offerItem.getFeeUnit().getValue()));
				}
			}
		}
		
		Map<String,Object> beans = new LinkedHashMap<>();
		Customer cust = offerPrice.getCustomer();
		beans.put("customerCode", cust.getCode());
		beans.put("customerName", cust.getName());
		beans.put("offerType", offerPrice.getTypeOfService()!= null ? offerPrice.getTypeOfService().getValue(): AELConst.EMPTY_STRING);
		beans.put("offerDate", StringUtil.getDateString(offerPrice.getDateOffer()));
		beans.put("offerItems", offerPriceExport);
		return beans;
	}
	
	/**
	 * Compose report data
	 * @param response
	 * @param reportName
	 * @param reportTemplates
	 * @param data
	 * @throws IOException
	 * @throws InvalidFormatException 
	 * @throws ParsePropertyException 
	 */
	public static void dispatchReport(HttpServletResponse response, String reportName, String reportTemplates, Map<String,Object> data){
		try{
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename="+reportName);
	        ServletOutputStream out = response.getOutputStream();
	 		XLSTransformer transformer = new XLSTransformer();
	 		//Get file from resources folder
	 		ClassLoader classLoader = ReportUtil.class.getClassLoader();
	 		File file = new File(classLoader.getResource(reportTemplates).getFile());
	 		InputStream targetStream = new FileInputStream(file);
	 		transformer.transformXLS(targetStream, data).write(out);
	 		out.flush();
	 	    out.close();
		}
		catch (Exception e){
			log.error("Could not write report to response", e);
		}
		
	}

	/**
	 * Create data for bienbangiaohang report
	 * @param packageInfo
	 * @return
	 */
	public static Map<String, Object> prepareDataForBienbangiaohang(
			Packageinfo packageInfo) {
		Map<String,Object> beans = new LinkedHashMap<>();
		Customer cust = packageInfo.getDocsgeneral().getCustomer();
		beans.put("cusCode", cust.getCode());
		beans.put("cusName", cust.getName());
		beans.put("invoiceNo", packageInfo.getInvoice());
		beans.put("contract", packageInfo.getPo());
		beans.put("description", packageInfo.getDocsgeneral().getProductDescription());
		beans.put("weight", FormatterUtil.formatBigDecimal(packageInfo.getDocsgeneral().getWeigth()));
		beans.put("pkgs", packageInfo.getDocsgeneral().getNoOfPkgsText());
		beans.put("jobTK", AELConst.PREFFIX_TK+AELConst.COLON+packageInfo.getCusDecOnNo()+AELConst.SEPARATOR+packageInfo.getDocsgeneral().getJobNo());
		
		return beans;
		
	}
	
	/**
	 * Prepare data for accounting custom report
	 * @param accountingcus
	 * @return
	 */
	public static Map<String,Object> prepareDataForOffers(Accountingcus accountingcus){
		List<CustomFeeExportModel> customFee = new ArrayList<>();
		if (accountingcus.getAccountingcusdetails()!=null) {
			for (Accountingcusdetail accountingCusDetailItem : accountingcus.getAccountingcusdetails()) {
				if (accountingCusDetailItem.getIsAdded() != null && accountingCusDetailItem.getIsAdded() == false){
					CustomFeeExportModel item = new CustomFeeExportModel(accountingCusDetailItem.getName() !=null ? accountingCusDetailItem.getName().getValue():AELConst.EMPTY_STRING,
							accountingCusDetailItem.getDescription()!=null ? accountingCusDetailItem.getDescription().getValue():AELConst.EMPTY_STRING, 
									accountingCusDetailItem.getQuantity20(), 
							accountingCusDetailItem.getQuantity40(), accountingCusDetailItem.getQuantityOt(), accountingCusDetailItem.getQuantityLCL(), 
							ConvertUtil.getNotNullValue(accountingCusDetailItem.getTotal()), ConvertUtil.getNotNullValue(accountingCusDetailItem.getGeneralVat()),
							accountingCusDetailItem.getNote(), accountingCusDetailItem.getInvoice());
					customFee.add(item);
				}
			}
		}
		List<CustomFeeExportModel> fee = new ArrayList<>();
		if (accountingcus.getExtendfeeaccs()!=null) {
			for (Extendfeeacc extendFeeAcc : accountingcus.getExtendfeeaccs()) {
				CustomFeeExportModel item = new CustomFeeExportModel(extendFeeAcc.getFeeowner().getName().getValue(), extendFeeAcc.getDescription().getValue(), extendFeeAcc.getQuantity20(), 
						extendFeeAcc.getQuantity40(), extendFeeAcc.getQuantityLCL(), 
						ConvertUtil.getNotNullValue(extendFeeAcc.getFeeowner().getAmount()), ConvertUtil.getNotNullValue(extendFeeAcc.getFeeowner().getVat()),
						extendFeeAcc.getNote(), extendFeeAcc.getInvoice());
				fee.add(item);
				
			}
		}
		Map<String,Object> beans = new LinkedHashMap<>();
		beans.put("customFee", customFee);
		beans.put("fee", fee);
		Customer cust = accountingcus.getDocsgeneral().getCustomer();
		Docsgeneral doc = accountingcus.getDocsgeneral();
		beans.put("custName", cust.getName());
		beans.put("custAddress", cust.getAddress());
		beans.put("custTaxNo", cust.getTaxno());
		beans.put("custTel", cust.getTel());
		beans.put("custFax", cust.getFax());
		beans.put("infoInvoice", doc.getInfoInvoice());
		beans.put("cusDecOnNo", doc.getPackageinfo().getCusDecOnNo());
		beans.put("placeDelivery", doc.getPlaceDelivery());
		beans.put("cmb", doc.getCmbText());
		beans.put("aelcmb", doc.getCmbText());
		beans.put("noOfPkgs", doc.getNoOfPkgsText());
		beans.put("kg", doc.getWeigthText());
		beans.put("colourApplying", doc.getPackageinfo() != null ? doc.getPackageinfo().getColourApplying().getValue() : AELConst.EMPTY_STRING);
		beans.put("po", doc.getPackageinfo() != null ? doc.getPackageinfo().getPo() : AELConst.EMPTY_STRING);
		beans.put("PTVT", doc.getPTVT());
		return beans;
	}
}
