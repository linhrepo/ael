package com.vn.ael.persistence.manager;

import java.util.List;

import org.appfuse.model.User;

import com.vn.ael.persistence.entity.Advanceform;

public interface AdvanceFormManager extends GenericManager<Advanceform> {

	void updateChilds(Advanceform advanceform);

	Advanceform saveWholePackage(Advanceform advanceform);
	
	List<Advanceform> findByEmpoyee(User employee);

}
