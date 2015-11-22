package com.vn.ael.persistence.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.AELConst;
import com.vn.ael.persistence.entity.Advanceform;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.entity.Refund;
import com.vn.ael.persistence.repository.AdvanceFormRepository;
import com.vn.ael.persistence.repository.MoneyBookRepository;
import com.vn.ael.webapp.util.CommonUtil;

@Transactional
@Service
public class AccountingMoneyBookManagerImpl extends GenericManagerImpl<MoneyBook> implements AccountingMoneyBookManager {
	
    private MoneyBookRepository moneyBookRepository;
    
    @Autowired
    private AdvanceFormRepository advanceFormRepository;
	
	@Autowired
    public AccountingMoneyBookManagerImpl(final MoneyBookRepository moneyBookRepository) {
        this.moneyBookRepository = moneyBookRepository;
        this.repository = moneyBookRepository;
    }

	
	@Override
	public List<MoneyBook> findByDuration(Date startDate, Date endDate) {
		return this.moneyBookRepository.findByDuration(startDate, endDate);
	}

	@Override
	public List<MoneyBook> findByMoneyVoucher(Integer typeOfVoucher) {
		//return this.moneyBookRepository.findByMoneyVoucher(1);
		return null;
	}
	
	private void updateVoucherNo(MoneyBook moneyBookElement, Integer type) { //0: phieuchi 1: phieuthu
		if (moneyBookElement.getVoucherNo() == null) {
			
			String prefix = (type == 0 ? AELConst.VOUCHER_NO_PREFIX_PAYMENT : AELConst.VOUCHER_NO_PREFIX_RECEPT);
			
			Integer counting = moneyBookRepository
					.findMaxCountingByType(type);
			if (counting == null) {
				counting = (type == 0 ? 
						AELConst.START_COUNT_VOUCHER_PAYMENT : AELConst.START_COUNT_VOUCHER_RECEPT);
			}
			String number = CommonUtil.addZero(counting + 1, CommonUtil.LENGTH_OF_COUNTER);
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String yearString = String.valueOf(year).substring(2);
			
			String voucherNo = prefix + number + "/" + yearString;
			moneyBookElement.setVoucherNo(voucherNo);
		}
	}
	
	@Override
	public MoneyBook insertMoneyBook(Advanceform advanceform) {
		
		//update moneybook 
	    MoneyBook moneyBook = new MoneyBook();
	    moneyBook.setRefNos(advanceform.getMultipleRefCode());
	    moneyBook.setDate(new Date());
	    moneyBook.setTypeOfBook(0);//cashbook
	    moneyBook.setTypeOfVoucher(0);//payment (phieuchi)
	    moneyBook.setDescription(advanceform.getPayReason());
	    moneyBook.setPaymentMoney(advanceform.getMultipleTotal());
	    moneyBook.setBalance(null);
	    moneyBook.setReceptMoney(null);
	    moneyBook = this.moneyBookRepository.save(moneyBook);
	    
	    updateVoucherNo(moneyBook, 0);
	    advanceform.setMoneyBook(moneyBook);
        
	    return moneyBook;
	}
	
	@Override
	public MoneyBook insertMoneyBook(Refund refund) {
		
		//update moneybook 
	    MoneyBook moneyBook = new MoneyBook();
	    moneyBook.setRefNos(refund.getMultipleRefCode());
	    moneyBook.setDate(new Date());
	    moneyBook.setTypeOfBook(0);//cashbook
	    moneyBook.setTypeOfVoucher(0);//payment (phieuchi)
	    moneyBook.setDescription(refund.getPayReason());
	    moneyBook.setPaymentMoney(refund.getMultipleTotal());
	    moneyBook.setBalance(null);
	    moneyBook.setReceptMoney(null);
	    moneyBook = this.moneyBookRepository.save(moneyBook);
	    
	    updateVoucherNo(moneyBook, 0);
	    refund.setMoneyBook(moneyBook);
        
	    return moneyBook;
	}
	
}
