/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.constants.TypeOfContainer;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.persistence.entity.Exfeetable;
import com.vn.ael.persistence.entity.Truckingdetail;
import com.vn.ael.persistence.entity.Truckingservice;
import com.vn.ael.persistence.repository.ContsealRepository;
import com.vn.ael.persistence.repository.DocserviceRepository;
import com.vn.ael.persistence.repository.ExfeetableRepository;
import com.vn.ael.persistence.repository.TruckingdetailRepository;
import com.vn.ael.persistence.repository.TruckingserviceRepository;
import com.vn.ael.webapp.util.EntityUtil;
import com.vn.ael.webapp.util.CommonUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class TruckingserviceManagerImpl extends GenericManagerImpl<Truckingservice> implements TruckingserviceManager{

    private TruckingserviceRepository truckingserviceRepository;
    
    @Autowired
    private DocserviceRepository docserviceRepository;
    
    @Autowired
    private ContsealRepository contsealRepository;
    
    @Autowired
    private TruckingdetailRepository truckingdetailRepository;
    
    @Autowired
    private ExfeetableRepository exfeetableRepository;

    @Autowired
    public TruckingserviceManagerImpl(final TruckingserviceRepository truckingserviceRepository) {
        this.truckingserviceRepository = truckingserviceRepository;
        this.repository = truckingserviceRepository;
    }

	@Override
	public Truckingservice createFromDocsgeneral(Docsgeneral docsgeneral) {
		if (docsgeneral !=null){
			Truckingservice truckingservice = this.truckingserviceRepository.findByDocsgeneral(docsgeneral);
			if (truckingservice == null){
				//create new
				truckingservice = new Truckingservice();
				List<Truckingdetail> truckingdetails = new ArrayList<>();
				List<Contseal> contseals = contsealRepository.findByDocsgeneral(docsgeneral);
				docsgeneral.setContseals(contseals);
				if (docsgeneral.getTypeOfContainer() != null && docsgeneral.getTypeOfContainer().getId() != TypeOfContainer.LCL && contseals != null && !contseals.isEmpty()){
					//create each detail for each contseals
					for (Contseal contseal : contseals){
						Truckingdetail truckingdetail = new Truckingdetail();
						truckingdetail.setConsteal(contseal);
						truckingdetails.add(truckingdetail);
						truckingdetail.setNoOfVehicle(docsgeneral.getSochuyen());
						truckingdetail.setVehicleNo(docsgeneral.getNameVehicle());
					}
				}
				else{
					Truckingdetail truckingdetail = new Truckingdetail();
					truckingdetail.setNoOfVehicle(docsgeneral.getSochuyen());
					truckingdetail.setVehicleNo(docsgeneral.getNameVehicle());
					truckingdetail.setIsAdded(true);
					truckingdetails.add(truckingdetail);
				}
				truckingservice.setDocsgeneral(docsgeneral);
				truckingservice.setTruckingdetails(truckingdetails);
				EntityUtil.updateExfeetableForNewTruckingdetail(truckingdetails);
			}else{
				this.updateChilds(truckingservice);
			}
			
			if (docsgeneral.getTypeOfContainer() != null && docsgeneral.getTypeOfContainer().getId() == TypeOfContainer.LCL){
				docsgeneral.setContseals(new ArrayList<Contseal>());
			}
			return truckingservice;
		}
		return null;
	}

	@Override
	public void updateChilds(Truckingservice truckingservice) {
		if (truckingservice != null && truckingservice.getId() != null){
			truckingservice.setTruckingdetails(truckingdetailRepository.findByTruckingservice(truckingservice));
			List<Contseal> contseals = contsealRepository.findByDocsgeneral(truckingservice.getDocsgeneral());
			truckingservice.getDocsgeneral().setContseals(contseals);
			
			if (truckingservice.getTruckingdetails() == null || truckingservice.getTruckingdetails().isEmpty()){
				List<Truckingdetail> truckingdetails = new ArrayList<>();
				Truckingdetail truckingdetail = new Truckingdetail();
				truckingdetail.setNoOfVehicle(truckingservice.getDocsgeneral().getSochuyen());
				truckingdetail.setVehicleNo(truckingservice.getDocsgeneral().getNameVehicle());
				truckingdetail.setIsAdded(true);
				truckingdetails.add(truckingdetail);
				truckingservice.setTruckingdetails(truckingdetails);
				EntityUtil.updateExfeetableForNewTruckingdetail(truckingdetails);
			}
			//check conteal again
			if (contseals != null || !contseals.isEmpty()){
				for (Contseal contseal: contseals){
					boolean isSet = false;
					for (Truckingdetail truckingdetail : truckingservice.getTruckingdetails()){
						if (truckingdetail.getConsteal() != null && truckingdetail.getConsteal().getId() == contseal.getId()){
							isSet = true;
							break;
						}
					}
					if (!isSet){
						//this is new consteal
						Truckingdetail truckingdetail = new Truckingdetail();
						truckingdetail.setConsteal(contseal);
						truckingdetail.setNoOfVehicle(truckingservice.getDocsgeneral().getSochuyen());
						truckingdetail.setVehicleNo(truckingservice.getDocsgeneral().getNameVehicle());
						truckingservice.getTruckingdetails().add(truckingdetail);
					}
				}
			}
			
			//wire fees
			List<Truckingdetail> truckingdetailsEmpty = new  ArrayList<>();
			for (Truckingdetail truckingdetail: truckingservice.getTruckingdetails()){
				truckingdetail.setExfeetables(exfeetableRepository.findByTruckingdetail(truckingdetail));
				if (truckingdetail.getExfeetables() == null || truckingdetail.getExfeetables().isEmpty() ){
					truckingdetailsEmpty.add(truckingdetail);
				}
			}
			EntityUtil.updateExfeetableForNewTruckingdetail(truckingdetailsEmpty,true);
		}
		
	}

	@Override
	public void saveWholePackage(Truckingservice truckingservice) {
		//wire staff
		this.checkToDeleteChilds(truckingservice);
		EntityUtil.wiredChildOfTruckingService(truckingservice);
		truckingserviceRepository.save(truckingservice);
	}

	@Override
	public void checkToDeleteChilds(Truckingservice truckingservice) {
		if (truckingservice.getTruckingdetails() != null){
			List<Truckingdetail> truckingdetails = new ArrayList<>();
			for (Truckingdetail truckingdetail: truckingservice.getTruckingdetails()){
				if (truckingdetail.getIsDeleted() != null && truckingdetail.getIsDeleted() == true){
					truckingdetailRepository.delete(truckingdetail);
				}else{
					truckingdetails.add(truckingdetail);
					
					if (truckingdetail.getExfeetables() != null){
						List<Exfeetable> exfeetables = new ArrayList<>();
						for (Exfeetable exfeetable : truckingdetail.getExfeetables()){
							if (exfeetable.getIsDeleted() != null && exfeetable.getIsDeleted() ==true){
								exfeetableRepository.delete(exfeetable);
							}
							else{
								exfeetables.add(exfeetable);
							}
						}
						truckingdetail.setExfeetables(exfeetables);
					}
				}
			}
			truckingservice.setTruckingdetails(truckingdetails);
		}
		
	}

	@Override
	public void saveTruckingdetail(List<Truckingdetail> truckingdetails) {	
		if(truckingdetails != null && !truckingdetails.isEmpty()){
			List<Truckingdetail> list = new ArrayList<>();
			for (Truckingdetail truckingdetail : truckingdetails) {
				Truckingdetail truckingdetail2 = truckingdetailRepository.findOne(truckingdetail.getId());
				truckingdetail2.setPhuthu(truckingdetail.getPhuthu());
				truckingdetail2.setNoContractUp(truckingdetail.getNoContractUp());
				truckingdetail2.setNoContractDown(truckingdetail.getNoContractDown());
				list.add(truckingdetail2);
			}
			truckingdetailRepository.save(list);
		}		
	}
}
