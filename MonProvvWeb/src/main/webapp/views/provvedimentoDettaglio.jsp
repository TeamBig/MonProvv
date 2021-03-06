<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />

<spring:message var="idHeader" code="listaProvvedimenti.id" />
<spring:message var="governoHeader" code="listaProvvedimenti.header.governo" />
<spring:message var="tipologiaHeader" code="listaProvvedimenti.header.tipologia" />
<spring:message var="fonteNormativaHeader" code="listaProvvedimenti.header.fonteNormativa" />
<spring:message var="artHeader" code="listaProvvedimenti.header.art" />
<spring:message var="commaHeader" code="listaProvvedimenti.header.comma" />
<spring:message var="provvDaAdottareHeader" code="listaProvvedimenti.header.provvDaAdottare" />
<spring:message var="titoloOggettoHeader" code="listaProvvedimenti.header.titoloOggetto" />
<spring:message var="tipoAttoHeader" code="listaProvvedimenti.header.tipoAtto" />
<spring:message var="dataAttoHeader" code="listaProvvedimenti.header.dataAtto" />
<spring:message var="numeroAttoHeader" code="listaProvvedimenti.header.numeroAtto" />
<spring:message var="collNormattivaHeader" code="listaProvvedimenti.header.collNormattiva" />

<spring:message var="termineDiScadenzaHeader" code="label.termineDiScadenza" />
<spring:message var="senzaTermineHeader" code="label.senza.termine" />

<spring:message var="parereHeader" code="label.parere" />
<spring:message var="noteInterneHeader" code="listaProvvedimenti.header.noteInterne" />

<spring:message var="statoDiAttuazioneHeader" code="listaProvvedimenti.header.statoDiAttuazione" />
<spring:message var="capofilaHeader" code="listaProvvedimenti.header.capofila" />
<spring:message var="proponenteHeader" code="listaProvvedimenti.header.proponente" />
<spring:message var="assegnazioneHeader" code="listaProvvedimenti.header.assegnazione" />
<spring:message var="allegatiHeader" code="listaProvvedimenti.header.allegati" />

<spring:message var="descrizioneHeader" code="listaAllegati.header.descrizione" />
<spring:message var="dimensioneHeader" code="listaAllegati.header.dimensione" />

<spring:message var="organoHeader" code="listaAssegnatari.header.organo" />
<spring:message var="presaInCaricoHeader" code="listaAssegnatari.header.presaInCarico" />
<spring:message var="allegatiAssegnatarioHeader" code="listaAssegnatari.header.allegati" />
<spring:message var="noteHeader" code="listaAssegnatari.header.note" />
<spring:message var="cronologiaModificheHeader" code="listaAssegnatari.header.cronologiaModifiche" />
<spring:message var="eliminaHeader" code="listaAssegnatari.header.elimina" />
<spring:message var="sollecitoHeader" code="listaAssegnatari.header.sollecito" />

<security:authorize	access="hasPermission(#provvedimentoDettaglio, 'modificaStato')" var="canModificaStato" />
<security:authorize	access="hasPermission(#provvedimentoDettaglio, 'chiusuraLavori')" var="canModificaChiusuraLavori" />
<security:authorize	access="hasPermission(#provvedimentoDettaglio, 'accettazione')" var="canAccettazione" />
<security:authorize access="hasPermission(#provvedimentoDettaglio, 'lavorazione')" var="canLavorazione" />
<security:authorize access="hasPermission(#provvedimentoDettaglio, 'fineLavorazione')" var="canFineLavorazione" />

<springform:form modelAttribute="provvedimentoDettaglio" cssClass="form-horizontal" action="" method="POST">
<fmt:formatDate value="${provvedimentoDettaglio.termineScadenza}" var="termineScadenzaFrt" pattern="dd/MM/yyyy" />
<fmt:formatDate value="${provvedimentoDettaglio.dataAtto}" var="dataAttoFrt" pattern="dd/MM/yyyy" />
	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span>Dettaglio Provvedimento</span></h3>
			</div>
		</div>
			<springform:hidden path="id" id="idProvvedimento" />
			<springform:hidden path="versione" id="versioneProvvedimento" />
			<div class="row">
				<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<span class="control-label">${idHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.id}</span>
								<input type="hidden" id="provvedimentoDettaglioId" value="${provvedimentoDettaglio.id}" /> 
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${governoHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.governo.denominazione}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${tipologiaHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.tipoProvvedimento.descrizione}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${fonteNormativaHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.fonteNormativa}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label" >${tipoAttoHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.tipoAtto.descrizione}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label" >${dataAttoHeader}</span>
							<div class="controls">
								<span>${dataAttoFrt}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label" >${numeroAttoHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.numeroAtto}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label" >${artHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.articolo}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${commaHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.comma}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label" >${collNormattivaHeader}</span>
							<div class="controls">
								<span><a href="${provvedimentoDettaglio.collNormattiva}" target="_blank" title="Apri collegamento ${provvedimentoDettaglio.collNormattiva}">${provvedimentoDettaglio.collNormattiva}</a></span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${provvDaAdottareHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.tipoProvvDaAdottare.descrizione}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${titoloOggettoHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.oggettoAsText}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${termineDiScadenzaHeader}</span>
							<div class="controls">
								<span>
									${termineScadenzaFrt}
								</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${senzaTermineHeader}</span>
							<div class="controls">
								<span>
									<c:if test="${provvedimentoDettaglio.senzaTermine}">
										Si
									</c:if>
									<c:if test="${not provvedimentoDettaglio.senzaTermine}">
										No
									</c:if>
								</span>
							</div>
						</div>
						
						
						
						<div class="control-group">
							<label class="control-label" for="statoDiAttuazione">${statoDiAttuazioneHeader}</label>
							<div class="controls">
								<c:if test="${canModificaStato or canModificaChiusuraLavori }">
									<springform:select path="stato" id="statoDiAttuazioneDettaglio" cssClass="input-xlarge" >
										<springform:options items="${listaStatoDiAttuazione}" itemValue="id" itemLabel="descrizione" />
									</springform:select>
								</c:if>
								<c:if test="${(not canModificaStato) and (not canModificaChiusuraLavori)}">
									<span>${provvedimentoDettaglio.stato.descrizione}</span>
								</c:if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="enteCapofila">${capofilaHeader}</label>
							<div class="controls">
								<span>${provvedimentoDettaglio.organoCapofila.denominazione}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${proponenteHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.organoConcertante.denominazione}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${parereHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.desOrganoParere}</span>
							</div>
						</div>
						<div class="control-group">
							<span class="control-label">${noteInterneHeader}</span>
							<div class="controls">
								<span>${provvedimentoDettaglio.noteInterne}</span>
							</div>
						</div>
				</div>
			</div>
			
			<!-- Allegati insert -->
			<div class="row">
				<div class="span12">
					<h3 class="text-left underline">
						<span>Associazione a provvedimenti pregressi</span>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="span12">	
					<display:table 	name="${listaProvvedimentiPregressi}" 
										requestURI="" sort="external" partialList="false"
										 id="provvedimentiParent" 
										class="table table-hover table-bordered"
										summary="Elenco Provvedimenti Pregressi" style="width: 100%">

							<display:column title="Titolo / Oggetto" property="provvedimentoCollegato.oggettoAsText" headerScope="col" />
					</display:table>
				</div>
			</div>
			<!-- allegati end -->

			<!-- Allegati insert -->
			<div class="row">
				<div class="span12">
					<h3 class="text-left underline">
						<span>Allegati</span>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="span12">	
					<display:table 	name="${listaAllegati}" 
										requestURI="" sort="external" partialList="false"
										 id="allegato" 
										class="table table-hover table-bordered"
										summary="Elenco Allegati" style="width: 100%">

							<display:column title="${idHeader}" property="id" headerClass="hidden" headerScope="col" class="hidden" />
							<display:column title="${descrizioneHeader}" href="/private/provvedimenti/ricerca/downloadAllegato/${allegato.id}" headerScope="col" class="vcenter">
								<spring:url value="/private/provvedimenti/ricerca/downloadAllegato?id=${allegato.id}" var="urlDownload" />
								<a href="${urlDownload}" class="download">${allegato.descrizione}</a>
							</display:column>
							<display:column title="${dimensioneHeader}" headerScope="col">
								<%-- <c:out value="${allegato.dimensioneAsText}"></c:out> --%>
								<c:if test="${not empty allegato.dimensione }">
									<spring:eval expression="T(it.tesoro.monprovv.utils.StringUtils).convertBytesToKb(${allegato.dimensione},true)"/>
								</c:if>
							</display:column>
					</display:table>
				</div>
			</div>
			<!-- allegati end -->
			<div class="row">
				<div class="span12">
					<h3 class="text-left underline"><span>Assegnatari</span></h3>
				</div>
			</div>
			<div class="row">
				<div class="span12">
						<display:table 	name="${listaAssegnazione}" 
											requestURI="" sort="external" partialList="false"
											 id="assegnazione" 
											class="table table-hover table-bordered"
											summary="Elenco Assegnatari" style="width: 100%">
	
								<display:column title="${idHeader}" property="id" headerClass="hidden" headerScope="col" class="hidden" />
								<display:column title="${organoHeader}" property="organo.denominazione" headerClass="medium" headerScope="col" class="medium" />
								<display:column title="${presaInCaricoHeader}"  headerScope="col" class="vcenter center">
									<c:choose>
									      <c:when test="${assegnazione.stato.codice eq 'ACC'}">
									      	<i class="icon-check icon-large"></i>
									      </c:when>
									      <c:when test="${assegnazione.stato.codice eq 'FLA'}">
									      	<i class="icon-smile icon-large text-green" title="Fine lavorazione"></i>
									      </c:when>
										  <c:when test="${assegnazione.stato.codice eq 'RIF'}">
										  	<spring:url var="url_popoverrifiuto" value="/private/provvedimenti/motivazionerifiuto?id={id}">
										  		<spring:param name="id" value="${assegnazione.id}" />
										  	</spring:url>
									      	<a href="${url_popoverrifiuto}" class="popoverRifiuto"><i class="icon-remove-sign icon-large" title="Assegnazione rifiutata"></i>&nbsp;Motivazione rifiuto</a>
									      </c:when>
									</c:choose>
								</display:column>
								<display:column title="${allegatiHeader}" headerScope="col" headerClass="medium">
									<c:forEach var="allegato" items="${assegnazione.allegatoList}">
										<spring:url value="/private/provvedimenti/ricerca/downloadAllegato?id=${allegato.id}" var="urlDownload" />
										<div><a href="${urlDownload}" class="download">${allegato.descrizione}</a>
											<c:if test="${not empty allegato.dimensione }">
												(<spring:eval expression="T(it.tesoro.monprovv.utils.StringUtils).convertBytesToKb(${allegato.dimensione},true)"/>)
											</c:if> 
										</div>
									</c:forEach>
								</display:column>
								<display:column title="${noteHeader}" property="nota.testoAsText"  headerScope="col" />
								
								<display:column title="${cronologiaModificheHeader}" headerScope="col" class="vcenter center">
									<spring:url value="dettaglio/modalCronologia?id=${assegnazione.id}" var="urlModale" />
									<a href="${urlModale}" role="button" data-toggle="modal" data-target="#modalCronologia"><i class="icon-time icon-large"></i></a>
								</display:column>
<%-- 								<display:column title="${eliminaHeader}"  headerScope="col" headerClass="center" class="vcenter center">
									<i class="icon-trash icon-large" title="Elimina assegnazione"></i>
								</display:column> --%>
								<security:authorize access="hasPermission(#provvedimentoDettaglio, 'sollecitoVisible')">
									<display:column title="${sollecitoHeader}"  headerScope="col" headerClass="center" class="vcenter center">
										<c:if test="${(assegnazione.stato.codice ne 'RIF')}">
											<c:set value="${assegnazione.numSolleciti eq '' ? 'Per questa assegnazione sono stati inviati ' + assegnazione.numSolleciti : 'Nessun sollecito inviato per questa assegnazione' }" var="title_solleciti"></c:set>
										
											<a href="#modalSollecito" role="button" data-toggle="modal" id="anchorModalSollecito"><i class="icon-envelope-alt icon-large" title="Per questa assegnazione sono stati inviati ${assegnazione.numSolleciti} solleciti"></i></a>
										</c:if>
									</display:column>
								</security:authorize>
						</display:table>
				</div>
<%-- 				<springform:form cssClass="form-horizontal" commandName="assegnatarioNew" id="assegnazioneForm" name="assegnazioneForm" action="#" method="GET">
					<springform:hidden path="provvedimento.id" id="idProvvedimento"/>
					<div class="control-group">
						<label class="control-label" for="organo">Nuovo assegnatario</label>
						<div class="controls">
							<springform:select path="organo" id="organo" cssClass="input-xlarge" >
								<springform:options items="${listaOrgani}" itemValue="id" itemLabel="denominazione" />
							</springform:select>
							<button type="button" id="insertAssegnatario" class="btn">Aggiungi &nbsp;<i class="icon-plus"></i></button>
						</div>
					</div>
				</springform:form> --%>
			</div>
			<div class="row">
				<div class="span12">
					<div class="form-horizontal">
						<div class="control-group">
							<div class="form-actions pull-right">
								<c:if test="${ canModificaChiusuraLavori }">
									<button type="button" class="btn btn-primary" id="salvaeinvianotifica" name="salvaeinvianotifica">Salva e invia notifica&nbsp;<i class="icon-file-alt"></i></button>
									<button type="submit" class="btn" name="annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
								</c:if>
								
								<c:if test="${ canFineLavorazione }">
									<button type="submit" class="btn" name="fineLavorazione">Fine lavorazione&nbsp;<i class="icon-share-alt"></i></button>								
								</c:if>
								<c:if test="${ canLavorazione }">
									<button type="submit" class="btn btn-primary" name="noteAllegati">Inserisci note e allegati&nbsp;<i class="icon-file-alt"></i></button>
									<button type="submit" class="btn" name="indietro">Indietro&nbsp;<i class="icon-arrow-left"></i></button>								
								</c:if>
																
								<c:if test="${ canAccettazione }">
									<button type="submit" class="btn btn-primary" name="accettaAssegnazione">Accetta assegnazione&nbsp;<i class="icon-ok"></i></button>
									<button type="button" class="btn" id="rifiutaAssegnazione">Rifiuta assegnazione&nbsp;<i class="icon-remove"></i></button>
								</c:if>

								<security:authorize access="hasPermission(#provvedimentoDettaglio, 'richiesta')">
									<button type="submit" class="btn btn-primary" id="richiediAssegnazione">Richiedi assegnazione&nbsp;<i class="icon-check"></i></button>
									<input type="hidden" id="richiediAssegnazioneId" value="${provvedimentoDettaglio.id}" /> 
								</security:authorize>
							
								<c:if test="${!canAccettazione and canModificaStato }">
									<button type="submit" class="btn btn-primary" name="salvaDettaglio">Salva &nbsp;<i class="icon-save"></i></button>
									<button type="submit" class="btn" name="annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
								</c:if>
								<security:authorize access="hasPermission(#provvedimentoDettaglio, 'modificaProvvedimento')">
									<button type="submit" class="btn btn-primary" name="modifica">Modifica &nbsp;<i class="icon-edit"></i></button>
									<button type="submit" class="btn" name="indietro">Indietro&nbsp;<i class="icon-arrow-left"></i></button>
								</security:authorize>

								<c:if test="${ !canLavorazione and !canModificaStato and !canAccettazione and !canModificaChiusuraLavori}">
									<button type="submit" class="btn" name="indietro">Indietro&nbsp;<i class="icon-arrow-left"></i></button>
								</c:if>								
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	
	
<!--  finestra modale -->
 
	<div id="modalCronologia" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&#215;</button>
			<h3 id="myModalLabel">Cronologia modifiche</h3>
		</div>
		<div class="modal-body">
			<i class="fa fa-spinner fa-spin"></i>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Chiudi</button>
		</div>
	</div>
	
	<!--  finestra modale invio notifiche-->
	<div id="modalSalvaInviaNotifica" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&#215;</button>
			<h3 id="myModalLabel">Invia notifica</h3>
		</div>
		<div class="modal-body">
			<i class="fa fa-spinner fa-spin"></i>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="inviaNotificaModal">Invia notifica <i class="icon-send"></i></button>
		</div>
	</div>
	
	<!--  finestra modale invio sollecito-->
	<springform:hidden path="idAssegnatarioSollecito" id="idAssegnatarioSollecito" />
	<div id="modalSollecito" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&#215;</button>
			<h3 id="myModalLabel">Invia sollecito</h3>
		</div>
		<div class="modal-body">
			<div class="control-group">
				<label class="control-label" for="oggettoSollecito">Oggetto</label>
				<div class="controls">
					<springform:input path="oggettoSollecito" cssClass="input-xlarge" id="oggettoSollecito" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="testoSollecito">Testo</label>
				<div class="controls">
				<springform:textarea path="testoSollecito" cssClass="input-xlarge" rows="10" id="testoSollecito" />
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="inviaSollecitoModal">Invia sollecito <i class="icon-send"></i></button>
		</div>
	</div>
	<!-- fine finestra modale -->

	
	<!--  modal richiesta assegnazione  -->
	<div id="modalRichiestaAssegnazione" class="modal hide fade" tabindex="-1" role="dialog">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&#215;</button>
			<h3>Richiesta assegnazione</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="art">Motivazione</label>
					<div class="controls">
						<springform:textarea path="motivazioneRichiesta" class="input-xlarge" rows="10" />
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" id="richiediAssegnazioneModal" >Invia richiesta&nbsp;<i class="icon-location-arrow"></i></button>
		</div>
	</div>
	<!-- fine  modal richiesta assegnazione  -->
	
	<!--  modal rifiuto assegnazione  -->
	<div id="modalRifiutoAssegnazione" class="modal hide fade" tabindex="-1" role="dialog">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&#215;</button>
			<h3>Rifiuto assegnazione</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="art">Motivazione</label>
					<div class="controls">
						<springform:textarea path="motivazioneRifiuto" class="input-xlarge" rows="10" />
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" id="rifiutoAssegnazioneModal" >Invia&nbsp;<i class="icon-location-arrow"></i></button>
		</div>
	</div>
	<!-- fine  modal rifiuto assegnazione  -->
	
	
</springform:form>
