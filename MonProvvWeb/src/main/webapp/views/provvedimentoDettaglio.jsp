<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />

<spring:message var="idHeader" code="listaProvvedimenti.header.id" />
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
<spring:message var="parereHeader" code="label.termineDiScadenza" />

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


	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span>Dettaglio Provvedimento</span></h3>
			</div>
		</div>
		<springform:form modelAttribute="provvedimentoDettaglio" cssClass="form-horizontal" action="#" method="POST">
			<div class="row">
				<div class="span10 offset2 dettaglio">
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
								<span>${provvedimentoDettaglio.dataAtto}</span>
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
								<span>${provvedimentoDettaglio.collNormattiva}</span>
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
								<span>${provvedimentoDettaglio.termineScadenza}</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="statoDiAttuazioneDettaglio">${statoDiAttuazioneHeader}</label>
							<div class="controls">
								<security:authorize
									access="hasPermission(#provvedimentoDettaglio, 'modificaStato')" var="canModificaStato" />
							
								<c:if test="${canModificaStato }">
									<select id="statoDiAttuazioneDettaglio" class="input-xlarge">
										<option>Inserito</option>
										<option>Sospeso</option>
										<option selected>Fine lavorazione</option>
										<option>Chiusura lavori</option>
										<option>Adottato</option>
										<option>Non attuabile</option>
										<option>Superato</option>
									</select>
								</c:if>
								
								<c:if test="${not canModificaStato }">
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
								<span>${provvedimentoDettaglio.parere}</span>
							</div>
						</div>
				</div>
			</div>
			</springform:form>
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

							<display:column title="${idHeader}" property="id" headerScope="col" />
							<display:column title="${descrizioneHeader}" href="/private/ricercaProv/downloadAllegato/${allegato.id}" headerScope="col" class="vcenter">
								<spring:url value="/private/ricercaProv/downloadAllegato/${allegato.id}" var="urlDownload" />
								<a href="${urlDownload}" class="download">${allegato.descrizione}</a>
							</display:column>
							<display:column title="${dimensioneHeader}" headerScope="col">
								<c:out value="${allegato.dimensioneAsText}"></c:out>
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
										  <c:when test="${assegnazione.stato.codice eq 'RIF'}">
									      	<a href="#" id="popoverRifiuto"><i class="icon-remove-sign icon-large" title="Assegnazione rifiutata"></i>&nbsp;Motivazione rifiuto</a>
									      </c:when>
									</c:choose>
								</display:column>
								<display:column title="${allegatiHeader}" headerScope="col" headerClass="medium">
									<c:forEach var="allegato" items="${assegnazione.allegatoList}">
										<spring:url value="/private/ricercaProv/downloadAllegato/${allegato.id}" var="urlDownload" />
										<div><a href="${urlDownload}" class="download">${allegato.descrizione}</a> (${allegato.dimensioneAsText})</div>
									</c:forEach>
								</display:column>
								<display:column title="${noteHeader}" property="nota.testoAsText"  headerScope="col" />
								<display:column title="${cronologiaModificheHeader}"  headerScope="col" headerClass="center" class="vcenter center">
									<a href="#modalCronologia" role="button" data-toggle="modal"><i class="icon-time icon-large"></i></a>
								</display:column>
								<display:column title="${eliminaHeader}"  headerScope="col" headerClass="center" class="vcenter center">
									<i class="icon-trash icon-large" title="Elimina assegnazione"></i>
								</display:column>
								<display:column title="${sollecitoHeader}"  headerScope="col" headerClass="center" class="vcenter center">
									<a href="#modalSollecito" role="button" data-toggle="modal"><i class="icon-envelope-alt icon-large" title="Invio sollecito"></i></a>
								</display:column>
						</display:table>
				</div>
				<springform:form cssClass="form-horizontal" commandName="assegnatarioNew" id="assegnazioneForm" name="assegnazioneForm" action="#" method="GET">
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
				</springform:form>
			</div>
			<div class="row">
				<div class="span12">
					<div class="form-horizontal">
						<div class="control-group">
							<div class="form-actions pull-right">
								<security:authorize access="hasPermission(#provvedimentoDettaglio, 'lavorazione')">
									<button type="submit" class="btn btn-primary" id="noteAllegatiProvvedimento" value="noteallegati">Inserisci note e allegati&nbsp;<i class="icon-file-alt"></i></button>
									<button type="submit" class="btn" id="fineLavorazioneProvvedimento" value="finelavorazione">Fine lavorazione&nbsp;<i class="icon-share-alt"></i></button>								
								</security:authorize>

								<security:authorize access="hasPermission(#provvedimentoDettaglio, 'accettazione')">
									<button type="submit" class="btn btn-primary" name="accettaAssegnazione">Accetta assegnazione&nbsp;<i class="icon-ok"></i></button>
									<button type="button" class="btn" name="rifiutaAssegnazione">Rifiuta assegnazione&nbsp;<i class="icon-remove"></i></button>
								</security:authorize>

							
								<c:if test="${canModificaStato }">
									<button type="submit" class="btn btn-primary" id="salva">Salva &nbsp;<i class="icon-save"></i></button>
									<button type="button" class="btn" id="annullaModificaProvvedimento" value="Annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
								</c:if>
								
								<security:authorize access="hasPermission(#provvedimentoDettaglio, 'modificaProvvedimento')">
									<button type="submit" class="btn" id="modificaProvvedimento" value="Modifica">Modifica &nbsp;<i class="icon-edit"></i></button>
								</security:authorize>
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
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Cronologia modifiche</h3>
		</div>
		<div class="modal-body">
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th>Data</th>
						<th>Operazione</th>
						<th>Organo</th>
						<th>Utente</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>09-08-2014 09:37</td>
						<td>Inserimento nota</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Marco Iezzi</td>
					</tr>
					<tr>
						<td>10-08-2014 14:30</td>
						<td>Modifica nota</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Francesco Carlucci</td>
					</tr>
					<tr>
						<td>10-08-2014 15:56</td>
						<td>Inserimento allegato</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Daniele Fiorio</td>
					</tr>
					<tr>
						<td>13-08-2014 10:23</td>
						<td>Inserimento allegato</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Daniele Fiorio</td>
					</tr>
					<tr>
						<td>20-08-2014 17:10</td>
						<td>Cancellazione allegato</td>
						<td>Agenzia Dogane e monopoli</td>
						<td>Alessandro Bartolucci</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Chiudi</button>
		</div>
	</div>
	
	<div id="modalSalvaInviaNotifica" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Invia notifica</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="email">Email</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="email"></input>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="oggetto">Oggetto</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="oggetto"></input>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="art">Testo</label>
					<div class="controls">
						<textarea class="input-xlarge" rows="10"></textarea>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="inviaNotifica">Invia notifica <i class="icon-send"></i></button>
		</div>
	</div>
<!-- fine finestra modale -->

<!--  modal invio sollecito  -->
	<div id="modalSollecito" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">Invio sollecito</h3>
		</div>
		<div class="modal-body">
			<div class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="art">Testo</label>
					<div class="controls">
						<textarea class="input-xlarge" rows="10"></textarea>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true" id="richiediAssegnazione">Invia &nbsp;<i class="icon-location-arrow"></i></button>
		</div>
	</div>
