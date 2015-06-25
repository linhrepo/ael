<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="feeTablesacc.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

	<h2><fmt:message key="debit.manifest"/></h2>
    <jsp:include page="searchDebit.jsp"></jsp:include>
    <input type="hidden" value="${approve }" id="approve"/>
    <table id="truckingList" class="display datatable" cellspacing="0" width="100%" >
    	<%-- childDetailURL="/admin/accounting/feesDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="packageInfo.masterFee" />,<fmt:message key="packageInfo.feeName" />,<fmt:message key="packageInfo.feevalue" />,<fmt:message key="packageInfo.feevavat" />,<fmt:message key="packageIngo.total" />,<fmt:message key="accounting.approval" />,<fmt:message key="accounting.approvalDate" />,<fmt:message key="accounting.feeAdminApproval" />,<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="masterFee.value,name.value,amount[money],vat[money],total[money],approvedText[Key],dateChange[Date],checkByAdminText[Key]"
    	editDetail="/admin/accounting/changeApproval"
    	actionCheck = "checkByAdmin:false,null"
    	successLoadMessage="accounting.fees.detailLoaded"
    	> --%>
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
                <th><fmt:message key="debit.thutien"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
                <th><fmt:message key="debit.thutien"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${docsgeneralList}" var="trucking" varStatus="idx">
        	<tr params="docId=${trucking.id}" class="${trucking.isCollectMoney ? '':'impress' }">
                <td>${idx.index+1}</td>
              	<td>${trucking.jobNo}</td>
              	<td><fmt:message key="${trucking.typeOfDocs.textKey}"/></td>
              	<td>${trucking.typeOfContainer.value}</td>
              	<td>
              		<c:choose>
              			<c:when test="${trucking.isCollectMoney}">
              				<fmt:message key="debit.dathu"/>
              			</c:when>
              			<c:otherwise>
              				<fmt:message key="debit.chuathu"/>
              			</c:otherwise>
              		</c:choose>
              	</td>
              	<td>
                	<a <%-- href="approveCollectMoney?id=${trucking.id}" --%> value="${trucking.id}" id="approveMoney" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr> 
        </c:forEach>
        </tbody>
    </table>
<script>
	$(document).ready(function(){
		if($("#approve").val() != null && $("#approve").val() != ""){
			UTIL.showMessage('<fmt:message key="debit.approved"/>', "info");
		}
		$('#approveMoney').click(function(){			
			$.confirm({
			    text: '<fmt:message key="debit.confirmApprove"/>',
			    confirm: function() {
			    	$(location).attr('href','<c:url value="approveCollectMoney?id="></c:url>' + $('#approveMoney').attr("value") + "&approve=1");
			    },
			    cancel: function() {
			        // nothing to do
			    }
			});
		});
	});
</script>