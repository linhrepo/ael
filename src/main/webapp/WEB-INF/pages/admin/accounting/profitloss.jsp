<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <title><fmt:message key="menu.acct.profitloss"/></title>
    <meta name="menu" content="AccountingMenu"/>
</head>

<div class="col-sm-10">
    <h2><fmt:message key="menu.acct.profitloss"/></h2>
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="conditions"  method="post" action="profitloss" id="profitlossForm" cssClass="well" showLoading="false">
    	<div class="container-fluid">
    		<div class="row">		    
		    	<div class="form-group col-md-4">
					<appfuse:label styleClass="control-label"
						key="customer.title" />
					<form:select path="customerId" id="customercode"
						cssClass="form-control select2">
						<form:option value=""><fmt:message key="searchall" /></form:option>
						<c:forEach items="${docsSelection.customers}" var="cust">
							<form:option value="${cust.id}">${cust.code}</form:option>
						</c:forEach>
					</form:select>
				</div>
				
				<div class="form-group col-md-4">
					<appfuse:label styleClass="control-label"
						key="accountingnhathau.jobNo" />
					<form:select path="job" id="customercode" 
						cssClass="form-control select2">
						<form:option value=""><fmt:message key="searchall" /></form:option>
						<form:options items="${jobList }"/>
					</form:select>
				</div>
				<div class="form-group col-md-4">
					<appfuse:label styleClass="control-label"
						key="trucking.typeOfDocs" />
					<form:select path="typeOfDocs" id="typeOfDocs"
						cssClass="form-control select2">
						<c:forEach var="entry" items="${typeOfDocs}">
							<form:option value="${entry.key}"><fmt:message key="${entry.value}" /></form:option>
						</c:forEach>
					</form:select>
				</div>
			    
			    <div class="form-group col-md-4">
				    <appfuse:label styleClass="control-label" key="accountingnhathau.startdate"/>
				    <form:input path="startDate" provide="datepicker" maxlength="45"  class="form-control" id="startDateVT1"/>
			    </div>
			    
			    <div class="form-group col-md-4">
				    <appfuse:label styleClass="control-label" key="accountingnhathau.enddate"/>
				    <form:input path="endDate" provide="datepicker" maxlength="45"  class="form-control" id="endDateVT1"/>
			    </div>
    		</div>
    	</div>
	     <hr>
	    <div class="form-group form-actions">
	        <button class="btn btn-primary">
	    	<i class="fa fa-print"></i> <fmt:message key="button.search"/>
	    	<input type="submit" style="display: none;"/>
	    	</button>
	    
	    	<c:if test="${fn:length(summary.details) gt 0}">
		        <button class="btn btn-success" onclick="goDownload()">
		    	<i class="fa fa-print"></i> <fmt:message key="accounting.transport.download"/>
		    	</button>
	    	</c:if>
	    </div> 
    </form:form>
    
    <table id="profitlossList" class="display datatable" cellspacing="0" width="100%" >
        <%--<thead>
             <tr>
            	<th><fmt:message key="table.no"/></th>
                <th><fmt:message key="profitloss.file"/></th>
                <th><fmt:message key="profitloss.customer"/></th>
                <th><fmt:message key="profitloss.nhathau"/></th>
                <th><fmt:message key="profitloss.tongthu"/></th>
                <th><fmt:message key="profitloss.tongchi"/></th>
                <th><fmt:message key="profitloss.thuho"/></th>
                <th><fmt:message key="profitloss.debit"/></th>
                <th><fmt:message key="profitloss.pl"/></th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th><fmt:message key="table.no"/></th>
                <th><fmt:message key="profitloss.file"/></th>
                <th><fmt:message key="profitloss.customer"/></th>
                <th><fmt:message key="profitloss.nhathau"/></th>
                <th><fmt:message key="profitloss.tongthu"/></th>
                <th><fmt:message key="profitloss.tongchi"/></th>
                <th><fmt:message key="profitloss.thuho"/></th>
                <th><fmt:message key="profitloss.debit"/></th>
                <th><fmt:message key="profitloss.pl"/></th>
            </tr>
        </tfoot> --%>
        <thead>
       		 <tr>
            	<th>STT</th>
                <th>File</th>
                <th>Khach hang</th>
                <th>Nha thau</th>
                <th>Tong thu</th>
                <th>Tong chi</th>
                <th>Thu ho</th>
                <th>Debit</th>
                <th>P/L</th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th>STT</th>
                <th>File</th>
                <th>Khach hang</th>
                <th>Nha thau</th>
                <th>Tong thu</th>
                <th>Tong chi</th>
                <th>Thu ho</th>
                <th>Debit</th>
                <th>P/L</th>
            </tr>
        </tfoot>
        <tbody>
        <c:forEach items="${summary.details}" var="prolos" varStatus="idx">
        	<tr>
                <td>${idx.index+1}</td>
                <td>
                	${prolos.jobNo}
                </td>
                 <td>
                 	${prolos.cusName}
                </td>
          		<td>
                 	${prolos.nhathau}
                </td>
                <td>
                 	${prolos.tongThu}
                </td>
                <td>
                 	${prolos.tongChi}
                </td>
                <td>
                 	${prolos.thuHo}
                </td>
                <td>
                 	${prolos.debit}
                </td>
                <td>
                 	${prolos.profitLoss}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript" src="<c:url value='/scripts/advanceRefund.js'/>"></script>
<script>
	function goDownload() {
		$("#profitlossForm").attr("action", "profitloss/download");
		$("#profitlossForm").attr("method", "get");
		$("#profitlossForm").submit();
	}
</script>