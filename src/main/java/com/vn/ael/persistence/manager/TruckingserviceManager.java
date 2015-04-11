package com.vn.ael.persistence.manager;

import java.util.List;

import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;

public interface TruckingserviceManager extends GenericManager<Truckingservice> {
	
	/**
	 * Check information inside docsgeneral to create new service info
	 * @param docsgeneral
	 * @return
	 */
	public Truckingservice createFromDocsgeneral(Docsgeneral docsgeneral);
	
	/**
	 * Update childs
	 * @param truckingservice
	 */
	public void updateChilds(Truckingservice truckingservice);

	/**
	 * Save whole package
	 * @param truckingservice
	 */
	public void saveWholePackage(Truckingservice truckingservice);
	
	/**
	 * Check to delete removed childs
	 * @param truckingservice
	 */
	public void checkToDeleteChilds(Truckingservice truckingservice);
	
	public void saveTruckingdetail(List<Truckingdetail> truckingdetails);
	
}

