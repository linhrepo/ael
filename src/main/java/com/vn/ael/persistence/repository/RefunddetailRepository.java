/**
 * 
 */
package com.vn.ael.persistence.repository;

import java.util.List;

import com.vn.ael.persistence.entity.Advancedetail;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Refunddetail;

/**
 * @author liv1hc
 */
public interface RefunddetailRepository extends GenericRepository<Refunddetail> {

	List<Refunddetail> findByRefund(Refund advanceform);
	
}
