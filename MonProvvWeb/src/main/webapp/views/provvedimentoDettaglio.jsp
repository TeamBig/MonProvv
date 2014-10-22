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
								<select id="statoDiAttuazioneDettaglio" class="input-xlarge">
									<option>Inserito</option>
									<option>Sospeso</option>
									<option selected>Fine lavorazione</option>
									<option>Chiusura lavori</option>
									<option>Adottato</option>
									<option>Non attuabile</option>
									<option>Superato</option>
								</select>
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
			<!-- Allegati insert -->
			<c:if test="${tableAllegatiSize gt 0}">
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
									<c:out value="${allegato.dimensione} Kb"></c:out>
								</display:column>
						</display:table>
					</div>
				</div>
			</c:if>
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
	
								<display:column title="${organoHeader}" property="organo.denominazione" headerScope="col" class="medium" />
								<display:column title="${presaInCaricoHeader}"  headerScope="col" class="vcenter center">
									<c:choose>
									      <c:when test="${assegnazione.stato.codice=='ASS'}">
									      	<i class="icon-check icon-large"></i>
									      </c:when>
										  <c:when test="${assegnazione.stato.codice=='RIF'}">
									      	<a href="#" id="popoverRifiuto"><i class="icon-remove-sign icon-large" title="Assegnazione rifiutata"></i>&nbsp;Motivazione rifiuto</a>
									      </c:when>
									</c:choose>
								</display:column>
								<display:column title="${allegatiHeader}" headerScope="col">
									<c:forEach var="allegato" items="${assegnazione.allegatoList}">
										<spring:url value="/private/ricercaProv/downloadAllegato/${allegato.id}" var="urlDownload" />
										<div><a href="${urlDownload}" class="download">${allegato.descrizione}</a> (${allegato.dimensione} Kb)</div>
									</c:forEach>
								</display:column>
								<display:column title="${noteHeader}"  headerScope="col" />
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
<!-- 					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th class="medium">
									Organo
								</th>
								<th>
									Presa in carico
								</th>
								<th class="medium">
									Allegati
								</th>
								<th>
									Note
								</th>
								<th>
									Cronologia Modifiche
								</th>
								<th class="center">
									Elimina
								</th>								
								<th class="center">
									Sollecito
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="vcenter">
									Ag. Territorio 
								</td>
								<td class="vcenter center">
									<i class="icon-check icon-large"></i>
								</td>
								<td>
									<a href="" class="download">Documento 1</a> (PDF - 1Mb)<br>
									<a href="" class="download">Documento 2</a> (Excel - 350Kb)<br>
									<a href="" class="download">Documento 3</a> (Testo - 1Kb) <br>
								</td>
								<td>
									Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
								</td>
								<td class="vcenter center">
									<a href="#modalCronologia" role="button" data-toggle="modal"><i class="icon-time icon-large"></i></a>
								</td>	
								<td class="vcenter center">
									<i class="icon-trash icon-large gray"></i>
								</td>
								<td class="vcenter center">
									<a href="#modalSollecito" role="button" data-toggle="modal"><i class="icon-envelope-alt icon-large" title="Invio sollecito"></i></a>
								</td>
							</tr>
 							<tr>
								<td>
									MEF (Dipto finanze)  
								</td>
								<td class="vcenter center">
									<a href="#" id="popoverRifiuto"><i class="icon-remove-sign icon-large" title="Assegnazione rifiutata"></i>&nbsp;Motivazione rifiuto</a>
								</td>
								<td>
								</td>
								<td>
									
								</td>
								<td class="vcenter center">
									<a href="#modalCronologia" data-toggle="modal"><i class="icon-time icon-large" title="Apri cronologia"></i></a>
								</td>	
								<td class="vcenter center">
									<i class="icon-trash icon-large" title="Elimina assegnazione"></i>
								</td>
								<td class="vcenter center">
									<a href="#modalSollecito" role="button" data-toggle="modal"><i class="icon-envelope-alt icon-large" title="Invio sollecito"></i></a>
								</td>															
							</tr>
							<tr id="assegnatarioDipTesoro">
								<td class="vcenter">
									Dip.to Tesoro
								</td>
								<td class="vcenter">
									
								</td>
								<td class="vcenter">
									
								</td>
								<td>
									
								</td>
								<td class="vcenter center">
								</td>									
								<td class="vcenter center">
									<a href="#" id="deleteEnte"><i class="icon-trash icon-large gray"></i></a>
								</td>
								<td>
								</td>
							</tr>
						</tbody>
					</table> -->
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
								<button type="submit" class="btn btn-primary" id="salva">Salva &nbsp;<i class="icon-save"></i></button>
								<button type="button" class="btn" id="annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
								<springform:button type="submit" class="btn" id="modifica" name="action" value="Modifica">Modifica &nbsp;<i class="icon-edit"></i></springform:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</springform:form>
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