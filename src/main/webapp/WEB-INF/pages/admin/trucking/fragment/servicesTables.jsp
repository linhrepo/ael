<%@ include file="/common/taglibs.jsp"%>
<h3><fmt:message key="truckingservice.services"/></h3>
    	<hr>
	<div class="row">
	 <%-- <spring:bind path="truckingservice.departure">
			    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
			    </spring:bind>
			        <appfuse:label styleClass="control-label" key="truckingservice.departure"/>
			        <form:input path="departure" id="departure" autofocus="true" cssClass="form-control"/>
			        <form:errors path="departure" cssClass="help-block"/>
			    </div>
	 <spring:bind path="truckingservice.arrival">
			    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''} col-md-4">
			    </spring:bind>
			        <appfuse:label styleClass="control-label" key="truckingservice.arrival"/>
			        <form:input path="arrival" id="arrival" autofocus="true" cssClass="form-control"/>
			        <form:errors path="arrival" cssClass="help-block"/>
			    </div> --%>
	</div> 
<table id="detailsList"
	class="display table table-condensed inFormTableDetail no-more-tables" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th><fmt:message key="table.no" /></th>
			<c:if test="${not empty truckingservice.docsgeneral.contseals and truckingservice.docsgeneral.typeOfContainer.id == -1}">
				<th><fmt:message key="truckingservice.contNo" /></th>
				<th><fmt:message key="truckingservice.sealNo" /></th>
			</c:if>
			
				<c:choose>
					<c:when test="${truckingservice.docsgeneral.typeOfDocs.value == 5}">
						<th><fmt:message key="truckingservice.shippingLine1" /></th>
						<th><fmt:message key="truckingservice.shippingLineSea" /></th>
						<th><fmt:message key="truckingservice.shippingLine2" /></th>
						<th><fmt:message key="truckingservice.shipName" /></th>
						<th><fmt:message key="truckingservice.transCount" /></th>
					</c:when>
					<c:otherwise>
						<th><fmt:message key="truckingservice.shippingLine" /></th>
						<th><fmt:message key="truckingservice.vehicleNo" /></th>
					</c:otherwise>
				</c:choose>
			<th><fmt:message key="truckingservice.dateDev" /></th>
			<th><fmt:message key="truckingservice.deliveryPlace" /></th>
			<c:if test="${truckingservice.docsgeneral.typeOfContainer.id == -2}">
			<th><fmt:message key="table.action" /></th>
			</c:if>
		</tr>
	</thead>
	<tbody class="parent">
		<c:forEach items="${truckingservice.truckingdetails}"
			var="detail" varStatus="idx">
			<tr class="parent ${detail.isAdded == true ? 'hidden' :''} ">
				<td colType="index" data-title="<fmt:message key="table.no"/>">${idx.index+1}</td>
				<td colType="generalInfo" class="hidden">
					<form:hidden path="truckingdetails[${idx.index}].id" /> 
					<form:hidden path="truckingdetails[${idx.index}].isAdded"
								valueType="added" /> 
					<form:hidden
								path="truckingdetails[${idx.index}].isDeleted"
								valueType="deleted" />
				</td>
					<c:if test="${not empty truckingservice.docsgeneral.contseals && truckingservice.docsgeneral.typeOfContainer.id == -1 }">
						<td style="min-width:120px;" data-title="<fmt:message key="truckingservice.contNo"/>">
						<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						 <form:input path="truckingdetails[${idx.index}].consteal.noOfCont" id="contseal${idx.index}" autofocus="true" cssClass="form-control selectOwner" disabled="true"/> 
						 <form:hidden path="truckingdetails[${idx.index}].consteal.id"/>
					</div></td>
					
					<td style="min-width:120px;" data-title="<fmt:message key="truckingservice.sealNo"/>"> 
						<div class="form-group">
							<select class="form-control selectClient"
							 disabled="true"> 
							<c:forEach items="${contsealsSelection.seal}" var="seal">
								<option value="${seal.key}" <c:if test="${detail.consteal.id == seal.key}">selected='selected'</c:if>>${seal.value}</option>
							</c:forEach>
							</select>
						</div>
					</td>
					</c:if>
					<c:choose>
					<c:when test="${truckingservice.docsgeneral.typeOfDocs.value == 5}">
						<td class="feeSelect" data-title="<fmt:message key="truckingservice.shippingLine1"/>"><spring:bind
							path="truckingservice.truckingdetails[${idx.index}].nhathau.id">
							<div
								class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
						</spring:bind> <form:select path="truckingdetails[${idx.index}].nhathau.id" style="min-width:120px;"
							id="phuthau1${idx.index}" autofocus="true" cssClass="form-control"
							items="${nhathaus}" /> <form:errors
							path="truckingdetails[${idx.index}].nhathau.id"
							cssClass="help-block" />
						</div></td>
						<td class="feeSelect" data-title="<fmt:message key="truckingservice.shippingLineSea"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].nhathau.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:select path="truckingdetails[${idx.index}].nhathau.id"
						id="shippingline${idx.index}" autofocus="true" cssClass="form-control"
						items="${hangtaus}" /> <form:errors
						path="truckingdetails[${idx.index}].nhathau.id"
						cssClass="help-block" />
					</div></td>
					<td class="feeSelect" data-title="<fmt:message key="truckingservice.shippingLine2"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].nhathau.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:select path="truckingdetails[${idx.index}].nhathau.id"
						id="phuthau2${idx.index}" autofocus="true" cssClass="form-control"
						items="${nhathaus}" /> <form:errors
						path="truckingdetails[${idx.index}].nhathau.id"
						cssClass="help-block" />
					</div></td>
					<td style="min-width:100px;" data-title="<fmt:message key="truckingservice.shipName"/>" >
					</c:when>
					<c:otherwise>
						<td class="feeSelect" data-title="<fmt:message key="truckingservice.shippingLine"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].nhathau.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:select path="truckingdetails[${idx.index}].nhathau.id"
						id="nhathau${idx.index}" autofocus="true" cssClass="form-control"
						items="${nhathaus}" /> <form:errors
						path="truckingdetails[${idx.index}].nhathau.id"
						cssClass="help-block" />
					</div></td>
					<td style="min-width:100px;" data-title="<fmt:message key="truckingservice.vehicleNo"/>">
					</c:otherwise>
				</c:choose>
					
							<spring:bind path="truckingdetails[${idx.index}].vehicleNo">
								<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							</spring:bind> 
							<form:input path="truckingdetails[${idx.index}].vehicleNo"
								id="amount${idx.index}" maxlength="45" autofocus="true"
								cssClass="form-control" /> 
							<form:errors
								path="truckingdetails[${idx.index}].vehicleNo"
								cssClass="help-block" />
							</div>
				</td>
				<c:choose>
					<c:when test="${truckingservice.docsgeneral.typeOfDocs.value == 5}">
						<td data-title="<fmt:message key="truckingservice.transCount"/>">
							<spring:bind path="truckingdetails[${idx.index}].noOfVehicle">
								<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							</spring:bind> 
							<form:input path="truckingdetails[${idx.index}].noOfVehicle"
								id="amount${idx.index}" maxlength="45" autofocus="true"
								cssClass="form-control" /> 
							<form:errors
								path="truckingdetails[${idx.index}].noOfVehicle"
								cssClass="help-block" />
							</div>
						</td>
					</c:when>
				</c:choose>
				<td data-title="<fmt:message key="truckingservice.dateDev"/>">
							<spring:bind path="truckingdetails[${idx.index}].dateDev">
								<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
							</spring:bind> 
							<form:input path="truckingdetails[${idx.index}].dateDev"
								id="dateDev${idx.index}" maxlength="45" data-provide="datepicker" 
								cssClass="form-control" /> 
							<form:errors
								path="truckingdetails[${idx.index}].dateDev"
								cssClass="help-block" />
							</div>
						</td>
				<td>
					<spring:bind path="truckingdetails[${idx.index}].deliveryPlace">
					<div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> 
					<form:input path="truckingdetails[${idx.index}].deliveryPlace"
								id="deliveryPlace${idx.index}" maxlength="45" autofocus="true"
								cssClass="form-control" /> 
					<form:errors
								path="truckingdetails[${idx.index}].deliveryPlace"
								cssClass="help-block" />
					</div>
				</td>
						<c:if test="${truckingservice.docsgeneral.typeOfContainer.id == -2}">		
							<td rowType="actions">
								<span class="iconButton removeRow" title="<fmt:message key='table.buttonEditTitle'/>"> 
									<i class="fa fa-trash"></i>
								</span>
							</td>
						</c:if>
			</tr>
			<tr colType="detail" class="${detail.isAdded == true ? 'hidden' :''}">
				<td>
					<button class="btn btn-default" type="button" data-toggle="collapse" data-target="#child${idx.index}" aria-expanded="false" aria-controls="collapseExample">
					  <fmt:message key="trucking.tooglefeeTables" />
					</button>
				</td>
				<td colspan="10">
<!-- 				DETAIL -->
<div class="in" id="child${idx.index}">		

<!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
    	<a href="#home${idx.index}" aria-controls="home" role="tab" data-toggle="tab">
    		<fmt:message key="exhibition.feeTablesDetail" />
		</a>
	</li>
    <li role="presentation"><a href="#profile${idx.index}" aria-controls="profile" role="tab" data-toggle="tab">
    	<fmt:message key="exhibition.feeTablesSummary" />
    </a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home${idx.index}">
<h3>
	<fmt:message key="exhibition.feeTables" />
</h3>
<table id="childTable${idx.index}" 
	class="display table table-condensed inFormTable feeTable" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th><fmt:message key="table.no" /></th>
			<th><fmt:message key="inland.masterFee" /></th>
			<th><fmt:message key="inland.feeName" /></th>
			<th><fmt:message key="inland.feevalue" /></th>
			<th><fmt:message key="inland.feevavat" /></th>
			<th><fmt:message key="inland.total" /></th>
			<th><fmt:message key="inland.feeInvoiceNo" /></th>
			<th><fmt:message key="table.action" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${detail.exfeetables}" var="exfeetable" varStatus="idxx"> 
			<tr class="${exfeetable.isAdded == true ? 'hidden' :''}">
				<td colType="index" data-title="<fmt:message key="table.no"/>">${idxx.index+1}</td>
				<td colType="generalInfo" class="hidden"><form:hidden
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].id" /> <form:hidden
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].isAdded"
						valueType="added" /> <form:hidden
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].isDeleted"
						valueType="deleted" /></td>
					<td><spring:bind
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].masterFee.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:select
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].masterFee.id"
						id="masterFee${idx.index}" autofocus="true"
						cssClass="form-control masterFee"
						items="${selections['masterFees']}" /> <form:errors
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].masterFee.id"
						cssClass="help-block" />
					</div></td>
					<td data-title="<fmt:message key="inland.feeName"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].exfeetables[${idxx.index}].name.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}" addUrl="/admin/config/constant" type="19">
					</spring:bind> <form:select path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].name.id"
						id="name${idxx.index}" autofocus="true" cssClass="form-control"
						items="${selections['exhFeeNames']}" /> <form:errors
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].name.id"
						cssClass="help-block" />
					</div></td>
					<td data-title="<fmt:message key="inland.feevalue"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].exfeetables[${idxx.index}].amount">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:input path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].amount"
						id="amount${idxx.index}" maxlength="45" autofocus="true" 
						cssClass="form-control amount money" /> <form:errors
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].amount"
						cssClass="help-block" />
					</div></td>
				<td data-title="<fmt:message key="inland.feevavat"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].exfeetables[${idxx.index}].vat">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:input path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].vat"
						id="vat${idxx.index}" maxlength="45" autofocus="true" 
						cssClass="form-control vat money" /> <form:errors
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].vat"
						cssClass="help-block" />
					</div></td>
				<td data-title="<fmt:message key="inland.total"/>">
					<form:input path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].total"
						id="total${idxx.index}" maxlength="45" autofocus="true" 
						cssClass="form-control total readonly" disabled="true" readonly="true"/>
				</td>
				<td data-title="<fmt:message key="inland.feeInvoiceNo"/>"><spring:bind
						path="truckingservice.truckingdetails[${idx.index}].exfeetables[${idxx.index}].invoiceNo">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:input path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].invoiceNo"
						id="vat${idxx.index}" maxlength="45" autofocus="true" 
						cssClass="form-control" /> <form:errors
						path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].invoiceNo"
						cssClass="help-block" />
					</div></td>
				<td rowType="actions"><span class="iconButton removeRow" data-title="<fmt:message key="table.action"/>"
					title="<fmt:message key='table.buttonEditTitle'/>"> <i
						class="fa fa-trash"></i>
				</span></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="row">
	<div class="col-md-10"></div>
	<div class="col-md-2">
		<span class="btn btn-primary" target-table="childTable${idx.index}"> <i
			class="icon-ok"></i> +</span>
	</div>
</div>

	</div>
    <div role="tabpanel" class="tab-pane" id="profile${idx.index}">
    	<table id="childTable${idx.index}Summary" class="display table table-striped ">
			<thead>
				<tr>
					<th><fmt:message key="table.no" /></th>
					<th><fmt:message key="inland.masterFee" /></th>
					<th><fmt:message key="inland.total" /></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
  </div>
</div>		


				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${truckingservice.docsgeneral.typeOfContainer.id == -2}">
<div class="row">
	<div class="col-md-10">
	<span class="btn btn-primary" target-table="detailsList"> <i
			class="icon-ok"></i> <fmt:message key="button.add" /></span>
	</div>
	<div class="col-md-2">
		
	</div>
</div>
</c:if>

<div id="productTypeModal" class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><fmt:message key="productInfo.addNew"/></h4>
      </div>
      <div class="modal-body">
       <form id="addNewProductForm">
		  <div class="form-group">
		    <label for="typeModal"><fmt:message key="saleInfo.typeOfProduct"/></label>
		    <input type="text" name="unit.value" class="form-control" id="typeModal" placeholder="<fmt:message key="saleInfo.typeOfProduct"/>" readonly="true">
		    <input type="text" name="unit.id" class="form-control" style="display:none;">
		  </div>
		  <div class="form-group">
		    <label for="codeModal"><fmt:message key="saleInfo.productCode"/></label>
		    <input type="text" name="code" class="form-control" id="codeModal" placeholder="<fmt:message key="saleInfo.productCode"/>">
		  </div>
		  <div class="form-group">
		    <label for="nameModal"><fmt:message key="saleInfo.productName"/></label>
		    <input type="text" name="name" class="form-control" id="nameModal" placeholder="<fmt:message key="saleInfo.productName"/>">
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="addNewProduct">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>
$(document).ready(function(){
	var addEventForContseal = function(row){
		var selectOwner = $(row).find(".selectOwner").first(),
		selectClient = $(row).find(".selectClient").first();
		$(selectClient).val($(selectOwner).val());
		$(selectOwner).on("change",function(){
			$(selectClient).val($(this).val());
		});
	};
	
	$("tr").each(function(){
		addEventForContseal($(this));
	});
	
	$("#detailsList").bind("afterAddRow",function(e,row){
		addEventForContseal(row);
	});
});
</script>

<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>
