package com.vn.ael.persistence.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.BasicAdvance;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.entity.Refund;

public interface AccountingMoneyBookManager extends GenericManager<MoneyBook> {
	
	MoneyBook insertMoneyBook(MoneyBook moneyBook);
	
	List<MoneyBook> findByDuration(Date startDate, Date endDate);
	
	List<MoneyBook> findByMoneyVoucher(Integer typeOfVoucher);
	
	MoneyBook checkFirstBalance(Integer bookType);
	
	void insertFirstBalance(MoneyBook firstBalance);
	
	Integer getMaxVoucherNo(VoucherType type);
	
	MoneyBook getMoneyBookByVoucherNoAndType(Integer voucherNo, VoucherType voucherType);
	
	void updateBasicAdvance(Advanceform form, MoneyBook book);

	void updateBasicAdvance(Refund form, MoneyBook book);
	
	MoneyBook updateMoneyBook(Long id, Integer voucherNo, Date date, String reason);
}
