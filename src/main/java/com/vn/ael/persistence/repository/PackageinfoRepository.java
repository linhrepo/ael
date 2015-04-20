/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Packageinfo;

/**
 * @author khoi
 */
public interface PackageinfoRepository extends GenericRepository<Packageinfo> {

	@Query("SELECT max(e.counting) FROM Packageinfo e WHERE e.docsgeneral.customer.id = :customerId")
	Integer findMaxCountingByCustomer(@Param("customerId") Long customerId);
	
	@Query("SELECT e FROM Packageinfo e WHERE (e.docsgeneral.customer.id = :customerId or :customerId is null) and "
			+ "(e.docsgeneral.typeOfImport.id =:typeOfImport or :typeOfImport is null) and "
			+ "(e.docsgeneral.typeOfContainer.id =:typeOfContainer or :typeOfContainer is null) and "
			+ "(e.docsgeneral.doDelivery =:doDelivery or :doDelivery is null) and "
			+ "(e.doRelease =:doRelease or :doRelease is null)")
	List<Packageinfo> searchPackageInfo(@Param("customerId") Long customerId,
			@Param("typeOfImport") Long typeOfImport,
			@Param("typeOfContainer") Long typeOfContainer,
			@Param("doDelivery") Boolean doDelivery,
			@Param("doRelease") Boolean doRelease);
	
	@Query("SELECT DISTINCT e.shipper FROM Packageinfo e WHERE e.shipper != '' and e.shipper is not null")
	List<String> findShipper();
	
	@Query("SELECT DISTINCT e.consignee FROM Packageinfo e")
	List<String> findConsignee();
	
}
 