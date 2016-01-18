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
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) and "
			+ "(t.truckingservice.docsgeneral.jobNo = :jobNo or :jobNo = '') and "
			+ "(t.truckingservice.docsgeneral.customer.id = :customer or :customer is null) "
			+ "group by (t.id) "
			+ "order by t.truckingservice.docsgeneral.jobNo, t.truckingservice.docsgeneral.customer, t.consteal, t.id")
	List<Truckingdetail> searchNhathau(@Param(value="startDate") Date startDate, @Param(value="endDate") Date endDate, 
			@Param(value="nhathauId")Long nhathauId, @Param(value="jobNo")String jobNo, 
			@Param(value="customer")Long customer);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.truckingservice where (t.nhathau.id =:nhathauId or :nhathauId is null) and "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) and "
			+ "(t.truckingservice.docsgeneral.jobNo = :jobNo or :jobNo = '') and "
			+ "(t.truckingservice.docsgeneral.customer.id = :customer or :customer is null) "
			+ "group by (t.id) "
			+ "order by t.truckingservice.docsgeneral.jobNo, t.truckingservice.docsgeneral.customer, t.consteal, t.id")
	List<Truckingdetail> searchPreNhathau(@Param(value="startDate") Date startDate, @Param(value="endDate") Date endDate, 
			@Param(value="nhathauId")Long nhathauId, @Param(value="jobNo")String jobNo, 
			@Param(value="customer")Long customer);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice.docsgeneral d where d.typeOfDocs =:transId and "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) and "
			+ "(d.customer.id = :customerId or :customerId is null) and "
			+ "(t.nhathau.id = :nhathauId or :nhathauId is null) and "
			+ "(t.truckingservice.docsgeneral.jobNo = :jobNo or :jobNo = '') "
			+ "group by t.id "
			+ "order by d.customer.id, d.jobNo, t.consteal, t.id ")
	List<Truckingdetail> findAllByConditionVantai(@Param(value="transId") ServicesType transId, @Param(value="startDate") Date startDate, 
			@Param(value="endDate")Date endDate, @Param(value="customerId")Long customerId, @Param(value="nhathauId")Long nhathauId, 
			@Param(value="jobNo")String jobNo);

	@Query("from Truckingdetail t LEFT JOIN FETCH t.truckingservice ser where ser.id =:id "
			+"and (t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) "
			+ "order by t.consteal, t.nhathau, t.id")
	List<Truckingdetail> findWithTruckingserviceAndMonthYear(@Param("id")Long serviceId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables LEFT JOIN FETCH t.truckingservice.docsgeneral d where d.typeOfDocs =:transId and "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) and "
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
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) and "
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
	

	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables f LEFT JOIN FETCH t.truckingservice LEFT JOIN FETCH t.truckingservice.docsgeneral d where "
			+ "(t.dateDev >= :startDate or :startDate is null) and (t.dateDev <= :endDate or :endDate is null) and "
			+ "(t.nhathau.id = :nhathau or :nhathau = '') and "
			+ "(f.checkByAdmin =:checkByAdmin or :checkByAdmin is null) and "
			+ "(f.approved =:approved or :approved is null) and "
			+ "d.doAccounting =:doAccounting "
			+ "group by (t.id) "
			+ "order by t.truckingservice.docsgeneral.jobNo, t.truckingservice.docsgeneral.customer, t.consteal, t.id")
	List<Truckingdetail> searchFeeNhathau(@Param(value="startDate") Date startDate, @Param(value="endDate") Date endDate, 
			@Param(value="nhathau")Long nhathau, @Param("doAccounting") Boolean doAccounting,
			@Param(value="checkByAdmin") Boolean checkByAdmin, @Param(value="approved") Boolean approved);
	
	@Query("from Truckingdetail t LEFT JOIN FETCH t.exfeetables f LEFT JOIN FETCH t.truckingservice LEFT JOIN FETCH t.truckingservice.docsgeneral d where "
			+ "d.doAccounting =:doAccounting "
			+ "group by (t.id) "
			+ "order by t.consteal, t.id")
	List<Truckingdetail> findByDoAccounting(@Param("doAccounting") Boolean doAccounting);
	
	//for accounting
	@Query("SELECT e FROM Truckingdetail e inner join fetch e.truckAccounting f WHERE "
			+ "(e.nhathau.id = :nhathau or :nhathau is null) and "
			+ "(f.payMoneyStatus =:payMoneyStatus or :payMoneyStatus is null) "
			)
	List<Truckingdetail> searchTruckingFee(
			@Param(value = "nhathau") Long nhathau,
			@Param(value = "payMoneyStatus") Integer payMoneyStatus);
}
