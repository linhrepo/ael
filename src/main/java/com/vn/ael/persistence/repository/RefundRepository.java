/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import org.appfuse.model.User;

import com.vn.ael.persistence.entity.Refund;

/**
 * @author liv1hc
 */
public interface RefundRepository extends GenericRepository<Refund> {
	
	List<Refund> findByEmployee(User employee);
	
}
