<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />

<spring:message var="idHeader" code="listaOrgani.header.id" />
<spring:message var="denominazioneHeader" code="listaOrgani.header.denominazione" />
<spring:message var="denominazioneEstesaHeader" code="listaOrgani.header.denominazione.estesa" />
<spring:message var="enteConcertanteHeader" code="listaOrgani.header.ente.concertante" />
<spring:message var="enteTipoHeader" code="listaOrgani.header.ente.tipo" />

<spring:message var="nomeHeader" code="listaOrgani.listaUtenti.nome" />
<spring:message var="cognomeHeader" code="listaOrgani.listaUtenti.cognome" />
<spring:message var="cfHeader" code="listaOrgani.listaUtenti.cf" />
<spring:message var="emailHeader" code="listaOrgani.listaUtenti.email" />
<spring:message var="tipoHeader" code="listaOrgani.listaUtenti.tipo" />

<div class="container" id="dettaglioEnte">
	<div class="row">
		<div class="span12">
		
			<springform:form method="post" commandName="organoToEdit" cssClass="form-horizontal">
				
				<springform:hidden path="id"/>
				<springform:hidden path="denominazione"/>
				<springform:hidden path="denominazioneEstesa"/>
				<springform:hidden path="flagConcertante"/>
				<springform:hidden path="unitaOrgAstage"/>
				<springform:hidden path="versione"/>
				
				<h3 class="text-left underline">
					<span>Dettaglio Ente</span>
				</h3>
				
				<div class="row">
					<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<span class="control-label">Id</span>
							<div class="controls">
								<span>${organoToEdit.id}</span>
							</div>
						</div>			
						<div class="control-group">
							<span class="control-label">${denominazioneHeader}</span>
							<div class="controls">
								<span>${organoToEdit.denominazione}</span>
							</div>
						</div>	
						
						<div class="control-group">
							<span class="control-label">${denominazioneEstesaHeader}</span>
							<div class="controls">
								<span>${organoToEdit.denominazioneEstesa}</span>
							</div>
						</div>
						
						<div class="control-group">
							<span class="control-label">${enteConcertanteHeader}</span>
							<div class="controls">
								<span>${organoToEdit.concertante}</span>
							</div>
						</div>
						
						<div class="control-group">
							<span class="control-label">${enteTipoHeader}</span>
							<div class="controls">
								<span>${organoToEdit.tipo}</span>
							</div>
						</div>
						
								
					</div>
				</div>
				
		
				<h3 class="text-left underline">
					<span>Utenti Ente</span>
				</h3>
				
				<c:if test="${tableUtentiListOrganiRisultatiSize gt 0}">
					<div class="row">
						<div class="span12">
						
						<display:table 	name="${organoToEdit.utenteList}" 
											pagesize="${risultatiPerPagina}" 
											requestURI="" sort="external" partialList="true"
											size="${tableUtentiListOrganiRisultatiSize}" id="utente" 
											class="table table-hover table-bordered"
											summary="Elenco Utenti">
	
								<display:column title="${idHeader}" property="id" headerScope="col" />
								<display:column title="${nomeHeader}" property="nome" headerScope="col" />
								<display:column title="${cognomeHeader}" property="cognome" headerScope="col" />
								<display:column title="${cfHeader}" property="codiceFiscale" headerScope="col" />
								<display:column title="${emailHeader}" property="email" headerScope="col" />
								<display:column title="${tipoHeader}" headerScope="col" >
									<c:choose>
										<c:when test="${flagIntEst == 'I'}">
										Interna
										</c:when>
										<c:otherwise>Esterna</c:otherwise>
									</c:choose>
								</display:column>
																
								
							</display:table>
							
						</div>
					</div>
				
				</c:if>

				<div class="control-group">
					<div class="pull-right">
						<button type="submit" class="btn" id="indietro" name="buttonBack" value="OK">Indietro &nbsp;<i class="icon-arrow-left"></i></button>
						<button type="submit" class="btn" id="modifica" name="buttonModify" value="OK">Modifica &nbsp;<i class="icon-edit"></i></button>
					</div>
				</div>
			</springform:form>
			
		</div>
		
	</div>
	
	<div class="row">
		<div class="span12"></div>
	</div>
</div>