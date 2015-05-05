<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="feeTablesacc.title"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

<div class="col-sm-10">

<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><fmt:message key="accounting.feeManifest" /></a></li>
		<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab"><fmt:message key="accounting.feeNhathau" /></a></li>
	</ul>
	<!-- Tab panes -->
	<div class="tab-content">
		<input type="hidden" id="flag" value="${flag }">
		<div role="tabpanel" class="tab-pane active" id="home"><jsp:include page="fragment/feeManifest.jsp"></jsp:include></div>
		<div role="tabpanel" class="tab-pane" id="settings"><jsp:include page="fragment/feeNhathau.jsp"></jsp:include></div>		
	</div>





	<%-- <h2><fmt:message key="feeTablesacc.heading"/></h2>
    <jsp:include page="searchFeeTables.jsp"></jsp:include>
    <table id="truckingList" class="display nestedDatatable" cellspacing="0" width="100%" 
    	childDetailURL="/admin/accounting/feesDetail" 
    	emptyMessage="accounting.fees.detailFailed"
    	detailTableInfo="<fmt:message key="table.no"/>,<fmt:message key="packageInfo.masterFee" />,<fmt:message key="packageInfo.feeName" />,<fmt:message key="packageInfo.feevalue" />,<fmt:message key="packageInfo.feevavat" />,<fmt:message key="packageIngo.total" />,<fmt:message key="accounting.approval" />,<fmt:message key="accounting.approvalDate" />,<fmt:message key="accounting.feeAdminApproval" />,<fmt:message key="accounting.changeApproval" />"
    	detailTableMapping="masterFee.value,name.value,amount[money],vat[money],total[money],approvedText[Key],dateChange[Date],checkByAdminText[Key]"
    	editDetail="/admin/accounting/changeApproval"
    	actionCheck = "checkByAdmin:false,null"
    	successLoadMessage="accounting.fees.detailLoaded"
    	>
        <thead>
            <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="trucking.refNo"/></th>
                <th><fmt:message key="trucking.typeOfDocs"/></th>
                <th><fmt:message key="trucking.typeOfContainer"/></th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${docsgeneralList}" var="trucking" varStatus="idx">
        	<tr params="docId=${trucking.id}">
                <td>${idx.index+1}</td>
              	<td>${trucking.jobNo}</td>
              	<td><fmt:message key="${trucking.typeOfDocs.textKey}"/></td>
              	<td>${trucking.typeOfContainer.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div> --%>

<script type="text/javascript">
 $(document).ready(function(){
	 $(".nestedDatatable tbody td i").each(function(){
		 var nTr = $(this).parents('tr')[0];
		 var reloadChild = function(tr){
			 $(tr).find("td i").click();
			 $(tr).find("td i").click();
		 };
		 
		 $(nTr).bind("afterOpenChild",function(ev,oInnerTable){
			 $(oInnerTable).find("td.actions a").on("click",function(e){
				 e.preventDefault();
				 var link = $(this).attr("href");
				 $.ajax({
					  type: "POST",
					  async:false,
					  url: link,
					  success: function(data){
						  if (data == "error"){
							  UTIL.showMessage(showMessage("accounting.changeApprovalError"), "error");
						  }else{
							  reloadChild(nTr);
						  }
					  },
					  dataType: "text",
					  error: function (xhr, status,response) {
						  UTIL.showMessage(showMessage("ajax.error"), "error");
				      }
					});
			 });
		 });
	 });
	 
	 if($('#flag').val() == "1"){		 
	 	$('.nav-tabs a[href="#settings"]').tab('show');
	 }
 });
</script>