<%@ include file="/common/taglibs.jsp"%>
<div class="row">
		    <spring:bind path="packageInfo.docsgeneral.jobNo">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.jobNo"/>
		        <form:input path="docsgeneral.jobNo" id="jobNo" maxlength="45"  cssClass="form-control" readonly="true"/>
		        <form:errors path="docsgeneral.jobNo" cssClass="help-block"/>
		    </div>
		    
		    <c:choose >
		    	<c:when test="${empty  packageInfo.docsgeneral.customer}">
				   	<spring:bind path="packageInfo.docsgeneral.customer.code">
				    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
				    </spring:bind>
				        <appfuse:label styleClass="control-label" key="packageInfo.customerCode"/>
				        <form:select path="docsgeneral.customer.id" id="customercode"  cssClass="form-control select2" readonly="${packageInfo.id != null ? 'true' : 'false' }">
				        	<c:forEach items="${docsSelection.customers}" var="cust">
				        		<form:option value="${cust.id}">${cust.code}</form:option>
				        	</c:forEach>
				        </form:select>
				        <form:errors path="docsgeneral.customer.id" cssClass="help-block"/>
				    </div>
				</c:when>
				<c:otherwise>
					<form:hidden path="docsgeneral.customer.id"/>
					<spring:bind path="packageInfo.docsgeneral.customer.code">
				    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
				    </spring:bind>
				        <appfuse:label styleClass="control-label" key="packageInfo.customerCode"/>
				        <form:input path="docsgeneral.customer.code" id="customercode" maxlength="45"  cssClass="form-control" disabled="true"/>
				        <form:errors path="docsgeneral.customer.code" cssClass="help-block"/>
				    </div>
				</c:otherwise>
		    </c:choose>
		    
		    <spring:bind path="packageInfo.docsgeneral.docReceiveDate">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.docReceiveDate"/>
		        <form:input path="docsgeneral.docReceiveDate" data-provide="datepicker" id="docReceiveDate" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="docsgeneral.docReceiveDate" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.imExMode">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2" addUrl="/admin/config/constant" type="11">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.imExMode"/>
		        <form:select path="imExMode.id" id="imExMode"   cssClass="form-control select2" items="${docsSelection.selections['imExModes']}"/>
		        <form:errors path="imExMode" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.processingStaff">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.processingStaff"/>
		        <form:select path="docsgeneral.processingStaff.id" id="processingStaff"  cssClass="form-control select2">
		        	<c:forEach items="${docsSelection.staff}" var="st">
		        		<form:option value="${st.id}">${st.firstName}&nbsp;${st.lastName} </form:option>
		        	</c:forEach>
		        </form:select>
		        <form:errors path="docsgeneral.processingStaff.id" cssClass="help-block"/>
		    </div>
		    
		    <div class="form-group col-md-1">
		    	<fmt:message key='packageInfo.imported' var="importText"/>
		        <form:radiobutton path="docsgeneral.isImport" value="true" label="${importText}"/>
		        <br>
		        <fmt:message key='packageInfo.exported' var="exportText"/>
		        <form:radiobutton path="docsgeneral.isImport" value="false" label="${exportText}"/>
		    </div>
	    </div>
	    <hr>
	    <div class="row">
	    	<spring:bind path="packageInfo.docsgeneral.productDescription">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-6 ">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.productDescription"/>
		        <form:textarea path="docsgeneral.productDescription" id="productDescription"  cssClass="form-control "/>
		        <form:errors path="docsgeneral.processingStaff" cssClass="help-block"/>
		    </div>
		    
	    	 <spring:bind path="packageInfo.docsgeneral.note">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-6 has-warning">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.note"/>
		        <form:textarea path="docsgeneral.note" id="note"  cssClass="form-control "/>
		        <form:errors path="docsgeneral.note" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.typeOfTransport.id">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.typeOfTransport"/>
		        <form:select path="typeOfTransport.id" id="typeOfTransport"   cssClass="form-control select2" items="${docsSelection.selections['typeOfTransports']}"/>
		        <form:errors path="typeOfTransport.id" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.typeOfContainer.id">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.typeOfContainer"/>
		        <form:select 
		        showElements="-1:consealContainer" 
		        path="docsgeneral.typeOfContainer.id" id="typeOfContainer"   cssClass="form-control select2" items="${docsSelection.selections['typeOfContainers']}"/>
		        <form:errors path="docsgeneral.typeOfContainer.id" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.shipper">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.shipper"/>
		        <form:input path="shipper" id="shipper" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="shipper" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.nameVehicle">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.nameVehicle"/>
		        <form:input path="nameVehicle" id="nameVehicle" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="nameVehicle" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.po">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.po"/>
		        <form:input path="po" id="po" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="po" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.invoice">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.invoice"/>
		        <form:input path="invoice" id="invoice" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="invoice" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.consignee">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.consignee"/>
		        <form:input path="consignee" id="consignee" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="consignee" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.placeDelivery">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.placeDelivery"/>
		        <form:input path="docsgeneral.placeDelivery" id="placeDelivery" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="docsgeneral.placeDelivery" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.placeRev">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.placeRev"/>
		        <form:input path="docsgeneral.placeRev" id="placeRev" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="docsgeneral.placeRev" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.cmb">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.cmb"/>
		        <form:input path="docsgeneral.cmb" id="cmb" maxlength="45"  cssClass="form-control number"/>
		        <form:errors path="docsgeneral.cmb" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.billOfLading">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.billOfLading"/>
		        <form:input path="billOfLading" id="billOfLading" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="billOfLading" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.noOfPkgs">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.noOfPkgs"/>
		        <form:input path="docsgeneral.noOfPkgs" id="noOfPkgs" maxlength="45"  cssClass="form-control number"/>
		        <form:errors path="docsgeneral.noOfPkgs" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.portOfLoading">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.portOfLoading"/>
		        <form:input path="portOfLoading" id="portOfLoading" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="portOfLoading" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.portOfArrival">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.portOfArrival"/>
		        <form:input path="portOfArrival" id="portOfArrival" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="portOfArrival" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.weigth">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.weigth"/>
		        <form:input path="docsgeneral.weigth" id="weigth" maxlength="45"  cssClass="form-control money"/>
		        <form:errors path="docsgeneral.weigth" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.etd">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.etd"/>
		        <form:input path="etd" id="etd" data-provide="datepicker" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="etd" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.eta">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.eta"/>
		        <form:input path="eta" id="eta" data-provide="datepicker" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="eta" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.wareHouseNo">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.wareHouseNo"/>
		        <form:input path="wareHouseNo" id="wareHouseNo" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="wareHouseNo" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.customsDept">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.customsDept"/>
		        <form:select path="customsDept.id" id="customsDept"   cssClass="form-control" items="${docsSelection.selections['customDepts']}"/>
		        <form:errors path="customsDept" cssClass="help-block"/>
		    </div>
		    
		     <spring:bind path="packageInfo.freeDemDate">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.freeDemDate"/>
		        <form:input path="freeDemDate" id="freeDemDate" data-provide="datepicker" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="freeDemDate" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.freeDetDate">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.freeDetDate"/>
		        <form:input path="freeDetDate" id="freeDetDate" data-provide="datepicker" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="freeDetDate" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.shippingLine">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.shippingLine"/>
		        <form:select path="docsgeneral.shippingLine.id" id="shippingLine"   cssClass="form-control select2" items="${hangtaus}"/>
		        <form:errors path="docsgeneral.shippingLine" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.placeEmptyDown">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.placeEmptyDown"/>
		        <form:input path="docsgeneral.placeEmptyDown" id="placeEmptyDown" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="docsgeneral.placeEmptyDown" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.placeEmptyUp">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.placeEmptyUp"/>
		        <form:input path="docsgeneral.placeEmptyUp" id="placeEmptyUp" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="docsgeneral.placeEmptyUp" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.colourApplying">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.colourApplying"/>
		        <form:select path="colourApplying.id" id="colourApplying"   cssClass="form-control" items="${docsSelection.selections['colourApplyings']}"/>
		        <form:errors path="colourApplying" cssClass="help-block"/>
		    </div>
		    
		     <spring:bind path="packageInfo.customsDate">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.customsDate"/>
		        <form:input path="customsDate" id="customsDate" data-provide="datepicker" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="customsDate" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.cusDecOnNo">
		    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-2">
		    </spring:bind>
		        <appfuse:label styleClass="control-label" key="packageInfo.cusDecOnNo"/>
		        <form:input path="cusDecOnNo" id="cusDecOnNo" maxlength="45"  cssClass="form-control"/>
		        <form:errors path="cusDecOnNo" cssClass="help-block"/>
		    </div>
		    
		    <spring:bind path="packageInfo.docsgeneral.contactDelivery">
				    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
				    </spring:bind>
				        <appfuse:label styleClass="control-label" key="inland.contactDelivery"/>
				        <form:input path="docsgeneral.contactDelivery" id="contactDelivery"   cssClass="form-control"/>
				        <form:errors path="docsgeneral.contactDelivery" cssClass="help-block"/>
				    </div>
				    
				    <spring:bind path="packageInfo.docsgeneral.infoInvoice">
				    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-3">
				    </spring:bind>
				        <appfuse:label styleClass="control-label" key="inland.infoInvoice"/>
				        <form:input path="docsgeneral.infoInvoice" id="infoInvoice"   cssClass="form-control"/>
				        <form:errors path="docsgeneral.infoInvoice" cssClass="help-block"/>
				    </div>
		    
	    </div>