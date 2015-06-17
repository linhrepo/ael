<%@ include file="/common/taglibs.jsp"%>
<hr>
<table id="detailsList"
	class="display table table-condensed no-more-tables feeTable fullBorder" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th rowspan="2"><fmt:message key="table.no" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.jobNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.khonhan"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.khogiao"/></th>
			<th rowspan="2"><fmt:message key="accountingtrans.dateDev"/></th>
<%-- 			<th colspan="4" class="centerText"><fmt:message key="accountingtrans.typeOfContainer"/></th> --%>
			<th rowspan="2"><fmt:message key="accountingtrans.vehicleNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.contNo" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.placegetcont" /></th>
			<th rowspan="2"><fmt:message key="accountingtrans.placeputcont" /></th>
<%-- 			<th rowspan="2"><fmt:message key="accountingtrans.volumn" /></th> --%>
<%-- 			<th rowspan="2"><fmt:message key="accountingtrans.kg" /></th> --%>
<%-- 			<th rowspan="2"><fmt:message key="accountingtrans.chiho" /></th> --%>
			<th rowspan="2"><fmt:message key="accountingcus.unitPrice" /></th>
<%-- 			<th rowspan="2"><fmt:message key="accountingcus.total" /></th> --%>
			<th rowspan="2"><fmt:message key="accountingcus.vat" /></th>
			<th rowspan="2"><fmt:message key="accountingcus.feevat" /></th>
			<th rowspan="2"><fmt:message key="accountingcus.feewithvat" /></th>
			<th rowspan="2"><fmt:message key="accountingcus.note" /></th>
		</tr>
<!-- 		<tr> -->
<%-- 			<th class="numberCol"><fmt:message key="accountingtrans.20"/></th> --%>
<%-- 			<th class="numberCol"><fmt:message key="accountingtrans.40"/></th> --%>
<%-- 			<th class="numberCol"><fmt:message key="accountingcus.otCont"/></th> --%>
<%-- 			<th class="numberCol"><fmt:message key="accountingtrans.LCL"/></th> --%>
<!-- 		</tr> -->
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
		  			<form:hidden path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.id"/>
		  			<form:hidden path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.truckingdetail.id"/>
		  		</td>
		  		<c:if test="${indx.index == 0 }">
		  			<td rowspan="${fn:length(doc.truckingservice.truckingdetails)}" colType="index" data-title="<fmt:message key="table.no" />">${idx.index+1}</td>
				<td data-title="<fmt:message key="accountingtrans.jobNo" />" rowspan="${fn:length(doc.truckingservice.truckingdetails)}">
					<div class="form-group">
				        ${doc.jobNo}
				    </div>
				</td>
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
		  		</c:if>
		  		
		  		<td data-title="<fmt:message key="accountingtrans.dateDev" />">
					<div class="form-group">
						<fmt:formatDate value="${detail.dateDev}" pattern="dd/MM/yyyy"/>
				    </div>
				</td>
				
<%-- 				<c:if test="${indx.index == 0 }"> --%>
				
<%-- 				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.20"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						${doc.noOf20Cont} --%>
<!-- 					</div> -->
<%-- 					<form:hidden path="docs[${idx.index}].noOf20Cont"/> --%>
<!-- 				</td> -->
<%-- 				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.40"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						${doc.noOf40Cont} --%>
<!-- 					</div> -->
<%-- 					<form:hidden path="docs[${idx.index}].noOf40Cont"/> --%>
<!-- 				</td> -->
<%-- 				<td data-title="<fmt:message key="accountingcus.typeOfContainer"/>:<fmt:message key="accountingcus.otCont" />" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						${doc.otCont} --%>
<!-- 					</div> -->
<%-- 					<form:hidden path="docs[${idx.index}].noOf40Cont"/> --%>
<!-- 				</td> -->
<%-- 				<td data-title="<fmt:message key="accountingtrans.typeOfContainer"/>:<fmt:message key="accountingtrans.LCL"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						${doc.isLCL ? 'x' : ''} --%>
<!-- 					</div> -->
<%-- 					<form:hidden path="docs[${idx.index}].isLCL"/> --%>
<!-- 				</td> -->
<%-- 				</c:if> --%>
				
				<td data-title="<fmt:message key="accountingtrans.vehicleNo"/>">
					<div class="form-group">
							${detail.vehicleNo}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.contNo"/>" noOfCont="${detail.consteal.noOfCont}" count="${indx.index}">
					<div class="form-group">
							${detail.consteal.noOfCont}
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.placegetcont"/>">
						<div class="form-group">
							${detail.placeGetCont}
						</div>
					</td>
					<td data-title="<fmt:message key="accountingtrans.placeputcont"/>">
						<div class="form-group">
							${detail.placePutCont}
						</div>
					</td>
				
<%-- 				<c:if test="${indx.index == 0 }"> --%>
<%-- 					<td data-title="<fmt:message key="accountingtrans.volumn"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						${doc.cmbText} --%>
<!-- 					</div> -->
<!-- 					</td> -->
<%-- 					<td data-title="<fmt:message key="accountingtrans.kg"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 						<div class="form-group"> -->
<%-- 							${doc.weigthText} --%>
<!-- 						</div> -->
<!-- 					</td> -->
<%-- 					<td data-title="<fmt:message key="accountingtrans.chiho"/>" rowspan="${fn:length(doc.truckingservice.truckingdetails)}"> --%>
<!-- 					<div class="form-group money"> -->
<%-- 						${doc.chiho} --%>
<!-- 					</div> -->
<!-- 					</td> -->
<%-- 				</c:if> --%>
				<td data-title="<fmt:message key="accountingtrans.price"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.priceUnit" cssClass="form-control money amount" />
					</div>
				</td>
<%-- 				<td data-title="<fmt:message key="accountingtrans.otherfee"/>"> --%>
<!-- 					<div class="form-group"> -->
<%-- 						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.price" cssClass="form-control money amount" /> --%>
<!-- 					</div> -->
<!-- 				</td> -->
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.vat" cssClass="form-control money2 vat" />
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.vatValue" cssClass="form-control money vatAmount" readonly="true"/>
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.total" cssClass="form-control money total" readonly="true"/>
					</div>
				</td>
				<td data-title="<fmt:message key="accountingtrans.total"/>">
					<div class="form-group">
						<form:input path="docs[${idx.index}].truckingservice.truckingdetails[${indx.index}].transreportext.note" cssClass="form-control" maxlength="250"/>
					</div>
				</td>
		  	</tr>
		  </c:forEach>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
$(document).ready(function(){
// 	$("[count]").each(function(){
// 		var noOfCont = $(this).attr("noOfCont");
// 		var listOrther = $("[noOfCont='"+noOfCont+"']");
// 		if ($(this).attr("remove") == undefined){
// 			$(this).attr("rowspan",listOrther.length);
// 		}
// 		if (listOrther.length >1){
// 			for (var i=0; i< listOrther.length;++i){
// 				if ( !$(this).is(listOrther[i]) && $(listOrther[i]).attr("rowspan")== undefined){
// 					$(listOrther[i]).attr("remove",true);
// 				}	
// 			}
// 		}
// 	});
// 	$("[remove]").remove();
});
</script>
<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>