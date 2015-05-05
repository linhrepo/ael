<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="accountingnhathau.title"/></title>
    <meta name="menu" content="NhathauMenu"/>
</head>

<div class="col-sm-10">
    <h2><fmt:message key="accountingnhathau.heading"/></h2>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="conditions" method="get"
		action="nhathau" id="accountingNhathauForm" cssClass="well">
	     <div class="container-fluid">
	    	<div class="row">
			    <div class="form-group col-md-3">
				    <appfuse:label styleClass="control-label" key="nhathau.name"/>
				    <form:select path="nhathauId" id="nhathauId" class="form-control">
		    			<c:forEach items="${nhathauList}" var="nhathau">
		    				<option value="${nhathau.id}">${nhathau.name}</option>
		    			</c:forEach>
			    	</form:select>
			    </div>
			    
			    <div class="form-group col-md-2">
					<appfuse:label styleClass="control-label"
						key="customer.title" />
					<form:select path="customerId" id="customercode"
						cssClass="form-control select2">
						<form:option value=""><fmt:message key="searchall" /></form:option>
						<c:forEach items="${docsSelection.customers}" var="cust">
							<form:option value="${cust.id}">${cust.code}</form:option>
						</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group col-md-2">
					<appfuse:label styleClass="control-label"
						key="accountingnhathau.jobNo" />
					<form:select path="job" id="job" 
						cssClass="form-control select2">
						<form:option value=""><fmt:message key="searchall" /></form:option>
						<form:options items="${jobList }"/>
					</form:select>
				</div>
				
			    <div class="form-group col-md-2">
				    <appfuse:label styleClass="control-label" key="accountingnhathau.startdate"/>
				    <form:input path="startDate" provide="datepicker" maxlength="45"  class="form-control" id="startDate"/>
			    </div>
			    
			    <div class="form-group col-md-2">
				    <appfuse:label styleClass="control-label" key="accountingnhathau.enddate"/>
				    <form:input path="endDate" provide="datepicker" maxlength="45"  class="form-control" id="endDate"/>
			    </div>
	    	</div>
	    </div>
	     <hr>
	    <div class="form-group form-actions">
	        <button class="btn btn-primary">
	    	<i class="fa fa-search"></i> <fmt:message key="button.search"/>
	    	<input type="submit" style="display: none;"/>
	    	</button>
	    </div>
    </form:form>
</div>
