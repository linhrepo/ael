package com.vn.ael.persistence.manager;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Packageinfo;
import com.vn.ael.webapp.dto.Search;

public interface PackageinfoManager extends GenericManager<Packageinfo> {
	
	/**
	 * Persit whole package to db
	 * @param packageinfo
	 * @return 
	 */
	Packageinfo saveWholePackage(Packageinfo packageinfo);
	
	/**
	 * Delete whole package info
	 * @param packageinfo
	 */
	void deleteWholePackage(Packageinfo packageinfo);
	
	List<Packageinfo> searchPackageInfo(Search search);	
}
