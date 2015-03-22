<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="advanceform.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="advanceform.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='advanceform.heading'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="advanceform" method="post" action="advanceForm" id="advanceformForm" cssClass="well" readonly="${advanceform.doApproval == 'true' ? 'readonly' :''}" >
    	<jsp:include page="fragment/advanceInput.jsp"></jsp:include>
    	<jsp:include page="fragment/advanceDetails.jsp"></jsp:include>
    	<jsp:include page="fragment/accInput.jsp"></jsp:include>
    	 <div class="form-group form-actions">
    		<c:if test="${advanceform.doApproval != true}">
		        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
		            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
		        </button>
	        </c:if>
	        <c:if test="${advanceform.doApproval == true}">
	        	<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACCOUNTING"> 
	        		<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
		           	 <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
		        	</button>
		        	<a class="btn btn-success">
		           	 <i class="icon-ok icon-white"></i> <fmt:message key="advanceform.printPayment"/>
		        	</a>
	        	</security:authorize>
	        </c:if>
	        <c:if test="${not empty advanceform.id && advanceform.doApproval != true}">
	          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
	              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
	          </button>
	        </c:if>
        <a type="submit" class="btn btn-default" name="cancel" href="advanceForms">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </a>
    	</div>
    </form:form>
    <hr>
    <div class="row">
    	<div class="col-md-7"></div>
    	<div class="col-md-5">
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${advanceform.creator.username}</span> on ${advanceform.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${advanceform.updator.username}</span> on ${advanceform.lastUpdateDate}
    	</div>
    </div>
</div>
 
<v:javascript formName="advanceformForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>