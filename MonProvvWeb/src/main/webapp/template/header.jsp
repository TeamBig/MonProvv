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

	<span class="utente">${nome} ${cognome} (${ruolo}) - ${organo} - <a id="popoverNotifiche" href="${base}/private/notifiche/conteggio" title="Notifiche" data-url="${base}/private/notifiche/elenco">
		<i class="icon-bell-alt" style="position:relative; text-decoration:none;" > 
		<span class="icon-stack" style="color: #cb4437; text-decoration:none; position:absolute; top:-11px; right:-10px; display: none;" id="notifBadge">
			<i class="icon-circle icon-stack-base" style="font-size: 1em;"></i>
          	<i class="icon-light" style="font-size: 0.7em;" id="countNotifiche"></i>
		</span> 
		</i> </a>
		 - <a href="<c:url value="/j_spring_security_logout" />" title="logout - ${nome} ${cognome}">ESCI <i class="icon-signout"></i></a>
	 </span>
</security:authorize>
<security:authorize access="!isAuthenticated()">
	<span class="utente"><a href="${base}/private">ACCEDI <i class="icon-signin"></i></a></span>
</security:authorize>
 


