<%@ include file="/common/taglibs.jsp" %>
<style>
.highlight {
	background : #38b44a !important;
}
.input-amount {
	margin: 0.2em !important;
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
              				<button id='${trucking.id}_0' onclick="collectMoney('${trucking.jobNo}','${trucking.id}_0', ${trucking.phiAel})">
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiAelChuaThu}"></fmt:formatNumber></button>
              			</c:when>
              			<c:otherwise>
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiAelChuaThu}"></fmt:formatNumber>
              			</c:otherwise>
              		</c:choose>
              	</td>
              	<td>
              		<c:choose>
              			<c:when test="${not empty trucking.phiChiHo and trucking.collectMoneyStatus != 1 and trucking.collectMoneyStatus != 3 and trucking.phiChiHo != '0.00'}">
              				<button id='${trucking.id}_1' onclick="collectMoney('${trucking.jobNo}', '${trucking.id}_1', ${trucking.phiChiHo})">
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiChiHoChuaThu}"></fmt:formatNumber></button>
              			</c:when>
              			<c:otherwise>
              				<fmt:formatNumber pattern="#,###" value="${trucking.phiChiHoChuaThu}"></fmt:formatNumber>
              			</c:otherwise>
              		</c:choose>
              	<td>
              		<c:if test="${not empty trucking.phiAel}">
              			<fmt:formatNumber pattern="#,###" value="${trucking.phiAelChuaThu + trucking.phiChiHoChuaThu}"></fmt:formatNumber>
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
			<%-- <tr><td><fmt:message key="advanceform.refcode"/></td><td id="vi-refcodes"></td></tr> --%>
			<tr><td><fmt:message key="moneybook.feeType"/></td><td id="vi-vouchertype"></td></tr>
			<tr><td><fmt:message key="moneybook.amount"/></td><td id="vi-amount"></td></tr>
			<tr><td><fmt:message key="moneybook.sum"/></td><td id="vi-sum"></td></tr>
			<tr><td><fmt:message key="moneybook.date"/></td><td><input id="vi-date" /></td></tr>
			<tr><td><fmt:message key="moneybook.voucherNo"/></td><td><input id="vi-id" placeholder="Input voucher no"/></td></tr>
			<tr><td><fmt:message key="moneybook.description"/></td><td><input id="vi-reason" placeholder="Content"/></td></tr>
		</tbody>
	</table>
	<!-- <span id="error-msg" style="color: red;"></span> -->
</div>
<script>
var multiplePrice = [];
var multipleAmount = "";
function collectMoney(jobNo, buttonId, amountButton) {
	var button = $("#"+buttonId);
	var value = buttonId + "-" + amountButton;
	
	if (button.hasClass("highlight")) {
		var index = multiplePrice.indexOf(value);
		if (index > -1) {
			multiplePrice.splice(index, 1);
			button.removeClass("highlight");
		}	
	} else {
		var type = buttonId.substring(buttonId.length - 1);
		if (type == '0') {
			jobNo += "_AEL";
		} else {
			jobNo += "_CH";
		}
		multipleAmount +=  jobNo + "<input class='input-amount' id='" + buttonId + "_" + amountButton + "' value='" + amountButton + "'></input><br>";
		multiplePrice.push(value);
		button.addClass("highlight");
	}
}
function processCollectMoney(moneyType) {
	if (multiplePrice.length > 0) {
		$.ajax({
		    type: "GET",
		    url: "collectMoney",
		    data: {"moneyType": moneyType},
		    success: function(msg){
		    	/* reviewCollectMoney(id, jobNo, feeType, amount, msg); */
		    	reviewCollectMoney(moneyType, multiplePrice, msg);
		    },
		    error: function(msg){
		    	alert("not ok");
		    }
		}); 
	} else {
		alert('Please choose at least one row');
	}
}

//function reviewCollectMoney(id, jobNo, feeType, amount, voucherInfo, button) {
//var value = id +"-" +type + "-" + jobNo + "-" + amount;
function reviewCollectMoney(moneyType, multiplePrice, voucherInfo) {

	var jobNo = "";
	var feeType;
	var amount = 0;
	for (var i = 0; i < multiplePrice.length; i++) {
		var v = multiplePrice[i].split("-");
		jobNo += v[0] + ",";
		amount += parseFloat(v[1]);
	}

	var data = JSON.parse(voucherInfo);
	/* $("#vi-refcodes").html(jobNo); */
	var feeTypeName = feeType == 0 ? "NTTK" : "PT";
	$("#vi-vouchertype").html(feeTypeName);
	$("#vi-amount").html(multipleAmount);
	$("#vi-sum").html(amount.toLocaleString('en-IN'));
	
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
            			    data: { 
            			    		"moneyType" : moneyType,
            			    		//"data" : JSON.stringify(multiplePrice),
            			    		"date" : $(".modal-content #vi-date").val(),
	       		    			    "voucherNo" : $(".modal-content #vi-id").val(),
	       		    			    "reason" : $(".modal-content #vi-reason").val(),
	       		    			 	"amount": amount,
	       		    			    "jobNo": jobNo
	       		    			    }, //for update docs collectMoneyStatus
            			    success: function(msg){
            			    	for (var i = 0; i < multiplePrice.length; i++) {
            			    		var v = multiplePrice[i].split("-");    		
            			    		var td = $('#' + v[0]).closest("td");
            			    		var am = v[1];
		        	       		    td.html(am.toLocaleString('en-IN'));
		        	       		    
		        	       		    var statusStr = "Updated";
		        	       		 	td.closest("tr").find("td").last().html(statusStr);
			        	       		 /* switch (status) {
			        	       		    case "0" : statusStr = "<fmt:message key='debit.type.no'/>";
			        	       		    break;
			        	       			case "1" : statusStr = "<fmt:message key='debit.type.yes'/>";
			        	       			td.closest("tr").removeClass("impress");
			        	       			break;
			        	       			case "2" : 
			        	       			case "3" : statusStr = "<fmt:message key='debit.type.still'/>";
			        	       			break;
		        	       		    } */
            			    	}	        
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