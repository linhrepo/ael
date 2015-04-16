package com.vn.ael.persistence.manager;

import java.util.List;

import org.appfuse.model.User;

import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.webapp.dto.Search;

public interface RefundManager extends GenericManager<Refund> {

	void updateChilds(Refund refund);

	Refund saveWholePackage(Refund refund);
	
	List<Refund> findByEmpoyee(User employee);
	
	List<Refund> searchRefund(Search search);

}
