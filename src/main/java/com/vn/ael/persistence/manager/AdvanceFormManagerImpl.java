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
import com.vn.ael.persistence.repository.AdvanceFormRepository;
import com.vn.ael.persistence.repository.AdvancedetailRepository;
import com.vn.ael.persistence.repository.UserRepository;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class AdvanceFormManagerImpl extends GenericManagerImpl<Advanceform> implements AdvanceFormManager{

    private AdvanceFormRepository advanceFormRepository;
    
    @Autowired
    private AdvancedetailRepository advancedetailRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    public AdvanceFormManagerImpl(final AdvanceFormRepository advanceFormRepository) {
        this.advanceFormRepository = advanceFormRepository;
        this.repository = advanceFormRepository;
    }

	@Override
	public void updateChilds(Advanceform advanceform) {
		if (advanceform != null && advanceform.getId() != null) {
			advanceform.setAdvancedetails(advancedetailRepository.findByAdvanceform(advanceform));
		}

		if (advanceform.getAdvancedetails() == null
				|| advanceform.getAdvancedetails().isEmpty()) {
			List<Advancedetail> items = new ArrayList<>();
			Advancedetail advancedetail = new Advancedetail();
			advancedetail.setIsAdded(true);
			items.add(advancedetail);
			advanceform.setAdvancedetails(items);
		}	
	}

	@Override
	public Advanceform saveWholePackage(Advanceform advanceform) {
		this.checkToDeleteChilds(advanceform);
		advanceform.setEmployee(userRepository.getOne(advanceform.getEmployee().getId()));
		EntityUtil.wireChildOfAdvanceform(advanceform);
		return advanceFormRepository.save(advanceform);
	}

	/**
	 * Check to delete childs
	 * @param advanceform
	 */
	private void checkToDeleteChilds(Advanceform advanceform) {
		if (advanceform.getAdvancedetails() != null){
			List<Advancedetail> items = new ArrayList<>();
			for (Advancedetail advancedetail: advanceform.getAdvancedetails()){
				if (advancedetail.getIsDeleted() != null && advancedetail.getIsDeleted() == true){
					advancedetailRepository.delete(advancedetail);
				}else{
					items.add(advancedetail);
				}
			}
			advanceform.setAdvancedetails(items);
		}
	}

	@Override
	public List<Advanceform> findByEmpoyee(User employee) {
		return null;
	}
}
