/**
 * 
 */
package com.vn.ael.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Inland;

/**
 * @author liv1hc
 */
public interface InlandRepository extends GenericRepository<Inland> {

	@Query("SELECT max(e.counting) FROM Inland e WHERE e.docsgeneral.customer.id = :customerId")
	Integer findMaxCountingByCustomer(@Param("customerId")Long id);
	
}
