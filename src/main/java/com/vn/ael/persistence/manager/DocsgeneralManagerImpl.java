/**
 *
 */
package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.TypeOfContainer;
import com.vn.ael.constants.TypeOfFee;
import com.vn.ael.enums.ConfigurationType;
import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Configuration;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Docservice;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Exhibition;
import com.vn.ael.persistence.entity.Extendfeeacc;
import com.vn.ael.persistence.entity.Inland;
import com.vn.ael.persistence.entity.Inlandsize;
import com.vn.ael.persistence.entity.Multitype;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.persistence.repository.ConfigurationRepository;
import com.vn.ael.persistence.repository.ContsealRepository;
import com.vn.ael.persistence.repository.DocserviceRepository;
import com.vn.ael.persistence.repository.DocsgeneralRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.InlandsizeRepository;
import com.vn.ael.persistence.repository.MultitypeRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.persistence.repository.TruckingserviceRepository;
import com.vn.ael.webapp.dto.AccountingTransCondition;
import com.vn.ael.webapp.util.ConvertUtil;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class DocsgeneralManagerImpl extends GenericManagerImpl<Docsgeneral> implements DocsgeneralManager{

    private DocsgeneralRepository docsgeneralRepository;

    @Autowired
    private ContsealRepository contsealRepository;
    
    @Autowired
    private DocserviceRepository docserviceRepository;
    
    @Autowired
    private ExfeetableRepository exfeetableRepository;
    
    @Autowired
    private InlandsizeRepository inlandsizeRepository;
    
    @Autowired
    private MultitypeRepository multitypeRepository;
    
    @Autowired
    private TruckingserviceRepository truckingserviceRepository;
    
    @Autowired
    private TruckingdetailRepository truckingdetailRepository;
    
    @Autowired
    public DocsgeneralManagerImpl(final DocsgeneralRepository docsgeneralRepository) {
        this.docsgeneralRepository = docsgeneralRepository;
        this.repository = docsgeneralRepository;
    }

	@Override
	public void checkToDeleteChilds(Docsgeneral docsgeneral) {
		if (docsgeneral.getContseals() != null){
			List<Contseal> conts = new ArrayList<>();
			for (Contseal contseal: docsgeneral.getContseals()){
				if (contseal.getIsDeleted() != null && contseal.getIsDeleted() == true){
					contsealRepository.delete(contseal);
				}else{
					conts.add(contseal);
				}
			}
			docsgeneral.setContseals(conts);
		}
		
		if (docsgeneral.getDocservices() != null){
			List<Docservice> docservices = new ArrayList<>();
			for (Docservice docservice: docsgeneral.getDocservices()){
				if (docservice.getIsDeleted() != null && docservice.getIsDeleted() == true){
					docserviceRepository.delete(docservice);
				}else{
					docservices.add(docservice);
				}
			}
			docsgeneral.setDocservices(docservices);
		}
		
		if (docsgeneral.getExfeetables() != null){
			List<Exfeetable> exfeetables = new ArrayList<>();
			for (Exfeetable exfeetable: docsgeneral.getExfeetables()){
				if (exfeetable.getIsDeleted() != null && exfeetable.getIsDeleted() == true){
					exfeetableRepository.delete(exfeetable);
				}else{
					exfeetables.add(exfeetable);
				}
			}
			docsgeneral.setExfeetables(exfeetables);
		}
		
		if (docsgeneral.getContTypes() != null){
			List<Multitype> multitypes = new ArrayList<>();
			for (Multitype multitype: docsgeneral.getContTypes()){
				if (multitype.getIsDeleted() != null && multitype.getIsDeleted() == true){
					multitypeRepository.delete(multitype);
				}else{
					multitypes.add(multitype);
				}
			}
			docsgeneral.setContTypes(multitypes);
		}
		
	}

	@Override
	public void updateChilds(Docsgeneral docsgeneral) {
		if (docsgeneral != null && docsgeneral.getId() != null){
			docsgeneral.setContseals(contsealRepository.findByDocsgeneral(docsgeneral));
	    	docsgeneral.setDocservices(docserviceRepository.findByDocsgeneral(docsgeneral));
	    	docsgeneral.setExfeetables(exfeetableRepository.findByDocsgeneral(docsgeneral, EntityUtil.EXFEETABLE_DEFAULT_SORTING));
	    	docsgeneral.setInlandsizes(inlandsizeRepository.findByDocsgeneral(docsgeneral));
	    	docsgeneral.setContTypes(multitypeRepository.findByDocsgeneral(docsgeneral));
		}
		
		if (docsgeneral.getContseals() == null || docsgeneral.getContseals().isEmpty()){
			List<Contseal> cons = new ArrayList<>();
			Contseal contseal = new Contseal();
			contseal.setIsAdded(true);
			cons.add(contseal);
			docsgeneral.setContseals(cons);
		}
		
		if (docsgeneral.getInlandsizes() == null || docsgeneral.getInlandsizes().isEmpty()){
			List<Inlandsize> inlandsizes = new ArrayList<>();
			Inlandsize inlandsize = new Inlandsize();
			inlandsize.setIsAdded(true);
			inlandsizes.add(inlandsize);
			docsgeneral.setInlandsizes(inlandsizes);
		}
		
		if (docsgeneral.getDocservices() == null || docsgeneral.getDocservices().isEmpty()){
			List<Docservice> docs = new ArrayList<>();
			Docservice docservice = new Docservice();
			docservice.setIsAdded(true);
			docs.add(docservice);
			docsgeneral.setDocservices(docs);
		}
		
		if (docsgeneral.getExfeetables() == null || docsgeneral.getExfeetables().isEmpty()){
			List<Exfeetable> exfeetables = new ArrayList<>();
			Exfeetable exfeetable = new Exfeetable();
			exfeetable.setIsAdded(true);
			exfeetables.add(exfeetable);
			docsgeneral.setExfeetables(exfeetables);
		}
		
		if (docsgeneral.getContTypes() == null || docsgeneral.getContTypes().isEmpty()){
			List<Multitype> multitypes = new ArrayList<>();
			Multitype multitype = new Multitype();
			multitype.setIsAdded(true);
			multitypes.add(multitype);
			docsgeneral.setContTypes(multitypes);
		}
		
	}

	@Override
	public List<Docsgeneral> findByDoDelivery(Boolean doDelivery) {
		return this.docsgeneralRepository.findByDoDelivery(doDelivery);
	}

	@Override
	public void updateAccounting(Docsgeneral docsgeneral) {
		if (docsgeneral != null && docsgeneral.getId() != null){
			Docsgeneral docsgeneralDB = docsgeneralRepository.getOne(docsgeneral.getId());
			docsgeneralDB.setDoAccounting(docsgeneral.getDoAccounting());
			docsgeneralRepository.save(docsgeneralDB);
		}
	}

	@Override
	public List<Docsgeneral> findByDoAccounting(Boolean doAccounting) {
		return this.docsgeneralRepository.findByDoAccounting(doAccounting);
	}

	@Override
	public List<Docsgeneral> findByDoAccountingAndTypeOfDocs(
			Boolean doAccounting, ServicesType servicesType) {
		return this.docsgeneralRepository.findByDoAccountingAndTypeOfDocs(doAccounting,servicesType);
	}

	@Override
	public List<Docsgeneral> findDoAccountingInlandSealand(
			Boolean doAccounting) {
		return this.docsgeneralRepository.getDoAccountingInlandSealand(doAccounting, ServicesType.DVVT_INLAND, ServicesType.DVVT_SEALAND);
	}
	
	@Override
	public List<Docsgeneral> findAllByCondition(
			AccountingTransCondition accountingTransCondition) {
		List<Docsgeneral> docsgenerals = this.docsgeneralRepository.getDoAccountingInlandSealandAndTime(true, ServicesType.DVVT_INLAND, ServicesType.DVVT_SEALAND, accountingTransCondition.getMonth(), accountingTransCondition.getYear(),accountingTransCondition.getCustomerId());
		if (docsgenerals != null && !docsgenerals.isEmpty()){
			for (Docsgeneral docsgeneral : docsgenerals){
				List<Exfeetable> exfeetables = exfeetableRepository.findByDocsgeneral(docsgeneral);
				if (exfeetables != null && !exfeetables.isEmpty()){
					if (docsgeneral.getChiho() == null){
						docsgeneral.setChiho(BigDecimal.ZERO);
					}
					
					for (Exfeetable exfeetable : exfeetables){
						if (exfeetable.getMasterFee() != null && exfeetable.getMasterFee().getId() == TypeOfFee.CHI_HO_ID){
							//add to accounting cus
							docsgeneral.setChiho(docsgeneral.getChiho().add(ConvertUtil.getNotNullValue(exfeetable.getAmount())));
						}
					}
				}
			}
		}
		return docsgenerals;
	}

	@Override
	public void updateContTruck(Docsgeneral docsgeneral) {
		//update counting for LCL/FCL
		if (docsgeneral != null){
			if (docsgeneral.getTypeOfContainer() != null
					&& docsgeneral.getTypeOfContainer().getId() == TypeOfContainer.LCL){
				docsgeneral.setIsLCL(true);
			}else{
				docsgeneral.setIsLCL(false);
				List<Contseal> contseals = contsealRepository.findByDocsgeneral(docsgeneral);
				int count20 = 0;
				int count40 = 0;
				if (contseals != null && !contseals.isEmpty()){
					for (Contseal contseal :  contseals){
						if (contseal.getTypeOfCont() != null)
							if(contseal.getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_20_START)){
								count20+=1;
							}else if(contseal.getTypeOfCont().getValue().startsWith(TypeOfContainer.FCL_40_START)){
								count40+=1;
							}
						}	
					}
				docsgeneral.setNoOf20Cont(count20);
				docsgeneral.setNoOf40Cont(count40);
				}
			}
		//load trucking service
		Truckingservice truckingservice = this.truckingserviceRepository.findByDocsgeneral(docsgeneral);
		List<Truckingdetail> truckingdetails = this.truckingdetailRepository.findByTruckingservice(truckingservice);
		docsgeneral.setTruckingservice(truckingservice);
		truckingservice.setTruckingdetails(truckingdetails);
	}

}
