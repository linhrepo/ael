package com.vn.ael.persistence.manager;

import java.util.List;

import org.appfuse.model.User;

import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.Refund;

public interface RefundManager extends GenericManager<Refund> {

	void updateChilds(Refund refund);

	Refund saveWholePackage(Refund refund);
	
	List<Refund> findByEmpoyee(User employee);

}
