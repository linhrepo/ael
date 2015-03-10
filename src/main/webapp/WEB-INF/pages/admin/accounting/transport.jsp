<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="accountingTrans.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="accountingTrans.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='accountingTrans.heading'/> - ${accountingTrans.condition.month}/${accountingTrans.condition.year}</h2>
 	<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="accountingTrans" method="post" action="transport" id="accountingTransForm" cssClass="well">
    <form:hidden path="condition.customerId"/>
    <form:hidden path="condition.month"/>
    <form:hidden path="condition.year"/>
    <div class="container-fluid">
    	<jsp:include page="fragment/transport/generalInput.jsp"></jsp:include>
    	<jsp:include page="fragment/transport/transTable.jsp"></jsp:include>
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