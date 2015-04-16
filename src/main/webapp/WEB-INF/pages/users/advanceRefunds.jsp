<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="advanceList.title"/></title>
    <meta name="menu" content="Home"/>
</head>

<div class="col-sm-12">	
    <h2></h2>
    <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><fmt:message key="advanceList.heading"/></a></li>
		    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab"><fmt:message key="refundList.heading"/></a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		  <input type="hidden" id="flag" value="${flag }">
		    <div role="tabpanel" class="tab-pane active" id="home"><jsp:include page="fragment/advanceForms.jsp"></jsp:include></div>
		    <div role="tabpanel" class="tab-pane" id="settings"><jsp:include page="fragment/refunds.jsp"></jsp:include></div>
		  </div>
		</div>
</div>
<script>
	$(document).ready(function(){
		$("#startDate").datepicker("setDate",new Date());
		$("#endDate").datepicker("setDate",new Date());
		$("#startTimeRefund").datepicker("setDate",new Date());
		$("#endTimeRefund").datepicker("setDate",new Date());
		$("#startDate1").datepicker("setDate",new Date());
		$("#endDate1").datepicker("setDate",new Date());
		
		//active tab
		if($('#flag').val() == "1"){
			$('.nav-tabs a[href="#home"]').tab('show');
		}
		else if($('#flag').val() == "2"){
			$('.nav-tabs a[href="#settings"]').tab('show');
		}
	});
	
</script>
