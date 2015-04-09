/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;

/**
 * @author liv1hc
 */
public interface TruckingdetailRepository extends GenericRepository<Truckingdetail> {
	
	/**
	 * 
	 * @param truckingservice
	 * @return
	 */
	List<Truckingdetail> findByTruckingservice(Truckingservice truckingservice);
	
	/**
	 * Fectch fee tables
	 * @param truckingservice
	 * @return
	 */
	@Query("SELECT h FROM Truckingdetail h LEFT JOIN FETCH h.exfeetables WHERE h.truckingservice.id = :serviceId ")
	List<Truckingdetail> findWithFullTruckingservice(@Param("serviceId")Long id);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice where t.nhathau.id=:nhathauId and t.dateDev between :startDate and :endDate group by (t.id)")
	List<Truckingdetail> findAllByConditionDateTime(@Param(value="startDate") Date startDate, @Param(value="endDate") Date endDate, @Param(value="nhathauId")long nhathauId);
	
}
