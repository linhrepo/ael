/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.Date;
import java.util.List;

import org.appfuse.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Advanceform;

/**
 * @author liv1hc
 */
public interface AdvanceFormRepository extends GenericRepository<Advanceform> {
	
	List<Advanceform> findByEmployee(User employee);
	
	@Query("SELECT e FROM Advanceform e WHERE (e.employee.id = :employeeId or :employeeId is null) and "
			+ "(e.date between :startDate and :endDate) and "
			+ "(e.timeRefund between :startTimeRefund and :endTimeRefund) and "
			+ "(e.doApproval =:doApproval or :doApproval is null)")
	List<Advanceform> searchAdvanceForm(@Param("employeeId") Long employeeId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("startTimeRefund") Date startTimeRefund,
			@Param("endTimeRefund") Date endTimeRefund,
			@Param("doApproval") Boolean doApproval);
	
}
