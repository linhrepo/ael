<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-12">
	<jsp:include page="searchAdvance.jsp"></jsp:include>
    <div id="actions" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='/users/advanceForm'/>">
            <i class="fa fa-plus"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="fa fa-sign-out"></i> <fmt:message key="button.done"/></a>
    </div>
    <table id="advanceformList" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${advanceformList}" var="adv" varStatus="idx">
        	<tr>
                <td>${idx.index+1}</td>
                <td>
                	<fmt:formatDate value="${adv.date}" pattern="dd-MM-yyyy"/>
                </td>
                 <td class="money">
                 	${adv.total}
                </td>
              	<td>
              		<c:if test="${adv.doApproval}">
              			<a><i class="fa fa-check"></i></a>
              		</c:if>
              	</td>
                <td>
                	<a href="${ctx}/users/advanceForm?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

