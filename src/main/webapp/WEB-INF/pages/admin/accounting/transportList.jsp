<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="accountingTrans.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

<div class="col-sm-12">
    <h2><fmt:message key="accountingTrans.heading"/></h2>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form  method="get" action="transport" id="accountingtranForm" class="well">
     <div class="container-fluid">
    	<div class="row">
		    <div class="form-group col-md-8">
		    <appfuse:label styleClass="control-label" key="customer.name"/>
		    <select name="customerId" class="form-control">
	    			<c:forEach items="${customers}" var="customer">
	    				<option value="${customer.id}">${customer.code} - ${customer.name}</option>
	    			</c:forEach>
	    	</select>
		    </div>
		    
		    <div class="form-group col-md-2">
		    <appfuse:label styleClass="control-label" key="accounting.month"/>
		    <select name="month" class="form-control">
	    			<c:forEach begin="1" end="12" var="month">
	    				<option value="${month}">${month}</option>
	    			</c:forEach>
	    	</select>
		    </div>
		    
		    <div class="form-group col-md-2">
		    <appfuse:label styleClass="control-label" key="accounting.year"/>
		    <select name="year" class="form-control">
	    			<c:forEach begin="2015" end="2016" var="year">
	    				<option value="${year}">${year}</option>
	    			</c:forEach>
	    	</select>
		    </div>
    	</div>
    </div>
     <hr>
    <div class="form-group form-actions">
        <input type="submit" class="btn btn-primary" value="<fmt:message key="button.search"/>"/>
    </div>
    </form>
</div>

