<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="feeTablesacc.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

<div class="col-sm-10">
<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><fmt:message key="accounting.feeSummary" /></a></li>
		<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab"><fmt:message key="accounting.feeNhathau" /></a></li>
<%-- 		<li role="presentation"><a href="#sumary" aria-controls="settings" role="tab" data-toggle="tab"><fmt:message key="accounting.feeAdmin" /></a></li> --%>
	</ul>
	<!-- Tab panes -->
	<div class="tab-content">
		<input type="hidden" id="flag" value="${flag}">
		<div role="tabpanel" class="tab-pane active" id="home"><jsp:include page="../feeManifest.jsp"></jsp:include></div>
		<div role="tabpanel" class="tab-pane" id="settings"><jsp:include page="fragment/feeNhathau.jsp"></jsp:include></div>		
<%-- 		<div role="tabpanel" class="tab-pane" id="sumary"><jsp:include page="fragment/feeRefundAdmin.jsp"></jsp:include></div> --%>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/custom/approveFee.js'/>"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 if($('#flag').val() == "1"){		 
	 	$('.nav-tabs a[href="#settings"]').tab('show');
	 }
 });
</script>
<script type="text/javascript" src="<c:url value='/scripts/advanceRefund.js'/>"></script>
