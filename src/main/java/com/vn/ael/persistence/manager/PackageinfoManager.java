package com.vn.ael.persistence.manager;

import java.util.List;

import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Packageinfo;

public interface PackageinfoManager extends GenericManager<Packageinfo> {
	
	/**
	 * Persit whole package to db
	 * @param packageinfo
	 */
	void saveWholePackage(Packageinfo packageinfo);
	
	/**
	 * Delete whole package info
	 * @param packageinfo
	 */
	void deleteWholePackage(Packageinfo packageinfo);
	
}
