package com.vn.ael.persistence.manager;

import java.util.List;

import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Extendfeeacc;

public interface ExfeetableManager extends GenericManager<Exfeetable> {
	
	/**
	 * Update vat 
	 * @param exs
	 */
	void updateVats(List<Extendfeeacc> exs);

}
