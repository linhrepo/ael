/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.MoneyBook;

/**
 * @author phuongdbk
 */
public interface MoneyBookRepository extends GenericRepository<MoneyBook> {
	
	@Query("SELECT m FROM MoneyBook m WHERE "
			+ "(m.date >= :startDate or :startDate is null) and (m.date <= :endDate or :endDate is null)  "
			)
	List<MoneyBook> findByDuration(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	/*List<MoneyBook> findByMoneyBook(Integer typeOfBook);*/
	
}
