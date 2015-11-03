<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-12">
	<jsp:include page="searchAdvance.jsp"></jsp:include>
	 <%-- <a class="btn btn-default" href="<c:url value='/home'/>">
            <i class="fa fa-sign-out"></i> <fmt:message key="button.done"/></a>
            <hr> --%>
    <div id="actions" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='/users/advanceForm?isAdmin=1'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addAdvanceAdmin"/></a>
        <span></span>
        <a class="btn btn-success" onclick="downloadPhieuchi()">
          	<i class="fa fa-print"></i> <fmt:message key="advanceform.printPayment"/>
       	</a>
        <span>*<fmt:message key="advanceform.print.notification"/></span>
    </div>
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
</style>
<script>
var printedIds = [];
var printedGroupString = "";

$(document).ready(function() {
	$("tr").click(function() {
		/* var groupString = $(this).find("td").eq(2).text() + " " + $(this).find("td").eq(3).text(); 
		var same = true;
		
		if (groupString.localCompare(printedGroupString) != 0) {
			same = false;
		} else {
			same = true;
			printedGroupString = groupString;
		}
		alert(printedGroupString); */
		
		if ($(this).find(".icon-status").size() > 0) {
			var id = $(this).attr("params").substring(3);
			printedIds.push(id);
			
			$(this).find("td").each(function() {
				if($(this).hasClass("highlight")) {
					$(this).removeClass("highlight");
				} else {
					$(this).addClass("highlight");
				}
			});
		}
	})
});

function downloadPhieuchi() {
	alert("download" + printedIds.length);
	
	var ids = "1,2,3";
	window.location.href="advanceForm/phieuchi/download?id=" + ids;
}
</script>