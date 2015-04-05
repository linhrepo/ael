<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="nhathau.title"/></title>
    <meta name="menu" content="TrackingMenu"/>
</head>

<div class="col-sm-12">
    <h2><fmt:message key="nhathau.heading"/></h2>
 	<div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='nhathauForm?method=Add&from=list'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>
    <table id="constants" class="display datatable" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="nhathau.name"/></th>
                <th><fmt:message key="nhathau.code"/></th>
                <th><fmt:message key="nhathau.taxNo"/></th>
                <th><fmt:message key="nhathau.contactName"/></th>
                <th><fmt:message key="nhathau.phoneNumber"/></th>
                <th><fmt:message key="nhathau.type"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="nhathau.name"/></th>
                <th><fmt:message key="nhathau.code"/></th>
                <th><fmt:message key="nhathau.taxNo"/></th>
                <th><fmt:message key="nhathau.contactName"/></th>
                <th><fmt:message key="nhathau.phoneNumber"/></th>
                <th><fmt:message key="nhathau.type"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${nhathauList}" var="cons" varStatus="idx">
        	<tr>
        		<td>${idx.index+1}</td>
                <td>${cons.name}</td>
                <td>${cons.code}</td>
                <td>${cons.taxNo}</td>
                <td>${cons.contactName}</td>
              	<td>${cons.phoneNumber}</td>
              	<td><fmt:message key="${types[cons.type]}"/></td>
              	<td>
                	<a href="nhathauForm?id=${cons.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

