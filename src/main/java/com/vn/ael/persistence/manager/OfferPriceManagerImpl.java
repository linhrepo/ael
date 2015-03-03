/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.Contseal;
import com.vn.ael.persistence.entity.Customer;
import com.vn.ael.persistence.entity.OfferItem;
import com.vn.ael.persistence.entity.OfferPrice;
import com.vn.ael.persistence.repository.CustomerRepository;
import com.vn.ael.persistence.repository.OfferItemRepository;
import com.vn.ael.persistence.repository.OfferPriceRepository;
import com.vn.ael.webapp.util.EntityUtil;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service("offerpriceManager")
public class OfferPriceManagerImpl extends GenericManagerImpl<OfferPrice> implements OfferPriceManager{

    private OfferPriceRepository offerPriceRepository;
    
    @Autowired
    private OfferItemRepository offerItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    

    @Autowired
    public OfferPriceManagerImpl(final OfferPriceRepository offerPriceRepository) {
        this.offerPriceRepository = offerPriceRepository;
        this.repository = offerPriceRepository;
    }

	@Override
	public List<OfferPrice> findByCustomer(Customer customer) {
		return this.offerPriceRepository.findByCustomer(customer);
	}

	@Override
	public List<OfferPrice> findWithCustomerId(String customerId) {
		return this.offerPriceRepository.findWithCustomerId(new Long(customerId));
	}

	@Override
	public void updateChilds(OfferPrice offerPrice) {
		if (offerPrice != null && offerPrice.getId() != null) {
			offerPrice.setOfferItems(offerItemRepository.findByOfferPrice(offerPrice));
		}

		if (offerPrice.getOfferItems() == null
				|| offerPrice.getOfferItems().isEmpty()) {
			List<OfferItem> items = new ArrayList<>();
			OfferItem offerItem = new OfferItem();
			offerItem.setIsAdded(true);
			items.add(offerItem);
			offerPrice.setOfferItems(items);
		}		
	}

	@Override
	public void saveWholePackage(OfferPrice offerPrice) {
		this.checkToDeleteChilds(offerPrice);
		EntityUtil.wireChildOfOfferPrice(offerPrice);
		offerPriceRepository.save(offerPrice);
	}

	/**
	 * Check to delete unwired objects
	 */
	private void checkToDeleteChilds(OfferPrice offerPrice) {
		if (offerPrice.getOfferItems() != null){
			List<OfferItem> items = new ArrayList<>();
			for (OfferItem offerItem: offerPrice.getOfferItems()){
				if (offerItem.getIsDeleted() != null && offerItem.getIsDeleted() == true){
					offerItemRepository.delete(offerItem);
				}else{
					items.add(offerItem);
				}
			}
			offerPrice.setOfferItems(items);
		}
		
	}

	@Override
	public List<OfferPrice> findByCustomerAndTypeOfService(Customer customer,
			ServicesType servicesType) {
		return this.offerPriceRepository.findByCustomerAndTypeOfService(customer, servicesType);
	}
}
