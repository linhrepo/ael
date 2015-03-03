<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="inlandsize.title"/></title>
    <meta name="menu" content="DocsMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="inlandsize.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='contseal.heading'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="inlandsize" method="post" action="inlandsize" id="inlandsizeForm" cssClass="well">
    <form:hidden path="id"/>
    <form:hidden path="creator.id"/>
    <form:hidden path="createdDate"/>
    <form:hidden path="inland.id"/>
    <div class="container-fluid">
	    <div class="row">
		    <spring:bind path="inlandsize.deep">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="inlandsize.deep"/>
		        <form:input path="deep" id="deep" maxlength="45" autofocus="true" cssClass="form-control"/>
		        <form:errors path="deep" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="inlandsize.width">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="inlandsize.width"/>
		        <form:input path="width" id="deep" maxlength="45" autofocus="true" cssClass="form-control"/>
		        <form:errors path="width" cssClass="help-block"/>
		    </div>
		    <spring:bind path="inlandsize.height">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="inlandsize.height"/>
		        <form:input path="height" id="height" maxlength="45" autofocus="true" cssClass="form-control"/>
		        <form:errors path="width" cssClass="help-block"/>
		    </div>
	    </div>
	   
	</div>
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty inlandsize.id}">
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
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${inlandsize.creator.username}</span> on ${inlandsize.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${inlandsize.updator.username}</span> on ${inlandsize.lastUpdateDate}
    	</div>
    </div>
    
</div>
 
<v:javascript formName="inlandsizeForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>