/**
 * 
 */
package com.vn.ael.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Exhibition;

/**
 * @author liv1hc
 */
public interface ExhibitionRepository extends GenericRepository<Exhibition> {

	@Query("SELECT max(e.counting) FROM Exhibition e WHERE e.docsgeneral.customer.id = :customerId")
	Integer findMaxCountingByCustomer(@Param("customerId")Long id);
	
}
