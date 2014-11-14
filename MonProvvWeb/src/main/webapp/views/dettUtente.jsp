<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message var="idHeader" code="gestione.utente.id" />
<spring:message var="cognomeHeader" code="gestione.utente.cognome" />
<spring:message var="nominativoHeader" code="gestione.utente.nominativo" />
<spring:message var="nomeHeader" code="gestione.utente.nome" />
<spring:message var="cfHeader" code="gestione.utente.cf" />
<spring:message var="emailHeader" code="gestione.utente.email" />
<spring:message var="tipoHeader" code="gestione.utente.tipo" />
<spring:message var="enteHeader" code="gestione.utente.ente" />
<spring:message var="ruoloHeader" code="gestione.utente.ruolo" />
<spring:message var="amministratoreHeader" code="gestione.utente.amministratore" />
<spring:message var="datanascitaHeader" code="gestione.utente.data.nascita" />
<spring:message var="sessoHeader" code="gestione.utente.sesso" />

<div class="container" id="dettaglioUtente">

	<c:url value="/private/admin/utenti/dettaglio" var="formPath" />

	<springform:form action="${formPath}" method="POST" commandName="utenteToEdit" cssClass="form-horizontal">
		<div class="row">
			<div class="span12">
			
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>
				<springform:hidden path="flagAttivo"/>
				<springform:hidden path="utenteAstage" id="hiddenUtenteAstage"/>
				
				<h3 class="text-left underline">
					<span>Dettaglio Utente</span>
				</h3>
				
				<div class="row">
					<div class="span10 offset2 dettaglio">

						<div class="control-group">
							<span class="control-label">${tipoHeader}</span>
							<div class="controls">
								<span>
									${utenteToEdit.tipo}
									<springform:hidden path="flagIntEst" id="tipoNuovoUtente" />
								</span>
							</div>
						</div>

						<div class="control-group" >
							<span class="control-label">${cognomeHeader}</span>
							<div class="controls">
								<span>
									${utenteToEdit.cognome}
									<springform:hidden path="cognome" id="cognome" />
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<span class="control-label">${nomeHeader}</span>
							<div class="controls">
								<span>
									${utenteToEdit.nome}
									<springform:hidden path="nome" id="nome" />
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<label class="control-label" for="dataNascita">${datanascitaHeader}</label>
							<div class="controls">
								<span>
									<fmt:formatDate value="${utenteToEdit.dataNascita}" pattern="dd/MM/yyyy" />
									<springform:hidden path="dataNascita" id="dataNascitaV" />
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<label class="control-label" for="sesso">${sessoHeader}</label>
							<div class="controls">
								<span>
									<c:set var = "MASCHIO" value="M" />
									<c:set var = "FEMMINA" value="F" />
									<c:choose>
										<c:when test="${ utenteToEdit.sesso eq MASCHIO }">Maschile</c:when>
										<c:when test="${ utenteToEdit.sesso eq FEMMINA }">Femminile</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
									<springform:hidden path="sesso" id="sesso" />
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<span class="control-label">${cfHeader}</span>
							<div class="controls">
								<span>
									${utenteToEdit.codiceFiscale}
									<springform:hidden path="codiceFiscale" id="codiceFiscale" />
								</span>
							</div>
						</div>
						
						<div class="control-group" >	
							<span class="control-label">${emailHeader}</span>
							<div class="controls">
								<span>
									${utenteToEdit.email}
									<springform:hidden path="email" id="email" />
								</span>
							</div>
						</div>
						
						<div class="control-group" >	
							<span class="control-label">${enteHeader}</span>
							<div class="controls">
								<span>
									<c:if test="${not empty utenteToEdit.organo}">
										${utenteToEdit.organo.denominazione}
									</c:if>
									<springform:hidden path="organo" id="organo"/>
								</span>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="ruolo">${ruoloHeader}</label>
							<div class="controls">
								<span>
									<c:if test="${not empty utenteToEdit.ruolo}">
										${utenteToEdit.ruolo.descrizione}
									</c:if>
								</span>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="ruolo">${amministratoreHeader}</label>
							<div class="controls">
								<span>
									<c:if test="${utenteToEdit.amministratore}">
										Si
									</c:if>
									<c:if test="${not utenteToEdit.amministratore}">
										No
									</c:if>
								</span>
							</div>
						</div>

					</div>
				</div>
				
				<div class="control-group">
					<div class="pull-right">
						<button type="submit" class="btn" id="buttonModify" name="buttonModify" value="modify">Modifica &nbsp;<i class="icon-edit"></i></button>
						<button type="submit" class="btn" id="buttonBack" name="buttonBack" value="back">Indietro &nbsp;<i class="icon-arrow-left"></i></button>
					</div>
				</div>
			</div>
		</div>
	
	</springform:form>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>