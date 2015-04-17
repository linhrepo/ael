<%@ include file="/common/taglibs.jsp"%>
<hr>
<table id="detailsList"
	class="display table table-condensed no-more-tables feeTable fullBorder" cellspacing="0"
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
		<tr class="hidden">
			<td>
					<form:hidden path="docs[${idx.index}].id"/>
					<form:hidden path="docs[${idx.index}].inland.id"/>
					<form:hidden path="docs[${idx.index}].truckingservice.id"/>
			</td>
		</tr>
		  <c:forEach items="${doc.truckingservice.truckingdetails}" var="detail" varStatus="indx">
		  	<tr>
		  		<td class="hidden">
		  			<form:hidden path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].id"/>
		  		</td>
		  		<c:if test="${indx.index == 0 }">
		  			<td rowspan="${fn:length(doc.truckingservice.truckingdetails)}" colType="index" data-title="<fmt:message key="table.no" />">${idx.index+1}</td>
				<td data-title="<fmt:message key="accountingtrans.jobNo" />" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
				        ${doc.jobNo}
				    </div>
				</td>
		  		</c:if>
		  		
		  		<td data-title="<fmt:message key="accountingtrans.dateDev" />">
					<div class="form-group">
						<fmt:formatDate value="${detail.dateDev}" pattern="dd/MM/yyyy"/>
				    </div>
				</td>
				
				<c:if test="${indx.index == 0 }">
					<td data-title="<fmt:message key="accountingtrans.khonhan" />" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
				        ${doc.placeRev}
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.khogiao" />" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
				        ${doc.placeDelivery}
				    </div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.20"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
						${doc.noOf20Cont}
					</div>
					<form:hidden path="docs[${idx.index}].noOf20Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.40"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
						${doc.noOf40Cont}
					</div>
					<form:hidden path="docs[${idx.index}].noOf40Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingcus.typeOfContainer"/>:<fmt:message key="accountingcus.otCont" />" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
						${doc.otCont}
					</div>
					<form:hidden path="docs[${idx.index}].noOf40Cont"/>
				</td>
				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.LCL"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
						${doc.isLCL ? 'x' : ''}
					</div>
					<form:hidden path="docs[${idx.index}].isLCL"/>
				</td>
				</c:if>
				
				<td data-title="<fmt:message key="accountingtrans.vehicleNo"/>">
					<div class="form-group">
							${detail.vehicleNo}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.contNo"/>">
					<div class="form-group">
							${detail.consteal.noOfCont}
					</div>
				</td>
				
				<c:if test="${indx.index == 0 }">
					<td data-title="<fmt:message key="accountingtrans.volumn"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
						${doc.cmbText}
					</div>
					</td>
					<td data-title="<fmt:message key="accountingtrans.kg"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
						<div class="form-group">
							${doc.weigthText}
						</div>
					</td>
					<td data-title="<fmt:message key="accountingtrans.placegetcont"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
						<div class="form-group">
							${doc.truckingservice.arrival}
						</div>
					</td>
					<td data-title="<fmt:message key="accountingtrans.placeputcont"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
						<div class="form-group">
							${doc.truckingservice.departure}
						</div>
					</td>
				</c:if>
				
				<td data-title="<fmt:message key="accountingtrans.chiho"/>">
					<div class="form-group money">
						${detail.chiho}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.price"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].accountingPrice" cssClass="form-control money amount" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.otherfee"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].otherFees" cssClass="form-control money amount" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<input  class="form-control total" readonly="true"/>
					</div>
				</td>
		  	</tr>
		  </c:forEach>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>