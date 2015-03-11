/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.entity.Extendfeeacc;
import com.vn.ael.persistence.repository.ConfigurationRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.ExhibitionRepository;
import com.vn.ael.webapp.util.ConvertUtil;
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
					Exfeetable exfeetable2 = exfeetableRepository.getOne(extendfeeacc.getFeeowner().getId());
					if (exfeetable2!=null && exfeetable2.getId() != null){
						exfeetable2.setVat(extendfeeacc.getFeeowner().getVat());
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

}
