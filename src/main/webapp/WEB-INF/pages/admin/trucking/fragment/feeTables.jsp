<%@ include file="/common/taglibs.jsp"%>
<h3>
	<fmt:message key="exhibition.feeTables" />
</h3>
<hr>
<div role="tabpanel">
  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">
    		<fmt:message key="exhibition.feeTablesDetail" />
		</a>
	</li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">
    	<fmt:message key="exhibition.feeTablesSummary" />
    </a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
    	<table id="exfeetableList${idx.index}" 
	class="display table table-condensed inFormTable feeTable" cellspacing="0"
	width="100%">
	<thead>
		<tr>
			<th><fmt:message key="table.no" /></th>
			<th><fmt:message key="inland.masterFee" /></th>
			<th><fmt:message key="inland.feeName" /></th>
			<th><fmt:message key="inland.feevalue" /></th>
			<th><fmt:message key="inland.feevavat" /></th>
			<th><fmt:message key="inland.total" /></th>
			<th><fmt:message key="table.action" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${detail.exfeetables}"
			var="exfeetable" varStatus="idxx">
			
		</c:forEach>
	</tbody>
</table>
<div class="row">
	<div class="col-md-10"></div>
	<div class="col-md-2">
		<span class="btn btn-primary" target-table="${idx.index}"> <i
			class="icon-ok"></i> <fmt:message key="button.add" /></span>
	</div>
</div>
	</div>
    <div role="tabpanel" class="tab-pane" id="profile">
    	<table id="exfeetableList${idx.index}Summary" class="display table table-striped">
			<thead>
				<tr>
					<th><fmt:message key="table.no" /></th>
					<th><fmt:message key="inland.masterFee" /></th>
					<th><fmt:message key="inland.total" /></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
  </div>
</div>
<hr>
<script type="text/javascript" src="<c:url value='/scripts/custom/feeTables.js'></c:url>"></script>