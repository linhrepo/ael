<%@ include file="/common/taglibs.jsp"%>
<hr>
<table id="detailsList"
	class="display table table-condensed" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th rowspan="2"><fmt:message key="table.no" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.jobNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.dateDev"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.khonhan"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.khogiao"/></th>
			<th colspan="3" class="centerText"><fmt:message key="accountingtrans.typeOfContainer"/></th>
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
			<th class="numberCol"><fmt:message key="accountingtrans.LCL"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${accountingTrans.docs}" var="doc" varStatus="idx">
			<tr>
				<td colType="index">${idx.index+1}</td>
				<td class="hidden">
					<form:hidden path="docs[${idx.index}].id"/>
					<form:hidden path="docs[${idx.index}].inland.id"/>
				</td>
				<td>
					<div class="form-group">
				        <input value="${doc.jobNo}"  maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				    </div>
				</td>
				<td>
					<div class="form-group">
				        <input value="${doc.inland.dateDevPack}" data-provide="datepicker" maxlength="45" autofocus="true" class="form-control"/>
				    </div>
				</td>
				<td>
					<div class="form-group">
				        <input value="${doc.placeRev}"  maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				        <c:if test="${not empty doc.placeRev1}">
				        	<input value="${doc.placeRev1}"  maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				        </c:if>
				    </div>
				</td>
				<td>
					<div class="form-group">
				        <input value="${doc.placeDelivery}"  maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				        <c:if test="${not empty doc.placeDelivery1}">
				        	<input value="${doc.placeDelivery1}"  maxlength="45" autofocus="true" class="form-control" readonly="true"/>
				        </c:if>
				    </div>
				</td>
				<td>
					<div class="form-group">
						<form:input path="docs[${idx.index}].noOf20Cont" readonly="true" autofocus="true" cssClass="form-control"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<form:input path="docs[${idx.index}].noOf40Cont" readonly="true" autofocus="true" cssClass="form-control"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input readonly="readonly" class="form-control" value="${doc.isLCL ? 'x' : ''}">
					</div>
					<form:hidden path="docs[${idx.index}].isLCL"/>
				</td>
				<td>
					<div class="form-group">
						<c:forEach items="${doc.truckingservice.truckingdetails}" var="trucking">
							<input value="${trucking.vehicleNo}" maxlength="45" autofocus="true" class="form-control" readonly="readonly"/>
						</c:forEach>
					</div>
				</td>
				<td>
					<div class="form-group">
						<c:forEach items="${doc.truckingservice.truckingdetails}" var="trucking">
							<input value="${trucking.consteal.noOfCont}" maxlength="45" autofocus="true" class="form-control" readonly="readonly"/>
						</c:forEach>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input value="${doc.cmbText}" maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input value="${doc.weigthText}" maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input value="${doc.placeEmptyUp}" maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input value="${doc.placeEmptyDown}" maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<form:input  path="docs[${idx.index}].chiho" maxlength="45" autofocus="true" cssClass="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
				<td>
					<div class="form-group">
						<input maxlength="45" autofocus="true" class="form-control" readonly="true"/>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
