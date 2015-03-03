package com.vn.ael.persistence.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.persistence.entity.BasedEntityTracking;

public interface EntityService {

	/**
	 * Update creator, updator & date for BasedEntityTracking
	 * @param basedEntityTracking
	 * @param status
	 * @param request
	 */
	void checkUpdateInfo(BasedEntityTracking basedEntityTracking, boolean isNew, HttpServletRequest request);
	
}
