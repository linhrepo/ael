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
	
	@Query("SELECT max(e.id) FROM MoneyBook e WHERE e.typeOfVoucher = :type")
	Integer findMaxCountingByType(@Param("type") Integer type);
	
	@Query("SELECT m FROM MoneyBook m WHERE voucherNo = :voucherNo and id = -1")
	MoneyBook findByVoucherNo(@Param("voucherNo") String voucherNo);
	
	@Query("SELECT m FROM MoneyBook m WHERE typeOfBook = :bookType and id = -2")
	MoneyBook findFistBalance(@Param("bookType") Integer bookType);
}
