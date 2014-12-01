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
<spring:message var="senzaTermineHeader" code="label.senza.termine" />


	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span>Modifica Provvedimento</span></h3>
			</div>
		</div>
		
		<c:url value="/private/provvedimenti/ricerca/modifica" var="formPath" />
		
		<springform:form action="${formPath}" modelAttribute="provvedimentoModifica" commandName="provvedimentoModifica" cssClass="form-horizontal" method="POST">
		
			<springform:hidden path="id" id="idProvvedimento" />
			<springform:hidden path="versione" id="versioneProvvedimento" />
			
			<springform:hidden path="idAllegatiUpdList" id="idAllegatiUpdList" />
			<springform:hidden path="idAllegatiDelList" id="idAllegatiDelList" />
					
			<div class="row">
				<div class="offset0 text-left">
					<p>&nbsp;(<sup id="n2">*</sup>) <em>Campi obbligatori</em></p>
				</div>
				<div class="span10 offset2 dettaglio">
				
						<spring:bind path="provvedimentoModifica.governo" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="governo" cssClass="control-label" cssErrorClass="control-label text-error" for="governo">Governo <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<springform:select path="governo" id="governo" cssClass="input-xlarge" >
										<springform:option value="">Sceglierne uno...</springform:option>
										<springform:options items="${listaGoverno}" itemValue="id" itemLabel="denominazione" />
									</springform:select>
									<springform:errors path="governo" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.tipoProvvedimento" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="tipoProvvedimento" cssClass="control-label" cssErrorClass="control-label text-error" for="tipologia">${tipologiaHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<springform:select path="tipoProvvedimento" id="tipologia" cssClass="input-xlarge" >
										<springform:option value="">Sceglierne uno...</springform:option>
										<springform:options items="${listaTipologia}" itemValue="id" itemLabel="descrizione" />
									</springform:select>
									<springform:errors path="tipoProvvedimento" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<%-- 						
						<div class="control-group">
							<label class="control-label" for="fonteNormativa">${fonteNormativaHeader}</label>
							<div class="controls">
								<span>${provvedimentoDettaglio.fonteNormativa}</span>
								<springform:input path="fonteNormativa" id="fonteNormativa" cssClass="input-xlarge"/>
							</div>
						</div> 
						--%>
						
						<spring:bind path="provvedimentoModifica.tipoAtto" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="tipoAtto" cssClass="control-label" cssErrorClass="control-label text-error" for="tipoAtto">${tipoAttoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<springform:select path="tipoAtto" id="tipoAtto" cssClass="input-xlarge" >
										<springform:option value="">Sceglierne uno...</springform:option>
										<springform:options items="${listaTipoAtto}" itemValue="id" itemLabel="descrizione" />
									</springform:select>
									<springform:errors path="tipoAtto" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div> 
						</spring:bind>
						
						
						<c:set value="OK" var="erroreInDataAtto"  />
						<spring:bind path="provvedimentoModifica.dataAtto" >
							<c:if test="${(not empty status.errorMessage)}">
								<c:set value="ERRORE" var="erroreInDataAtto" />
							</c:if>
						</spring:bind>
						<spring:bind path="provvedimentoModifica.appoDataFormat4dataAtto" >
							<c:if test="${(not empty status.errorMessage)}">
								<c:set value="ERRORE" var="erroreInDataAtto" />
							</c:if>
						</spring:bind>
					
						<div class="control-group ${('ERRORE' == erroreInDataAtto) ? ' error':''}">
							<springform:label path="dataAtto" cssClass="control-label ${('ERRORE' == erroreInDataAtto) ? ' text-error':''}" cssErrorClass="control-label text-error" for="dataAttoV">${dataAttoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>	
							<div class="controls">
								<springform:input type="text" id="dataAttoV" path="dataAtto" class="input-xlarge dataValid" />&nbsp;<i class="icon-calendar icon-large" id="dataAtto"></i>
								<springform:errors path="dataAtto" cssClass="text-error help-inline"></springform:errors>
								<springform:errors path="appoDataFormat4dataAtto" cssClass="text-error help-inline"></springform:errors>
							</div>
						</div>
						
						<spring:bind path="provvedimentoModifica.numeroAtto" >
						<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
							<springform:label path="numeroAtto" cssClass="control-label" cssErrorClass="control-label text-error" for="numeroAtto">${numeroAttoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>							
							<div class="controls">
									<springform:input path="numeroAtto" id="numeroAtto" cssClass="input-small numberValid"/>
									<springform:errors path="numeroAtto" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.articolo" >
						<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
							<springform:label path="articolo" cssClass="control-label" cssErrorClass="control-label text-error" for="articolo">${artHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>							
								<div class="controls">
									<springform:input path="articolo" id="articolo" cssClass="input-small"/>
									<springform:errors path="articolo" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.comma" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="comma" cssClass="control-label" cssErrorClass="control-label text-error" for="comma">${commaHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>							
								<div class="controls">
									<%-- <span>${provvedimentoDettaglio.comma}</span> --%>
									<springform:input path="comma" id="comma" cssClass="input-small"/>
									<springform:errors path="comma" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.collNormattiva" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="collNormattiva" cssClass="control-label" cssErrorClass="control-label text-error" for="collNormattiva">${collNormattivaHeader}</springform:label>							
								<div class="controls">
									<springform:input path="collNormattiva" id="collNormattiva" cssClass="input-large"/>
									<springform:errors path="collNormattiva" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.tipoProvvDaAdottare" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="tipoProvvDaAdottare" cssClass="control-label" cssErrorClass="control-label text-error" for="tipoProvvDaAdottare">${provvDaAdottareHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<%-- <span>${provvedimentoDettaglio.tipoProvvDaAdottare.descrizione}</span> --%>
									<springform:select path="tipoProvvDaAdottare" id="tipoProvvDaAdottare" cssClass="input-xlarge" >
										<springform:option value="">Sceglierne uno...</springform:option>
										<springform:options items="${listaTipoProvvDaAdottare}" itemValue="id" itemLabel="descrizione" />
									</springform:select>
									<springform:errors path="tipoProvvDaAdottare" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.oggetto" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="oggetto" cssClass="control-label" cssErrorClass="control-label text-error" for="titoloOggetto">${titoloOggettoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<springform:textarea path="oggetto" class="input-xlarge" id="titoloOggetto" cols="30" rows="4" />
									<springform:errors path="oggetto" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="provvedimentoModifica.appoDataFormat4dtTermineScadenza" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="termineScadenza" cssClass="control-label" cssErrorClass="control-label text-error" for="dp1v">${termineDiScadenzaHeader}</springform:label>							
								<div class="controls form-inline">
									<springform:input type="text" id="dp1v" path="termineScadenza" class="input-xlarge dataValid" />&nbsp;<i class="icon-calendar icon-large" id="dp1"></i>&nbsp;
									<button type="button" class="btn" id="plus30">+ 30 gg</button>&nbsp;
									<button type="button" class="btn" id="plus60">+ 60 gg</button>&nbsp;
									<button type="button" class="btn" id="plus90">+ 90 gg</button>&nbsp;
									<button type="button" class="btn" id="plus120">+ 120 gg</button>
									<springform:errors path="appoDataFormat4dtTermineScadenza" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<div class="control-group">
							<label class="control-label" for="senzaTermine">${senzaTermineHeader}</label>
							<div class="controls">	
								<springform:checkbox path="senzaTermine" id="senzaTermine" value="S"/>	
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
						
						<div class="control-group" id="proponenteDiv">
							<label class="control-label" for="proponente">${proponenteHeader}</label>
							<div class="controls">
								<springform:select path="organoConcertante" id="proponente" cssClass="input-xlarge" >
									<springform:options items="${listaProponente}" itemValue="id" itemLabel="denominazione" />
								</springform:select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="parere">${parereHeader}</label>
							<div class="controls">
								<springform:select path="organoParere" id="parere" cssClass="input-xlarge" >
									<springform:option value="">Sceglierne uno...</springform:option>
									<springform:options items="${listaOrganoParere}" itemValue="id" itemLabel="denominazione" />
								</springform:select>
								<%-- <span>${provvedimentoDettaglio.parere}</span> --%>
								<%--<springform:textarea path="parere" class="input-xlarge" id="parere" cols="30" rows="4" />--%>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="noteInterne">${noteInterneHeader}</label>
							<div class="controls">
								<springform:textarea path="noteInterne" class="input-xlarge" id="noteInterne" cols="30" rows="4" />
							</div>
						</div>
						
				</div>
			</div>
			<div class="span2 offset2">
				<springform:select path="provvedimentiParentSel" id="custom-headers" cssClass="input-xlarge" multiple="multiple">
					<springform:options items="${listaProvvedimenti}" itemValue="id" itemLabel="oggetto" />
				</springform:select>
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

							<display:column title="${idHeader}" property="id" headerScope="col" class="hidden" headerClass="hidden" />
							<display:column title="${descrizioneHeader}" headerScope="col" class="vcenter">
								<spring:url value="/private/provvedimenti/ricerca/downloadAllegato?id=${allegato.id}" var="urlDownload" />
								<a href="${urlDownload}" class="download">${allegato.descrizione}</a>
							</display:column>
							<display:column title="${dimensioneHeader}" headerScope="col">
								<c:if test="${not empty allegato.dimensione }">
									<spring:eval expression="T(it.tesoro.monprovv.utils.StringUtils).convertBytesToKb(${allegato.dimensione},true)"/>
								</c:if>
							</display:column>
							<display:column title="${eliminaHeader}" headerScope="col" class="vcenter center">
								<a href="javascript:void(0)" id="eliminaAllegato" ><i class="icon-trash icon-large" title="Elimina allegato"></i></a>
							</display:column>
					</display:table>
				</div>
			</div>
			
			<c:url value="/private/provvedimenti/ricerca/modifica/inserisciAllegato" var="formAllegatiPath" />

			<springform:form cssClass="form-horizontal" id="allegatoForm" name="allegatoForm" action="${formAllegatiPath}" method="POST" enctype="multipart/form-data">
		
		 		<div class="progress progress-striped active">
					<div class="bar" style="width: 0%;">
						<div class="percent">0%</div >
					</div>
				</div>
				
				<input type="hidden" name="idProvvedimento" id="idProvvedimentoAllegato" />
				<div class="row">
					<div class="span12">
		
						<div class="control-group">		
							<label class="control-label" for="allegato">File da allegare</label>
							<div class="controls">
								<input type="text" class="form-control input-xlarge" readonly="readonly" name="textAllegato" id="textAllegato">
								<span class="input-group-btn"> 
									<span class="btn btn-file"> Browse...<input type="file" name="allegatoProvvedimento" id="allegatoProvvedimento">
									</span>
								</span> 
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
								<button type="button" id="allegatoInserisci" class="btn">Aggiungi <i class="icon-plus"></i></button>
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
	
								<display:column title="${idHeader}" property="id" headerScope="col" class="hidden" headerClass="hidden" />
								<display:column title="${organoHeader}" property="organo.denominazione" headerScope="col" class="medium" headerClass="medium" />
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
										<spring:url value="/private/provvedimenti/ricerca/downloadAllegato/${allegato.id}" var="urlDownload" />
										<div><a href="${urlDownload}" class="download">${allegato.descrizione}</a>
											<c:if test="${not empty allegato.dimensione }">
												(<spring:eval expression="T(it.tesoro.monprovv.utils.StringUtils).convertBytesToKb(${allegato.dimensione},true)"/>)
											</c:if> 
										</div>
									</c:forEach>
								</display:column>
								<display:column title="${noteHeader}" property="nota.testoAsText" headerClass="medium"  headerScope="col" />
								<display:column title="${cronologiaModificheHeader}" headerScope="col" class="vcenter center">
									<spring:url value="dettaglio/modalCronologia?id=${assegnazione.id}" var="urlModale" />
									<a href="${urlModale}" role="button" data-toggle="modal" data-target="#modalCronologia"><i class="icon-time icon-large"></i></a>
								</display:column>
								<display:column title="${eliminaHeader}"  headerScope="col" headerClass="center" class="vcenter center">
									<a href="javascript:void(0)" id="eliminaAssegnatario" ><i class="icon-trash icon-large" title="Elimina assegnazione"></i></a>
								</display:column>
						</display:table>
				</div>
				<springform:form cssClass="form-horizontal" commandName="assegnatarioNew" id="assegnazioneForm" name="assegnazioneForm" action="#" method="GET">
					<springform:hidden path="provvedimento.id" id="idProvvedimento"/>
					<div class="control-group">
						<label class="control-label" for="organo">Nuovo assegnatario</label>
						<div class="controls">
							<springform:select path="organo" id="organo" cssClass="input-xlarge" >
								<springform:options items="${listaOrganiAssegnatari}" itemValue="id" itemLabel="denominazione" />
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
								<button type="submit" class="btn btn-primary" id="aggiornaProvvedimento" value="Salva">Salva &nbsp;<i class="icon-save"></i></button>
								<button type="button" class="btn" id="annullaIndietroDettaglio" name="action" value="Annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
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
			<i class="fa fa-spinner fa-spin"></i>
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
