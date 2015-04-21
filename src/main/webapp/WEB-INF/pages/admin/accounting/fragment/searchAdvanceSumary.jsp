<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="packageInfoList.title" /></title>
<meta name="menu" content="AccountingMenu" />
</head>
<div class="col-sm-12">
	<form:form commandName="search" method="post"
		action="searchAdvanceSumary" id="advanceSumaryForm" cssClass="well">
		<div class="row">
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="advanceform.employee" />
				<form:select path="employee" id="employeecode"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach items="${docsSelection.staff}" var="st">
		        		<form:option value="${st.id}">${st.firstName}&nbsp;${st.lastName} </form:option>
		        	</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="advanceform.startdate" />
					<form:input path="startDate" provide="datepicker" id="startDate"  cssClass="form-control"/>
			        <form:errors path="startDate" cssClass="help-block"/>
			</div>
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="advanceform.enddate" />
					<form:input path="endDate" provide="datepicker" id="endDate"  cssClass="form-control"/>
			        <form:errors path="endDate" cssClass="help-block"/>
			</div>			
		</div> 
		<div class="row">
			<div class="form-group col-md-2">
				<button type="submit" class="btn btn-primary" onclick="bCancel=false" name="search"><i class="fa fa-search"></i> <fmt:message key="button.search"></fmt:message></button>
				<button type="submit" class="btn btn-primary" onclick="bCancel=false" name="export"><i class="fa fa-print"></i> <fmt:message key="accounting.transport.download"></fmt:message></button>
			</div>
		</div>
	</form:form>
</div>