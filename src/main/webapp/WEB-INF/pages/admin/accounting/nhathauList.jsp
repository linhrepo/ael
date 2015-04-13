<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="accountingnhathau.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

<div class="col-sm-10">
    <h2><fmt:message key="accountingnhathau.heading"/></h2>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form  method="get" action="nhathau" id="accountingNhathauForm" class="well">
     <div class="container-fluid">
    	<div class="row">
		    <div class="form-group col-md-8">
			    <appfuse:label styleClass="control-label" key="nhathau.name"/>
			    <select name="nhathauId" class="form-control">
	    			<c:forEach items="${nhathauList}" var="nhathau">
	    				<option value="${nhathau.id}">${nhathau.name}</option>
	    			</c:forEach>
		    	</select>
		    </div>
		    
		    <div class="form-group col-md-2">
			    <appfuse:label styleClass="control-label" key="accountingnhathau.startdate"/>
			    <input name="startDate" provide="datepicker" maxlength="45"  class="form-control" id="startDate"/>
			    <%-- <select name="month" class="form-control">
		    			<c:forEach begin="1" end="12" var="month">
		    				<option value="${month}">${month}</option>
		    			</c:forEach>
		    	</select> --%>
		    </div>
		    
		    <div class="form-group col-md-2">
			    <appfuse:label styleClass="control-label" key="accountingnhathau.enddate"/>
			    <input name="endDate" provide="datepicker" maxlength="45"  class="form-control" id="endDate"/>
			    <%-- <select name="year" class="form-control">
		    			<c:forEach begin="2015" end="2016" var="year">
		    				<option value="${year}">${year}</option>
		    			</c:forEach>
		    	</select> --%>
		    </div>
		    <%-- <div class="form-group col-md-2">
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
		    </div> --%>
    	</div>
    </div>
     <hr>
    <div class="form-group form-actions">
        <button class="btn btn-primary">
    	<i class="fa fa-search"></i> <fmt:message key="button.search"/>
    	<input type="submit" style="display: none;"/>
    	</button>
    </div>
    </form>
</div>
<script>
	$(document).ready(function(){
		$("#startDate").datepicker("setDate",new Date());
		$("#endDate").datepicker("setDate",new Date());
		/* $("#startDate").datepicker({defaultDate: new Date()}); */
	});
	
</script>
