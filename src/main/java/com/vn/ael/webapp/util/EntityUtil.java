package com.vn.ael.webapp.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Docservice;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Inlandsize;
import com.vn.ael.persistence.entity.Multitype;
import com.vn.ael.persistence.entity.Nhathau;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.webapp.dto.ContsealSelections;

public class EntityUtil {
	
	public static Sort EXFEETABLE_DEFAULT_SORTING = new Sort(Sort.Direction.ASC, "masterFee", "id");
	
	public static Sort CONFIGURATION_DEFAULT_SORTING = new Sort(Sort.Direction.ASC, "type", "value", "id");
	
	/**
	 * Wired all childs of Docsgeneral
	 * @param docsgeneral
	 */
	public static void wiredChildOfDocsGeneral(Docsgeneral docsgeneral){
		List<Contseal> conts = new ArrayList<>();
		if (docsgeneral.getContseals() != null && !docsgeneral.getContseals().isEmpty()){
			for (Contseal contseal: docsgeneral.getContseals()){
				if (contseal.getIsAdded() == null || !contseal.getIsAdded()){
					contseal.setDocsgeneral(docsgeneral);
					conts.add(contseal);
				}
			}
			docsgeneral.setContseals(conts);
		}
		
		List<Docservice> docservices = new ArrayList<>();
		if (docsgeneral.getDocservices() != null && !docsgeneral.getDocservices().isEmpty()){
			for (Docservice docservice: docsgeneral.getDocservices()){
				if (docservice.getIsAdded() == null || !docservice.getIsAdded()){
					docservice.setDocsgeneral(docsgeneral);
					docservices.add(docservice);
				}
			}
			docsgeneral.setDocservices(docservices);
		}
		
		List<Exfeetable> exfeetables = new ArrayList<>();
		if (docsgeneral.getExfeetables() != null && !docsgeneral.getExfeetables().isEmpty()){
			for (Exfeetable exfeetable: docsgeneral.getExfeetables()){
				if (exfeetable.getIsAdded() == null || !exfeetable.getIsAdded()){
					exfeetable.setDocsgeneral(docsgeneral);
					exfeetables.add(exfeetable);
				}
			}
			docsgeneral.setExfeetables(exfeetables);
		}
		
		List<Inlandsize> inlandsizes = new ArrayList<>();
		if (docsgeneral.getInlandsizes() != null && !docsgeneral.getInlandsizes().isEmpty()){
			for (Inlandsize inlandsize: docsgeneral.getInlandsizes()){
				if (inlandsize.getIsAdded() == null || !inlandsize.getIsAdded()){
					inlandsize.setDocsgeneral(docsgeneral);
					inlandsizes.add(inlandsize);
				}
			}
			docsgeneral.setInlandsizes(inlandsizes);
		}
		
		List<Multitype> multitypes = new ArrayList<>();
		if (docsgeneral.getContTypes() != null && !docsgeneral.getContTypes().isEmpty()){
			for (Multitype multitype: docsgeneral.getContTypes()){
				if (multitype.getIsAdded() == null || !multitype.getIsAdded()){
					multitype.setDocsgeneral(docsgeneral);
					multitypes.add(multitype);
				}
			}
			docsgeneral.setContTypes(multitypes);
		}
		
	}
	
	/**
	 * Attach child of offer price
	 * @param offerPrice
	 */
	public static void wireChildOfOfferPrice(OfferPrice offerPrice){
		List<OfferItem> items = new ArrayList<>();
		if (offerPrice.getOfferItems() != null && !offerPrice.getOfferItems().isEmpty()){
			for (OfferItem item: offerPrice.getOfferItems()){
				if (item.getIsAdded() == null || !item.getIsAdded()){
					item.setOfferPrice(offerPrice);
					items.add(item);
				}
			}
			offerPrice.setOfferItems(items);
		}
	}

	/**
	 * Wired truckingdetails for trucking service
	 * @param truckingservice
	 */
	public static void wiredChildOfTruckingService(
			Truckingservice truckingservice) {
		List<Truckingdetail> items = new ArrayList<>();
		if (truckingservice.getTruckingdetails() != null && !truckingservice.getTruckingdetails().isEmpty()){
			for (Truckingdetail item: truckingservice.getTruckingdetails()){
				if (item.getIsAdded() == null || !item.getIsAdded()){
					item.setTruckingservice(truckingservice);
					items.add(item);
					wireChildOfTruckingdetail(item);
				}
			}
			truckingservice.setTruckingdetails(items);
		}
	}
	
	/**
	 * Wire info 
	 * @param truckingdetail
	 */
	public static void wireChildOfTruckingdetail(Truckingdetail truckingdetail){
		List<Exfeetable> items = new ArrayList<>();
		if (truckingdetail.getExfeetables() != null && !truckingdetail.getExfeetables().isEmpty()){
			for (Exfeetable exfeetable: truckingdetail.getExfeetables()){
				if (exfeetable.getIsAdded() == null || !exfeetable.getIsAdded()){
					exfeetable.setTruckingdetail(truckingdetail);
					items.add(exfeetable);
				}
			}
			truckingdetail.setExfeetables(items);
		}
	}
	

	/**
	 * Create cont and seal map
	 * @param contseals
	 * @return
	 */
	public static ContsealSelections fromContseals(List<Contseal> contseals){
		ContsealSelections contsealSelections  = new ContsealSelections();
		if (contseals != null && !contseals.isEmpty()){
			Map<Long, String> conts = new LinkedHashMap<>();
			Map<Long, String> seals = new LinkedHashMap<>();
			for (Contseal contseal : contseals){
				conts.put(contseal.getId(), contseal.getNoOfCont());
				seals.put(contseal.getId(), contseal.getNoSeal());
			}
			contsealSelections.setConts(conts);
			contsealSelections.setSeal(seals);
		}
		return contsealSelections;
	}

	/**
	 * Add one element exfeetable for truckingdetail
	 * @param truckingdetails
	 */
	public static void updateExfeetableForNewTruckingdetail(
			List<Truckingdetail> truckingdetails,Boolean... add) {
		if (truckingdetails != null && !truckingdetails.isEmpty()){
			for (Truckingdetail truckingdetail : truckingdetails){
				List<Exfeetable> exfeetables = new ArrayList<>();
				Exfeetable exfeetable = new Exfeetable();
				exfeetable.setIsAdded(add.length > 0 ? add[0] : false);
				exfeetables.add(exfeetable);
				truckingdetail.setExfeetables(exfeetables);
			}
		}
	}
	
	/**
	 * 
	 * @param nhathaus
	 * @return
	 */
	public static Map<Long,String> fromNhathauList2Map(List<Nhathau> nhathaus){
		Map<Long,String> nhathauMap = new LinkedHashMap<>();
		
		if (nhathaus != null && !nhathaus.isEmpty()){
			for (Nhathau nhathau : nhathaus){
				nhathauMap.put(nhathau.getId(), nhathau.getFullInfo());
			}
		}
		return nhathauMap;
	}
}
