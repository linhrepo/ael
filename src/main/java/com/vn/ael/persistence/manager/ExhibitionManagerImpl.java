/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.AELConst;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.repository.ConfigurationRepository;
import com.vn.ael.persistence.repository.CustomerRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExhibitionRepository;
import com.vn.ael.persistence.repository.UserRepository;
import com.vn.ael.webapp.util.ConvertUtil;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class ExhibitionManagerImpl extends GenericManagerImpl<Exhibition> implements ExhibitionManager{

    private ExhibitionRepository exhibitionRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private DocsgeneralRepository docsgeneralRepository;

    @Autowired
    public ExhibitionManagerImpl(final ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
        this.repository = exhibitionRepository;
    }

	@Override
	public Exhibition findWholeObjects(String id) {
		Exhibition exhibition = this.exhibitionRepository.findOne(new Long(id));
		return exhibition;
	}

	@Override
	public void removeWholeExh(Exhibition exhibition) {
		this.docsgeneralRepository.delete(exhibition.getDocsgeneral());
	}

	@Override
	public void saveWholeExh(Exhibition exhibition) {
		// wire staff
		exhibition.getDocsgeneral().setCustomer(customerRepository.findOne(exhibition.getDocsgeneral().getCustomer().getId()));
		this.updateJobNo(exhibition);
		exhibition.getDocsgeneral().setTypeOfDocs(ServicesType.EXHS);
		exhibition.getDocsgeneral().setExhibition(exhibition);
		EntityUtil.wiredChildOfDocsGeneral(exhibition.getDocsgeneral());
		docsgeneralRepository.save(exhibition.getDocsgeneral());
	}

	/**
	 * Update Jobno
	 * 
	 * @param packageinfo
	 */
	private void updateJobNo(Exhibition exhibition) {
		if (exhibition.getId() == null
				|| !exhibition
						.getDocsgeneral()
						.getJobNo()
						.startsWith(
								exhibition.getDocsgeneral().getCustomer()
										.getCode())) {
			if (exhibition.getId() == null) {
				Integer counting = exhibitionRepository
						.findMaxCountingByCustomer(exhibition.getDocsgeneral()
								.getCustomer().getId());
				if (counting == null) {
					counting = AELConst.START_COUNT_JOB_ID;
				}
				exhibition.setCounting(counting + 1);
			}
			String jobNo = exhibition.getDocsgeneral().getCustomer().getCode()
					+ ServicesType.EXHS.getLabel() + exhibition.getCounting();
			exhibition.getDocsgeneral().setJobNo(jobNo);
		}

	}

}
