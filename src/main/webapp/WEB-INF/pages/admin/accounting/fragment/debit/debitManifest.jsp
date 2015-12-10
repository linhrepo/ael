<%@ include file="/common/taglibs.jsp" %>
<style>
.highlight {
	background : #38b44a !important;
}
</style>
<head>
    <title><fmt:message key="feeTablesacc.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

	<h2><fmt:message key="debit.manifest"/></h2>
    <jsp:include page="searchDebit.jsp"></jsp:include>
    <input type="hidden" value="${approve }" id="approve"/>
    <table id="truckingList" class="display datatable" cellspacing="0" width="100%" >
    	<%-- childDetailURL="/admin/accounting/feesDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="packageInfo.masterFee" />,<fmt:message key="packageInfo.feeName" />,<fmt:message key="packageInfo.feevalue" />,<fmt:message key="packageInfo.feevavat" />,<fmt:message key="packageIngo.total" />,<fmt:message key="accounting.approval" />,<fmt:message key="accounting.approvalDate" />,<fmt:message key="accounting.feeAdminApproval" />,<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="masterFee.value,name.value,amount[money],vat[money],total[money],approvedText[Key],dateChange[Date],checkByAdminText[Key]"
    	editDetail="/admin/accounting/changeApproval"
    	actionCheck = "checkByAdmin:false,null"
    	successLoadMessage="accounting.fees.detailLoaded"
    	> --%>
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
                <th><fmt:message key="trucking.phiAel"/></th>
                <th><fmt:message key="trucking.phiChiHo"/></th>
                <th><fmt:message key="trucking.tongThuKhachHang"/></th>
                <th><fmt:message key="debit.thutien"/></th>
                <%-- <th><fmt:message key="table.action"/></th> --%>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
                <th id="phiAel"><fmt:message key="trucking.phiAel"/></th>
                <th id="phiChiHo"><fmt:message key="trucking.phiChiHo"/></th>
                <th><fmt:message key="trucking.tongThuKhachHang"/></th>
                <th><fmt:message key="debit.thutien"/></th>
                <%-- <th><fmt:message key="table.action"/></th> --%>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${docsgeneralList}" var="trucking" varStatus="idx">
        	<tr params="docId=${trucking.id}" class="${trucking.collectMoneyStatus == 1 ? '':'impress' }">
                <td>${idx.index+1}</td>
              	<td>${trucking.jobNo}</td>
              	<td><fmt:message key="${trucking.typeOfDocs.textKey}"/></td>
              	<td>${trucking.typeOfContainer.value}</td>
              	<%-- <td><button>${trucking.phiAel}</button></td> --%>
              	<td>
              		<c:choose>
              			<%-- no for: empty, isCollectedAll (1) and not collected Ael (!2) --%>
              			
              			<c:when test="${not empty trucking.phiAel and trucking.collectMoneyStatus != 1 and trucking.collectMoneyStatus != 2 and trucking.phiAel != '0.0000'}">
              				<button id='${trucking.id}-0' onclick="collectMoney('${trucking.id}-0', '${trucking.jobNo}', ${trucking.phiAel})">
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiAel}"></fmt:formatNumber></button>
              			</c:when>
              			<c:otherwise>
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiAel}"></fmt:formatNumber>
              			</c:otherwise>
              		</c:choose>
              	</td>
              	<td>
              		<c:choose>
              			<c:when test="${not empty trucking.phiChiHo and trucking.collectMoneyStatus != 1 and trucking.collectMoneyStatus != 3 and trucking.phiChiHo != '0.00'}">
              				<button id='${trucking.id}-1' onclick="collectMoney('${trucking.id}-1', '${trucking.jobNo}', ${trucking.phiChiHo})">
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiChiHo}"></fmt:formatNumber></button>
              			</c:when>
              			<c:otherwise>
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiChiHo}"></fmt:formatNumber>
              			</c:otherwise>
              		</c:choose>
              	<td>
              		<c:if test="${not empty trucking.phiAel}">
              			<fmt:formatNumber pattern="#,###" value="${trucking.phiAel + trucking.phiChiHo}"></fmt:formatNumber>
              		</c:if>
              	</td>
              	<td>
              		<c:choose>
              			<c:when test="${trucking.collectMoneyStatus == 0}">
              				<fmt:message key="debit.type.no"/>
              			</c:when>
              			<c:when test="${trucking.collectMoneyStatus == 1}">
              				<fmt:message key="debit.type.yes"/>
              			</c:when>
              			<c:otherwise>
              				<fmt:message key="debit.type.still"/>
              			</c:otherwise>
              		</c:choose>
              	</td>
              	<%-- <td>
                	<a value="${trucking.id}" class="approveMoney" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td> --%>
            </tr> 
        </c:forEach>
        </tbody>
    </table>
<div id="voucher-info-modal" style="display:none;">
	<table class="display table table-striped table-bordered table-hover">
		<tbody>
			<tr><td><fmt:message key="advanceform.refcode"/></td><td id="vi-refcodes"></td></tr>
			<tr><td><fmt:message key="moneybook.feeType"/></td><td id="vi-feetype"></td></tr>
			<tr><td><fmt:message key="moneybook.amount"/></td><td id="vi-amount"></td></tr>
			<tr><td><fmt:message key="moneybook.date"/></td><td><input id="vi-date" /></td></tr>
			<tr><td><fmt:message key="moneybook.voucherNo"/></td><td><input id="vi-id" placeholder="Input voucher no"/></td></tr>
			<tr><td><fmt:message key="moneybook.description"/></td><td><input id="vi-reason" placeholder="Content"/></td></tr>
		</tbody>
	</table>
	<!-- <span id="error-msg" style="color: red;"></span> -->
</div>
<script>
var multiplePrice = [];
function collectMoney(buttonId, jobNo, amount) {
	alert(multiplePrice.length);
	var button = $("#"+buttonId);
	var value = buttonId + "-" + jobNo + "-" + amount;
	
	if (button.hasClass("highlight")) {
		var index = multiplePrice.indexOf(value);
		if (index > -1) {
			multiplePrice.splice(index, 1);
			button.removeClass("highlight");
		}	
	} else {
		multiplePrice.push(value);
		button.addClass("highlight");
	}
}
function processCollectMoney(money) {
	$.ajax({
	    type: "GET",
	    url: "collectMoney",
	    data: {"moneyType": money},
	    success: function(msg){
	    	/* reviewCollectMoney(id, jobNo, feeType, amount, msg); */
	    	reviewCollectMoney(multiplePrice);
	    },
	    error: function(msg){
	    	alert("not ok");
	    }
	}); 
}

//function reviewCollectMoney(id, jobNo, feeType, amount, voucherInfo, button) {
function reviewCollectMoney(multiplePrice) {
	
	
	var data = JSON.parse(voucherInfo);
	$("#vi-refcodes").html(jobNo);
	var feeTypeName = feeType == 0 ? $("#phiAel").text() : $("#phiChiHo").text();
	$("#vi-feetype").html(feeTypeName);
	$("#vi-amount").html(amount.toLocaleString('en-IN'));
	bootbox.dialog({
		   closeButton: false,
	       message: $("#voucher-info-modal").html(),
	       title: "COLLECT MONEY CONFIRMATION",
	       className: "modal-darkorange",
	       buttons: {
	    	   "Confirm": {
	               className: "btn-blue",
	               callback: function () {
            	    	$.ajax({
            			    type: "POST",
            			    url:  "saveMoney",
            			    data: { "jobId" : id,
            			    		"date" : $(".modal-content #vi-date").val(),
	       		    			    "voucherNo" : $(".modal-content #vi-id").val(),
	       		    			    "reason" : $(".modal-content #vi-reason").val(),
	       		    			 	"amount": amount,
	       		    			    "feeType": feeType, //for update docs collectMoneyStatus
	       		    			    "jobNo": jobNo}, //for update docs collectMoneyStatus
            			    success: function(msg){
            			    	var status = msg;
            			    	var td = $('#' + id + "-" + feeType).closest("td");
	        	       		    td.html(amount);
	        	       		    var statusStr = "";
	        	       		    switch (status) {
		        	       		    case "0" : statusStr = "<fmt:message key='debit.type.no'/>";
		        	       		    break;
		        	       			case "1" : statusStr = "<fmt:message key='debit.type.yes'/>";
		        	       			td.closest("tr").removeClass("impress");
		        	       			break;
		        	       			case "2" : 
		        	       			case "3" : statusStr = "<fmt:message key='debit.type.still'/>";
		        	       			break;
	        	       		    }
	        	       		    
	        	       		 	td.closest("tr").find("td").last().html(statusStr);
            			    	
            			    },
            			    error: function(msg) {
            			    	alert(msg);
            			    }
            			})
	               }
	           }, 
	           "Cancel": {
	               className: "btn-red"
	           }
	       }
	});
	
	$(".modal-content #vi-id").val(data.voucherNoPrint);
	$(".modal-content #vi-date").datepicker("setDate", new Date());
	$(".modal-content #vi-date").datepicker().on('changeDate', function(e) {
		$(this).datepicker('hide');
	})
}
</script>
<script src="../../scripts/bootbox/bootbox.js"></script>