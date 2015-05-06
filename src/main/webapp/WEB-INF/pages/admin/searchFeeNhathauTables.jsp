<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="packageInfoList.title" /></title>
<meta name="menu" content="AdminMenu" />
</head>
<div class="col-sm-12">
	<form:form commandName="search" method="post"
		action="searchFeeNhathauTables" id="feeNhathauTabelsForm" cssClass="well">
		<div class="row">
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="customer.title" />
				<form:select path="customer" id="customer"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach items="${docsSelection.customers}" var="cust">
						<form:option value="${cust.id}">${cust.code}</form:option>
					</c:forEach>
				</form:select>
			</div>
			
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="accountingnhathau.jobNo" />
				<form:select path="job" id="job" 
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<form:options items="${jobList }"/>
				</form:select>
			</div>
			
		    <div class="form-group col-md-3">
			    <appfuse:label styleClass="control-label" key="accountingnhathau.startdate"/>
			    <form:input path="startDate" provide="datepicker" maxlength="45"  class="form-control" id="startDate"/>
		    </div>
		</div>
		<div class="row">
		    <div class="form-group col-md-3">
			    <appfuse:label styleClass="control-label" key="accountingnhathau.enddate"/>
			    <form:input path="endDate" provide="datepicker" maxlength="45"  class="form-control" id="endDate"/>
		    </div>
		    <div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="accounting.approval" />
				<form:select path="approved" id="doRelease"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach var="entry" items="${enumStatus}">
						<form:option value="${entry}">${entry}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-3">
				<appfuse:label styleClass="control-label"
					key="accounting.feeAdminApproval" />
				<form:select path="checkByAdmin" id="doRelease"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach var="entry" items="${enumStatus}">
						<form:option value="${entry}">${entry}</form:option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-md-2">
				<button type="submit" class="btn btn-primary" onclick="bCancel=false" name="search"><i class="fa fa-search"></i> <fmt:message key="button.search"></fmt:message></button>
			</div>

		</div> 
	</form:form>
</div>