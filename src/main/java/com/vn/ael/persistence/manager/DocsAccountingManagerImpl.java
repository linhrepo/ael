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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.DocsAccounting;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.TruckAccounting;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.repository.DocsAccountingRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.TruckAccountingRepository;
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
    private TruckAccountingRepository truckAccountingRepository;
    
    @Autowired
    public DocsAccountingManagerImpl(final DocsAccountingRepository docsAccountingRepository) {
        this.docsAccountingRepository = docsAccountingRepository;
        this.truckingdetailRepository = truckingdetailRepository;
        this.truckAccountingRepository = truckAccountingRepository;
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
			Long id = acc.getJobId();
			if (mapMoney.get(id) == null) {
				ids.add(acc.getJobId());
				BigDecimal[] ar = new BigDecimal[2];
				ar[0] = acc.getTotal();
				ar[1] = acc.getChiho();
				mapMoney.put(id, ar);
			} else {
				BigDecimal[] ar = mapMoney.get(id);
				//update total from all trucking
				ar[0] = ar[0].add(acc.getTotal());
				//no need to update chiho, because it count only once for each job
				mapMoney.put(id, ar);
			}
			
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
	
	@Override
	public void updateCollectMoneyStatus(Map<Long, DocsAccounting> accountingMap) {
		Set<Long> listId = (Set<Long>) accountingMap.keySet();
		//List<Docsgeneral> docs = docsgeneralRepository.findAll(listId);
		for (Long id : listId) {
			Docsgeneral doc = docsgeneralRepository.getOne(id);
			DocsAccounting docsAcDb = docsAccountingRepository.getOne(doc.getDocsAccounting().getId());
			
			//input docsAc
			DocsAccounting docsAc = accountingMap.get(doc.getId());
			
			//DocsAccounting docsDb = doc.getDocsAccounting();
			BigDecimal newAelChuaThu = docsAcDb.getPhiAelChuaThu().subtract(docsAc.getPhiAelDaThu());
			BigDecimal newAelDaThu = docsAcDb.getPhiAelDaThu().add(docsAc.getPhiAelDaThu());
			BigDecimal newChiHoChuaThu = docsAcDb.getPhiChiHoChuaThu().subtract(docsAc.getPhiChiHoDaThu());
			BigDecimal newChiHoDaThu = docsAcDb.getPhiChiHoDaThu().add(docsAc.getPhiChiHoDaThu());
			//System.out.println(newAelChuaThu + "-" + newAelDaThu + "-" + newChiHoChuaThu + "-" + newChiHoDaThu);
			//update money
			docsAcDb.setPhiAelChuaThu(newAelChuaThu);
			docsAcDb.setPhiAelDaThu(newAelDaThu);
			docsAcDb.setPhiChiHoChuaThu(newChiHoChuaThu);
			docsAcDb.setPhiChiHoDaThu(newChiHoDaThu);
			//update status
			int status = 0;
			if (newAelChuaThu.signum() < 1 && newChiHoChuaThu.signum() < 1) {
				status = 1;//da thu
			} else if (newAelDaThu.signum() < 1 && newChiHoDaThu.signum() < 1) {
				status = 0;//chua thu
			} else {
				status = 2; //con no
			}
			docsAcDb.setCollectMoneyStatus(status);
			docsAccountingRepository.save(docsAcDb);
		}
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
		List<Truckingdetail> listTruck = truckingdetailRepository.searchTruckingFee(nhathauId, search.getPayMoneyStatus());
		return listTruck;
	}

	@Override
	public void updateTruckAccounting(Truckingdetail truckingdetail, BigDecimal phiAel, BigDecimal phiChiHo) {
		TruckAccounting truckac = truckingdetail.getTruckAccounting();
		if (truckac == null) {
			truckac = new TruckAccounting();
			truckac.setTruckingdetail(truckingdetail);
			truckac.setPhiAelChuaChi(phiAel == null ? BigDecimal.ZERO : phiAel);
			truckac.setPhiChiHoChuaChi(phiChiHo == null ? BigDecimal.ZERO : phiChiHo);
			truckac.setPayMoneyStatus(0);
			truckingdetail.setTruckAccounting(truckac);
		} else {
			if (truckac.getPayMoneyStatus() == 0) {
				if (phiAel == null) {
					phiAel = BigDecimal.ZERO;
				} 
				if (phiChiHo == null) {
					phiChiHo = BigDecimal.ZERO;
				}
				BigDecimal phiAelUpdated = truckingdetail.getTruckAccounting().getPhiAelChuaChi();
				if (phiAelUpdated != null) {
					phiAelUpdated = phiAelUpdated.add(phiAel);
				} else {
					phiAelUpdated = BigDecimal.ZERO;
				}
				BigDecimal phiChiHoUpdated = truckingdetail.getTruckAccounting().getPhiChiHoChuaChi();
				if (phiChiHoUpdated != null) {
					phiChiHoUpdated = phiChiHoUpdated.add(phiChiHo);
				} else {
					phiChiHoUpdated = BigDecimal.ZERO;
				}

				truckingdetail.getTruckAccounting().setPhiAelChuaChi(phiAelUpdated);
				truckingdetail.getTruckAccounting().setPhiChiHoChuaChi(phiChiHoUpdated);
			}
		}
		truckingdetailRepository.save(truckingdetail);
	}
	
	@Override
	public void updatePayMoneyStatus(Map<Long, TruckAccounting> accountingMap) {
		Set<Long> listId = (Set<Long>) accountingMap.keySet();
		//List<Docsgeneral> docs = docsgeneralRepository.findAll(listId);
		for (Long id : listId) {

			Truckingdetail truckingdetail = truckingdetailRepository.getOne(id);
			TruckAccounting truckAcDb = truckAccountingRepository.getOne(truckingdetail.getTruckAccounting().getId());
			//input docsAc
			TruckAccounting truckAc = accountingMap.get(truckingdetail.getId());
			
			//TruckAccounting docsDb = doc.getDocsAccounting();
			BigDecimal newAelChuaChi = truckAcDb.getPhiAelChuaChi().subtract(truckAc.getPhiAelDaChi());
			BigDecimal newAelDaChi = truckAcDb.getPhiAelDaChi() == null ? truckAc.getPhiAelDaChi()
					: truckAcDb.getPhiAelDaChi().add(truckAc.getPhiAelDaChi());
			
			BigDecimal newChiHoChuaChi = truckAcDb.getPhiChiHoChuaChi().subtract(truckAc.getPhiChiHoDaChi());
			BigDecimal newChiHoDaChi = truckAcDb.getPhiChiHoDaChi() == null ? truckAc.getPhiChiHoDaChi()
					: truckAcDb.getPhiChiHoDaChi().add(truckAc.getPhiChiHoDaChi());
			//System.out.println(newAelChuaThu + "-" + newAelDaThu + "-" + newChiHoChuaThu + "-" + newChiHoDaThu);
			//update money
			truckAcDb.setPhiAelChuaChi(newAelChuaChi);
			truckAcDb.setPhiAelDaChi(newAelDaChi);
			truckAcDb.setPhiChiHoChuaChi(newChiHoChuaChi);
			truckAcDb.setPhiChiHoDaChi(newChiHoDaChi);
			//update status
			int status = 0;
			if (newAelChuaChi.signum() < 1 && newChiHoChuaChi.signum() < 1) {
				status = 1;//da thu
			} else if (newAelDaChi.signum() < 1 && newChiHoDaChi.signum() < 1) {
				status = 0;//chua thu
			} else {
				status = 2; //con no
			}
			truckAcDb.setPayMoneyStatus(status);

			//docsAccountingRepository.save(docsAcDb);
		}
	}
}
