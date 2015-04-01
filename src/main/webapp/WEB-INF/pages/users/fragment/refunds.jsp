<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-12">
    <div id="actions" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='refund'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
    <table id="refundList" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="refund.date"/></th>
                <th><fmt:message key="refund.total"/></th>
                <th><fmt:message key="refund.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="refund.date"/></th>
                <th><fmt:message key="refund.total"/></th>
                <th><fmt:message key="refund.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${refundList}" var="adv" varStatus="idx">
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
                	<a href="refund?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
