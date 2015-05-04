/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.enums.ServicesType;
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
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice where (t.nhathau.id =:nhathauId or :nhathauId is null) and "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev < :endDate or :endDate is null) and "
			+ "(t.truckingservice.docsgeneral.jobNo = :jobNo or :jobNo = '') and "
			+ "(t.truckingservice.docsgeneral.customer.id = :customer or :customer is null) "
			+ "group by (t.id) "
			+ "order by t.truckingservice.docsgeneral.jobNo, t.truckingservice.docsgeneral.customer, t.consteal, t.id")
	List<Truckingdetail> searchNhathau(@Param(value="startDate") Date startDate, @Param(value="endDate") Date endDate, 
			@Param(value="nhathauId")Long nhathauId, @Param(value="jobNo")String jobNo, 
			@Param(value="customer")Long customer);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice.docsgeneral d where d.typeOfDocs =:transId and "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev < :endDate or :endDate is null) and "
			+ "(d.customer.id = :customerId or :customerId is null) and "
			+ "(t.nhathau.id = :nhathauId or :nhathauId is null) and "
			+ "(t.truckingservice.docsgeneral.jobNo = :jobNo or :jobNo = '') "
			+ "group by t.id "
			+ "order by d.customer.id, d.jobNo, t.consteal, t.id ")
	List<Truckingdetail> findAllByConditionVantai(@Param(value="transId") ServicesType transId, @Param(value="startDate") Date startDate, 
			@Param(value="endDate")Date endDate, @Param(value="customerId")Long customerId, @Param(value="nhathauId")Long nhathauId, 
			@Param(value="jobNo")String jobNo);

	@Query("from Truckingdetail t LEFT JOIN FETCH t.truckingservice ser where ser.id =:id "
			+"and MONTH(t.dateDev) = :month "
			+"and YEAR(t.dateDev) = :year "
			+ "order by t.consteal, t.nhathau, t.id")
	List<Truckingdetail> findWithTruckingserviceAndMonthYear(@Param("id")Long serviceId,
			@Param("month")Integer month, @Param("year")Integer year);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice.docsgeneral d where d.typeOfDocs =:transId and "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev < :endDate or :endDate is null) and "
			+ "(d.customer.id = :customerId or :customerId is null) and "
			+ "(d.jobNo = :jobNo or :jobNo = '') and "
			+ "(d.packageinfo.consignee = :consignee or :consignee = '') and "
			+ "(d.packageinfo.shipper = :shipper or :shipper = '') "
			+ "group by t.id "
			+ "order by d.customer.id, d.jobNo, t.consteal, t.id ")
	List<Truckingdetail> findAllByConditionPackageInfo(@Param(value="transId") ServicesType transId, @Param(value="startDate") Date startDate, 
			@Param(value="endDate")Date endDate, @Param(value="customerId")Long customerId, @Param(value="jobNo")String jobNo,
			@Param(value="consignee")String consignee, @Param(value="shipper")String shipper);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice.docsgeneral d WHERE "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev < :endDate or :endDate is null) and "
			+ "(d.customer.id = :customerId or :customerId is null) and "
			+ "d.typeOfDocs =:typeOfDocs and "
			+ "(d.jobNo = :jobNo or :jobNo = '') and "
			+ "d.doAccounting =:doAccounting "
			+ "group by t.id "
			+ "order by d.customer.id, d.jobNo, t.consteal, t.id ")
	List<Truckingdetail> searchProfitLoss(@Param("customerId") Long customerId,
			@Param("doAccounting") Boolean doAccounting,
			@Param(value="startDate") Date startDate, 
			@Param(value="endDate")Date endDate,
			@Param(value="typeOfDocs") ServicesType typeOfDocs,
			@Param(value="jobNo") String jobNo);
}
