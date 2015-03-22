<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="advanceList.title"/></title>
    <meta name="menu" content=Home/>
</head>

<div class="col-sm-12">
    <h2><fmt:message key="advanceList.heading"/></h2>
    <div id="actions" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='advanceForm'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
    <table id="advanceformList" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                <th><fmt:message key="accounting.approval"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                <th><fmt:message key="accounting.approval"/></th>
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
                	<a href="advanceForm?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

