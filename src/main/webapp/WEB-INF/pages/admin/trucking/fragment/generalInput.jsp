<%@ include file="/common/taglibs.jsp"%>
<div class="row">
		    <div class="form-group col-md-2">
		        <appfuse:label styleClass="control-label" key="truckingservice.jobNo"/>
		        <form:input path="docsgeneral.jobNo" id="jobNo" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    
			<div class="form-group col-md-2">
				<appfuse:label styleClass="control-label" key="truckingservice.customerCode"/>
			  	<form:input path="docsgeneral.customer.code" id="customercode" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
			</div>
			
			<div class="form-group col-md-2">
				  <appfuse:label styleClass="control-label" key="truckingservice.typeOfContainer"/>
				  <form:input path="docsgeneral.typeOfContainer.value" id="typeOfContainer"  autofocus="true" cssClass="form-control" disabled="true"/>
			</div>
			
			<div class="form-group col-md-2">
				  <appfuse:label styleClass="control-label" key="truckingservice.noOfPkgs"/>
				  <form:input path="docsgeneral.noOfPkgs" id="noOfPkgs"  autofocus="true" cssClass="form-control number" disabled="true"/>
			</div>
			
			<div class="form-group col-md-2">
				  <appfuse:label styleClass="control-label" key="truckingservice.weigth"/>
				  <form:input path="docsgeneral.weigth" id="weigth"  autofocus="true" cssClass="form-control money" disabled="true"/>
			</div>
			
			<div class="form-group col-md-2">
				  <appfuse:label styleClass="control-label" key="truckingservice.cmb"/>
				  <form:input path="docsgeneral.cmb" id="cmb"  autofocus="true" cssClass="form-control number" disabled="true"/>
			</div>
			
			<div class="form-group col-md-3">
				 <appfuse:label styleClass="control-label" key="truckingservice.contactDelivery"/>
				 <form:input path="contactDelivery" id="contactDelivery"  autofocus="true" cssClass="form-control"/>
			</div>
				    
			<div class="form-group col-md-3">
				  <appfuse:label styleClass="control-label" key="truckingservice.infoInvoice"/>
				  <form:input path="infoInvoice" id="infoInvoice"  autofocus="true" cssClass="form-control"/>
			</div>
			<%-- <c:choose>
				<c:when test="${truckingservice.docsgeneral.typeOfDocs.value != 0}"> --%>
					<%-- <div class="form-group col-md-3">
						 <appfuse:label styleClass="control-label" key="truckingservice.placeEmptyDown"/>
						 <form:input path="docsgeneral.placeEmptyDown" id="placeEmptyDown"  autofocus="true" cssClass="form-control" disabled="true"/>
					</div>
						    
					<div class="form-group col-md-3">
						  <appfuse:label styleClass="control-label" key="truckingservice.placeEmptyUp"/>
						  <form:input path="docsgeneral.placeEmptyUp" id="infoInvoice"  autofocus="true" cssClass="form-control" disabled="true"/>
					</div> --%>
					
			<c:choose>
				<c:when test="${truckingservice.docsgeneral.typeOfDocs.value == 1 || truckingservice.docsgeneral.typeOfDocs.value == 4 || truckingservice.docsgeneral.typeOfDocs.value == 5}">
				    <div class="form-group col-md-3">			    
				        <appfuse:label styleClass="control-label" key="inland.placeEmptyDown"/>
				        <form:input path="departure" id="departure" autofocus="true" cssClass="form-control"/>
				    </div>
				    
				    <div class="form-group col-md-3">
				        <appfuse:label styleClass="control-label" key="inland.placeEmptyUp"/>
				        <form:input path="arrival" id="arrival" autofocus="true" cssClass="form-control"/>
				    </div>
				</c:when>
				<c:when test="${truckingservice.docsgeneral.typeOfDocs.value == 0}">
					<div class="form-group col-md-3">			    
				        <appfuse:label styleClass="control-label" key="truckingservice.placeEmptyDown"/>
				        <form:input path="departure" id="departure" autofocus="true" cssClass="form-control"/>
				    </div>
				    
				    <div class="form-group col-md-3">
				        <appfuse:label styleClass="control-label" key="truckingservice.placeEmptyUp"/>
				        <form:input path="arrival" id="arrival" autofocus="true" cssClass="form-control"/>
				    </div>
				    
				
				    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">		  
				        <appfuse:label styleClass="control-label" key="packageInfo.freeDemDate"/>
				        <form:input path="docsgeneral.packageinfo.freeDemDate" id="freeDemDate" provide="datepicker" maxlength="45"  cssClass="form-control" disabled="true"/>
				    </div>
		    
				    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
				        <appfuse:label styleClass="control-label" key="packageInfo.freeDetDate"/>
				        <form:input path="docsgeneral.packageinfo.freeDetDate" id="freeDetDate" provide="datepicker" maxlength="45"  cssClass="form-control" disabled="true"/>
				    </div>
				</c:when>
			</c:choose>
							
			<c:if test="${truckingservice.docsgeneral.portPutCont != null}">
				<div class="form-group col-md-3">
					 <appfuse:label styleClass="control-label" key="truckingservice.portPutCont"/>
					 <form:input path="docsgeneral.portPutCont" id="portPutCont"  autofocus="true" cssClass="form-control" disabled="true"/>
				</div>
			</c:if>
			
			<c:if test="${truckingservice.docsgeneral.placeGetCont != null}">
				<div class="form-group col-md-3">
					  <appfuse:label styleClass="control-label" key="truckingservice.placeGetCont"/>
					  <form:input path="docsgeneral.placeGetCont" id="placeGetCont"  autofocus="true" cssClass="form-control" disabled="true"/>
				</div>
			</c:if>
			
			    <div class="form-group col-md-3">
					<appfuse:label styleClass="control-label" key="truckingservice.placeDelivery"/>
				  	<form:input path="docsgeneral.placeDelivery" id="placeDelivery" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
				</div>
				
				<div class="form-group col-md-3">
					<appfuse:label styleClass="control-label" key="truckingservice.placeRev"/>
				  	<form:input path="docsgeneral.placeRev" id="placeRev" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
				</div>
		    
		    <c:if test="${truckingservice.docsgeneral.placeDelivery1 != null}">
			    <div class="form-group col-md-3">
					<appfuse:label styleClass="control-label" key="truckingservice.placeDelivery1"/>
				  	<form:input path="docsgeneral.placeDelivery1" id="placeDelivery1" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
				</div>
		    </c:if>
		    
		    <c:if test="${truckingservice.docsgeneral.placeRev1 != null}">
			    <div class="form-group col-md-3">
					<appfuse:label styleClass="control-label" key="truckingservice.placeRev1"/>
				  	<form:input path="docsgeneral.placeRev1" id="placeRev1" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
				</div>
		    </c:if>
		    <div class="form-group col-md-9 has-warning">
		        <appfuse:label styleClass="control-label" key="packageInfo.note"/>
		        <form:textarea path="docsgeneral.note" id="note"  cssClass="form-control" disabled="true"/>
		        <form:errors path="docsgeneral.note" cssClass="help-block"/>
		    </div>
	    </div>
