<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="feeTablesacc.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

	<h2><fmt:message key="feeTablesacc.heading"/></h2>
	<jsp:include page="searchFeeTables.jsp"></jsp:include>
    <table id="truckingList" class="display nestedDatatable" cellspacing="0" width="100%" 
    	childDetailURL="/admin/accounting/feesDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="packageInfo.masterFee" />,<fmt:message key="packageInfo.feeName" />,<fmt:message key="packageInfo.feevalue" />,<fmt:message key="packageInfo.feevavat" />,<fmt:message key="packageIngo.total" />,<fmt:message key="accounting.approval" />,<fmt:message key="accounting.approvalDate" />,<fmt:message key="accounting.feeAdminApproval" />,<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="masterFee.value,name.value,amount[money],vat[money],total[money],approvedText[Key],dateChange[Date],checkByAdminText[Key]"
    	actionCheck = "approved:true"
    	editDetail="/admin/changeApproval"
    	successLoadMessage="accounting.fees.detailLoaded"
    	>
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${docsgeneralList}" var="trucking" varStatus="idx">
        	<tr params="docId=${trucking.id}">
                <td>${idx.index+1}</td>
              	<td>${trucking.jobNo}</td>
              	<td><fmt:message key="${trucking.typeOfDocs.textKey}"/></td>
              	<td>${trucking.typeOfContainer.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>