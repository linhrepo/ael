<%@ include file="/common/taglibs.jsp" %>

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
                <th></th>
                <th></th>
                <th><fmt:message key="debit.thutien"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
                <th></th>
                <th></th>
                <th><fmt:message key="debit.thutien"/></th>
                <th><fmt:message key="table.action"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${docsgeneralList}" var="trucking" varStatus="idx">
        	<tr params="docId=${trucking.id}" class="${trucking.isCollectMoney ? '':'impress' }">
                <td>${idx.index+1}</td>
              	<td>${trucking.jobNo}</td>
              	<td><fmt:message key="${trucking.typeOfDocs.textKey}"/></td>
              	<td>${trucking.typeOfContainer.value}</td>
              	<td>${trucking.phiAEL}</td>
              	<td>${trucking.phiThuHo}</td>
              	<td>
              		<c:choose>
              			<c:when test="${trucking.isCollectMoney}">
              				<fmt:message key="debit.dathu"/>
              			</c:when>
              			<c:otherwise>
              				<fmt:message key="debit.chuathu"/>
              			</c:otherwise>
              		</c:choose>
              	</td>
              	<td>
                	<a value="${trucking.id}" class="approveMoney" class="iconButton" title="<fmt:message key='table.buttonEditTitle'/>"><i class="fa fa-pencil-square-o"></i></a>
                </td>
            </tr> 
        </c:forEach>
        </tbody>
    </table>
    
<div id="voucher-info-modal" style="display:none;">
		<table class="display table table-striped table-bordered table-hover">
			<tbody>
				<tr><td><fmt:message key="advanceform.refcode"/></td><td id="vi-refcodes"></td></tr>
				<tr><td><fmt:message key="advanceform.employee"/></td><td id="vi-name"></td></tr>
				<tr><td><fmt:message key="advanceform.total"/></td><td id="vi-amount"></td></tr>
				<tr><td><fmt:message key="moneybook.date"/></td><td><input id="vi-date" /></td></tr>
				<tr><td><fmt:message key="moneybook.voucherNo"/></td><td><input id="vi-id" placeholder="Input voucher no"/></td></tr>
				<tr><td><fmt:message key="moneybook.description"/></td><td><input id="vi-reason" placeholder="Content"/></td></tr>
			</tbody>
		</table>
		<!-- <span id="error-msg" style="color: red;"></span> -->
	</div>
<div style="display:none;">
	<div id="myModal"><span>This campaign hasn't been set Result Target yet. Go
							to Result target setting page?</span></div>
	<button id="bootbox-options">${needResultTargetSetting}</button>
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

$(document).ready(function(){
	if($("#approve").val() != null && $("#approve").val() != ""){
		UTIL.showMessage('<fmt:message key="debit.approved"/>', "info");
	}
	$('.approveMoney').click(function(){			
		/* $.confirm({
		    text: '<fmt:message key="debit.confirmApprove"/>',
		    confirm: function() {
		    	$(location).attr('href','<c:url value="approveCollectMoney?id="></c:url>' + $('#approveMoney').attr("value") + "&approve=1");
		    },
		    cancel: function() {
		        // nothing to do
		    }
		}); */
		//docId=5
		var id = $(this).closest("tr").attr("params");
		id = id.substring(6);
		alert(id);
		$.ajax({
		    type: "GET",
		    url: "loadJobMoney",
		    data: {"id": id},
		    success: function(msg){
		    	alert("done");
		    }
		}); 
	});
});

function downloadPhieuchi(voucherType) {
	if (printedIds.length > 0) {
		var ids = printedIds[0];
		for(var i = 1; i < printedIds.length; i ++) {
			ids += "," + printedIds[i];
		}
		if(voucherType == 0) {//advanceForm
			$.ajax({
			    type: "POST",
			    url: "../../users/advanceForm/phieuchi/print",
			    data: {"id": ids},
			    success: function(msg){
			    	reviewVoucherPayment(ids, msg, "advanceForm");
			    }
			}); 
		} else {
			$.ajax({
			    type: "POST",
			    url: "../../users/refund/phieuchi/print",
			    data: {"id": ids},
			    success: function(msg){
			    	reviewVoucherPayment(ids, msg, "refund");
			    }
			}); 
		}
	}
}

function reviewVoucherPayment(ids, voucherInfo, type) {
	var data = JSON.parse(voucherInfo);
	var name = currentRow[0].find("td").eq(2).text();
	$("#vi-name").html(name);
	$("#vi-refcodes").html(data.refCodes);
	$("#vi-amount").html(data.amount.toLocaleString('en-IN'));

	bootbox.dialog({
		   closeButton: false,
	       message: $("#voucher-info-modal").html(),
	       title: "PRINT PAYMENT SHEET CONFIRMATION",
	       className: "modal-darkorange",
	       buttons: {
	    	   "Confirm": {
	               className: "btn-blue",
	               callback: function () {
            	    	
            	    	$.ajax({
            			    type: "POST",
            			    url: "../../users/" + type + "/phieuchi/createmoneybook",
            			    data: { "date" : $(".modal-content #vi-date").val(),
	       		    			    "voucherNo" : $(".modal-content #vi-id").val(),
	       		    			    "reason" : $(".modal-content #vi-reason").val()},
            			    success: function(msg){
            			    	if (msg == "ok") { 
	        	       		    	window.location.href="../../users/" + type + "/phieuchi/download?id=" + ids;
	        	       		    	
	        	       		    	for (var i = 0; i < currentRow.length; i++) {
	        	       		    		currentRow[i].find("td").eq(7).html($(".modal-content #vi-id").val());
	        	       		        	currentRow[i].find("td").removeClass("highlight");
	        	       		    	}
	            			    	printedGroupString = "";
	        	       		    	printedIds = [];
	        	       		    	currentRow = [];
	        	       		    	//onOffButton();
	        	       		    	$('.btn-download').addClass('disabled');
            			    	} else {
            			    		alert(msg);
            			    		//reviewVoucherPayment(ids, voucherInfo, type);
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
	/* $(".modal-content #vi-reason").val(data.reason); */
	$(".modal-content #vi-date").datepicker("setDate", new Date());
	$(".modal-content #vi-date").datepicker().on('changeDate', function(e) {
		$(this).datepicker('hide');
	})
}
</script>
<script src="../../scripts/bootbox/bootbox.js"></script>    
<!-- <script>
	$(document).ready(function(){
		if($("#approve").val() != null && $("#approve").val() != ""){
			UTIL.showMessage('<fmt:message key="debit.approved"/>', "info");
		}
		$('#approveMoney').click(function(){			
			/* $.confirm({
			    text: '<fmt:message key="debit.confirmApprove"/>',
			    confirm: function() {
			    	$(location).attr('href','<c:url value="approveCollectMoney?id="></c:url>' + $('#approveMoney').attr("value") + "&approve=1");
			    },
			    cancel: function() {
			        // nothing to do
			    }
			}); */
		});
	});
</script> -->