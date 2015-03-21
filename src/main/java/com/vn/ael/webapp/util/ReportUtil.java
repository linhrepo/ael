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
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.webapp.dto.OfferItemExportModel;

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

}
