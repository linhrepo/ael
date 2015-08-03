<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="packageInfoList.title" /></title>
<meta name="menu" content="AccountingMenu" />
</head>
<div class="col-sm-12">
	<form:form commandName="search" method="post"
		action="searchDebit" id="feeTabelsForm" cssClass="well">
		<div class="row">
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="accountingnhathau.jobNo" />
				<form:select path="job" id="job" 
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<form:options items="${jobList }"/>
				</form:select>
			</div>
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="trucking.typeOfDocs" />
				<form:select path="typeOfDocs" id="typeOfDocs"
					cssClass="form-control select2">
					<form:option value=""><fmt:message key="searchall" /></form:option>
					<c:forEach var="entry" items="${typeOfDocs}">
						<form:option value="${entry.key}"><fmt:message key="${entry.value}" /></form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="debit.collectMoney" />
				<form:select path="isCollectMoney" id="isCollectMoney"
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