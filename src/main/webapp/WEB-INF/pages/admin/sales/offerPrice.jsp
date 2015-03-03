<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="offerPrice.title"/></title>
    <meta name="menu" content="SalesMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="offerPrice.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='offerPrice.heading'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="offerPrice" method="post" action="offerPrice" id="offerPriceForm" cssClass="well">
    <form:hidden path="id"/>
    <form:hidden path="customer.id"/>
    <form:hidden path="creator.id"/>
    <form:hidden path="createdDate"/>
    <div class="container-fluid">
	    <div class="row">
		    <spring:bind path="offerPrice.customer.code">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="customer.code"/>
		        <form:input path="customer.code" id="code" maxlength="45"  cssClass="form-control" disabled="true"/>
		        <form:errors path="customer.code" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="offerPrice.customer.name">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="customer.name"/>
		        <form:input path="customer.name" id="name" maxlength="255" autofocus="true" cssClass="form-control" disabled="true"/>
		        <form:errors path="customer.name" cssClass="help-block"/>
		    </div>
	    
		    <spring:bind path="offerPrice.typeOfService">
			<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
			   <appfuse:label styleClass="control-label" key="offerPrice.typeOfService"/>
			    <form:select path="typeOfService" id="typeOfService" cssClass="form-control" >
		         <c:forEach items="${services}" var="sem" varStatus="cnt">
                  <option value="${sem.key}" <c:if test="${sem.key == offerPrice.typeOfService.value}">selected</c:if>><fmt:message key="${sem.value}"/></option>
     			 </c:forEach>
		        </form:select>
			   <form:errors path="typeOfService" cssClass="help-block"/>
			</div>
			
			<spring:bind path="offerPrice.dateOffer">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="offerPrice.dateOffer"/>
		        <form:input data-provide="datepicker" path="dateOffer" id="dateOffer" maxlength="255" autofocus="true" cssClass="form-control"/>
		        <form:errors path="dateOffer" cssClass="help-block"/>
		    </div>
	    </div>
	    
	</div>
    	<h3><fmt:message key="offerPriceList.title"/></h3>
    	 <hr>
    	<table id="offerItemList" class="display table table-condensed inFormTable" cellspacing="0" width="100%">
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="offerItem.nameOfService"/></th>
                <th><fmt:message key="offerItem.feeWithVAT"/></th>
                <th><fmt:message key="offerItem.feeNoVAT"/></th>
                <th><fmt:message key="offerItem.currency"/></th>
                <th><fmt:message key="offerItem.feeUnit"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${offerPrice.offerItems}" var="offerItem" varStatus="idx">
        	<tr class="${offerItem.isAdded == true ? 'hidden' :''}">
                <td colType="index">${idx.index+1}</td>
                <td colType="generalInfo" class="hidden">
	                	<form:hidden path="offerItems[${idx.index}].id" />
	                	<form:hidden path="offerItems[${idx.index}].isAdded" valueType="added" />
	                	<form:hidden path="offerItems[${idx.index}].isDeleted" valueType="deleted"/>
	            </td>
              	<td>
              			<spring:bind path="offerItems[${idx.index}].nameOfService.id">
					    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					    </spring:bind>
					        <form:select path="offerItems[${idx.index}].nameOfService.id" id="nameOfService${idx.index}" autofocus="true" cssClass="form-control" items="${selections['services']}"/>
					        <form:errors path="offerItems[${idx.index}].nameOfService.id" cssClass="help-block"/>
					    </div>
				</td>
              	<td>
              			<spring:bind path="offerItems[${idx.index}].feeWithVAT">
					    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					    </spring:bind>
					        <form:input path="offerItems[${idx.index}].feeWithVAT" id="feeWithVAT${idx.index}" maxlength="45" autofocus="true" cssClass="form-control money"/>
					        <form:errors path="offerItems[${idx.index}].feeWithVAT" cssClass="help-block"/>
					    </div>
				</td>
              	<td>
              			<spring:bind path="offerItems[${idx.index}].feeNoVAT">
					    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					    </spring:bind>
					        <form:input path="offerItems[${idx.index}].feeNoVAT" id="feeNoVAT${idx.index}" maxlength="45" autofocus="true" cssClass="form-control money"/>
					        <form:errors path="offerItems[${idx.index}].feeNoVAT" cssClass="help-block"/>
					    </div>
				</td>
              	<td>
              			<spring:bind path="offerItems[${idx.index}].currency.id">
					    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					    </spring:bind>
					        <form:select path="offerItems[${idx.index}].currency.id" id="currency${idx.index}" autofocus="true" cssClass="form-control" items="${selections['currencies']}"/>
					        <form:errors path="offerItems[${idx.index}].currency.id" cssClass="help-block"/>
					    </div>
				</td>
              	<td>
              			<spring:bind path="offerItems[${idx.index}].feeUnit.id">
					    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					    </spring:bind>
					        <form:select path="offerItems[${idx.index}].feeUnit.id" id="feeUnit${idx.index}" autofocus="true" cssClass="form-control" items="${selections['units']}"/>
					        <form:errors path="offerItems[${idx.index}].feeUnit.id" cssClass="help-block"/>
					    </div>
				</td>
                <td>
                	<span class="iconButton removeRow" title="<fmt:message key='table.buttonEditTitle'/>">
	                			<i class="fa fa-trash"></i>
	                	</span>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="row">
	    	<div class="col-md-10"></div>
	    	<div class="col-md-2">
	    		<span class="btn btn-primary" target-table="offerItemList">
		            <i class="icon-ok"></i> <fmt:message key="button.add"/></span>
	    	</div>
    </div>
    <hr>
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty offerPrice.id}">
          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
        <a type="submit" class="btn btn-default" name="cancel" href="customer?id=${offerPrice.customer.id }">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </a>
    </div>
    </form:form>
    <hr>
    <div class="row">
    	<div class="col-md-7"></div>
    	<div class="col-md-5">
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${offerPrice.creator.username}</span> on ${offerPrice.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${offerPrice.updator.username}</span> on ${offerPrice.lastUpdateDate}
    	</div>
    </div>
</div>
 
<v:javascript formName="offerPriceForm" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>