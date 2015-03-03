/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
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
	
}
