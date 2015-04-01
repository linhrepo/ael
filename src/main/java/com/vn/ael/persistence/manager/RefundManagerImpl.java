/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.persistence.entity.Advancedetail;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Refunddetail;
import com.vn.ael.persistence.repository.AdvanceFormRepository;
import com.vn.ael.persistence.repository.AdvancedetailRepository;
import com.vn.ael.persistence.repository.RefundRepository;
import com.vn.ael.persistence.repository.RefunddetailRepository;
import com.vn.ael.persistence.repository.UserRepository;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class RefundManagerImpl extends GenericManagerImpl<Refund> implements RefundManager{

    private RefundRepository refundRepository;
    
    @Autowired
    private RefunddetailRepository refunddetailRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    public RefundManagerImpl(final RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
        this.repository = refundRepository;
    }

	@Override
	public void updateChilds(Refund refund) {
		if (refund != null && refund.getId() != null) {
			refund.setRefunddetails(refunddetailRepository.findByRefund(refund));
		}

		if (refund.getRefunddetails() == null
				|| refund.getRefunddetails().isEmpty()) {
			List<Refunddetail> items = new ArrayList<>();
			Refunddetail refunddetail = new Refunddetail();
			refunddetail.setIsAdded(true);
			items.add(refunddetail);
			refund.setRefunddetails(items);
		}	
	}

	@Override
	public Refund saveWholePackage(Refund refund) {
		this.checkToDeleteChilds(refund);
		refund.setEmployee(userRepository.getOne(refund.getEmployee().getId()));
		EntityUtil.wireChildOfRefund(refund);
		return refundRepository.save(refund);
	}

	/**
	 * Check to delete childs
	 * @param advanceform
	 */
	private void checkToDeleteChilds(Refund refund) {
		if (refund.getRefunddetails() != null){
			List<Refunddetail> items = new ArrayList<>();
			for (Refunddetail refunddetail: refund.getRefunddetails()){
				if (refunddetail.getIsDeleted() != null && refunddetail.getIsDeleted() == true){
					refunddetailRepository.delete(refunddetail);
				}else{
					items.add(refunddetail);
				}
			}
			refund.setRefunddetails(items);
		}
	}

	@Override
	public List<Refund> findByEmpoyee(User employee) {
		return this.refundRepository.findByEmployee(employee);
	}
}