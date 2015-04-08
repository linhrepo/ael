<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="truckingservice.title"/></title>
    <meta name="menu" content="TrackingMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="truckingservice.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='truckingservice.heading'/> - <fmt:message key='${truckingservice.docsgeneral.typeOfDocs.textKey }'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="truckingservice" method="post" action="service" id="truckingserviceForm" cssClass="well">
    <form:hidden path="id"/>
    <form:hidden path="creator.id"/>
    <form:hidden path="createdDate"/>
    <form:hidden path="docsgeneral.id"/>
    <div class="container-fluid">
    	<jsp:include page="fragment/generalInput.jsp"></jsp:include>
	   	<jsp:include page="fragment/servicesTables.jsp"></jsp:include>
	   	<jsp:include page="fragment/checkboxInput.jsp"></jsp:include>
	   	<div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty truckingservice.id and !truckingservice.docsgeneral.doAccounting}">
          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
	</div>
    
    </form:form>
    <hr>
    <div class="row">
    	<div class="col-md-7"></div>
    	<div class="col-md-5">
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${truckingservice.creator.username}</span> on ${truckingservice.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${truckingservice.updator.username}</span> on ${truckingservice.lastUpdateDate}
    	</div>
    </div>
    
</div>
 
<v:javascript formName="truckingserviceForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>