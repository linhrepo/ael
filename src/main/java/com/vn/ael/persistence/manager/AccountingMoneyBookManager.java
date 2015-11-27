package com.vn.ael.persistence.manager;

import java.util.Date;
import java.util.List;

import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.entity.Refund;

public interface AccountingMoneyBookManager extends GenericManager<MoneyBook> {
	
	MoneyBook insertMoneyBook(Advanceform advanceform);
	
	MoneyBook insertMoneyBook(Refund refund, VoucherType type);
	
	List<MoneyBook> findByDuration(Date startDate, Date endDate);
	
	List<MoneyBook> findByMoneyVoucher(Integer typeOfVoucher);
	
	void updateReason(String voucherNo, String reason);
}
