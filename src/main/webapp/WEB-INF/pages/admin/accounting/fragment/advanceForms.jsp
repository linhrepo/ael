<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-12">
	<jsp:include page="searchAdvance.jsp"></jsp:include>
	 <%-- <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="fa fa-sign-out"></i> <fmt:message key="button.done"/></a>
            <hr> --%>
    <!-- <div id="actions" class="btn-group"></div> -->
   	   <a class="btn btn-primary" href="<c:url value='/users/advanceForm?isAdmin=1'/>">
           <i class="fa fa-plus"></i><fmt:message key="button.addAdvanceAdmin"/></a>
       <!-- <span></span> -->
       <a class="btn btn-success btn-download" onclick="downloadPhieuchi()">
         	<i class="fa fa-print"></i> <fmt:message key="advanceform.printPayment"/>
       </a>
       <br><br>
       <%-- <span>*<fmt:message key="advanceform.print.notification"/></span> --%>
    
<table id="advanceformListAdmin" class="display nestedDatatable" cellspacing="0" width="100%" 
    	childDetailURL="/admin/accounting/advDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="advanceform.description"/>,
    	<fmt:message key="advanceform.amount"/>,<fmt:message key="accounting.approval" />,
    	<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="description,amount[money],approvedText[Key]"
    	editDetail="/admin/accounting/changeAdvanceApproval"
    	actionCheck = "checkByAdmin:false,null"
    	actionConfirm = "true"    	
    	successLoadMessage="accounting.fees.detailLoaded"
    	>
        <thead>
            <tr>
<%--             	<th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.refcode"/></th>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                 <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="advanceform.print"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
<%--                 <th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.refcode"/></th>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                 <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="advanceform.print"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${advanceformList}" var="adv" varStatus="idx">
        	<c:if test="${not empty adv.advancedetails and adv.isAdmin}">
	        	<tr class="${adv.doApproval ? '':'impress' }" params="id=${adv.id}">
<%-- 	                <td>${idx.index+1}</td> --%>
					<td>${adv.refCode}</td>
					<td>
	                	${adv.employee.firstName}&nbsp;${adv.employee.lastName}
	                </td>
	                <td>
	                	<fmt:formatDate value="${adv.date}" pattern="dd-MM-yyyy"/>
	                </td>
	                 <td class="money">
	                 	<fmt:formatNumber pattern="#,###" value="${adv.total}"></fmt:formatNumber>
	                </td>
	                <td>
              		<c:if test="${adv.isAdmin}">
              			<fmt:message key="refund.adminFee"/>
              		</c:if>
<%--               		<c:if test="${!adv.isAdmin}"> --%>
<%--               			<fmt:message key="refund.manifestFee"/> --%>
<%--               		</c:if> --%>
              	</td>
	              	<td class="icon-status">
	              		<c:if test="${adv.doApproval}">
	              			<a><i class="fa fa-check"></i></a>
	              		</c:if>
	              		<%-- <c:if test="${adv.doApproval == false}">
	              			<a><i class="fa fa-clock-o"></i></a>
	              		</c:if> --%>
	              	</td>
	              	<td class="icon-status">
	              		<c:if test="${adv.doPrint}">
	              			${adv.moneyBook.voucherNo}
	              		</c:if>
	              	</td>
	                <td>
	                	<a href="${ctx}/users/advanceForm?id=${adv.id}" class="iconButton" 
	                	title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa fa-arrow-right"></i></a>
	                </td>
	            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    	<a class="btn btn-primary" href="<c:url value='/users/advanceForm?isAdmin=0'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addAdvanceJob"/></a>
   		<a class="btn btn-success btn-download" onclick="downloadPhieuchi()">
         	<i class="fa fa-print"></i> <fmt:message key="advanceform.printPayment"/>
      	</a>
    <br><br>
            
    <table id="advanceformListJob" class="display nestedDatatable" cellspacing="0" width="100%" 
    	childDetailURL="/admin/accounting/advDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="advanceform.fileCus"/>,
    	<fmt:message key="advanceform.goodDes"/>,<fmt:message key="advanceform.description"/>,
    	<fmt:message key="advanceform.amount"/>,<fmt:message key="accounting.approval" />,
    	<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="jobNo,goodDes,description,amount[money],approvedText[Key]"
    	editDetail="/admin/accounting/changeAdvanceApproval"
    	actionCheck = "checkByAdmin:false,null"
    	actionConfirm = "true"    	
    	successLoadMessage="accounting.fees.detailLoaded"
    	>
        <thead>
            <tr>
<%--             	<th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.refcode"/></th>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="advanceform.print"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
<%--                 <th><fmt:message key="table.no"/></th> --%>
				<th><fmt:message key="advanceform.refcode"/></th>
				<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="advanceform.date"/></th>
                <th><fmt:message key="advanceform.total"/></th>
                <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.approval"/></th>
                <th><fmt:message key="advanceform.print"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${advanceformList}" var="adv" varStatus="idx">
        	<c:if test="${not empty adv.advancedetails and !adv.isAdmin}">
	        	<tr class="${adv.doApproval ? '':'impress' }" params="id=${adv.id}">
<%-- 	                <td>${idx.index+1}</td> --%>
					<td>${adv.refCode}</td>
					<td>
	                	${adv.employee.firstName}&nbsp;${adv.employee.lastName}
	                </td>
	                <td>
	                	<fmt:formatDate value="${adv.date}" pattern="dd-MM-yyyy"/>
	                </td>
	                 <td class="money" >
	                 	<fmt:formatNumber pattern="#,###" value="${adv.total}"></fmt:formatNumber>
	                </td>
	                <td>
<%--               		<c:if test="${adv.isAdmin}"> --%>
<%--               			<fmt:message key="refund.adminFee"/> --%>
<%--               		</c:if> --%>
              		<c:if test="${!adv.isAdmin}">
              			<fmt:message key="refund.manifestFee"/>
              		</c:if>
              	</td>
	              	<td class="icon-status">
	              		<c:if test="${adv.doApproval}">
	              			<a><i class="fa fa-check"></i></a>
	              		</c:if>
	              		<%-- <c:if test="${adv.doApproval == false}">
	              			<a><i class="fa fa-clock-o"></i></a>
	              		</c:if> --%>
	              	</td>
	              	<td class="icon-status">
	              		<c:if test="${adv.doPrint}">
	              			${adv.moneyBook.voucherNo}
	              		</c:if>
	              	</td>
	                <td>
	                	<a href="${ctx}/users/advanceForm?id=${adv.id}" class="iconButton" 
	                	title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-arrow-right"></i></a>
	                </td>
	            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>

<style>
	.highlight {
		pointer: cursor;
		background : #38b44a !important;
	}
	.disabled {
	    background: #dddddd;
	}
</style>
<script>
var printedIds = [];
var printedGroupString = "";
var currentRow = [];

$(document).ready(function() {
	onOffButton();
	$( "table" ).on( "click", "tr td:not(:first-child,:last-child)", function() {
		var tr =  $(this).closest("tr");
		currentRow.push(tr);
		groupString = tr.find("td").eq(2).text() + " " + tr.find("td").eq(3).text(); 
		var same = true;
		if (printedGroupString != "" && groupString.localeCompare(printedGroupString) != 0) {
			same = false;
		}
		var id = tr.attr("params");
		var printed = tr.find("td").eq(7).html().trim().length > 0;
		/* var a = (id != null);
		var b = (!tr.hasClass("impress"));
		var c = (same);
		var d = (!printed); 
		alert(a +" " + b + " " + c + " " + d); */
		
		if (id != null && !tr.hasClass("impress") && same && !printed) {
			idNum = id.substring(3);
			printedIds.push(idNum);
			
			tr.find("td:not(:first-child,:last-child)").each(function() {
				if($(this).hasClass("highlight")) {
					$(this).removeClass("highlight");
					
					var index = printedIds.indexOf(idNum);
					if (index > -1) {
						printedIds.splice(index, 1);
						currentRow.splice(index, 1);
					}
					
					if (printedIds.length == 0) {
						printedGroupString = "";
						currentRow = [];
					}
				} else {
					$(this).addClass("highlight");
					printedGroupString = groupString;
				}
			});
		} 
		onOffButton();
	})
});

function onOffButton() {
	if (printedGroupString.length > 0) {
		$('.btn-download').removeClass('disabled');
	} else {
		$('.btn-download').addClass('disabled');
	}
}

function downloadPhieuchi() {
	if (printedIds.length > 0) {
		var ids = printedIds[0];
		for(var i = 1; i < printedIds.length; i ++) {
			ids += "," + printedIds[i];
		}
		//window.location.href="../../users/advanceForm/phieuchi/download?id=" + ids;
		
		$.ajax({
		    type: "POST",
		    url: "../../users/advanceForm/phieuchi/print",
		    data: {"id": ids},
		    success: function(msg){
		    	for (var i = 0; i < currentRow.length; i++) {
		    		currentRow[i].find("td").eq(7).html(msg);
			    	currentRow[i].find("td").removeClass("highlight");
		    	}
		    	printedGroupString = "";
		    	printedIds = [];
		    	window.location.href="../../users/advanceForm/phieuchi/download?id=" + ids;
		    	onOffButton();
		    }
		}); 

	}
	
}
</script>