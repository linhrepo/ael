<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="multitype.title"/></title>
    <meta name="menu" content="DocsMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="multitype.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='multitype.heading'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="multitype" method="post" action="multitype" id="multitypeForm" cssClass="well">
    <form:hidden path="id"/>
    <form:hidden path="creator.id"/>
    <form:hidden path="createdDate"/>
    <form:hidden path="inland.id"/>
    <div class="container-fluid">
	    <div class="row">
	    	<spring:bind path="multitype.type">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="contseal.typeOfCont"/>
		        <form:select path="type.id" id="type"  autofocus="true" cssClass="form-control" items="${typeOfConts}"/>
		        <form:errors path="type.id" cssClass="help-block"/>
		    </div>
	    	<spring:bind path="multitype.amount">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="multitype.amount"/>
		        <form:input path="amount" id="amount" maxlength="45" autofocus="true" cssClass="form-control"/>
		        <form:errors path="amount" cssClass="help-block"/>
		    </div>
	    </div>
	   
	</div>
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty multitype.id}">
          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    </form:form>
    <hr>
    <div class="row">
    	<div class="col-md-7"></div>
    	<div class="col-md-5">
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${multitype.creator.username}</span> on ${multitype.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${multitype.updator.username}</span> on ${multitype.lastUpdateDate}
    	</div>
    </div>
    
</div>
 
<v:javascript formName="inlandsizeForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>