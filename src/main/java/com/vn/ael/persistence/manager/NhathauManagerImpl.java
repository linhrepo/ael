/**
 *
 */
package com.vn.ael.persistence.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.ael.enums.NhathauType;
import com.vn.ael.persistence.entity.Nhathau;
import com.vn.ael.persistence.repository.NhathauRepository;

/**
 * @author liv1hc
 *
 */
@Transactional
@Service
public class NhathauManagerImpl extends GenericManagerImpl<Nhathau> implements NhathauManager{

    private NhathauRepository nhathauRepository;

    @Autowired
    public NhathauManagerImpl(final NhathauRepository nhathauRepository) {
        this.nhathauRepository = nhathauRepository;
        this.repository = nhathauRepository;
    }

	@Override
	public List<Nhathau> findByType(NhathauType type) {
		return this.nhathauRepository.findByType(type.getValue());
	}

}
