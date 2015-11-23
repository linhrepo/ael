<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-10">
	<h2><fmt:message key='menu.acct.listphieuthu'/></h2>
	<jsp:include page="searchPhieuThu.jsp"></jsp:include>
	<br/>
    <div id="actions" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='phieuthu?isAdmin=1'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addPhieuthuAdmin"/></a>
    	<a class="btn btn-primary" href="<c:url value='phieuthu?isAdmin=0'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addPhieuthuJob"/></a>
        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="fa fa-sign-out"></i><fmt:message key="button.done"/></a>
    </div>
    <table id="phieuthuList" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
            	<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="phieuthu.date"/></th>
                <th><fmt:message key="phieuthu.total"/></th>
                <th><fmt:message key="refund.type"/></th>
            <%--     <th><fmt:message key="refund.approval"/></th> --%>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="phieuthu.date"/></th>
                <th><fmt:message key="phieuthu.total"/></th>
                <th><fmt:message key="refund.type"/></th>
               <%--  <th><fmt:message key="refund.approval"/></th> --%>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${refundList}" var="adv" varStatus="idx">
        	<tr>
                <td>${idx.index+1}</td>
                <td>
                	${adv.employee.firstName}&nbsp;${adv.employee.lastName}
                </td>
                <td>
                	<fmt:formatDate value="${adv.date}" pattern="yyyy-MM-dd"/>
                </td>
                 <td class="money">
                 	<fmt:formatNumber>${adv.total}</fmt:formatNumber>
                </td>
                 <td>
              		<c:if test="${adv.isRAdmin}">
              			<fmt:message key="refund.adminFee"/>
              		</c:if>
              		<c:if test="${!adv.isRAdmin}">
              			<fmt:message key="refund.manifestFee"/>
              		</c:if>
              	</td>
              	<%-- <td>
              		<c:if test="${adv.doApproval}">
              			<a><i class="fa fa-check"></i></a>
              		</c:if>
              	</td> --%>
                <td>
                	<a href="phieuthu?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

