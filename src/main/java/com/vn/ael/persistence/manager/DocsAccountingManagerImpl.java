/**
 *
 */
package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.DocsAccounting;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.repository.DocsAccountingRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.webapp.dto.AccountingCollectMoneyCondition;
import com.vn.ael.webapp.dto.AccountingContractorPaymentCondition;
import com.vn.ael.webapp.dto.AccountingTransportExport;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class DocsAccountingManagerImpl extends GenericManagerImpl<DocsAccounting> implements DocsAccountingManager {

  
    private DocsAccountingRepository docsAccountingRepository;
   
    @Autowired
    private DocsgeneralRepository docsgeneralRepository;
    
    @Autowired
    private TruckingdetailRepository truckingdetailRepository;
    
    @Autowired
    public DocsAccountingManagerImpl(final DocsAccountingRepository docsAccountingRepository) {
        this.docsAccountingRepository = docsAccountingRepository;
        this.truckingdetailRepository = truckingdetailRepository;
        this.repository = docsAccountingRepository;
    }
	
   
    

    
	//for cus & exh
	@Override
	public void updateAccounting(Docsgeneral docsgeneral, BigDecimal phiAel, BigDecimal phiChiHo) {
		//DocsAccounting docAc = docsAccountingRepository.findByDocsgeneral(docsgeneral);
		DocsAccounting docAc = docsgeneral.getDocsAccounting();
		if (docAc == null) {
			docAc = new DocsAccounting();
			docAc.setDocsgeneral(docsgeneral);
			docAc.setPhiAelChuaThu(phiAel);
			docAc.setPhiChiHoChuaThu(phiChiHo);
			docAc.setCollectMoneyStatus(0);
			docsgeneral.setDocsAccounting(docAc);
		} else {
			if (docAc.getCollectMoneyStatus() == 0) {
				docsgeneral.getDocsAccounting().setPhiAelChuaThu(phiAel);
				docsgeneral.getDocsAccounting().setPhiChiHoChuaThu(phiChiHo);
			}
		}
		docsgeneralRepository.save(docsgeneral);
	}

	
	@Override
	public void updateAccounting(List<AccountingTransportExport> list) {//for transport
		List<Long> ids = new ArrayList<Long>();
		Map<Long, BigDecimal[]> mapMoney = new HashMap<Long, BigDecimal[]>();
		
		for (AccountingTransportExport acc : list) {
			ids.add(acc.getJobId());
			BigDecimal[] ar = new BigDecimal[2];
			ar[0] = acc.getTotal();
			ar[1] = acc.getChiho();
			mapMoney.put(acc.getJobId(), ar);
		}
		
		for (Long id : ids) {
			Docsgeneral docsgeneral = docsgeneralRepository.getOne(id);
			DocsAccounting docAc = docsgeneral.getDocsAccounting();
			
			if (docAc == null) {
				docAc = new DocsAccounting();
				docAc.setDocsgeneral(docsgeneral);
				docAc.setPhiAelChuaThu(mapMoney.get(docsgeneral.getId())[0]);
				docAc.setPhiChiHoChuaThu(mapMoney.get(docsgeneral.getId())[1]);
				docAc.setCollectMoneyStatus(0);
				docsgeneral.setDocsAccounting(docAc);
			} else {
				if (docAc.getCollectMoneyStatus() == 0) {
					docsgeneral.getDocsAccounting().setPhiAelChuaThu(mapMoney.get(docsgeneral.getId())[0]);
					docsgeneral.getDocsAccounting().setPhiChiHoChuaThu(mapMoney.get(docsgeneral.getId())[1]);
				}
			}
			docsgeneralRepository.save(docsgeneral);
		}
	}
	
	/*@Override
	public Integer updateCollectMoneyStatus(Long jobId, int feeType) {
		Docsgeneral doc = docsgeneralRepository.getOne(jobId);
		BigDecimal phiAel = doc.getPhiAel();
		BigDecimal phiChiHo = doc.getPhiChiHo();
		int currentCollectMoneyStatus = doc.getCollectMoneyStatus();
		//collect money type
		// 0: chua thu (default)
		// 1: da thu
		// 2: chi moi thu AEL
		// 3: chi moi thu phiChiHo
		if (feeType == 0) {//phiAel
			if (phiChiHo.compareTo(BigDecimal.ZERO) == 0 || currentCollectMoneyStatus == 3) {//phiChiHo = 0 hoac da thu chiho
				doc.setCollectMoneyStatus(1);
			} else {
				doc.setCollectMoneyStatus(2);
			} 
		} else if (feeType == 1) {//phiChiHo
			if (phiAel.compareTo(BigDecimal.ZERO) == 0 || currentCollectMoneyStatus == 2) {//phiAel = 0 hoac da thu ael
				doc.setCollectMoneyStatus(1);
			} else {
				doc.setCollectMoneyStatus(3);
			}
		}

		docsgeneralRepository.save(doc);
		return doc.getCollectMoneyStatus();
	}*/
	
	@Override
	public void updateCollectMoneyStatus(Map<Long, Integer> feeTypeMap) {
		/*Set<Long> listId = (Set<Long>) feeTypeMap.keySet();
		List<Docsgeneral> docs = docsAccountingRepository.findAll(listId);
		
		for (Docsgeneral doc : docs) {
			Integer feeType = feeTypeMap.get(doc.getId());
			
			if (feeType == 2) {//done
				doc.setCollectMoneyStatus(1);
			} else {
				BigDecimal phiAel = doc.getPhiAel();
				BigDecimal phiChiHo = doc.getPhiChiHo();
				if (phiAel == null) phiAel = BigDecimal.ZERO;
				if (phiChiHo == null) phiChiHo = BigDecimal.ZERO;
				int currentCollectMoneyStatus = doc.getCollectMoneyStatus();
				//collect money type
				// 0: chua thu (default)
				// 1: da thu
				// 2: chi moi thu AEL
				// 3: chi moi thu phiChiHo
				if (feeType == 0) {//phiAel
					if (phiChiHo.compareTo(BigDecimal.ZERO) == 0 || currentCollectMoneyStatus == 3) {//phiChiHo = 0 hoac da thu chiho
						doc.setCollectMoneyStatus(1);
					} else {
						doc.setCollectMoneyStatus(2);
					} 
				} else if (feeType == 1) {//phiChiHo
					if (phiAel.compareTo(BigDecimal.ZERO) == 0 || currentCollectMoneyStatus == 2) {//phiAel = 0 hoac da thu ael
						doc.setCollectMoneyStatus(1);
					} else {
						doc.setCollectMoneyStatus(3);
					}
				}
			}
			
			docsAccountingRepository.save(doc);
		}*/
	}
	
	
	@Override
	public List<Docsgeneral> searchDebit(AccountingCollectMoneyCondition search) {
		ServicesType servicesType = null;
		Long cusId = search.getCustomer();
		//only check with docs ready for accounting
		if(search.getTypeOfDocs() == null){
			return docsAccountingRepository.searchDebit(true, servicesType, cusId, search.getCollectMoneyStatus());
		}
		return docsAccountingRepository.searchDebit(true, ServicesType.fromValue(search.getTypeOfDocs().intValue()), cusId, search.getCollectMoneyStatus());
	}


	@Override
	public List<Truckingdetail> searchDocsTruckingFee(AccountingContractorPaymentCondition search) {
		ServicesType servicesType = null;
		Long nhathauId = search.getNhathauId();
		//only check with docs ready for accounting
		
		return truckingdetailRepository.searchTruckingFee(nhathauId, search.getPayMoneyStatus());
	}
}
