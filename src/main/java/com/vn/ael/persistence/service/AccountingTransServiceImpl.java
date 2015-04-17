package com.vn.ael.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Inland;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.repository.InlandRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.dto.AccountingTrans;

@Service
public class AccountingTransServiceImpl implements AccountingTransService{

	@Autowired
	private TruckingdetailRepository truckingdetailRepository;
	
	@Override
	public void saveWholePackage(AccountingTrans accountingTrans) {
		if (accountingTrans != null && accountingTrans.getDocs() != null && !accountingTrans.getDocs().isEmpty()){
			List<Truckingdetail> truckingdetails = new ArrayList<>();
			for (Docsgeneral docsgeneral : accountingTrans.getDocs()){
				if (docsgeneral.getTruckingservice() != null && docsgeneral.getTruckingservice().getTruckingdetails() != null){
					for (Truckingdetail truckingdetail : docsgeneral.getTruckingservice().getTruckingdetails()){
						if (truckingdetail.getId() != null){
							Truckingdetail storageDetail = truckingdetailRepository.findOne(truckingdetail.getId());
							storageDetail.setAccountingPrice(truckingdetail.getAccountingPrice());
							storageDetail.setOtherFees(truckingdetail.getOtherFees());
							truckingdetails.add(storageDetail);
						}
					}
				}
			}
			truckingdetailRepository.save(truckingdetails);
		}
	}
}
