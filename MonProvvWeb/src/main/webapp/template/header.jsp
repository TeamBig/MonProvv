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

	<span class="utente">Utente: ${nome} ${cognome} (Ruolo ${ruolo}) - ${organo} - <a id="popoverNotifiche" href="#">2 messaggi non letti</a> - 
		<a href="<c:url value="/j_spring_security_logout" />" title="logout - ${nome} ${cognome}">ESCI</a>
	 </span>
</security:authorize>
<security:authorize access="!isAuthenticated()">
	<span class="utente"><a href="${base}/private">ACCEDI</a></span>
</security:authorize>
 


