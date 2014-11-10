<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />

<spring:message var="idHeader" code="gestione.utente.id" />
<spring:message var="nomeHeader" code="gestione.utente.nome" />
<spring:message var="cognomeHeader" code="gestione.utente.cognome" />
<spring:message var="cfHeader" code="gestione.utente.cf" />
<spring:message var="emailHeader" code="gestione.utente.email" />
<spring:message var="tipoHeader" code="gestione.utente.tipo" />
<spring:message var="enteHeader" code="gestione.utente.ente" />




<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">
			<div id="elencoNotifiche">
				
				<c:forEach items="${notifiche}" var="notifica">
				
					<div class="aprinotifica">
						<spring:url value="/private/notifiche/dettaglio?id=${notifica.id}" var="linkNotifica"  />
						<a href="${linkNotifica}" class="no" />
						<span class="${notifica.flagLettura == 'S' ? 'letta' : 'nonletta' }" >${notifica.oggetto}</span>
						<br>
						<span>${notifica.testo}</span>
					</div>
					<c:if test="${notifica.tipoNotifica == 'O'}">
						<spring:url value="/private/notifiche/gestione?id=${notifica.id}" var="gestioneNotifica"  />
						<a href="${gestioneNotifica}">Clicca qui</a> per gestire la richiesta
					</c:if>
					
					<hr/>
				</c:forEach>			
								
			</div>
		</div>
	</div>

</div>