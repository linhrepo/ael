<%@ include file="/common/taglibs.jsp"%>
<script src="<c:url value="/scripts/custom/phieuthu.js" />"></script>
<head>
    <title><fmt:message key="menu.acct.phieuthu"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="menu.acct.phieuthu"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='menu.acct.phieuthu'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="refund" method="post" action="phieuthu" id="refundForm" cssClass="well" readonly="${refund.doApproval == 'true' ? 'readonly' :''}" >
    	<jsp:include page="fragment/phieuthu/phieuthuInput.jsp"></jsp:include>
    	<jsp:include page="fragment/phieuthu/phieuthuDetails.jsp"></jsp:include>
<%--     	<jsp:include page="fragment/phieuthu/accInput.jsp"></jsp:include> --%>
    	 <div class="form-group form-actions">
    		<c:if test="${refund.doApproval != true}">
		        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
		            <i class="fa fa-floppy-o"></i> <fmt:message key="button.save"/>
		        </button>
	        </c:if>
	        <c:if test="${refund.doApproval == true}">
	        	<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACCOUNTING"> 
	        		<button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
		           	 <i class="fa fa-floppy-o"></i> <fmt:message key="button.save"/>
		        	</button>
	        	</security:authorize>
	        </c:if>
	         <c:if test="${not empty refund.id}">
	        	<security:authorize ifAnyGranted="ROLE_ADMIN,ROLE_ACCOUNTING"> 
		        	<a class="btn btn-success" href="phieuthu/download?id=${refund.id}">
		           	 <i class="fa fa-print"></i> <fmt:message key="refund.printPayment"/>
		        	</a>
	        	</security:authorize>
	        </c:if>
	        <c:if test="${not empty refund.id && refund.doApproval != true}">
	          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
	              <i class="fa fa-trash"></i> <fmt:message key="button.delete"/>
	          </button>
	        </c:if>
    	</div>
    </form:form>
    <hr>
    <div class="row">
    	<div class="col-md-7"></div>
    	<div class="col-md-5">
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${refund.creator.username}</span> on ${refund.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${refund.updator.username}</span> on ${refund.lastUpdateDate}
    	</div>
    </div>
</div>
 
<v:javascript formName="phieuthuForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>