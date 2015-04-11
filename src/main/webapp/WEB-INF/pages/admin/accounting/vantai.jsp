<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="accountingnhathau.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
    <meta name="child" content="true"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="accountingnhathau.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='accountingnhathau.heading'/>  
    	<fmt:formatDate value="${accountingNhathau.condition.startDate}" var="startDate" 
                type="date" pattern="dd/MM/yyyy" />
		&nbsp;${startDate} - 
		<fmt:formatDate value="${accountingNhathau.condition.endDate}" var="endDate" 
                type="date" pattern="dd/MM/yyyy" />
        ${endDate}  
	</h2>
 	<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="accountingVantai" method="post" action="nhathau" id="accountingVantaiForm" cssClass="well">
	    <form:hidden path="condition.nhathauId"/>
	    <form:hidden path="condition.startDate"/>
	    <form:hidden path="condition.endDate"/>
	    
	    <div class="container-fluid">
    		<%-- <jsp:include page="fragment/nhathau/generalInput.jsp"></jsp:include> --%>
    		<jsp:include page="fragment/vantai/vantaiTable.jsp"></jsp:include>
    	</div>
	    <div class="form-group form-actions">
	        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
	            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
	        </button>
	        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
	            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
	        </button>
	    </div>
    </form:form>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>