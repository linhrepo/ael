<%@ include file="/common/taglibs.jsp"%>
<br>
<div class="col-sm-10">
	<h2>
		<fmt:message key='moneybook.title' />
	</h2>
	<jsp:include page="moneyBookSearch.jsp"></jsp:include>
	<br />

	<%-- <h3>
		<fmt:message key='moneybook.cashbook.title' />
	</h3> --%>

	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#cashbooktab"
				aria-controls="home" role="tab" data-toggle="tab"> <fmt:message
						key="moneybook.cashbook.title" />
			</a></li>
			<li role="presentation"><a href="#bankbooktab"
				aria-controls="profile" role="tab" data-toggle="tab"> <fmt:message
						key="moneybook.bankbook.title" />
			</a></li>
		</ul>
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="cashbooktab">
				<br>
				<table>
					<tr>
						<td><fmt:message key="moneybook.first.balance" />&nbsp;</td>
						<c:if test="${empty firstCashBalance}">
							<td><input id="cashAmount" /></td>
							<td><button onclick="saveFirstBalance()">Save</button></td>
						</c:if>
						<c:if test="${not empty firstCashBalance}">
							<td><span>${firstCashBalance.amount}</span></td>
							<td><button onclick="editFirstBalance()">Edit</button></td>
						</c:if>
					</tr>
				</table>
				<br>
				<table id="cashbook" class="display datatable" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th><fmt:message key="table.no" /></th>
							<th><fmt:message key="moneybook.voucherNo" /></th>
							<th><fmt:message key="moneybook.date" /></th>
							<th><fmt:message key="moneybook.voucherType" /></th>
							<th><fmt:message key="moneybook.description" /></th>
							<th><fmt:message key="moneybook.paymentMoney" /></th>
							<th><fmt:message key="moneybook.receptMoney" /></th>
							<th><fmt:message key="moneybook.balance" /></th>
							<th>Action</th>
						</tr>
					</thead>

					<tfoot>
						<tr>
							<th><fmt:message key="table.no" /></th>
							<th><fmt:message key="moneybook.voucherNo" /></th>
							<th><fmt:message key="moneybook.date" /></th>
							<th><fmt:message key="moneybook.voucherType" /></th>
							<th><fmt:message key="moneybook.description" /></th>
							<th><fmt:message key="moneybook.paymentMoney" /></th>
							<th><fmt:message key="moneybook.receptMoney" /></th>
							<th><fmt:message key="moneybook.balance" /></th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach items="${cashbooks}" var="mb" varStatus="idx">
							<tr id='${mb.id}'>
								<td>${idx.index+1}</td>
								<td>${mb.voucherNoPrint}</td>
								<td><fmt:formatDate value="${mb.date}" pattern="yyyy-MM-dd" />
								</td>
								<td><fmt:message key="${mb.typeOfVoucher.printValue}" /></td>
								<td>${mb.description}</td>
								<td class="money"><fmt:formatNumber>${mb.paymentMoney}</fmt:formatNumber>
								</td>
								<td class="money"><fmt:formatNumber>${mb.receptMoney}</fmt:formatNumber>
								</td>
								<td class="money"><fmt:formatNumber>${mb.balance}</fmt:formatNumber>
								</td>
								<td><span
									onclick="editMoneyBook('${mb.id}', '${mb.voucherNoPrint}', '<fmt:formatDate value="${mb.date}" pattern="dd-MM-yyyy"/>', '${mb.description}');"
									class="label label-success"> <i class="fa fa-pencil-square-o"></i> </span>
									&nbsp;
									<span
									onclick="deleteMoneyBook('${mb.id}');"
									class="label label-danger"> <i class="fa fa-trash"></i> </span>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div role="tabpanel" class="tab-pane" id="bankbooktab">
				<br>
				<table>
					<tr>
						<td><fmt:message key="moneybook.first.balance" />&nbsp;</td>
						<c:if test="${empty firstBankBalance}">
							<td><input id="cashAmount" /></td>
							<td><button onclick="saveFirstBalance()">Save</button></td>
						</c:if>
						<c:if test="${not empty firstBankBalance}">
							<td><span>${firstBankBalance.amount}</span></td>
							<td><button onclick="editFirstBalance()">Edit</button></td>
						</c:if>
					</tr>
				</table>
				<br>
				<table id="bankbook" class="display datatable" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th><fmt:message key="table.no" /></th>
							<th><fmt:message key="moneybook.voucherNo" /></th>
							<th><fmt:message key="moneybook.date" /></th>
							<th><fmt:message key="moneybook.voucherType" /></th>
							<th><fmt:message key="moneybook.description" /></th>
							<th><fmt:message key="moneybook.paymentMoney" /></th>
							<th><fmt:message key="moneybook.receptMoney" /></th>
							<th><fmt:message key="moneybook.balance" /></th>
							<th>Action</th>
						</tr>
					</thead>

					<tfoot>
						<tr>
							<th><fmt:message key="table.no" /></th>
							<th><fmt:message key="moneybook.voucherNo" /></th>
							<th><fmt:message key="moneybook.date.transaction" /></th>
							<th><fmt:message key="moneybook.voucherType" /></th>
							<th><fmt:message key="moneybook.description" /></th>
							<th><fmt:message key="moneybook.paymentMoney" /></th>
							<th><fmt:message key="moneybook.receptMoney" /></th>
							<th><fmt:message key="moneybook.balance" /></th>
							<th>Action</th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach items="${bankbooks}" var="mb" varStatus="idx">
							<tr id='${mb.id}'>
								<td>${idx.index+1}</td>
								<td>${mb.voucherNoPrint}</td>
								<td><fmt:formatDate value="${mb.date}" pattern="yyyy-MM-dd" />
								</td>
								<td><fmt:message key="${mb.typeOfVoucher.printValue}" /></td>

								<td>${mb.description}</td>
								<td class="money"><fmt:formatNumber>${mb.paymentMoney}</fmt:formatNumber>
								</td>
								<td class="money"><fmt:formatNumber>${mb.receptMoney}</fmt:formatNumber>
								</td>
								<td class="money"><fmt:formatNumber>${mb.balance}</fmt:formatNumber>
								</td>
								<td><span
									onclick="editMoneyBook('${mb.id}', '${mb.voucherNoPrint}', '<fmt:formatDate value="${mb.date}" pattern="dd-MM-yyyy"/>', '${mb.description}');"
									class="label label-success"> <i class="fa fa-pencil-square-o"></i> </span>
									&nbsp;
									<span
									onclick="deleteMoneyBook('${mb.id}');"
									class="label label-danger"> <i class="fa fa-trash"></i> </span>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="voucher-info-modal" style="display: none;">
	<table class="display table table-striped table-bordered table-hover">
		<tbody>
			<tr>
				<td><fmt:message key="moneybook.voucherNo" /></td>
				<td><input id="mb-no" /></td>
			</tr>
			<tr>
				<td><fmt:message key="moneybook.date.transaction" /></td>
				<td><input id="mb-date" /></td>
			</tr>
			<tr>
				<td><fmt:message key="moneybook.description" /></td>
				<td><input id="mb-des" /></td>
			</tr>
		</tbody>
	</table>
	<!-- <span id="error-msg" style="color: red;"></span> -->
</div>
<style>
.label {
	cursor: pointer;
}

.highlight {
	background: #38b44a !important;
}
</style>
<script>
	var res = "";
	function editMoneyBook(id, mbNo, mbDate, mbDes) {
		$("#" + id).addClass("highlight");
		bootbox.dialog({
					closeButton : false,
					message : $("#voucher-info-modal").html(),
					title : "EDIT MONEY BOOK",
					className : "modal-darkorange",
					buttons : {
						"Confirm" : {
							className : "btn-blue",
							callback : function() {
								res = $(".modal-content #mb-des").val();
								$
										.ajax({
											type : "POST",
											url : "updateMoneybook",
											data : {
												"id" : id,
												"date" : $(
														".modal-content #mb-date")
														.val(),
												"voucherNo" : $(
														".modal-content #mb-no")
														.val(),
												"reason" : $(
														".modal-content #mb-des")
														.val()
											},
											success : function(data) {
												if (data != "notok") {
													var msg = JSON.parse(data);
													$("#" + id).removeClass(
															"highlight");
													$("#" + id)
															.find("td")
															.eq(1)
															.html(msg.voucherNo);
													$("#" + id).find("td")
															.eq(2).html(
																	msg.date);
													//$("#"+id).find("td").eq(4).html(msg.reason);
													$("#" + id).find("td")
															.eq(4).html(res);
												} else {
													alert("Voucher no is wrong or duplicate");
												}
											},
											error : function(msg) {
												$("#" + id).removeClass(
														"highlight");
												alert(msg);
											}
										})
							}
						},
						"Cancel" : {
							className : "btn-red",
							callback : function() {
								$("#" + id).removeClass(
								"highlight");
							}
						}
					}
				});

		$(".modal-content #mb-no").val(mbNo);
		$(".modal-content #mb-des").val(mbDes);
		var dateArr = mbDate.split("-");
		$(".modal-content #mb-date").datepicker("setDate",
				new Date(dateArr[2], dateArr[1] - 1, dateArr[0]));
		$(".modal-content #mb-date").datepicker().on('changeDate', function(e) {
			$(this).datepicker('hide');
		})
	}

	function deleteMoneyBook(id) {
	 	if(confirm("Delete will make some actions revert. Please make sure you want to remove this row")) {
			$.ajax({
				type : "POST",
				url : "deleteMoneybook",
				data : {
					"id" : id
				},
				success : function(msg) {
					$("#" + id).remove();
					if (msg == "ok") {
						alert("Delete successfully!")
					} else {
						alert("Problem when delete this row, please ask Phuong for this :) ")
					}
				}
			});
	 	}
	}
			
	function saveFirstBalance() {
		var amount = $("#cashAmount").val();
		if (isNaN(amount)) {
			alert("Please input number");
		} else {
			$.ajax({
				type : "POST",
				url : "moneybook/saveFirstBalance",
				data : {
					"amount" : amount
				},
				success : function(msg) {
					alert("Done");
				}
			});
		}
	}
</script>
<script src="../../scripts/bootbox/bootbox.js"></script>
