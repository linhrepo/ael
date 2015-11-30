<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-10">
	<h2><fmt:message key='moneybook.title'/></h2>
	<jsp:include page="moneyBookSearch.jsp"></jsp:include>
	<br/>
    
    <h3><fmt:message key='moneybook.cashbook.title'/></h3>

	<table>
		<tr>
			<td><fmt:message key="moneybook.first.balance" />&nbsp;</td>
			<c:if test="${empty firstCashBalance}">
				<td><input id="cashAmount"/></td>
				<td><button onclick="saveFirstBalance()">Save</button></td>
			</c:if>
			<c:if test="${not empty firstCashBalance}">
				<td><span>${firstCashBalance.amount}</span></td>
				<td><button onclick="editFirstBalance()">Edit</button></td>
			</c:if>
		</tr>
	</table>
	<script>
		saveFirstBalance() {
			var amount = $("#cashAmount").val();
			if (isNaN(amount)) {
				alert("Please input number");
			} else {
				$.ajax({
				    type: "POST",
				    url: "moneybook/saveFirstBalance",
				    data: {"amount": amount},
				    success: function(msg){
				    	alert("Done");
				    }
				}); 
			}
		}
	</script>
	<br><br>
    <table id="cashbook" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
            	<th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th>RefNo(s)</th>
                <th><fmt:message key="moneybook.description"/></th>
                <th><fmt:message key="moneybook.paymentMoney"/></th>
                <th><fmt:message key="moneybook.receptMoney"/></th>
                <th><fmt:message key="moneybook.balance"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th>RefNo(s)</th>
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
                	${mb.voucherNoPrint}
                </td>
                <td>
                	<fmt:formatDate value="${mb.date}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                	<%-- ${mb.typeOfVoucher} --%>
                	<c:if test="${mb.typeOfVoucher == 0}"><fmt:message key="moneybook.voucherType.payment"/></c:if>
                	<c:if test="${mb.typeOfVoucher == 1}"><fmt:message key="moneybook.voucherType.recept"/></c:if>
                </td>
                <td>
                	${mb.refNos}
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
    <c:if test="${empty firstCashBalance}">
    
    </c:if>
    <table id="bankbook" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
            	<th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th>RefNo(s)</th>
                <th><fmt:message key="moneybook.description"/></th>
                <th><fmt:message key="moneybook.paymentMoney"/></th>
                <th><fmt:message key="moneybook.receptMoney"/></th>
                <th><fmt:message key="moneybook.balance"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="moneybook.voucherNo"/></th>
                <th><fmt:message key="moneybook.date"/></th>
                <th><fmt:message key="moneybook.voucherType"/></th>
                <th>RefNo(s)</th>
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
                	${mb.voucherNoPrint}
                </td>
                <td>
                	<fmt:formatDate value="${mb.date}" pattern="yyyy-MM-dd"/>
                </td>
                <td>
                	<%-- ${mb.typeOfVoucher} --%>
                	<c:if test="${mb.typeOfVoucher == 0}"><fmt:message key="moneybook.voucherType.payment"/></c:if>
                	<c:if test="${mb.typeOfVoucher == 1}"><fmt:message key="moneybook.voucherType.recept"/></c:if>
                </td>
                <td>
                	${mb.refNos}
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

