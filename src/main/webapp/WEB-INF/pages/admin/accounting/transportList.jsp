<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="accountingTrans.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

<div class="col-sm-10">
    <h2><fmt:message key="accountingTrans.heading"/></h2>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="conditions" method="get"
		action="transport" id="accountingtranForm" cssClass="well">
     <div class="container-fluid">
    	<div class="row">
		    <div class="form-group col-md-6">
		    <appfuse:label styleClass="control-label" key="customer.name"/>
		    <form:select path="customerId" class="form-control">
	    			<c:forEach items="${customers}" var="customer">
	    				<option value="${customer.id}">${customer.code} - ${customer.name}</option>
	    			</c:forEach>
	    	</form:select>
		    </div>
		    <div class="form-group col-md-2">
				<appfuse:label styleClass="control-label"
					key="accountingnhathau.jobNo" />
				<form:select path="jobList" id="jobList" 
					cssClass="form-control select2" multiple="true">
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

