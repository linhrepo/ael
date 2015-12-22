/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.DocsAccounting;
import com.vn.ael.persistence.entity.Docsgeneral;

/**
 * @author phuongdbk
 */
public interface DocsAccountingRepository extends GenericRepository<DocsAccounting> {
	/**
	 * Find by docsgeneral
	 * @param docs id
	 * @return
	 */
	DocsAccounting findByDocsgeneral(Docsgeneral docsgeneral);
	
	@Query("SELECT da FROM DocsAccounting da WHERE da.docsgeneral.id in :listId")
	List<DocsAccounting> findByDocsgeneralIds(@Param("listId") List<Long> listId);
}

