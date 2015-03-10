package com.vn.ael.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Inland;
import com.vn.ael.persistence.repository.InlandRepository;
import com.vn.ael.webapp.dto.AccountingTrans;

@Service
public class AccountingTransServiceImpl implements AccountingTransService{

	@Autowired
	private InlandRepository inlandRepository;
	
	@Override
	public void saveWholePackage(AccountingTrans accountingTrans) {
		if (accountingTrans != null && accountingTrans.getDocs() != null && !accountingTrans.getDocs().isEmpty()){
			List<Inland> inlands = new ArrayList<>();
			for (Docsgeneral docsgeneral : accountingTrans.getDocs()){
				Inland inland = inlandRepository.findOne(docsgeneral.getInland().getId());
				inland.setAccountingPrice(docsgeneral.getInland().getAccountingPrice());
				inland.setOtherFees(docsgeneral.getInland().getOtherFees());
				inlands.add(inland);
			}
			inlandRepository.save(inlands);
		}
	}
}
