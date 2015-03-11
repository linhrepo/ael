package com.vn.ael.persistence.manager;

import com.vn.ael.persistence.entity.Exhibition;

public interface ExhibitionManager extends GenericManager<Exhibition> {

	/**
	 * Find whold object based on id
	 * @param id
	 * @return
	 */
	Exhibition findWholeObjects(String id);

	/**
	 * Remove whole objects
	 * @param exhibition
	 */
	void removeWholeExh(Exhibition exhibition);

	/**
	 * Persit whole object
	 * @param exhibition
	 */
	void saveWholeExh(Exhibition exhibition);

	/**
	 * Load information for report
	 * @param id
	 * @return
	 */
	Exhibition findReportObjects(String id);

	/**
	 * Update values for exhibition report
	 * @param exhibition
	 */
	void saveWholeExhReport(Exhibition exhibition);

}
