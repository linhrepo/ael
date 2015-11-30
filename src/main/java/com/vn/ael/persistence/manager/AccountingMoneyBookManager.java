package com.vn.ael.persistence.manager;

import java.util.Date;
import java.util.List;

import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.BasicAdvance;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.entity.Refund;

public interface AccountingMoneyBookManager extends GenericManager<MoneyBook> {
	
	MoneyBook insertMoneyBook(BasicAdvance advanceform, VoucherType typeOfVoucher);
	
	List<MoneyBook> findByDuration(Date startDate, Date endDate);
	
	List<MoneyBook> findByMoneyVoucher(Integer typeOfVoucher);
	
	void updateMoneyBook(Long id, Long voucherNo, String reason);
	
	MoneyBook checkFirstBalance(Integer bookType);
	
	void insertFirstBalance(MoneyBook firstBalance);
	
	Integer getMaxVoucherNo(VoucherType type);
}
