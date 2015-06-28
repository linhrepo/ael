<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-12">
	<jsp:include page="searchRefund.jsp"></jsp:include>
    <div id="actionsRefund" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='/users/refund'/>">
            <i class="fa fa-plus"></i> <fmt:message key="button.addRefundAdmin"/></a>
    </div>
    <table id="refundList1" class="display nestedDatatable" cellspacing="0" width="100%" 
    	childDetailURL="/admin/accounting/feeRefundAdminDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="refund.description"/>,<fmt:message key="refund.amount"/>,<fmt:message key="refund.oAmount"/>,<fmt:message key="refund.accApproved" />,<fmt:message key="refund.adminApproved" />,<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="description,amount[money],oamount[money],approvedText[Key],checkByAdminText[Key]"
    	editDetail="/admin/accounting/changeRefundAdminApproval"
    	actionCheck = "checkByAdmin:false,null"
    	actionConfirm = "true"    	
    	successLoadMessage="accounting.fees.detailLoaded"
    	>
        <thead>
            <tr>
<%--             	<th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="refund.date"/></th>
                <th><fmt:message key="refund.total"/></th>
                <th><fmt:message key="refund.type"/></th>
               <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
<%--                 <th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="refund.date"/></th>
                <th><fmt:message key="refund.total"/></th>
                <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${refundList}" var="adv" varStatus="idx">
        	<c:if test="${not empty adv.refunddetails}">
        	<tr class="${adv.doApproval ? '':'impress' }" params="adminId=${adv.id}">
<%--                 <td>${index+1}</td> --%>
				<td>
	               	${adv.employee.firstName} ${adv.employee.lastName}
	            </td>
                <td>
                	<fmt:formatDate value="${adv.date}" pattern="dd-MM-yyyy"/>
                </td>
                 <td class="money">
                 	<fmt:formatNumber pattern="#,###" value="${adv.total}"></fmt:formatNumber>
                </td>
                 <td>
              		<c:if test="${adv.isAdmin}">
              			<fmt:message key="refund.adminFee"/>
              		</c:if>
<%--               		<c:if test="${!adv.isAdmin}"> --%>
<%--               			<fmt:message key="refund.manifestFee"/> --%>
<%--               		</c:if> --%>
              	</td>
              	<td>
              		<c:if test="${adv.doApproval}">
              			<a><i class="fa fa-check"></i></a>
              		</c:if>
              	</td>
                <td>
                	<c:if test="${adv.isAdmin}">
                		<a href="${ctx}/users/refund?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                	</c:if>
                	<c:if test="${!adv.isAdmin}">
                		<a href="${ctx}/users/refundJob?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                	</c:if>
                </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <hr>
     <a class="btn btn-primary" href="<c:url value='/users/refundJob'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addRefundJob"/></a>
            <br><br>
     <table id="refundList1Job" class="display nestedDatatable" cellspacing="0" width="100%" 
     	childDetailURL="/admin/accounting/feesDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="refund.fileCus"/>,<fmt:message key="packageInfo.masterFee" />,<fmt:message key="packageInfo.feeName" />,<fmt:message key="packageInfo.feevalue" />,<fmt:message key="packageInfo.feevavat" />,<fmt:message key="packageIngo.total" />,<fmt:message key="accounting.approval" />,<fmt:message key="accounting.approvalDate" />,<fmt:message key="accounting.feeAdminApproval" />,<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="docNo,masterFee.value,name.value,amount[money],vat[money],total[money],approvedText[Key],dateChange[Date],checkByAdminText[Key]"
    	editDetail="/admin/accounting/changeApproval"
    	actionCheck = "checkByAdmin:false,null"
    	actionConfirm = "true"    	
    	successLoadMessage="accounting.fees.detailLoaded"
    	>
        <thead>
            <tr>
<%--             	<th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="refund.date"/></th>
                <th><fmt:message key="refund.total"/></th>
                <th><fmt:message key="refund.type"/></th>
               <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
<%--                 <th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="refund.date"/></th>
                <th><fmt:message key="refund.total"/></th>
                <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${refundList}" var="adv" varStatus="idx">
        	<c:if test="${not empty adv.exfeetables}">
        	<tr class="${adv.doApproval ? '':'impress' }" params="refundId=${adv.id}">
<%--                 <td>${index+1}</td> --%>
				<td>
	               	${adv.employee.firstName} ${adv.employee.lastName}
	            </td>
                <td>
                	<fmt:formatDate value="${adv.date}" pattern="dd-MM-yyyy"/>
                </td>
                 <td class="money">
                 	<fmt:formatNumber pattern="#,###" value="${adv.total}"></fmt:formatNumber>
                </td>
                 <td>
<%--               		<c:if test="${adv.isAdmin}"> --%>
<%--               			<fmt:message key="refund.adminFee"/> --%>
<%--               		</c:if> --%>
              		<c:if test="${!adv.isAdmin}">
              			<fmt:message key="refund.manifestFee"/>
              		</c:if>
              	</td>
              	<td>
              		<c:if test="${adv.doApproval}">
              			<a><i class="fa fa-check"></i></a>
              		</c:if>
              	</td>
                <td>
                	<c:if test="${adv.isAdmin}">
                		<a href="${ctx}/users/refund?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                	</c:if>
                	<c:if test="${!adv.isAdmin}">
                		<a href="${ctx}/users/refundJob?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                	</c:if>
                </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>       
</div>

