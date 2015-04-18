package com.vn.ael.webapp.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jfree.util.Log;

import com.vn.ael.constants.AELConst;
import com.vn.ael.constants.TypeOfContainer;
import com.vn.ael.enums.ReportMergeInfo;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Truckingdetail;

public class ConvertUtil {
	
	/**
	 * Convert from Configuration List 2 Map
	 * @param confs
	 * @return
	 */
	public static Map<Long,String> fromConfigurationList2Map(List<Configuration> confs,boolean... withDes){
		Map<Long,String> map = new LinkedHashMap<>();
		if (confs != null){
			for (Configuration configuration : confs){
				if (withDes.length>0 && withDes[0]){
					map.put(configuration.getId(), configuration.getValue() + AELConst.SEPARATOR+configuration.getDescription());
				}else{
					map.put(configuration.getId(), configuration.getValue());
				}
					
			}
		}
		return map;
	}
	
	/**
	 * Change bigDecimal to 0 if it's null
	 * @param bigDecimal
	 * @return
	 */
	public static BigDecimal getNotNullValue(BigDecimal bigDecimal){
		if (bigDecimal == null){
			return BigDecimal.ZERO;
		}
		return bigDecimal;
	}

	/**
	 * From docslist 2 map
	 * @param docs
	 * @return
	 */
	public static Map<Long,String> fromDocsList2MapCus(List<Docsgeneral> docs) {
		Map<Long,String> map = new LinkedHashMap<>();
		if (docs != null && !docs.isEmpty()){
			for (Docsgeneral docsgeneral : docs){
				map.put( docsgeneral.getId(), docsgeneral.getJobNo()+AELConst.SEPARATOR+docsgeneral.getCustomer().getName());
			}
		}
		return map;
	}

	/**
	 * 
	 * @param docsgenerals
	 * @return
	 */
	public static Map<ReportMergeInfo,List<Integer>> generateMergeIndexForTrans(List<Docsgeneral> docsgenerals){
		//merge level 1 - by job
		Map<ReportMergeInfo,List<Integer>> map = new LinkedHashMap<>();
		List<Integer> jobsOrder = new ArrayList<>();
		//merge level 2 - by cont
		List<Integer> contOrder = new ArrayList<>();
		if (docsgenerals != null){
			for (Docsgeneral docsgeneral : docsgenerals){
				if (docsgeneral.getTruckingservice() != null && docsgeneral.getTruckingservice().getTruckingdetails() != null){
					jobsOrder.add(docsgeneral.getTruckingservice().getTruckingdetails().size());
					String contNo=null;
					int countCont = 1;
					if (docsgeneral.getTypeOfContainer().getId() != TypeOfContainer.LCL){
						for (Truckingdetail truckingdetail : docsgeneral.getTruckingservice().getTruckingdetails()){
							if (contNo == null || (truckingdetail.getConsteal() != null && !contNo.contentEquals(truckingdetail.getConsteal().getNoOfCont()))){
								if (contNo != null){
									contOrder.add(countCont);
									countCont = 1;
								}
									contNo = truckingdetail.getConsteal().getNoOfCont();
							}else{
								++countCont;
							}
						}
					}
					else{
						countCont = docsgeneral.getTruckingservice().getTruckingdetails().size();
					}
					contOrder.add(countCont);
				}else{
					jobsOrder.add(0);
				}
			}
		}
		map.put(ReportMergeInfo.BANG_KE_CUOC_VAN_CHUYEN, jobsOrder);
		map.put(ReportMergeInfo.BANG_KE_CUOC_VAN_CHUYEN_L2, contOrder);
		return map;
	}
	
	public static String convertToVND(BigDecimal value){
		String result = "";
		try {
			result = numberToString(value.doubleValue());
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("FAILED TO CONVERT BIGDECIMAL TO VND");
		}
		return result;
        
	}
	public static String formatNumberForRead(double number) {
        NumberFormat nf = NumberFormat.getInstance();
        String temp = nf.format(number);
        String strReturn = "";
        int slen = temp.length();
        for (int i = 0; i < slen; i++) {
            if (String.valueOf(temp.charAt(i)).equals("."))
                break;
            else if (Character.isDigit(temp.charAt(i))) {
                strReturn += String.valueOf(temp.charAt(i));
            }
        }
        return strReturn;

    }
    public static String numberToString(double number) {
        String sNumber = formatNumberForRead(number);
        // Tao mot bien tra ve
        String sReturn = "";
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Lat nguoc chuoi nay lai
        String sNumber1 = "";
        for (int i = iLen - 1; i >= 0; i--) {
            sNumber1 += sNumber.charAt(i);
        }
        // Tao mot vong lap de doc so
        // Tao mot bien nho vi tri cua sNumber
        int iRe = 0;
        do {
            // Tao mot bien cat tam
            String sCut = "";
            if (iLen > 3) {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + 3);
                sReturn = Read(sCut, iRe) + sReturn;
                iRe++;
                iLen -= 3;
            } else {
                sCut = sNumber1.substring((iRe * 3), (iRe * 3) + iLen);
                sReturn = Read(sCut, iRe) + sReturn;
                break;
            }
        } while (true);
        if(sReturn.length() > 1){
            sReturn = sReturn.substring(0,1).toUpperCase() + sReturn.substring(1);
        }
        sReturn = sReturn + "đồng";
        return sReturn;
    }

    // Khoi tao ham Read
    public static String Read(String sNumber, int iPo) {
        // Tao mot bien tra ve
        String sReturn = "";
        // Tao mot bien so
        String sPo[] = { "", "ngàn" + " ",
                "triệu" + " ", "tỷ" + " " };
        String sSo[] = { "không" + " ", "một" + " ",
                "hai" + " ", "ba" + " ",
                "bốn" + " ", "năm" + " ",
                "sáu" + " ", "bảy" + " ",
                "tám" + " ", "chín" + " " };
        String sDonvi[] = { "", "mươi" + " ",
                "trăm" + " " };
        // Tim chieu dai cua chuoi
        int iLen = sNumber.length();
        // Tao mot bien nho vi tri doc
        int iRe = 0;
        // Tao mot vong lap de doc so
        for (int i = 0; i < iLen; i++) {
            String sTemp = "" + sNumber.charAt(i);
            int iTemp = Integer.parseInt(sTemp);
            // Tao mot bien ket qua
            String sRead = "";
            // Kiem tra xem so nhan vao co = 0 hay ko
            if (iTemp == 0) {
                switch (iRe) {
                case 0:
                    break;// Khong lam gi ca
                case 1: {
                    if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
                        sRead = "lẻ" + " ";
                    }
                    break;
                }
                case 2: {
                    if (Integer.parseInt("" + sNumber.charAt(0)) != 0
                            && Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                        sRead = "không trăm" + " ";
                    }
                    break;
                }
                }
            } else if (iTemp == 1) {
                switch (iRe) {
                case 1:
                    sRead = "mười" + " ";
                    break;
                default:
                    sRead = "một" + " " + sDonvi[iRe];
                    break;
                }
            } else if (iTemp == 5) {
                switch (iRe) {
                case 0: {
                    if(sNumber.length() <= 1){
                        sRead = "năm" + " ";
                    }
                    else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
                        sRead = "lăm" + " ";
                    } else
                        sRead = "năm" + " ";
                    break;
                }
                default:
                    sRead = sSo[iTemp] + sDonvi[iRe];
                }
            } else {
                sRead = sSo[iTemp] + sDonvi[iRe];
            }

            sReturn = sRead + sReturn;
            iRe++;
        }
        if (sReturn.length() > 0) {
            sReturn += sPo[iPo];
        }

        return sReturn;
    }

    public static String getRomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
          int matches = Int/entry.getValue();
          res += repeat(entry.getKey(), matches);
          Int = Int % entry.getValue();
        }
        return res;
  }
  public static String repeat(String s, int n) {
    if(s == null) {
        return null;
    }
    final StringBuilder sb = new StringBuilder();
    for(int i = 0; i < n; i++) {
        sb.append(s);
    }
    return sb.toString();
  }
}
