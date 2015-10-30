<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-10">
	<h2><fmt:message key='moneybook.title'/></h2>
	<jsp:include page="moneyBookSearch.jsp"></jsp:include>
	<br/>
    
    <h3><fmt:message key='moneybook.cashbook.title'/></h3>
    <table id="cashbook" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.description"/></th>
                <th><fmt:message key="moneybook.paymentMoney"/></th>
                <th><fmt:message key="moneybook.receptMoney"/></th>
                <th><fmt:message key="moneybook.balance"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.description"/></th>
                <th><fmt:message key="moneybook.paymentMoney"/></th>
                <th><fmt:message key="moneybook.receptMoney"/></th>
                <th><fmt:message key="moneybook.balance"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${cashbooks}" var="mb" varStatus="idx">
        	<tr>
                <td>${idx.index+1}</td>
                <td>
                	<fmt:formatDate value="${mb.date}" pattern="dd-MM-yyyy"/>
                </td>
                <td>
                	${mb.typeOfVoucher}
                </td>
                <td>
                	${mb.voucherNo}
                </td>
                <td>
                	${mb.description}
                </td>
                <td class="money">
                 	<fmt:formatNumber>${mb.paymentMoney}</fmt:formatNumber>
                </td>
                <td class="money">
                 	<fmt:formatNumber>${mb.receptMoney}</fmt:formatNumber>
                </td>
                <td class="money">
                 	<fmt:formatNumber>${mb.balance}</fmt:formatNumber>
                </td>
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <h3><fmt:message key='moneybook.bankbook.title'/></h3>
    <table id="bankbook" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.description"/></th>
                <th><fmt:message key="moneybook.paymentMoney"/></th>
                <th><fmt:message key="moneybook.receptMoney"/></th>
                <th><fmt:message key="moneybook.balance"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.description"/></th>
                <th><fmt:message key="moneybook.paymentMoney"/></th>
                <th><fmt:message key="moneybook.receptMoney"/></th>
                <th><fmt:message key="moneybook.balance"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${bankbooks}" var="mb" varStatus="idx">
        	<tr>
                <td>${idx.index+1}</td>
                <td>
                	<fmt:formatDate value="${mb.date}" pattern="dd-MM-yyyy"/>
                </td>
                <td>
                	${mb.typeOfVoucher}
                </td>
                <td>
                	${mb.voucherNo}
                </td>
                <td>
                	${mb.description}
                </td>
                <td class="money">
                 	<fmt:formatNumber>${mb.paymentMoney}</fmt:formatNumber>
                </td>
                <td class="money">
                 	<fmt:formatNumber>${mb.receptMoney}</fmt:formatNumber>
                </td>
                <td class="money">
                 	<fmt:formatNumber>${mb.balance}</fmt:formatNumber>
                </td>
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

