<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

	<div id="elencoNotifiche">

		<c:if test="${empty notifiche }">
			<span>Nessuna notifica ${soloNonLette == 'S' ? 'da leggere' : 'presente'}.</span>
		</c:if>

		<c:if test="${not empty notifiche }">
			<c:forEach items="${notifiche}" var="notifica">
				<div class="row">
					
					<div class="span1" style="text-align: center; margin-top: 15px; width: 50px;">
						<c:if test="${notifica.tipoNotifica == 'O'}">
							<i class="icon-gears icon-2x"></i>
						</c:if>
						<c:if test="${notifica.tipoNotifica == 'I'}">
							<span class="icon-stack">
		  						<i class="icon-circle-blank icon-stack-base"></i>
		  						<i class="icon-info"></i>
							</span>
						</c:if>
					</div>
					
					<div class="span${soloNonLette == 'S' ? '4' : '11'}">
						<div class="aprinotifica">
							<spring:url value="/private/notifiche/dettaglio?id=${notifica.id}" var="linkNotifica"  />
							<a href="${linkNotifica}" class="no"></a>
							<span class="${notifica.flagLettura == 'S' ? 'letta' : 'nonletta' }" >${notifica.oggetto}</span>
							<br>
							<span>${notifica.testo}</span>
						</div>
						
						<security:authorize access="hasPermission(#notifica, 'gestioneNotificaOperativa')" var="canOperate" /> 
						
						<c:if test="${canOperate}">
							<spring:url value="/private/notifiche/gestione?id=${notifica.id}" var="gestioneNotifica"  />
							<a href="${gestioneNotifica}">Clicca qui</a> per gestire la richiesta
						</c:if>
						<c:if test="${not canOperate}">
							<span>Richiesta gestita da ${notifica.utenteOperatore.nome}&nbsp;${notifica.utenteOperatore.cognome}</span>
						</c:if>
						
						
						<hr/>
					</div>
				</div>
			</c:forEach>			
		</c:if>
		
		<c:if test="${soloNonLette == 'S'}">
			<div class="row">
				<div class="span7">
					<div class="form-horizontal">
						<div class="control-group">
							<div class="form-actions form-actions-notifica">
								<spring:url value="/private/notifiche/elenco" var="url_tutte" />
								<a class="btn" href="${url_tutte }" id="mostraTutteLeNotifiche">Mostra tutte&nbsp;<i class="icon-time"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>

	</div>
</div>