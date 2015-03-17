<%@ include file="/common/taglibs.jsp"%>
<hr>
<table id="detailsList"
	class="display table table-condensed no-more-tables feeTable" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th rowspan="2"><fmt:message key="table.no" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.jobNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.dateDev"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.khonhan"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.khogiao"/></th>
			<th colspan="4" class="centerText"><fmt:message key="accountingtrans.typeOfContainer"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.vehicleNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.contNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.volumn" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.kg" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.placegetcont" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.placeputcont" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.chiho" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.price" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.otherfee" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.total" /></th>
		</tr>
		<tr>
			<th class="numberCol"><fmt:message key="accountingtrans.20"/></th>
			<th class="numberCol"><fmt:message key="accountingtrans.40"/></th>
			<th class="numberCol"><fmt:message key="accountingcus.otCont"/></th>
			<th class="numberCol"><fmt:message key="accountingtrans.LCL"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${accountingTrans.docs}" var="doc" varStatus="idx">
			<tr>
				<td colType="index" data-title="<fmt:message key="table.no" />">${idx.index+1}</td>
				<td class="hidden">
					<form:hidden path="docs[${idx.index}].id"/>
					<form:hidden path="docs[${idx.index}].inland.id"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.jobNo" />">
					<div class="form-group">
				        ${doc.jobNo}
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.dateDev" />">
					<div class="form-group">
					<fmt:formatDate value="${doc.inland.dateDevPack}" pattern="dd/MM/yyyy"/>
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.khonhan" />">
					<div class="form-group">
				        ${doc.placeRev}
				        <hr>
				        <c:if test="${not empty doc.placeRev1}">
				        	${doc.placeRev1}
				        </c:if>
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.khogiao" />">
					<div class="form-group">
				        ${doc.placeDelivery}
				        <hr>
				        <c:if test="${not empty doc.placeDelivery1}">
				        	${doc.placeDelivery1}
				        </c:if>
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.20"/>">
					<div class="form-group">
						${doc.noOf20Cont}
					</div>
					<form:hidden path="docs[${idx.index}].noOf20Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.40"/>">
					<div class="form-group">
						${doc.noOf40Cont}
					</div>
					<form:hidden path="docs[${idx.index}].noOf40Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingcus.typeOfContainer"/>:<fmt:message key="accountingcus.otCont" />">
					<div class="form-group">
						${doc.otCont}
					</div>
					<form:hidden path="docs[${idx.index}].noOf40Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.LCL"/>">
					<div class="form-group">
						${doc.isLCL ? 'x' : ''}
					</div>
					<form:hidden path="docs[${idx.index}].isLCL"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.vehicleNo"/>">
					<div class="form-group">
						<c:forEach items="${doc.truckingservice.truckingdetails}" var="trucking">
							${trucking.vehicleNo}
							<hr>
						</c:forEach>
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.contNo"/>">
					<div class="form-group">
						<c:forEach items="${doc.truckingservice.truckingdetails}" var="trucking">
							${trucking.consteal.noOfCont}
							<hr>
						</c:forEach>
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.volumn"/>">
					<div class="form-group">
						${doc.cmbText}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.kg"/>">
					<div class="form-group">
						${doc.weigthText}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.placegetcont"/>">
					<div class="form-group">
						${doc.placeEmptyUp}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.placeputcont"/>">
					<div class="form-group">
						${doc.placeEmptyDown}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.chiho"/>">
					<div class="form-group money">
						${doc.chiho}
					</div>
					<form:hidden path="docs[${idx.index}].chiho"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.price"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].inland.accountingPrice"  maxlength="45" autofocus="true" cssClass="form-control money amount" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.otherfee"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].inland.otherFees"  maxlength="45" autofocus="true" cssClass="form-control money amount" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control total" readonly="true"/>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>