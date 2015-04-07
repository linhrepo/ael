<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="packageInfoList.title" /></title>
<meta name="menu" content="DocsMenu" />
</head>
<div class="col-sm-12">
	<form:form commandName="search" method="post"
		action="searchInland" id="packageInfoForm" cssClass="well">
		<div class="row">
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="packageInfo.customerCode" />
				<form:select path="customer" id="customercode"
					cssClass="form-control select2">
					<form:option value="">None</form:option>
					<c:forEach items="${docsSelection.customers}" var="cust">
						<form:option value="${cust.id}">${cust.code}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="packageInfo.typeOfContainer" />
				<form:select path="typeOfContainer"
					id="typeOfContainer" cssClass="form-control select2">
					<form:option value="">None</form:option>
					<c:forEach var="entry" items="${docsSelection.selections['typeOfContainers']}">
						<form:option value="${entry.key}">${entry.value}</form:option>
					</c:forEach>
				</form:select>
			</div>
			
			<div class="form-group col-md-4">
				<appfuse:label styleClass="control-label"
					key="inland.doDelivery" />
				<form:select path="doDelivery" id="doMoveMethod"
					cssClass="form-control select2">
					<form:option value="">None</form:option>
					<c:forEach var="entry" items="${enumStatus}">
						<form:option value="${entry}">${entry}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group col-md-2">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>

		</div>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		/* $('select').prepend('<option val="0">None</option>'); */
	});
	
</script>