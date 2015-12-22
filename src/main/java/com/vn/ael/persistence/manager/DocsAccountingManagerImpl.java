/**
 *
 */
package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.TypeOfContainer;
import com.vn.ael.constants.TypeOfFee;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Accountingcus;
import com.vn.ael.persistence.entity.Accountingcusdetail;
import com.vn.ael.persistence.entity.Attachment;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.DocsAccounting;
import com.vn.ael.persistence.entity.Docservice;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Inlandsize;
import com.vn.ael.persistence.entity.Multitype;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.repository.AttachmentRepository;
import com.vn.ael.persistence.repository.ContsealRepository;
import com.vn.ael.persistence.repository.DocsAccountingRepository;
import com.vn.ael.persistence.repository.DocserviceRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.InlandsizeRepository;
import com.vn.ael.persistence.repository.MultitypeRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.persistence.repository.TruckingserviceRepository;
import com.vn.ael.webapp.dto.AccountingCollectMoneyCondition;
import com.vn.ael.webapp.dto.AccountingContractorPaymentCondition;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.dto.AccountingTransportExport;
import com.vn.ael.webapp.dto.Search;
import com.vn.ael.webapp.util.CommonUtil;
import com.vn.ael.webapp.util.ConvertUtil;
import com.vn.ael.webapp.util.EntityUtil;

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
    public DocsAccountingManagerImpl(final DocsAccountingRepository docsAccountingRepository) {
        this.docsAccountingRepository = docsAccountingRepository;
        this.repository = docsAccountingRepository;
    }
	
	//for thu tien khach hang
	@Override
	public void updatePhiAELAndChiHo(Docsgeneral docsgeneral, BigDecimal phiAel, BigDecimal phiChiHo) {
		DocsAccounting docAc = docsAccountingRepository.findByDocsgeneral(docsgeneral);
		
		if (docAc == null) {
			docAc = new DocsAccounting();
			docAc.setDocsgeneral(docsgeneral);
			docAc.setPhiAel(phiAel);
			docAc.setPhiChiHo(phiChiHo);
		} else {
			docAc.setPhiAel(phiAel);
			docAc.setPhiChiHo(phiChiHo);
		}
		docsAccountingRepository.save(docAc);
	}

	
	@Override
	public void updatePhiAELAndChiHo(List<AccountingTransportExport> list) {
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
			DocsAccounting docAc = docsAccountingRepository.findByDocsgeneral(docsgeneral);
			
			if (docAc == null) {
				docAc = new DocsAccounting();
				docAc.setDocsgeneral(docsgeneral);
				docAc.setPhiAel(mapMoney.get(docsgeneral.getId())[0]);
				docAc.setPhiChiHo(mapMoney.get(docsgeneral.getId())[1]);
			} else {
				docAc.setPhiAel(mapMoney.get(docsgeneral.getId())[0]);
				docAc.setPhiChiHo(mapMoney.get(docsgeneral.getId())[1]);
			}
			docsAccountingRepository.save(docAc);
			
			/*List<DocsAccounting> docAcs = docsAccountingRepository.findByDocsgeneralIds(ids);
			for (DocsAccounting doc : docAcs) {
				doc.setPhiAel(mapMoney.get(doc.getId())[0]);
				doc.setPhiChiHo(mapMoney.get(doc.getId())[1]);
			}
			
			docsAccountingRepository.save(docAcs);*/
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

}
