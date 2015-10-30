package com.vn.ael.persistence.manager;

import java.util.Date;
import java.util.List;

import com.vn.ael.persistence.entity.MoneyBook;

public interface AccountingMoneyBookManager extends GenericManager<MoneyBook> {
	
	List<MoneyBook> findByDuration(Date startDate, Date endDate);
	
	List<MoneyBook> findByMoneyVoucher(Integer typeOfVoucher);
}
