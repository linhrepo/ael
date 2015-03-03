<%@ include file="/common/taglibs.jsp"%>
<h3>
	<fmt:message key="inland.feeTables" />
</h3>
<hr>
<div role="tabpanel">
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">
    		<fmt:message key="exhibition.feeTablesDetail" />
		</a>
	</li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">
    	<fmt:message key="exhibition.feeTablesSummary" />
    </a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
    	<table id="exfeetableList" 
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
			<th><fmt:message key="table.action" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${inland.docsgeneral.exfeetables}"
			var="exfeetable" varStatus="idx">
			<tr class="${exfeetable.isAdded == true ? 'hidden' :''}">
				<td colType="index">${idx.index+1}</td>
				<td colType="generalInfo" class="hidden"><form:hidden
						path="docsgeneral.exfeetables[${idx.index}].id" /> <form:hidden
						path="docsgeneral.exfeetables[${idx.index}].isAdded"
						valueType="added" /> <form:hidden
						path="docsgeneral.exfeetables[${idx.index}].isDeleted"
						valueType="deleted" /></td>
				<td><spring:bind
						path="inland.docsgeneral.exfeetables[${idx.index}].masterFee.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:select
						path="docsgeneral.exfeetables[${idx.index}].masterFee.id"
						id="masterFee${idx.index}" autofocus="true"
						cssClass="form-control masterFee"
						items="${docsSelection.selections['masterFees']}" /> <form:errors
						path="docsgeneral.exfeetables[${idx.index}].masterFee.id"
						cssClass="help-block" />
					</div></td>
				<td><spring:bind
						path="inland.docsgeneral.exfeetables[${idx.index}].name.id">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:select path="docsgeneral.exfeetables[${idx.index}].name.id"
						id="name${idx.index}" autofocus="true" cssClass="form-control"
						items="${docsSelection.selections['exhFeeNames']}" /> <form:errors
						path="docsgeneral.exfeetables[${idx.index}].name.id"
						cssClass="help-block" />
					</div></td>
				<td><spring:bind
						path="inland.docsgeneral.exfeetables[${idx.index}].amount">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:input path="docsgeneral.exfeetables[${idx.index}].amount"
						id="amount${idx.index}" maxlength="45" autofocus="true"
						cssClass="form-control amount money" /> <form:errors
						path="docsgeneral.exfeetables[${idx.index}].amount"
						cssClass="help-block" />
					</div></td>
				<td><spring:bind
						path="inland.docsgeneral.exfeetables[${idx.index}].vat">
						<div
							class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
					</spring:bind> <form:input path="docsgeneral.exfeetables[${idx.index}].vat"
						id="vat${idx.index}" maxlength="45" autofocus="true" 
						cssClass="form-control vat money" /> <form:errors
						path="docsgeneral.exfeetables[${idx.index}].vat"
						cssClass="help-block" />
					</div></td>
				<td>
					<form:input path="docsgeneral.exfeetables[${idx.index}].total"
						id="total${idx.index}" maxlength="45" autofocus="true"
						cssClass="form-control total readonly" disabled="true" readonly="true"/> 
				</td>
				<td rowType="actions"><span class="iconButton removeRow"
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
		<span class="btn btn-primary" target-table="exfeetableList"> <i
			class="icon-ok"></i> <fmt:message key="button.add" /></span>
	</div>
</div>

	</div>
    <div role="tabpanel" class="tab-pane" id="profile">
    	<table id="exfeetableListSummary" class="display table table-striped ">
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

<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>
