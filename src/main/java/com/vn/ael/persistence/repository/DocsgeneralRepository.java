/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Docsgeneral;

/**
 * @author khoi
 */
public interface DocsgeneralRepository extends GenericRepository<Docsgeneral> {
	
	/**
	 * Find all by doDelivery value
	 * @param doDelivery
	 * @return
	 */
	List<Docsgeneral> findByDoDelivery(Boolean doDelivery);
	
	/**
	 * Find all by doAccounting value
	 * @param doAccounting
	 * @return
	 */
	List<Docsgeneral> findByDoAccounting(Boolean doAccounting);
	
	/**
	 * Find all by doAccounting value
	 * @param doAccounting
	 * @return
	 */
	List<Docsgeneral> findByDoAccountingAndTypeOfDocs(Boolean doAccounting, ServicesType typeOfDocs);
	
	/**
	 * Find all by doAccounting and Inland/Sealand
	 * @param doAccounting
	 * @return
	 */
	@Query("from Docsgeneral d where d.doAccounting = :doAccounting and (d.typeOfDocs = :inland or d.typeOfDocs = :seaType) ")
	List<Docsgeneral> getDoAccountingInlandSealand(@Param(value = "doAccounting")Boolean doAccounting,@Param(value = "inland")ServicesType inland,@Param(value = "seaType")ServicesType seaType);
	
	/**
	 * Find all by doAccounting and Inland/Sealand
	 * @param doAccounting
	 * @return
	 */
//	@Query("from Docsgeneral d where d.customer.id=:customerId and d.doAccounting = :doAccounting and (d.typeOfDocs = :inland or d.typeOfDocs = :seaType) "
//			+"and MONTH(d.inland.createdDate) = :month "
//			+"and YEAR(d.inland.createdDate) = :year "
//			)
//	//TODO: Find correct date for comparing
//	List<Docsgeneral> getDoAccountingInlandSealandAndTime(@Param(value = "doAccounting")Boolean doAccounting,@Param(value = "inland")ServicesType inland,@Param(value = "seaType")ServicesType seaType
//			,@Param(value="month") int month, @Param(value="year") int year, @Param(value="customerId")long customerId);
	
	@Query("select d from Docsgeneral d left join fetch d.truckingservice ser left join fetch ser.truckingdetails detail "
	+ "where d.customer.id=:customerId and d.doAccounting = :doAccounting and (d.typeOfDocs = :inland or d.typeOfDocs = :seaType) "
	+"and MONTH(detail.dateDev) = :month "
	+"and YEAR(detail.dateDev) = :year "
	+ "group by d"
	)
	List<Docsgeneral> getDoAccountingInlandSealandAndTime(@Param(value = "doAccounting")Boolean doAccounting,@Param(value = "inland")ServicesType inland,@Param(value = "seaType")ServicesType seaType
			,@Param(value="month") int month, @Param(value="year") int year, @Param(value="customerId")long customerId);
	
	@Query("from Docsgeneral d where d.shippingLine.id=:nhathauId and d.doAccounting = :doAccounting and d.docReceiveDate between :startDate and :endDate")
	List<Docsgeneral> findAllByConditionDateTime(@Param(value = "doAccounting")Boolean doAccounting,
			@Param(value="startDate") Date startDate, @Param(value="endDate") Date endDate, @Param(value="nhathauId")long nhathauId);
	
	@Query("SELECT e FROM Docsgeneral e WHERE (e.customer.id = :customerId or :customerId is null) and "
			+ "(e.typeOfImport.id =:typeOfImport or :typeOfImport is null) and "
			+ "(e.typeOfDocs =:typeOfDocs or :typeOfDocs is null) and "
			+ "(e.typeOfContainer.id =:typeOfContainer or :typeOfContainer is null) and "
			+ "(e.doAccounting =:doAccounting or :doAccounting is null) and "
			+ "(e.doDelivery =:doDelivery or :doDelivery is null)")
	List<Docsgeneral> searchTrucking(@Param("customerId") Long customerId,
			@Param("typeOfImport") Long typeOfImport,
			@Param("typeOfContainer") Long typeOfContainer,
			@Param("doAccounting") Boolean doAccounting,
			@Param(value="typeOfDocs") ServicesType typeOfDocs,
			@Param(value="doDelivery") Boolean doDelivery);
	
	@Query("SELECT e FROM Docsgeneral e WHERE (e.customer.id = :customerId or :customerId is null) and "
			+ "(e.typeOfImport.id =:typeOfImport or :typeOfImport is null) and "
			+ "e.typeOfDocs =:typeOfDocs and "
			+ "(e.typeOfContainer.id =:typeOfContainer or :typeOfContainer is null) and "
			+ "e.doAccounting =:doAccounting")
	List<Docsgeneral> searchAccounting(@Param("customerId") Long customerId,
			@Param("typeOfImport") Long typeOfImport,
			@Param("typeOfContainer") Long typeOfContainer,
			@Param("doAccounting") Boolean doAccounting,
			@Param(value="typeOfDocs") ServicesType typeOfDocs);
	
	@Query("SELECT DISTINCT e.jobNo FROM Docsgeneral e")
	List<String> getAllJob();
	
	@Query("SELECT e FROM Docsgeneral e WHERE (e.customer.id = :customerId or :customerId is null) and "
			+ "(e.typeOfImport.id =:typeOfImport or :typeOfImport is null) and "
			+ "e.typeOfDocs =:typeOfDocs and "
			+ "(e.packageinfo.customsDate between :startDate and :endDate) and "
			+ "(e.packageinfo.consignee = :consignee or :consignee = '') and "
			+ "(e.packageinfo.shipper = :shipper or :shipper = '') and "
			+ "(e.jobNo = :jobNo or :jobNo = '') and "
			+ "e.doAccounting =:doAccounting")
	List<Docsgeneral> searchShipment(@Param("customerId") Long customerId,
			@Param("typeOfImport") Long typeOfImport,
			@Param("doAccounting") Boolean doAccounting,
			@Param(value="typeOfDocs") ServicesType typeOfDocs,
			@Param(value="startDate") Date startDate,
			@Param(value="endDate") Date endDate,
			@Param(value="consignee") String consignee,
			@Param(value="shipper") String shipper,
			@Param(value="jobNo") String jobNo);
}
