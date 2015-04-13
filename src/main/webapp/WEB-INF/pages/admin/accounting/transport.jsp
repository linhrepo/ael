<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="accountingTrans.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
    <meta name="child" content="true"/>
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
    	<div role="tabpanel">

		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">
		    	<fmt:message key="menu.docs.fees"/>
		    </a></li>
		    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">
		    	<fmt:message key="menu.sales.prices"/>
		    </a></li>
		     <li role="presentation"><a href="#files" aria-controls="profile" role="tab" data-toggle="tab">
		    	<fmt:message key="offerPrice.files"/>
		    </a></li>
		  </ul>
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="home"><jsp:include page="fragment/transport/transTable.jsp"></jsp:include></div>
		    <div role="tabpanel" class="tab-pane" id="profile"><jsp:include page="fragment/transport/offers.jsp"></jsp:include></div>
		    <div role="tabpanel" class="tab-pane" id="files"><jsp:include page="fragment/transport/fileOffers.jsp"></jsp:include></div>
		  </div>
		</div>
    	
	</div>
    <div class="form-group form-actions">
						<a class="btn btn-success" href="transport/download?customerId=${accountingTrans.condition.customerId}&month=${accountingTrans.condition.month}&year=${accountingTrans.condition.year}">
							<i class="fa fa-print"></i>
							<fmt:message key="accounting.transport.download" />
						</a>
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="fa fa-floppy-o"></i> <fmt:message key="button.save"/>
        </button>
        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="fa fa-sign-out"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    </form:form>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>