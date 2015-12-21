/**
 *
 */
package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Extendfeeacc;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.RefundRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class ExfeetableManagerImpl extends GenericManagerImpl<Exfeetable> implements ExfeetableManager{

	@Autowired
	private DocsgeneralRepository docsgeneralRepository;
	
    private ExfeetableRepository exfeetableRepository;
    
    @Autowired
    private TruckingdetailRepository truckingdetailRepository;
    
    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    public ExfeetableManagerImpl(final ExfeetableRepository exfeetableRepository) {
        this.exfeetableRepository = exfeetableRepository;
        this.repository = exfeetableRepository;
    }

	@Override
	public void updateVats(List<Extendfeeacc> exs) {
		if (exs != null && !exs.isEmpty()){
			List<Exfeetable> exfeetables = new ArrayList<>();
			for (Extendfeeacc extendfeeacc: exs){
				if(extendfeeacc.getFeeowner() != null && extendfeeacc.getFeeowner().getId()!= null){
					Exfeetable exfeetable2 = exfeetableRepository.findOne(extendfeeacc.getFeeowner().getId());
					if (exfeetable2!=null && exfeetable2.getId() != null){
						exfeetable2.setVat(extendfeeacc.getFeeowner().getVat());
						exfeetable2.setTotal(extendfeeacc.getFeeowner().getTotal());
						exfeetables.add(exfeetable2);
					}
				}
			}
			exfeetableRepository.save(exfeetables);
		}
	}

	@Override
	public List<Exfeetable> findByDocsgeneral(Docsgeneral docsgeneral) {
		return this.exfeetableRepository.findByDocsgeneral(docsgeneral, EntityUtil.EXFEETABLE_DEFAULT_SORTING);
	}

	@Override
	public List<Exfeetable> findByDocsgeneral(Long id) {
		Docsgeneral docsgeneral = this.docsgeneralRepository.findOne(id);
		return this.findByDocsgeneral(docsgeneral);
	}

	@Override
	public List<Exfeetable> findByTruckingdetail(Long id) {
		Truckingdetail truckingdetail = truckingdetailRepository.findOne(id);
		return exfeetableRepository.findByTruckingdetail(truckingdetail);
	}

	@Override
	public List<Exfeetable> findByRefund(Long id) {
		Refund refund = refundRepository.findOne(id);
		return exfeetableRepository.findByRefund(refund);
	}
	public boolean updateDuplicated(List<Exfeetable> exfeetables) {
		boolean hasDuplicated = false;
		if (exfeetables != null && !exfeetables.isEmpty()){
			Map<String, Exfeetable> mapFee = new HashMap<String, Exfeetable>();
			
			for (Exfeetable exfeetable : exfeetables) {
				
				String key = exfeetable.getDocsgeneral().getId() + "_" +exfeetable.getMasterFee().getId() + "_" + exfeetable.getName().getId()
						+ "_" + exfeetable.getAmount() + "_" + exfeetable.getVat() + "_" + exfeetable.getInvoiceNo();
				
				if (mapFee.get(key) == null) {
					mapFee.put(key, exfeetable);
				} else {
					mapFee.get(key).setIsDuplicated(true);//go back and set for the first-in exfeetable 
					exfeetable.setIsDuplicated(true);
					hasDuplicated = true;
				}
			}
		}
		return hasDuplicated;
	}

	@Override
	public List<Exfeetable> findAllDuplicates() {
		return exfeetableRepository.findAllDuplicates();
	}

}
