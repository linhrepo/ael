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
import com.vn.ael.persistence.entity.Attachment;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.repository.CustomerRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.ExhibitionRepository;
import com.vn.ael.persistence.repository.RealattachmentRepository;
import com.vn.ael.persistence.repository.UserRepository;
import com.vn.ael.webapp.dto.Search;
import com.vn.ael.webapp.util.EntityUtil;
import com.vn.ael.webapp.util.CommonUtil;

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
    private ExfeetableRepository exfeetableRepository;
    
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
	public Exhibition saveWholeExh(Exhibition exhibition) {
		// wire staff
		exhibition.getDocsgeneral().setCustomer(customerRepository.findOne(exhibition.getDocsgeneral().getCustomer().getId()));
		this.updateJobNo(exhibition);
		exhibition.getDocsgeneral().setTypeOfDocs(ServicesType.EXHS);
		exhibition.getDocsgeneral().setExhibition(exhibition);
		EntityUtil.wiredChildOfDocsGeneral(exhibition.getDocsgeneral());
		Docsgeneral docsgeneral = docsgeneralRepository.save(exhibition.getDocsgeneral());
		return docsgeneral.getExhibition();
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
					+ ServicesType.EXHS.getLabel() + CommonUtil.addZero(exhibition.getCounting(), CommonUtil.LENGTH_OF_COUNTER);
			exhibition.getDocsgeneral().setJobNo(jobNo);
		}

	}

	@Transactional(readOnly = true)
	@Override
	public Exhibition findReportObjects(String id) {
		Exhibition exhibition = this.exhibitionRepository.findOne(new Long(id));
		List<Exfeetable> exfeetables;
		if (exhibition != null){
			exfeetables = this.exfeetableRepository.findByExhibition(exhibition);
		}else{
			exhibition = new Exhibition();
			exfeetables = null;
		}
		
		if (exfeetables == null || exfeetables.isEmpty()){
			exfeetables = new ArrayList<>();
			Exfeetable exfeetable = new Exfeetable();
			exfeetable.setIsAdded(true);
			exfeetables.add(exfeetable);
		}
		try{
			exhibition.setExfeetables(exfeetables);
		}catch (Exception e){
			log.debug("This is auto commit, need to fix later");
		}
		
		return exhibition;
	}

	@Override
	public void saveWholeExhReport(Exhibition exhibition) {
		Exhibition loadExhibition = this.exhibitionRepository.findOne(exhibition.getId());
		loadExhibition.setAttn(exhibition.getAttn());
		loadExhibition.setDateRe(exhibition.getDateRe());
		loadExhibition.setAccountNo(exhibition.getAccountNo());
		loadExhibition.setMode(exhibition.getMode());
		
		if (exhibition.getExfeetables() != null && !exhibition.getExfeetables().isEmpty()){
			//check to add or delete
			List<Exfeetable> exfeetables = new ArrayList<>();
			for (Exfeetable exfeetable : exhibition.getExfeetables()){
				if (exfeetable.getIsAdded() == null || !exfeetable.getIsAdded()){
					if (exfeetable.getIsDeleted() != null && exfeetable.getIsDeleted() == true){
						this.exfeetableRepository.delete(exfeetable);
					}else{
						exfeetable.setExhibition(loadExhibition);
						exfeetables.add(exfeetable);
					}
				}
			}
			loadExhibition.setExfeetables(exfeetables);
		}
		this.exhibitionRepository.save(loadExhibition);
	}

	@Override
	public List<Exhibition> searchInland(Search search) {
		return exhibitionRepository.searchExhibition(search.getCustomer(), search.getTypeOfImport(), search.getDoDelivery(), search.getTypeOfContainer());
	}

}
