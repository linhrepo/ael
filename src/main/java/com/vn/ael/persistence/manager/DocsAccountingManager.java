package com.vn.ael.persistence.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.vn.ael.enums.ServicesType;
import com.vn.ael.persistence.entity.DocsAccounting;
import com.vn.ael.persistence.entity.Docsgeneral;
import com.vn.ael.webapp.dto.AccountingCollectMoneyCondition;
import com.vn.ael.webapp.dto.AccountingContractorPaymentCondition;
import com.vn.ael.webapp.dto.AccountingTransportExport;

public interface DocsAccountingManager extends GenericManager<DocsAccounting> {

	void updatePhiAELAndChiHo(Docsgeneral docsgeneral, BigDecimal phiAel, BigDecimal phiChiHo);
	void updatePhiAELAndChiHo(List<AccountingTransportExport> list);
	void updateCollectMoneyStatus(Map<Long, Integer> feeTypeMap);
	List<Docsgeneral> searchDebit(AccountingCollectMoneyCondition search);
	List<Docsgeneral> searchDocsTruckingFee(AccountingContractorPaymentCondition search);
}
