<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

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

<c:url value="/private/ricercaProv/modifica" var="formPath" />

	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span>Modifica Provvedimento</span></h3>
			</div>
		</div>
		<springform:form action="${formPath}" modelAttribute="provvedimentoModifica" commandName="provvedimentoModifica" cssClass="form-horizontal" method="POST">
		<springform:hidden path="id" id="idProvvedimento" />
		<springform:hidden path="versione" id="versioneProvvedimento" />
			<div class="row">
				<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<label class="control-label" for="governo">Governo</label>
							<div class="controls">
								<springform:select path="governo" id="governo" cssClass="input-xlarge" >
									<springform:options items="${listaGoverno}" itemValue="id" itemLabel="denominazione" />
								</springform:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tipologia">${tipologiaHeader}</label>
							<div class="controls">
								<springform:select path="tipoProvvedimento" id="tipologia" cssClass="input-xlarge" >
									<springform:options items="${listaTipologia}" itemValue="id" itemLabel="descrizione" />
								</springform:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="fonteNormativa">${fonteNormativaHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.fonteNormativa}</span> --%>
								<springform:input path="fonteNormativa" id="fonteNormativa" cssClass="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tipoAtto">${tipoAttoHeader}</label>
							<div class="controls">
								<springform:select path="tipoAtto" id="tipoAtto" cssClass="input-xlarge" >
									<springform:options items="${listaTipoAtto}" itemValue="id" itemLabel="descrizione" />
								</springform:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="dataAtto" >${dataAttoHeader}</label>
							<div class="controls">
								<springform:input type="text" id="dataAttov" path="dataAtto" class="input-xlarge" />&nbsp;<i class="icon-calendar icon-large" id="dataAtto"></i>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="numeroAtto" >${numeroAttoHeader}</label>
							<div class="controls">
								<springform:input path="numeroAtto" id="numeroAtto" cssClass="input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="articolo" >${artHeader}</label>
							<div class="controls">
								<springform:input path="articolo" id="articolo" cssClass="input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="comma">${commaHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.comma}</span> --%>
								<springform:input path="comma" id="comma" cssClass="input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="collNormattiva">${collNormattivaHeader}</label>
							<div class="controls">
								<springform:input path="collNormattiva" id="collNormattiva" cssClass="input-large"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tipoProvvDaAdottare">${provvDaAdottareHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.tipoProvvDaAdottare.descrizione}</span> --%>
								<springform:select path="tipoProvvDaAdottare" id="tipoProvvDaAdottare" cssClass="input-xlarge" >
									<springform:options items="${listaTipoProvvDaAdottare}" itemValue="id" itemLabel="descrizione" />
								</springform:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="titoloOggetto">${titoloOggettoHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.oggetto}</span> --%>
								<springform:input path="oggetto" id="titoloOggetto" cssClass="input-xlarge"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="dp1v">${termineDiScadenzaHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.termineScadenza}</span> --%>
								<springform:input type="text" id="dp1v" path="termineScadenza" class="input-xlarge" />&nbsp;<i class="icon-calendar icon-large" id="dp1"></i>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="statoDiAttuazione">${statoDiAttuazioneHeader}</label>
							<div class="controls">
								<springform:select path="stato" id="statoDiAttuazione" cssClass="input-xlarge" >
									<springform:options items="${listaStatoDiAttuazione}" itemValue="id" itemLabel="descrizione" />
								</springform:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="enteCapofila">${capofilaHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.organoCapofila.denominazione}</span> --%>
								<springform:select path="organoCapofila" id="enteCapofila" cssClass="input-xlarge" >
									<springform:options items="${listaOrganoCapofila}" itemValue="id" itemLabel="denominazione" />
								</springform:select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="parere">${parereHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.parere}</span> --%>
								<springform:textarea path="parere" class="input-xlarge" id="parere" cols="30" rows="4" />
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
							<display:column title="${descrizioneHeader}" headerScope="col" class="vcenter">
								<spring:url value="/private/ricercaProv/downloadAllegato/${allegato.id}" var="urlDownload" />
								<a href="${urlDownload}" class="download">${allegato.descrizione}</a>
							</display:column>
							<display:column title="${dimensioneHeader}" headerScope="col">
								<c:out value="${allegato.dimensioneAsText}"></c:out>
							</display:column>
							<display:column title="${eliminaHeader}" headerScope="col" class="vcenter center">
								<a href="javascript:void(0)" id="eliminaAllegato" ><i class="icon-trash icon-large" title="Elimina allegato"></i></a>
							</display:column>
					</display:table>
				</div>
			</div>
			<springform:form cssClass="form-horizontal" id="allegatoForm" name="allegatoForm" action="#" method="POST" enctype="multipart/form-data">
				<div class="row">
					<div class="span12">	
						<div class="control-group">
							<label class="control-label" for="allegato">File da allegare</label>
							<div class="controls">
								<input type="file" name="allegatoProvvedimento" style="display:none;" id="allegatoProvvedimento" />
								<input type="text" name="textAllegato" id="textAllegato" class="input-xlarge" />
								<button type="button" onclick="$('#allegatoProvvedimento').click();" class="btn">Sfoglia</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="descrizione">Descrizione</label>
							<div class="controls">
								<input type="text" id="descrizioneAllegato" name="descrizioneAllegato" class="input-xxlarge" />
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="button" id="allegatoInserisci" class="btn">
									Aggiungi <i class="icon-plus"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</springform:form>
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
	
								<display:column title="${organoHeader}" property="organo.denominazione" headerScope="col" headerClass="medium" />
								<display:column title="${presaInCaricoHeader}"  headerScope="col" class="vcenter center">
									<c:choose>
									      <c:when test="${assegnazione.stato.codice eq 'ASS'}">
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
										<div><a href="${urlDownload}" class="download">${allegato.descrizione}</a> (${allegato.dimensione} Kb)</div>
									</c:forEach>
								</display:column>
								<display:column title="${noteHeader}" property="nota.testoAsText" headerScope="col" />
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
				<div class="control-group">
					<label class="control-label" for="enteAssegnatario">Nuovo assegnatario</label>
					<div class="controls">
						<select id="enteAssegnatario" class="span3">
									<option>Sceglierne uno...</option>  
									<option>Agenzia Entrate e Territorio                                                                                                 </option>
									<option>Agenzia Dogane e Monopoli                                                                                                    </option>
									<option>Agenzia Entrate                                                                                                              </option>
									<option>Dipartimento finanze (DLTFF)                                                                                                 </option>
									<option>Dip.to Tesoro                                                                                                                </option>
									<option>Guardia di finanza                                                                                                           </option>
									<option>Ragioneria Generale dello Stato                                                                                              </option>
						</select>
						<button type="button" id="insertEnte" class="btn">Aggiungi &nbsp;<i class="icon-plus"></i></button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span12">
					<div class="form-horizontal">
						<div class="control-group">
							<div class="form-actions pull-right">
								<button type="submit" class="btn btn-primary" id="aggiornaProvvedimento" value="Salva">Salva &nbsp;<i class="icon-save"></i></button>
								<button type="button" class="btn" id="annulla" name="action" value="Annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
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