<%@ include file="/common/taglibs.jsp"%>
			<c:if test="${not empty accountingcus.refNo }">
		      <b><fmt:message key="accountingcus.refNo"/>:</b> ${accountingcus.refNo}
		      <hr>
		    </c:if>

<div class="row">
			<form:hidden path="refNo"/>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.companyType"/>
		        <form:input path="docsgeneral.customer.name" id="companyType" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.address"/>
		        <form:input path="docsgeneral.customer.address" id="address" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.taxno"/>
		        <form:input path="docsgeneral.customer.taxno" id="taxno" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.tel"/>
		        <form:input path="docsgeneral.customer.tel" id="tel" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.fax"/>
		        <form:input path="docsgeneral.customer.fax" id="fax" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.bill"/>
		        <form:input path="docsgeneral.infoInvoice" id="bill" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.tkhq"/>
		        <form:input path="docsgeneral.packageinfo.cusDecOnNo" id="tkhq" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.placeDelivery"/>
		        <form:input path="docsgeneral.placeDelivery" id="placeDelivery" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.cmb"/>
		        <form:input path="docsgeneral.cmbText" id="cmb" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.aelcmb"/>
		        <form:input path="docsgeneral.cmbText" id="aelcmb" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.noOfPkgs"/>
		        <form:input path="docsgeneral.noOfPkgsText" id="noOfPkgs" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.kg"/>
		        <form:input path="docsgeneral.weigthText" id="kg" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.colourApplying"/>
		        <form:input path="docsgeneral.packageinfo.colourApplying.value" id="colourApplying" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.po"/>
		        <form:input path="docsgeneral.packageinfo.po" id="po" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-3">
		        <appfuse:label styleClass="control-label" key="accountingcus.PTVT"/>
		        <form:input path="docsgeneral.PTVT" id="PTVT" maxlength="45" autofocus="true" cssClass="form-control" disabled="true"/>
		    </div>
		    <div class="form-group col-md-9 has-warning">
		        <appfuse:label styleClass="control-label" key="packageInfo.note"/>
		        <form:textarea path="docsgeneral.note" id="note"  cssClass="form-control" disabled="true"/>
		    </div>
	    </div>