/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.Date;
import java.util.List;

import org.appfuse.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Refund;

/**
 * @author liv1hc
 */
public interface RefundRepository extends GenericRepository<Refund> {
	
	List<Refund> findByEmployee(User employee);
	
	@Query("SELECT e FROM Refund e WHERE e.isPhieuThu = false and (e.employee.id = :employeeId or :employeeId is null) and "
			+ "(e.date >= :startDate or :startDate is null) and (e.date <= :endDate or :endDate is null) and "
			+ "(e.doApproval =:doApproval or :doApproval is null)")
	List<Refund> searchRefund(@Param("employeeId") Long employeeId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("doApproval") Boolean doApproval);

	@Query("SELECT e FROM Refund e WHERE e.isPhieuThu = false and (e.employee.id = :employeeId or :employeeId is null) and "			
			+ "e.doApproval =:doApproval")
	List<Refund> findByEmployeeAndDoApproval(@Param("employeeId") Long employeeId,
			@Param("doApproval") Boolean doApproval);
	
	@Query("SELECT e FROM Refund e WHERE e.isPhieuThu = true")
	List<Refund> findAllPhieuThu();
	
	@Query("SELECT e FROM Refund e WHERE e.isPhieuThu = true and (e.employee.id = :employeeId or :employeeId is null) and "
			+ "(e.date >= :startDate or :startDate is null) and (e.date <= :endDate or :endDate is null) and "
			+ "(e.doApproval =:doApproval or :doApproval is null)")
	List<Refund> searchPhieuThu(@Param("employeeId") Long employeeId,
			@Param("startDate") Date startDate,
			@Param("endDate") Date endDate,
			@Param("doApproval") Boolean doApproval);
	
	@Query("SELECT e FROM Refund e WHERE e.isPhieuThu is null or e.isPhieuThu = false")
	List<Refund> findAllThanhToan();
	
	@Query("SELECT e FROM Refund e WHERE (e.isPhieuThu is null or e.isPhieuThu = false) and e.employee.id = :employeeId")
	List<Refund> findAllThanhToanEmployee(@Param("employeeId")long id);
	
	
}
