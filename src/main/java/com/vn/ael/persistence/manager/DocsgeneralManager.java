package com.vn.ael.persistence.manager;

import java.util.List;

import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.dto.Search;

public interface DocsgeneralManager extends GenericManager<Docsgeneral> {
	
	/**
	 * Verify to delete childs
	 * @param docsgeneral
	 */
	void checkToDeleteChilds(Docsgeneral docsgeneral);
	
	/**
	 * Load all necessary childs
	 * @param docsgeneral
	 */
	void updateChilds(Docsgeneral docsgeneral);
	
	/**
	 * 
	 * @param doDelivery
	 * @return
	 */
	List<Docsgeneral> findByDoDelivery(Boolean doDelivery);

	/**
	 * Update accounting value of docsgeneral
	 * @param docsgeneral
	 */
	void updateAccounting(Docsgeneral docsgeneral);

	/**
	 * Find all which has been sent to accounting
	 * @param doAccounting
	 * @return
	 */
	List<Docsgeneral> findByDoAccounting(Boolean doAccounting);
	
	/**
	 * 
	 * @param doAccounting
	 * @param inland
	 * @param exhibition
	 * @return
	 */
	List<Docsgeneral> findByDoAccountingAndTypeOfDocs(
			Boolean doAccounting, ServicesType servicesType);
	
	/**
	 * 
	 * @param doAccounting
	 * @param inland
	 * @param seaType
	 * @return
	 */
	List<Docsgeneral> findDoAccountingInlandSealand(Boolean doAccounting);
	
	/**
	 * Find all based on condition
	 * @param accountingTransCondition
	 * @return
	 */
	List<Docsgeneral> findAllByCondition(AccountingTransCondition accountingTransCondition);
	
	/**
	 * Count consteal
	 * @param docsgeneral
	 */
	void updateContTruck(Docsgeneral docsgeneral);
	
	/**
	 * Count consteal
	 * @param docsgeneral
	 */
	void updateContTruckDetail(Docsgeneral docsgeneral);
	
	List<Docsgeneral> findAllByConditionDateTime(AccountingTransCondition accountingTransCondition);
	
	List<Docsgeneral> searchTrucking(Search search);	
	
	List<Docsgeneral> searchAccounting(Search search);
	
	List<Docsgeneral> searchFeeTables(Search search);
	
	List<String> getAllJob();
	
	List<Docsgeneral> searchShipment(AccountingTransCondition accountingTransCondition);
	
	List<Docsgeneral> viewProfitLoss(AccountingTransCondition accountingTransCondition);
}
