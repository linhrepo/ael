<%@ include file="/common/taglibs.jsp" %>
<br>
<div class="col-sm-10">
	<h2><fmt:message key='menu.acct.listphieuthu'/></h2>
	<jsp:include page="searchPhieuThu.jsp"></jsp:include>
	<br/>
	
    <div id="actions" class="btn-group">
    	<a class="btn btn-primary" href="<c:url value='phieuthu?isAdmin=1'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addPhieuthuAdmin"/></a>
    	<a class="btn btn-primary" href="<c:url value='phieuthu?isAdmin=0'/>">
            <i class="fa fa-plus"></i><fmt:message key="button.addPhieuthuJob"/></a>
        <a class="btn btn-success btn-download" onclick="downloadPhieuthu()">
            <i class="fa fa-print"></i> <fmt:message key="refund.printPayment"/></a>
    </div>
    <table id="phieuthuList" class="display datatable" cellspacing="0" width="100%" >
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
            	<th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="phieuthu.date"/></th>
                <th><fmt:message key="phieuthu.total"/></th>
                <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.print"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="advanceform.employee"/></th>
                <th><fmt:message key="phieuthu.date"/></th>
                <th><fmt:message key="phieuthu.total"/></th>
                <th><fmt:message key="refund.type"/></th>
                <th><fmt:message key="advanceform.print"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${refundList}" var="adv" varStatus="idx">
        	<tr>
                <td id='${adv.id}'>${idx.index+1}</td>
                <td>
                	${adv.employee.firstName}&nbsp;${adv.employee.lastName}
                </td>
                <td>
                	<fmt:formatDate value="${adv.date}" pattern="yyyy-MM-dd"/>
                </td>
                 <td class="money">
                 	<fmt:formatNumber>${adv.total}</fmt:formatNumber>
                </td>
                 <td>
              		<c:if test="${adv.isRAdmin}">
              			<fmt:message key="refund.adminFee"/>
              		</c:if>
              		<c:if test="${!adv.isRAdmin}">
              			<fmt:message key="refund.manifestFee"/>
              		</c:if>
              	</td>
              	<td>
              		<c:if test="${adv.doPrint}">
              			${adv.moneyBook.voucherNo}
              		</c:if>
              	</td>
                <td>
                	<a href="phieuthu?id=${adv.id}" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr>
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

var printedId = "";
var currentRow = null;

$(document).ready(function() {
	onOffButton();
	$( "table" ).on( "click", "tr td:not(:first-child,:last-child)", function() {
		var tr =  $(this).closest("tr");
		currentRow = tr;
		var id = tr.find("td").first().attr("id");
		var printed = tr.find("td").eq(5).html().trim().length > 0;
		var same = true;
		if (printedId != "" && id != printedId) {
			same = false;
		}

		if (id != null && !printed && same) {
			printedId = id;
			
			tr.find("td:not(:first-child,:last-child)").each(function() {
				if($(this).hasClass("highlight")) {
					$(this).removeClass("highlight");
					printedId = "";
					
				} else {
					$(this).addClass("highlight");
				}
			});
		} 
		onOffButton();
	})
});

function onOffButton() {
	if (printedId.length > 0) {
		$('.btn-download').removeClass('disabled');
	} else {
		$('.btn-download').addClass('disabled');
	}
}

function downloadPhieuthu() {
	if (printedId.length > 0) {
		//window.location.href="../../users/advanceForm/phieuchi/download?id=" + ids;
		if(confirm("Download refund for ids : " + printedId + " ?")) {
			
			$.ajax({
			    type: "POST",
			    url: "phieuthu/print",
			    data: {"id": printedId},
			    success: function(msg){
			    	for (var i = 0; i < currentRow.length; i++) {
			    		currentRow.find("td").eq(5).html(msg);
				    	currentRow.find("td").removeClass("highlight");
			    	}
			    	window.location.href="phieuthu/download?id=" + printedId;
			    	currentRow = null;
			    	printedId = "";
			    	onOffButton();
			    },
			    error: function(msg){
			    	alert(msg);
			    }
			    
			}); 
			
		}
	}
	
}
</script>