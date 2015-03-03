/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.AELConst;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.Docservice;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.entity.Packageinfo;
import com.vn.ael.persistence.repository.ContsealRepository;
import com.vn.ael.persistence.repository.CustomerRepository;
import com.vn.ael.persistence.repository.DocserviceRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.OfferItemRepository;
import com.vn.ael.persistence.repository.OfferPriceRepository;
import com.vn.ael.persistence.repository.PackageinfoRepository;
import com.vn.ael.persistence.repository.UserRepository;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class PackageinfoManagerImpl extends GenericManagerImpl<Packageinfo> implements PackageinfoManager{

    private PackageinfoRepository packageinfoRepository;
    
    @Autowired
    private DocsgeneralRepository docsgeneralRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ContsealRepository contsealRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DocserviceRepository docserviceRepository;
    
    @Autowired
    private ExfeetableRepository exfeetableRepository;

    @Autowired
    public PackageinfoManagerImpl(final PackageinfoRepository packageinfoRepository) {
        this.packageinfoRepository = packageinfoRepository;
        this.repository = packageinfoRepository;
    }

	@Override
	public void saveWholePackage(Packageinfo packageinfo) {
		//wire staff
		packageinfo.getDocsgeneral().setCustomer(customerRepository.findOne(packageinfo.getDocsgeneral().getCustomer().getId()));
		packageinfo.getDocsgeneral().setProcessingStaff(userRepository.findOne(packageinfo.getDocsgeneral().getProcessingStaff().getId()));
		this.updateJobNo(packageinfo);
		packageinfo.getDocsgeneral().setTypeOfDocs(ServicesType.DVTQ);
		packageinfo.getDocsgeneral().setPackageinfo(packageinfo);
		EntityUtil.wiredChildOfDocsGeneral(packageinfo.getDocsgeneral());
		docsgeneralRepository.save(packageinfo.getDocsgeneral());
	}
	
	/**
	 * Update Jobno 
	 * @param packageinfo
	 */
	private void updateJobNo(Packageinfo packageinfo){
		if (packageinfo.getId() == null || !packageinfo.getDocsgeneral().getJobNo().startsWith(packageinfo.getDocsgeneral().getCustomer().getCode())){
			if (packageinfo.getId() == null){
				Integer counting = packageinfoRepository.findMaxCountingByCustomer(packageinfo.getDocsgeneral().getCustomer().getId());
				if (counting == null ){
					counting = AELConst.START_COUNT_JOB_ID;
				}
				packageinfo.setCounting(counting+1);
			}
			String jobNo = packageinfo.getDocsgeneral().getCustomer().getCode()+ServicesType.DVTQ.getLabel()+packageinfo.getCounting();
			packageinfo.getDocsgeneral().setJobNo(jobNo);
		}
		
	}

	@Override
	public void deleteWholePackage(Packageinfo packageinfo) {
		docsgeneralRepository.delete(packageinfo.getDocsgeneral());
	}

}
