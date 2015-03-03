/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import com.vn.ael.persistence.entity.Accountingcus;
import com.vn.ael.persistence.entity.Docsgeneral;

/**
 * @author liv1hc
 */
public interface AccountingcusRepository extends GenericRepository<Accountingcus> {
	
	Accountingcus findByDocsgeneral(Docsgeneral docsgeneral);
	

}
