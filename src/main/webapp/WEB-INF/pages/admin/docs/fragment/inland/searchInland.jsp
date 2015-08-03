<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="packageInfoList.title" /></title>
<meta name="menu" content="DocsMenu" />
</head>
<div class="col-sm-12">
	<form:form commandName="search" method="post"
		action="searchInland" id="packageInfoForm" cssClass="well">
		<div class="row">
			<!-- Add Phuc 1.8 -->
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="packageInfo.jobId" />
				<form:select path="jobNo" id="customercode"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach items="${docsSelection.docsgenerals}" var="docs">
						<form:option value="${docs.id}">${docs.jobNo}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="packageInfo.customerCode" />
				<form:select path="customer" id="customercode"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach items="${docsSelection.customers}" var="cust">
						<form:option value="${cust.id}">${cust.code}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="packageInfo.typeOfContainer" />
				<form:select path="typeOfContainer"
					id="typeOfContainer" cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach var="entry" items="${docsSelection.selections['typeOfContainers']}">
						<form:option value="${entry.key}">${entry.value}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="contseal.noOfCont" />
				<form:select path="contSeal" id="customercode"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach items="${docsSelection.contseals}" var="contseal">
						<form:option value="${contseal.id}">${contseal.noOfCont}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<!-- End Add Phuc 1.8 -->
			
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="inland.doDelivery" />
				<form:select path="doDelivery" id="doMoveMethod"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach var="entry" items="${enumStatus}">
						<form:option value="${entry}">${entry}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-4">
			    <appfuse:label styleClass="control-label" key="inland.dateStartNKH"/>
			    <form:input path="startDate" provide="datepicker" maxlength="45"  class="form-control" id="startDate"/>
		    </div>
		    
		    <div class="form-group col-md-4">
			    <appfuse:label styleClass="control-label" key="inland.dateEndNKH"/>
			    <form:input path="endDate" provide="datepicker" maxlength="45"  class="form-control" id="endDate"/>
		    </div>
		</div>
		<div class="row">
			<div class="form-group col-md-2">
				<button type="submit" class="btn btn-primary"><i class="fa fa-search"></i> <fmt:message key="button.search"></fmt:message></button>
			</div>

		</div>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		/* $('select').prepend('<option val="0">None</option>'); */
	});
	
</script>