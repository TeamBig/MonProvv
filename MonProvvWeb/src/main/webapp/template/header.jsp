<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<spring:eval expression="@config.getProperty('notifiche.numVisibili')" var="numNotificheVisibili" />
			
<spring:url value="/" var="base" />

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.utente.nome" var="nome" />
	<security:authentication property="principal.utente.cognome" var="cognome" />
	<security:authentication property="principal.ruoloCorrente.descrizione" var="ruolo" />
	<security:authentication property="principal.utente.organo.denominazione" var="organo" />

	<form action="${base}/j_spring_security_logout" method="post" style="margin-bottom: 0px;">
		<span class="utente">${nome} ${cognome} (Ruolo: ${ruolo}) - ${organo} - <a id="popoverNotifiche" href="${base}/private/notifiche/conteggio" title="Notifiche" data-url="${base}/private/notifiche/elencononlette">
			<i class="icon-bell-alt"> 
			<span class="icon-stack" id="notifBadge">
				<i class="icon-circle icon-stack-base" ></i>
	          	<i class="icon-light" id="countNotifiche"></i>
			</span> 
			</i></a>
			 - <a href="#" id="logout" title="logout - ${nome} ${cognome}">ESCI <i class="icon-signout"></i></a>
		 </span>
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	 </form>
</security:authorize>
<security:authorize access="!isAuthenticated()">
	<span class="utente"><a href="${base}/private">ACCEDI <i class="icon-signin"></i></a></span>
</security:authorize>
 


