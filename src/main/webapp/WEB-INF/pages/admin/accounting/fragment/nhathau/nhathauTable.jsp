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
			<th rowspan="2"><fmt:message key="accountingtrans.otherfee" /></th>
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
					<%-- <form:hidden path="docs[${idx.index}].inland.id"/> --%>
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
						<%-- <c:forEach items="${truck.truckingservice.truckingdetails}" var="trucking"> --%>
							${truck.consteal.noOfCont}
							<%-- <hr>
						</c:forEach> --%>
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
				<td data-title="<fmt:message key="accountingtrans.otherfee"/>">
					<div class="form-group">
						<form:input path="truckingdetails[${idx.index}].truckingservice.docsgeneral.inland.otherFees"  maxlength="45" autofocus="true" cssClass="form-control money amount" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.tiennang" />">
					<div class="form-group">
				        So tien nang
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.tienha" />">
					<div class="form-group">
				        So tien ha
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control total" readonly="true"/>
					</div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.hdnang" />">
					<div class="form-group">
				        HD nang
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingnhathau.hdha" />">
					<div class="form-group">
				        HD ha
				    </div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>