package com.vn.ael.persistence.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.BasedEntityTracking;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.manager.ConfigurationManager;
import com.vn.ael.persistence.manager.ExfeetableManager;
import com.vn.ael.persistence.manager.UserManager;
import com.vn.ael.persistence.repository.CustomerRepository;
import com.vn.ael.persistence.repository.UserRepository;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ConfigurationManager configurationManager = null;
	 
	@Autowired
	private ExfeetableManager exfeetableManager;
	
	@Override
	public void checkUpdateInfo(BasedEntityTracking basedEntityTracking,
			boolean isNew, HttpServletRequest request) {
		User loggedUser = userManager.getLoggedUser(request);
    	if (isNew){
    		//set creator && created time
    		basedEntityTracking.setCreator(loggedUser);
    		basedEntityTracking.setCreatedDate(Calendar.getInstance().getTime());
    	}
    	else{
    		basedEntityTracking.setCreator(userManager.findById(basedEntityTracking.getCreator().getId()));
    	}
    	basedEntityTracking.setUpdator(loggedUser);
    	basedEntityTracking.setLastUpdateDate(Calendar.getInstance().getTime());
	}
	

	@Override
	public List<Docsgeneral> listContainsDuplicatedFees(
			List<Docsgeneral> docsgenerals) {
		List<Docsgeneral> duplicated = new ArrayList<>();
		if (docsgenerals != null && !docsgenerals.isEmpty()){
			for (Docsgeneral docsgeneral : docsgenerals){
				docsgeneral.setIsContainDuplicated(exfeetableManager.updateDuplicated(exfeetableManager.findByDocsgeneral(docsgeneral)));
				if (docsgeneral.getIsContainDuplicated()){
					duplicated.add(docsgeneral);
				}
			}
		}
		return duplicated;
	}


}
