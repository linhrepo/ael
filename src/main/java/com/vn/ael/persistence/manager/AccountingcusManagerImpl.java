/**
 *
 */
package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.TypeOfContainer;
import com.vn.ael.constants.TypeOfFee;
import com.vn.ael.persistence.entity.Accountingcus;
import com.vn.ael.persistence.entity.Accountingcusdetail;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Extendfeeacc;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.persistence.repository.AccountingcusRepository;
import com.vn.ael.persistence.repository.ContsealRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.persistence.repository.TruckingserviceRepository;
import com.vn.ael.webapp.util.ConvertUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class AccountingcusManagerImpl extends GenericManagerImpl<Accountingcus> implements AccountingcusManager{

    private AccountingcusRepository accountingcusRepository;
    
    @Autowired
    private ExfeetableRepository exfeetableRepository;
    
    @Autowired
    private TruckingserviceRepository truckingserviceRepository;
    
    @Autowired
    private TruckingdetailRepository truckingdetailRepository; 
    
    @Autowired
    private ContsealRepository contsealRepository;
    
    @Autowired
    public AccountingcusManagerImpl(final AccountingcusRepository accountingcusRepository) {
        this.accountingcusRepository = accountingcusRepository;
        this.repository = accountingcusRepository;
    }

	@Override
	public Accountingcus createFromDocsgeneral(Docsgeneral docsgeneral) {
		if (docsgeneral != null){
			Accountingcus accountingcus = accountingcusRepository.findByDocsgeneral(docsgeneral);
			if (accountingcus == null){
				// create new accoutning for custom service
				accountingcus = new Accountingcus();
				accountingcus.setDocsgeneral(docsgeneral);
				this.updateCustomFee(accountingcus);
			}else{
				//TODO:load old
			}
			this.updateChilds(accountingcus);
			return accountingcus;
		}
		return null;
	}

	@Override
	public void saveWholePackage(Accountingcus accountingcus) {
		this.save(accountingcus);
	}
	
	/**
	 * New accounting cus is created, update values
	 * @param accountingcus
	 */
	private void updateChilds(Accountingcus accountingcus){
		if (accountingcus.getAccountingcusdetails() == null || !accountingcus.getAccountingcusdetails().isEmpty()){
			List<Accountingcusdetail> accountingcusdetails =  new ArrayList<>();
			Accountingcusdetail accountingcusdetail = new Accountingcusdetail();
			accountingcusdetail.setIsAdded(true);
			accountingcusdetails.add(accountingcusdetail);
			accountingcus.setAccountingcusdetails(accountingcusdetails);
		}
	}
	
	/**
	 * Calculate chiho and total amount from tablefee
	 * @param accountingcus
	 */
	private void updateCustomFee(Accountingcus accountingcus){
		//calculate trucking fee
		List<Exfeetable> exfeetables = exfeetableRepository.findByDocsgeneral(accountingcus.getDocsgeneral());
		List<Extendfeeacc> chiho = new ArrayList<Extendfeeacc>();
		if (exfeetables != null && !exfeetables.isEmpty()){
			for (Exfeetable exfeetable : exfeetables){
				if (exfeetable.getMasterFee() != null && exfeetable.getMasterFee().getId() == TypeOfFee.CHI_HO_ID){
					Extendfeeacc extendfeeacc = new Extendfeeacc();
					extendfeeacc.setFeeowner(exfeetable);
					//add to chiho list
					chiho.add(extendfeeacc);
				}
			}
			accountingcus.setExtendfeeaccs(chiho);
		}
	}
	

}
