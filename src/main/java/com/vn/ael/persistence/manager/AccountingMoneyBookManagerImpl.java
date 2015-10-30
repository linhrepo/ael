package com.vn.ael.persistence.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.repository.MoneyBookRepository;

@Transactional
@Service
public class AccountingMoneyBookManagerImpl extends GenericManagerImpl<MoneyBook> implements AccountingMoneyBookManager {
	@Autowired
    private MoneyBookRepository moneyBookRepository;
	
	/*@Autowired
    public AccountingMoneyBookManagerImpl(final MoneyBookRepository moneyBookRepository) {
        this.moneyBookRepository = moneyBookRepository;
    }*/

	@Override
	public List<MoneyBook> findByDuration(Date startDate, Date endDate) {
		return this.moneyBookRepository.findByDuration(startDate, endDate);
	}

	@Override
	public List<MoneyBook> findByMoneyVoucher(Integer typeOfVoucher) {
		//return this.moneyBookRepository.findByMoneyVoucher(1);
		return null;
	}
}
