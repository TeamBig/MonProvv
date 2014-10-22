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
							<label class="control-label" for="articolo" >${artHeader}</label>
							<div class="controls">
								<%-- <span>${provvedimentoDettaglio.articolo}</span> --%>
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
									<a href="${urlDownload}" class="download">${allegato.nomefile}</a>
								</display:column>
								<display:column title="${dimensioneHeader}" headerScope="col">
									<c:out value="${allegato.dimensione} Kb"></c:out>
								</display:column>
						</display:table>
					</div>
				</div>
			</c:if>
			<springform:form cssClass="form-horizontal" id="allegatoForm" name="allegatoForm" action="#" method="POST">
				<div class="row">
					<div class="span12">	
						<div class="control-group">
							<label class="control-label" for="allegato">File da allegare</label>
							<div class="controls">
								<input type="file" name="file" style="display:none;" id="allegatoProvvedimento" />
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
					<table class="table table-hover table-bordered">
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
					</table>
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
								<button type="submit" class="btn" id="modifica">Modifica &nbsp;<i class="icon-edit"></i></button>
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