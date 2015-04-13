<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="vn">
<head>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value="/images/icons/main.png"/>"/>
    <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
    <t:assets/>
    <script>
    PNotify.prototype.options.styling = "fontawesome";
    $.fn.datepicker.defaults.format="dd/mm/yyyy";
    </script>
    <decorator:head/>
</head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
    <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
    <c:set var="isChild" scope="request"><decorator:getProperty property="meta.child"/></c:set>

    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value='/'/>">
<%--             <img alt="" src="<c:url value="/images/icons/main.png"/>" style="margin-right:3px;"> --%>
            <fmt:message key="webapp.name"/></a>
        </div>

        <%@ include file="/common/menu.jsp" %>
        <c:if test="${pageContext.request.locale.language ne 'en'}">
            <div id="switchLocale"><a href="<c:url value='/?locale=en'/>">
                <fmt:message key="webapp.name"/> in English</a>
            </div>
        </c:if>
    </div>

    <div class="container" id="content">
        <%@ include file="/common/messages.jsp" %>
        <div class="row">
            <decorator:body/>

            <c:if test="${currentMenu == 'AdminMenu'}">
                <div class="col-sm-2">
                <menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
                    <menu:displayMenu name="AdminMenu"/>
                </menu:useMenuDisplayer>
                </div>
            </c:if>
            
            <c:if test="${currentMenu == 'AccountingMenu' && isChild != 'true'}">
                <div class="col-sm-2">
                <menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
                    <menu:displayMenu name="AccountingMenu"/>
                </menu:useMenuDisplayer>
                </div>
            </c:if>
            
            <c:if test="${currentMenu == 'DocsMenu' && isChild != 'true'}">
                <div class="col-sm-2">
                <menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
                    <menu:displayMenu name="DocsMenu"/>
                </menu:useMenuDisplayer>
                </div>
            </c:if>
            
             <c:if test="${currentMenu == 'TrackingMenu' && isChild != 'true'}">
                <div class="col-sm-2">
                <menu:useMenuDisplayer name="Velocity" config="navlistMenu.vm" permissions="rolesAdapter">
                    <menu:displayMenu name="TrackingMenu"/>
                </menu:useMenuDisplayer>
                </div>
            </c:if>
        </div>
    </div>

    <div id="footer" class="navbar-fixed-bottom">
        <span class="col-sm-6 text-left"><fmt:message key="webapp.version"/>
            <c:if test="${pageContext.request.remoteUser != null}">
            | <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
            </c:if>
        </span>
        <span class="col-sm-6 text-right">
            &copy; <fmt:message key="copyright.year"/> <a href="<fmt:message key="company.url"/>"><fmt:message key="company.name"/></a>
        </span>
    </div>
<%= (request.getAttribute("scripts") != null) ?  request.getAttribute("scripts") : "" %>
<security:authorize ifAnyGranted="ROLE_ADMIN"> 
    		<script type="text/javascript">
    			$(document).ready(function(){
    				$("form").find('[verify="true"]').find('input[type="checkbox"]').removeAttr("disabled");
    			});
    		</script>
</security:authorize>
</body>
</html>
