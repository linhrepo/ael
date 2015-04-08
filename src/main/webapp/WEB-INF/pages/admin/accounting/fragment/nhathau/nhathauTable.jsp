<%@ include file="/common/taglibs.jsp"%>
<hr>
<table id="detailsList"
	class="display table table-condensed no-more-tables feeTable" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th rowspan="2"><fmt:message key="table.no" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.dateDev"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.jobNo" /></th>
			<th rowspan="2"><fmt:message key="customer.title" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.contNo" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.hangnhapxuat"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.placegetcont" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.placeputcont" /></th>
			<th colspan="2" class="centerText"><fmt:message key="accountingtrans.typeOfContainer"/></th>			
			<th rowspan="2"><fmt:message key="accountingnhathau.noidi" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.noiden" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.price" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.phuthu" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.tiennang" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.tienha" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.total" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.hdnang" /></th>
			<th rowspan="2"><fmt:message key="accountingnhathau.hdha" /></th>
		</tr>
		<tr>
			<th class="numberCol"><fmt:message key="accountingtrans.20"/></th>
			<th class="numberCol"><fmt:message key="accountingtrans.40"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${accountingNhathau.truckingdetails}" var="truck" varStatus="idx">
			<tr>
				<td colType="index" data-title="<fmt:message key="table.no" />">${idx.index+1}</td>
				<td class="hidden">
					<form:hidden path="truckingdetails[${idx.index}].id"/>
					<form:hidden path="truckingdetails[${idx.index}].truckingservice.id"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.dateDev" />">
					<div class="form-group">
					<fmt:formatDate value="${truck.truckingservice.docsgeneral.docReceiveDate}" pattern="dd/MM/yyyy"/>
				    </div>
				</td>
				 <td data-title="<fmt:message key="accountingtrans.jobNo" />">
					<div class="form-group">
				        ${truck.truckingservice.docsgeneral.jobNo}
				    </div>
				</td>
				<td data-title="<fmt:message key="customer.title" />">
					<div class="form-group">
				        ${truck.truckingservice.docsgeneral.customer.name }
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.contNo"/>">
					<div class="form-group">
							${truck.consteal.noOfCont}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.hangnhapxuat" />">
					<div class="form-group">
				        ${truck.truckingservice.docsgeneral.typeOfImport.value }
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.placegetcont"/>">
					<div class="form-group">
						${truck.truckingservice.docsgeneral.placeEmptyUp}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.placeputcont"/>">
					<div class="form-group">
						${truck.truckingservice.docsgeneral.placeEmptyDown}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.20"/>">
					<div class="form-group">
						${truck.truckingservice.docsgeneral.noOf20Cont}
					</div>
					<form:hidden path="truckingdetails[${idx.index}].truckingservice.docsgeneral.noOf20Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.40"/>">
					<div class="form-group">
						${truck.truckingservice.docsgeneral.noOf40Cont}
					</div>
					<form:hidden path="truckingdetails[${idx.index}].truckingservice.docsgeneral.noOf40Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.noidi" />">
					<div class="form-group">
				        ${truck.truckingservice.docsgeneral.truckingservice.departure }
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.noiden" />">
					<div class="form-group">
				        ${truck.truckingservice.docsgeneral.truckingservice.arrival }
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.price"/>">
					<div class="form-group">
						<form:input path="truckingdetails[${idx.index}].truckingservice.docsgeneral.inland.accountingPrice"  maxlength="45" autofocus="true" cssClass="form-control money amount" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.phuthu"/>">
					<div class="form-group">
						<form:input path="truckingdetails[${idx.index}].phuthu"  maxlength="45" autofocus="true" cssClass="form-control money" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.tiennang" />">
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.tienha" />">
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control total" readonly="true"/>
					</div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.hdnang" />">
					<div class="form-group">
						<form:input path="truckingdetails[${idx.index}].noContractUp"  maxlength="45" autofocus="true" cssClass="form-control number" />
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.hdha" />">
					<div class="form-group">
						<form:input path="truckingdetails[${idx.index}].noContractDown"  maxlength="45" autofocus="true" cssClass="form-control number" />
				    </div>
				</td>
			</tr>
			<tr colType="truck" class="${truck.isAdded == true ? 'hidden' :''}">
				<td>
					<button class="btn btn-default" type="button"
						data-toggle="collapse" data-target="#child${idx.index}"
						aria-expanded="false" aria-controls="collapseExample">
						<fmt:message key="trucking.tooglefeeTables" />
					</button>
				</td>
				<td colspan="15">
					<!-- 				DETAIL -->
					<div class="in" id="child${idx.index}">

						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a
								href="#home${idx.index}" aria-controls="home" role="tab"
								data-toggle="tab"> <fmt:message
										key="exhibition.feeTablesDetail" />
							</a></li>
							<li role="presentation"><a href="#profile${idx.index}"
								aria-controls="profile" role="tab" data-toggle="tab"> <fmt:message
										key="exhibition.feeTablesSummary" />
							</a></li>
						</ul>

						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active"
								id="home${idx.index}">
								<h3>
									<fmt:message key="exhibition.feeTables" />
								</h3>
								<table id="childTable${idx.index}"
									class="display table table-condensed inFormTable feeTable"
									cellspacing="0" width="100%">
									<thead>
										<tr>
											<th><fmt:message key="table.no" /></th>
											<th><fmt:message key="inland.masterFee" /></th>
											<th><fmt:message key="inland.feeName" /></th>
											<th><fmt:message key="inland.feevalue" /></th>
											<th><fmt:message key="inland.feevavat" /></th>
											<th><fmt:message key="inland.total" /></th>
											<th><fmt:message key="inland.feeInvoiceNo" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${truck.exfeetables}" var="exfeetable"
											varStatus="idxx">
											<tr class="${exfeetable.isAdded == true ? 'hidden' :''}">
												<td colType="index"
													data-title="<fmt:message key="table.no"/>">${idxx.index+1}</td>
												<td colType="generalInfo" class="hidden"><form:hidden
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].id" />
													<form:hidden
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
														items="${selections['masterFees']}" disabled="disabled"/> <form:errors
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].masterFee.id"
														cssClass="help-block" />
													</div></td>
												<td data-title="<fmt:message key="inland.feeName"/>"><spring:bind
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].name.id">
														<div
															class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}"
															addUrl="/admin/config/constant" type="19">
													</spring:bind> <form:select
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].name.id"
														id="name${idxx.index}" autofocus="true"
														cssClass="form-control"
														items="${selections['exhFeeNames']}" disabled="disabled"/> <form:errors
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].name.id"
														cssClass="help-block" />
													</div></td>
												<td data-title="<fmt:message key="inland.feevalue"/>"><spring:bind
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].amount">
														<div
															class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
													</spring:bind> <form:input
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].amount"
														id="amount${idxx.index}" maxlength="45" autofocus="true"
														cssClass="form-control amount money" readonly="true"/> <form:errors
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].amount"
														cssClass="help-block" />
													</div></td>
												<td data-title="<fmt:message key="inland.feevavat"/>"><spring:bind
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].vat">
														<div
															class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
													</spring:bind> <form:input
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].vat"
														id="vat${idxx.index}" maxlength="45" autofocus="true"
														cssClass="form-control vat money" readonly="true"/> <form:errors
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].vat"
														cssClass="help-block" />
													</div></td>
												<td data-title="<fmt:message key="inland.total"/>"><form:input
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].total"
														id="total${idxx.index}" maxlength="45" autofocus="true"
														cssClass="form-control total " readonly="true"/></td>
												<td data-title="<fmt:message key="inland.feeInvoiceNo"/>"><spring:bind
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].invoiceNo">
														<div
															class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
													</spring:bind> <form:input
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].invoiceNo"
														id="vat${idxx.index}" maxlength="45" autofocus="true"
														cssClass="form-control" readonly="true"/> <form:errors
														path="truckingdetails[${idx.index}].exfeetables[${idxx.index}].invoiceNo"
														cssClass="help-block" />
													</div></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane" id="profile${idx.index}">
								<table id="childTable${idx.index}Summary"
									class="display table table-striped ">
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
<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//feetable
		$("table.feeTable").each(function(){
			FEE_TABLE_CONTROL.init($(this).attr("id"));
		});
		$('select').attr("disabled", "disabled");
	});
</script>