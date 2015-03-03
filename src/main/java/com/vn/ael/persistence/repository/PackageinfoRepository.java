/**
 * 
 */
package com.vn.ael.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Packageinfo;

/**
 * @author khoi
 */
public interface PackageinfoRepository extends GenericRepository<Packageinfo> {

	@Query("SELECT max(e.counting) FROM Packageinfo e WHERE e.docsgeneral.customer.id = :customerId")
	Integer findMaxCountingByCustomer(@Param("customerId") Long customerId);
}
