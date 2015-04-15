package com.vn.ael.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.FormatterPattern;
import com.vn.ael.constants.ReportTeamplates;
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
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.webapp.dto.AccountingExhibitionItemExport;
import com.vn.ael.webapp.dto.AccountingTrans;
import com.vn.ael.webapp.dto.AccountingTransportExport;
import com.vn.ael.webapp.dto.AdvanceRequestItem;
import com.vn.ael.webapp.dto.CustomFeeExportModel;
import com.vn.ael.webapp.dto.ExhibitionFeetable;
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
	public static Map<String,Object> prepareDataForCust(Accountingcus accountingcus){
		BigDecimal cusFeeTotal = BigDecimal.ZERO;
		BigDecimal cusFeeVat = BigDecimal.ZERO;
		
		BigDecimal chihoTotal = BigDecimal.ZERO;
		BigDecimal chihoVat = BigDecimal.ZERO;
		
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
					cusFeeTotal = cusFeeTotal.add(accountingCusDetailItem.getTotal());
					cusFeeVat = cusFeeVat.add(accountingCusDetailItem.getFeevat());
				}
			}
		}
		BigDecimal cusFinalVal  = cusFeeTotal.add(cusFeeVat);
		
		List<CustomFeeExportModel> fee = new ArrayList<>();
		if (accountingcus.getExtendfeeaccs()!=null) {
			for (Extendfeeacc extendFeeAcc : accountingcus.getExtendfeeaccs()) {
				CustomFeeExportModel item = new CustomFeeExportModel(extendFeeAcc.getFeeowner().getName().getValue(), extendFeeAcc.getDescription().getValue(), extendFeeAcc.getQuantity20(), 
						extendFeeAcc.getQuantity40(), extendFeeAcc.getQuantityLCL(), 
						ConvertUtil.getNotNullValue(extendFeeAcc.getFeeowner().getAmount()), ConvertUtil.getNotNullValue(extendFeeAcc.getFeeowner().getVat()),
						extendFeeAcc.getNote(), extendFeeAcc.getInvoice());
				fee.add(item);
				chihoTotal = chihoTotal.add(extendFeeAcc.getFeeowner().getAmount());
				chihoVat = chihoVat.add(extendFeeAcc.getFeeowner().getVatFee());
			}
		}
		BigDecimal chihoFinalVal = chihoTotal.add(chihoVat);
		
		
		Map<String,Object> beans = new LinkedHashMap<>();
		beans.put("chihoFinalVal", chihoFinalVal);
		beans.put("chihoTotal", chihoTotal);
		beans.put("chihoVat", chihoVat);
		
		beans.put("cusFinalVal", cusFinalVal);
		beans.put("cusFeeTotal", cusFeeTotal);
		beans.put("cusFeeVat", cusFeeVat);
		
		beans.put("total", chihoFinalVal.add(cusFeeTotal));
		
		beans.put("customFee", customFee);
		beans.put("fee", fee);
		beans.put("updateDate",FormatterUtil.formatDate(accountingcus.getLastUpdateDate()));		
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
		beans.put("colourApplying", doc.getPackageinfo().getColourApplying() != null ? doc.getPackageinfo().getColourApplying().getFullInfo(): AELConst.EMPTY_STRING);
		beans.put("po",doc.getPackageinfo().getPo());
		beans.put("PTVT", doc.getPTVT());
		beans.put("vol", doc.getCmbText());
		return beans;		
	}
	
	/**
	 * Prepare data for ACCOUNTING TRANSPORT report
	 * @param year 
	 * @param month 
	 * @param offerPrice
	 * @return
	 */
	public static Map<String,Object> prepareDataForAccountingTransport(AccountingTrans accountingTrans, String month, String year){
		List<AccountingTransportExport> accountingTransExport	 = new ArrayList<>();
		if (accountingTrans.getDocs()!=null) {
			int i=0;
			for (Docsgeneral doc : accountingTrans.getDocs()) {
				AccountingTransportExport item = new AccountingTransportExport();
				item.setJobNo(doc.getJobNo());
				//TODO: find correct dat
//				item.setDateDev(df.format(doc.getInland().getDateDevPack()));
				item.setPlaceRev1(doc.getPlaceRev1());
				item.setPlaceDelivery1(doc.getPlaceDelivery1());
				item.setNoOf20Cont(doc.getNoOf20Cont());
				item.setNoOf40Cont(doc.getNoOf40Cont());
				item.setNoOfOthCont(doc.getOtCont());
				item.setIsLCL(doc.getIsLCL()? "x":"");
				String vehicle = "";
				try {
					for (Truckingdetail truckDetail : doc.getTruckingservice().getTruckingdetails()) {
						vehicle+=" "+truckDetail.getVehicleNo();
					}
				} catch (Exception e) {
					// TODO: handle exception
					log.error("ERROR: WHEN LOAD VEHICLE NO: "+e.getMessage());
				}
				item.setVehicleNo(vehicle);
				String noOfCont = "";
				try {
					for (Truckingdetail truckDetail : doc.getTruckingservice().getTruckingdetails()) {
						noOfCont+=" "+truckDetail.getConsteal().getNoOfCont();
					}
				} catch (Exception e) {
					// TODO: handle exception
					log.error("ERROR: WHEN LOAD NO OF CONT: "+e.getMessage());
				}
				item.setNoOfCont(noOfCont);
				item.setVolumn(doc.getCmbText());
				item.setKg(doc.getWeigthText());
				item.setPlacegetcont(doc.getPlaceEmptyUp());
				item.setPlaceputcont(doc.getPlaceEmptyDown());
				item.setChiho(doc.getChiho().toString());
//				item.setAccountingPrice(doc.getInland()!=null?doc.getInland().getAccountingPrice().toString():"");
				item.setOtherfee(doc.getInland()!=null?doc.getInland().getOtherFees().toString():"");
				
				accountingTransExport.add(item);
				}
		}
		
		Map<String,Object> beans = new LinkedHashMap<>();
		Customer cust = accountingTrans.getCustomer();
		beans.put("custCode", cust.getCode());
		beans.put("custName", cust.getName());
		beans.put("custAddress", cust.getAddress());
		beans.put("custTaxNo", cust.getTaxno());
		beans.put("custPhone", cust.getTel());
		beans.put("custFax", cust.getFax());
		beans.put("accountingTrans", accountingTransExport);
		beans.put("month", month);
		beans.put("year", year);
		return beans;
	}

	public static void dispatchExhibitionReport(HttpServletResponse response,
			String accountingExhibitionItems,
			String accountingExhibitionItemsTemplate, Exhibition exhibition, List<Configuration> masterFee) {
		response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+accountingExhibitionItems);
        ServletOutputStream out =null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		JasperReport report = null;
		FileInputStream template = null ;
		File logo = null;
		try {
			/*template = new FileInputStream("resources/reportTemplate.jrxml");*/
			ClassLoader classLoader = ReportUtil.class.getClassLoader();
			File file = new File(classLoader.getResource(accountingExhibitionItemsTemplate).getFile());
			logo = new File(classLoader.getResource(ReportTeamplates.ACCOUNTING_EXHIBITION_ITEMS_AEL_LOGO).getFile());
			template = new FileInputStream(file);
			} catch(FileNotFoundException e) {
			System.out.println("File could not be found on filesystem");
			} catch(IOException ioe) {
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
			Customer cust= exhibition.getDocsgeneral().getCustomer();
			parameterMap.put("companyName", cust!=null? cust.getName():AELConst.EMPTY_STRING);
			parameterMap.put("address", cust!=null? cust.getAddress():AELConst.EMPTY_STRING);
			parameterMap.put("fax", cust!=null? cust.getFax():AELConst.EMPTY_STRING);
			parameterMap.put("tel", cust!=null? cust.getTel():AELConst.EMPTY_STRING);
			parameterMap.put("attn",exhibition.getAttn() );
			parameterMap.put("exhibitor",exhibition.getExhibitor() );
			parameterMap.put("exName", exhibition.getExName());
			parameterMap.put("from_to", exhibition.getExhPlace());
			parameterMap.put("cmb", exhibition.getDocsgeneral()!=null?exhibition.getDocsgeneral().getCmbText():AELConst.EMPTY_STRING);
			parameterMap.put("weight", exhibition.getDocsgeneral()!=null?exhibition.getDocsgeneral().getNoOfPkgs().toString()+"/"+exhibition.getDocsgeneral().getWeigthText().toString():AELConst.EMPTY_STRING);
			parameterMap.put("invoiceNo", exhibition.getDocsgeneral()!=null?exhibition.getDocsgeneral().getJobNo():AELConst.EMPTY_STRING);
			parameterMap.put("accountNo", exhibition.getAccountNo());
			parameterMap.put("mode", AELConst.EMPTY_STRING);
			parameterMap.put("logo", logo!=null?logo.getAbsolutePath():AELConst.EMPTY_STRING);
			
			//Calculate total
			double grandTotal = 0;
			if (!exhibition.getExfeetables().isEmpty()) {
				for (Exfeetable fee  : exhibition.getExfeetables()) {
					grandTotal += fee.getTotal()!=null?fee.getTotal().doubleValue():0;
				}
			}
			parameterMap.put("grandTotal",Double.toString(grandTotal));
			
			JasperPrint print = JasperFillManager.fillReport(report, parameterMap, getDataSource(exhibition.getExfeetables(),masterFee));
			if (out!=null) {
				JasperExportManager.exportReportToPdfStream(print,out);
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}			
		if (!exhibition.getExfeetables().isEmpty()) {
			
		}		
	}
	//Get datasource for export accounting exhibition
	private static JRDataSource getDataSource(List<Exfeetable> fees, List<Configuration> masterFee) {
	    Collection<ExhibitionFeetable> coll = new ArrayList<ExhibitionFeetable>();
	    for (Configuration master : masterFee) {
	    	List<AccountingExhibitionItemExport> tmpEx = new ArrayList<AccountingExhibitionItemExport>();
	    	for (Exfeetable fee : fees) {
				if (fee.getMasterFee().getId().toString().equals(master.getId().toString())) {
					AccountingExhibitionItemExport item = new AccountingExhibitionItemExport(fee.getName().getValue(), "", fee.getTotal()!=null?fee.getTotal().toString():"0");
					tmpEx.add(item);
				}
			}
	    	if (!tmpEx.isEmpty()) {
	    		ExhibitionFeetable bean = new ExhibitionFeetable(tmpEx, master.getValue());
		    	coll.add(bean);
			}
	    	
		}
	    return new JRBeanCollectionDataSource(coll);
	}
	
	public static Map<String,Object> prepareDataForAdvanceRequest(Advanceform advanceForm){
		Map<String,Object> beans = new LinkedHashMap<>();
		double total = 0;
		List<AdvanceRequestItem> listAdvance = new ArrayList<AdvanceRequestItem>();
		List<Advancedetail> listAdvanceDetail= new ArrayList<Advancedetail>();
		listAdvanceDetail.addAll(advanceForm.getAdvancedetails());
		if (!listAdvanceDetail.isEmpty()) {
			for (Advancedetail advancedetail : listAdvanceDetail) {
				AdvanceRequestItem item = new AdvanceRequestItem();
				item.setJobNo(advancedetail.getDocs()!=null? advancedetail.getDocs().getJobNo():AELConst.EMPTY_STRING);
				item.setPackageDetail(advancedetail.getGoodDes());
				item.setAdvanceReason(advancedetail.getDescription());
				item.setAdvanceReason(advancedetail.getAmount().toString());
				total+=advancedetail.getAmount().doubleValue();
				listAdvance.add(item);
			}
		}
		beans.put("advanceDetails", listAdvance);
		beans.put("total", total);
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		beans.put("advanceDate", df.format(advanceForm.getDate()));
		beans.put("employee", advanceForm.getEmployee());
		beans.put("refundDate",df.format(advanceForm.getTimeRefund()));		
		return beans;
	}
}
