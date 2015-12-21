/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Truckingdetail;

/**
 * @author liv1hc
 */
public interface ExfeetableRepository extends GenericRepository<Exfeetable> {
	
	/**
	 * Find by docsgeneral, with soting condition
	 * @param docsgeneral
	 * @return
	 */
	List<Exfeetable> findByDocsgeneral(Docsgeneral docsgeneral, Sort sort);
	
	/**
	 * Find by docsgeneral
	 * @param docsgeneral
	 * @return
	 */
	List<Exfeetable> findByDocsgeneral(Docsgeneral docsgeneral);
	
	/**
	 * Find by truckingdetail
	 * @param truckingdetail
	 * @return
	 */
	List<Exfeetable> findByTruckingdetail(Truckingdetail truckingdetail);

	/**
	 * Find by truckingdetail
	 * @param truckingdetail
	 * @return
	 */
	@Query("from Exfeetable e where e.truckingdetail.truckingservice.id = :truckingId ")
	List<Exfeetable> findWithTruckingService(@Param(value = "truckingId") long truckingId);
	
	/**
	 * Find by Exhibition
	 * @param exhibition
	 * @return
	 */
	List<Exfeetable> findByExhibition(Exhibition exhibition);
	
	List<Exfeetable> findByRefund(Refund refund);
	
	List<Exfeetable> findByDocsgeneralAndMasterFeeAndNameAndAmountAndVatAndInvoiceNo(Docsgeneral docsgeneral, 
			Configuration masterFee, Configuration name,BigDecimal amount, BigDecimal vat, String invoiceNo);
	
	/**
	 * Find all by docsgeneral
	 * @param truckingdetail
	 * @return
	 */
	@Query("from Exfeetable e where e.docsgeneral is not null ")
	List<Exfeetable> findAllHaveDocsgeneral();
}
