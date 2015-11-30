package com.vn.ael.persistence.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.VoucherType;
import com.vn.ael.persistence.entity.BasicAdvance;
import com.vn.ael.persistence.entity.MoneyBook;
import com.vn.ael.persistence.repository.AdvanceFormRepository;
import com.vn.ael.persistence.repository.MoneyBookRepository;

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
			
			Integer counting = moneyBookRepository.findMaxVoucherNoByType(type);
			
			moneyBookElement.setVoucherNo((long) (counting + 1));
		}
	}
	
	
	
	@Override
	public MoneyBook insertMoneyBook(BasicAdvance form, VoucherType typeOfVoucher) {
		
		//update moneybook 
	    MoneyBook moneyBook = new MoneyBook();
	    moneyBook.setRefNos(form.getMultipleRefCode());
	    moneyBook.setTypeOfBook(0);//cashbook
	    
	    moneyBook.setTypeOfVoucher(typeOfVoucher.getValue());//payment (phieuchi)
	    moneyBook.setDescription(form.getPayReason());
	    moneyBook.setPaymentMoney(form.getMultipleTotal());
	    moneyBook.setReceptMoney(form.getMultipleTotal());
	    
	    moneyBook.setDate(null);
	    moneyBook.setBalance(null);
	    
	    moneyBook = this.moneyBookRepository.save(moneyBook);
	    
	    updateVoucherNo(moneyBook, VoucherType.PHIEUCHI.getValue());
	    form.setMoneyBook(moneyBook);
        
	    return moneyBook;
	}
	
	/*@Override
	public MoneyBook insertMoneyBook(Refund refund, VoucherType typeOfVoucher) {
		
		//update moneybook 
	    MoneyBook moneyBook = new MoneyBook();
	    moneyBook.setRefNos(refund.getMultipleRefCode());
	    moneyBook.setTypeOfBook(0);//cashbook
	    moneyBook.setTypeOfVoucher(typeOfVoucher.getValue());
	    moneyBook.setDescription(refund.getPayReason());
	    if (typeOfVoucher.getValue() == 0) { 
	    	moneyBook.setPaymentMoney(refund.getMultipleTotal());
	    } else {
	    	moneyBook.setReceptMoney(refund.getMultipleTotal());
	    }
	    moneyBook.setBalance(null);
	    moneyBook = this.moneyBookRepository.save(moneyBook);
	    
	    updateVoucherNo(moneyBook, typeOfVoucher.getValue());
	    refund.setMoneyBook(moneyBook);
        
	    return moneyBook;
	}*/
	
	@Override
	public void updateMoneyBook(Long id, Long voucherNo, String reason) {
		MoneyBook moneyBook = this.moneyBookRepository.getOne(id);//or findOne???
		if (moneyBook != null) {
			moneyBook.setVoucherNo(voucherNo);
			moneyBook.setDescription(reason);
			this.moneyBookRepository.save(moneyBook);
		}
	}
	
	
	@Override
	public MoneyBook checkFirstBalance(Integer bookType) {
		MoneyBook moneyBook = this.moneyBookRepository.findFistBalance(bookType);

		return moneyBook;
	}
	
	@Override
	public void insertFirstBalance(MoneyBook firstBalance) {
		this.moneyBookRepository.save(firstBalance);
	}
	
	@Override
	public Integer getMaxVoucherNo(VoucherType type) {
		return this.moneyBookRepository.findMaxVoucherNoByType(type.getValue());
	}
	
}
