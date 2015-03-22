/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import org.appfuse.model.User;

import com.vn.ael.persistence.entity.Advanceform;

/**
 * @author liv1hc
 */
public interface AdvanceFormRepository extends GenericRepository<Advanceform> {
	
	List<Advanceform> findByEmployee(User employee);
	
}
