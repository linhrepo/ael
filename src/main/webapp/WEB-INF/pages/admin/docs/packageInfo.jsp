<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="packageInfo.title"/></title>
    <meta name="menu" content="DocsMenu"/>
</head>
 <c:set var="delObject" scope="request"><fmt:message key="packageInfo.title"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>
    <h2><fmt:message key='packageInfo.heading'/></h2>
 
<div class="col-sm-12">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="packageInfo" method="post" action="packageInfo" id="packageInfoForm" cssClass="well" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <form:hidden path="creator.id"/>
    <form:hidden path="counting"/>
    <form:hidden path="docsgeneral.id"/>
    <form:hidden path="createdDate"/>
    <div class="container-fluid">
	    <jsp:include page="fragment/packageInfo/generalInput.jsp"></jsp:include>
	    <hr>
	    <div role="tabpanel">
		  <!-- Nav tabs -->
		  <ul class="nav nav-tabs" role="tablist">
		    <li role="presentation" class="active"><a href="#services" aria-controls="services" role="tab" data-toggle="tab"><fmt:message key="packageInfo.addServicesTitle" /></a></li>
		    <li role="presentation" id="contsstab"><a href="#contss" aria-controls="contss" role="tab" data-toggle="tab"><fmt:message key="packageInfo.listContsealDetail" /></a></li>
		    <li role="presentation"><a href="#fees" aria-controls="fees" role="tab" data-toggle="tab"><fmt:message key="packageInfo.feeTables" /></a></li>
		    <li role="presentation"><a href="#invoices" aria-controls="invoices" role="tab" data-toggle="tab"><fmt:message key="docsgeneral.invoices" /></a></li>
		  </ul>
		
		  <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="services"><jsp:include page="fragment/packageInfo/serviceTables.jsp"></jsp:include></div>
		    <div role="tabpanel" class="tab-pane" id="contss"><jsp:include page="fragment/packageInfo/contsealTables.jsp"></jsp:include></div>
		    <div role="tabpanel" class="tab-pane" id="fees"><jsp:include page="fragment/packageInfo/feeTables.jsp"></jsp:include></div>
		    <div role="tabpanel" class="tab-pane" id="invoices"><jsp:include page="fragment/packageInfo/attachments.jsp"></jsp:include></div>
		  </div>
		</div>   	
	    <jsp:include page="fragment/packageInfo/checkboxInput.jsp"></jsp:include>
	</div>
    <div class="form-group form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty packageInfo.id}">
          <button type="submit" class="btn btn-danger" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>
         <c:if test="${not empty packageInfo.doRelease and packageInfo.doRelease == true}">
          <button type="submit" class="btn btn-success" name="print" onclick="bCancel=true">
              <i class="icon-trash"></i> <fmt:message key="packageInfo.button.printBienbang"/>
          </button>
        </c:if>
        <button type="submit" class="btn btn-default" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
    </form:form>
    
    <hr>
    <div class="row">
    	<div class="col-md-7"></div>
    	<div class="col-md-5">
    		<fmt:message key="notify.createdBy"/>: <span style="color:green;">${packageInfo.creator.username}</span> on ${packageInfo.createdDate}
    		<br/>
    		<fmt:message key="notify.lastUpdatedBy"/>: <span style="color:green;">${packageInfo.updator.username}</span> on ${packageInfo.lastUpdateDate}
    	</div>
    </div>
</div>
</script>
<v:javascript formName="customer" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>