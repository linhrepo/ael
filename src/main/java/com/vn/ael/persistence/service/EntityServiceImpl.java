package com.vn.ael.persistence.service;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.BasedEntityTracking;
import com.vn.ael.persistence.manager.ConfigurationManager;
import com.vn.ael.persistence.manager.UserManager;
import com.vn.ael.persistence.repository.CustomerRepository;
import com.vn.ael.persistence.repository.UserRepository;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ConfigurationManager configurationManager = null;
	 
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

}
