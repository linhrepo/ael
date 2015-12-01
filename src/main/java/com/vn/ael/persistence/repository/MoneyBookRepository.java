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
	
	@Query("SELECT max(e.voucherNo) FROM MoneyBook e WHERE e.typeOfVoucher = :type")
	Integer findMaxVoucherNoByType(@Param("type") Integer type);
	
	@Query("SELECT m FROM MoneyBook m WHERE voucherNo = :voucherNo and typeOfVoucher = :type")
	MoneyBook findByVoucherNoAndType(@Param("voucherNo") Integer voucherNo, @Param("type") Integer type);
	
	@Query("SELECT m FROM MoneyBook m WHERE typeOfBook = :bookType and id = -2")
	MoneyBook findFistBalance(@Param("bookType") Integer bookType);
}
