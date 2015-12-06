package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	List<Docsgeneral> findAllByCondition(AccountingTransCondition accountingTransCondition,boolean forList);
	
	/**
	 * Update chi ho
	 * @param docsgeneral
	 */
	void updateChiHo(Docsgeneral docsgeneral, boolean addTruck);
	
	/**
	 * Update chi ho
	 * @param docsgeneral
	 */
	void updateDebit(Docsgeneral docsgeneral);
	
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
	
	List<Docsgeneral> searchTrucking(Search search);	
	
	List<Docsgeneral> searchAccounting(Search search);
	
	List<Docsgeneral> searchFeeTables(Search search);
	
	List<String> getAllJob();
	
	List<Docsgeneral> searchShipment(AccountingTransCondition accountingTransCondition);
	
	void updateTongChiPhi(Docsgeneral docsgeneral);
	
	void updateTongChiPhi(List<Docsgeneral> docsgenerals);
	
	List<Docsgeneral> findByDoAccountingAndIsCollectMoney(Boolean doAccounting, Boolean isCollectMoney);
	
	List<Docsgeneral> searchDebit(Search search);
	
	Map<Long,String> getJobForSelection();

	void updateDelivery(Docsgeneral docsgeneral);
	
	//for thu tien khach hang
	void updatePhiAELAndThuHo(Docsgeneral docsgeneral, BigDecimal phiAel, BigDecimal thuHo);

}
