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
<spring:message var="statoDiAttuazioneHeader" code="listaProvvedimenti.header.statoDiAttuazione" />
<spring:message var="capofilaHeader" code="listaProvvedimenti.header.capofila" />
<spring:message var="proponenteHeader" code="listaProvvedimenti.header.proponente" />
<spring:message var="assegnazioneHeader" code="listaProvvedimenti.header.assegnazione" />
<spring:message var="allegatiHeader" code="listaProvvedimenti.header.allegati" />
<spring:message var="tipoAttoHeader" code="listaProvvedimenti.header.tipoAtto" />
<spring:message var="dataAttoHeader" code="listaProvvedimenti.header.dataAtto" />
<spring:message var="numeroAttoHeader" code="listaProvvedimenti.header.numeroAtto" />
<spring:message var="collNormattivaHeader" code="listaProvvedimenti.header.collNormattiva" />
<spring:message var="organoHeader" code="listaAssegnatari.header.organo" />
<spring:message var="eliminaHeader" code="listaAssegnatari.header.elimina" />
<spring:message var="termineDiScadenzaHeader" code="label.termineDiScadenza" />
<spring:message var="parereHeader" code="label.parere" />
<spring:message var="descrizioneHeader" code="listaAllegati.header.descrizione" />
<spring:message var="dimensioneHeader" code="listaAllegati.header.dimensione" />

<c:url value="/private/provvedimenti/ricerca/noteAllegatiProv/inserisciAllegato" var="formAllegatiPath" />
<c:url value="/private/provvedimenti/ricerca/nuovo" var="formPath" />

	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span><c:out value="${titolo}" /></span></h3>
			</div>
		</div>
		<springform:form action="${formPath}" modelAttribute="provvedimentoInserisci" name="provvedimentoInserisci" commandName="provvedimentoInserisci" cssClass="form-horizontal" method="POST" enctype="multipart/form-data">
		<springform:hidden path="collNormattiva" id="collNormattiva" cssClass="input-xxlarge" />
		<springform:hidden path="currentStep" name="currentStep"/>
		<springform:hidden path="stepSuccessivo" name="stepSuccessivo"/>
		<c:if test="${currentStep eq 1}">
		<!-- STEP 1  -->
			<div class="row">
				<div class="span10 offset2">
					<div class="control-group">
						<springform:label path="tipoGoverno" cssClass="control-label" for="governo"><spring:message code="label.tipoGoverno"/></springform:label>
						<div class="controls">
							<springform:select path="tipoGoverno" id="governo" cssClass="input-xlarge" >
								<springform:option value="">Sceglierne uno...</springform:option>
								<springform:options items="${listaGoverno}" itemValue="id" itemLabel="denominazione" />
							</springform:select>
						</div>
					</div>
					<div class="control-group">
						<springform:label path="tipologia" cssClass="control-label" for="tipologia"><spring:message code="label.tipologia"/></springform:label>
						<div class="controls">
							<springform:select path="tipologia" id="tipologia" cssClass="input-xlarge" >
								<springform:option value="">Sceglierne uno...</springform:option>
								<springform:options items="${listaTipologia}" itemValue="id" itemLabel="descrizione" />
							</springform:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="tipoAtto">${tipoAttoHeader}</label>
						<div class="controls">
							<springform:select path="tipoAtto" id="tipoAtto" cssClass="input-xlarge" >
								<springform:option value="">Sceglierne uno...</springform:option>
								<springform:options items="${listaTipoAtto}" itemValue="id" itemLabel="descrizione" />
							</springform:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="dataAtto" >${dataAttoHeader}</label>
						<div class="controls">
							<springform:input type="text" id="dataAttoV" path="dataAtto" class="input-xlarge" />&nbsp;<i class="icon-calendar icon-large" id="dataAtto"></i>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="numeroAtto" >${numeroAttoHeader}</label>
						<div class="controls">
							<springform:input path="numeroAtto" id="numeroAtto" cssClass="input-small"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="art">Art.</label>
						<div class="controls">
							<springform:input path="art" cssClass="input-small"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="comma">Comma</label>
						<div class="controls">
							<springform:input path="comma" cssClass="input-small"/>
						</div>
					</div>
					<div class="control-group">
						<%-- <label class="control-label" for="collNormattiva">${collNormattivaHeader}</label> --%>
						<span class="control-label" >${collNormattivaHeader}</span>
						<div class="controls">
							<span id="linkNormattivaSpan"></span>
						</div>
					</div>
					<div class="control-group">
						<springform:label path="tipoProvvDaAdottare" cssClass="control-label" for="tipoProvvDaAdottare"><spring:message code="label.provvDaAdottare"/></springform:label>
						<div class="controls">
							<springform:select path="tipoProvvDaAdottare" id="tipoProvvDaAdottare" cssClass="input-xlarge" >
								<springform:option value="">Sceglierne uno...</springform:option>
								<springform:options items="${listaTipoProvvDaAdottare}" itemValue="id" itemLabel="descrizione" />
							</springform:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="titoloOggetto">Titolo /
							Oggetto</label>
						<div class="controls">
							<springform:input path="titoloOggetto" cssClass="input-xlarge"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="dp1v">Termine di
							scadenza</label>
						<div class="controls form-inline">
							<springform:input type="text" id="dp1v" path="dtTermineScadenza" class="input-xlarge" />&nbsp;<i class="icon-calendar icon-large" id="dp1"></i>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="statoDiAttuazione">Stato
							di attuazione</label>
						<div class="controls">
							<span>Inserito</span>
						</div>
					</div>
					<div class="control-group" id="proponenteDiv">
						<label class="control-label" for="proponente">${proponenteHeader}</label>
						<div class="controls">
							<springform:select path="proponente" id="proponente" cssClass="input-xlarge" >
								<springform:options items="${listaProponente}" itemValue="id" itemLabel="denominazione" />
							</springform:select>
						</div>
					</div>
 					<div class="control-group">
						<label class="control-label" for="parere">${parereHeader}</label>
						<div class="controls">
							<springform:textarea path="parere" class="input-xlarge" id="parere" cols="30" rows="4" />
						</div>
					</div>
				</div>
			</div>

			<!-- Allegati insert -->
			<div class="row">
				<div class="span12">
					<h3 class="text-left underline">
						<span>Allegati</span>
					</h3>
			
					<display:table
						name="${listaAllegati}" 
						requestURI="" sort="external" partialList="false"
						id="allegato" 
						class="table table-hover table-bordered"
						summary="Elenco Allegati" 
						style="width: 100%">
		
						<display:column title="${idHeader}" property="id" headerScope="col" />
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
							<a href="javascript:void(0)" id="eliminaAllegato" ><i class="icon-trash icon-large gray" title="Elimina allegato"></i></a>
						</display:column>
					</display:table>
				</div>
			</div>
  			<div class="progress progress-striped active">
				<div class="bar" style="width: 0%;">
					<div class="percent">0%</div >
				</div>
			</div>
			<div class="row">
				<div class="span12">	
					<div id="allegatoForm">
						<springform:hidden path="idAllegatiUpdList" id="idAllegatiUpdList" />
						<%-- <springform:hidden path="idAllegatiDelList" id="idAllegatiDelList" /> --%>
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
								<button type="button" id="allegatoInserisciIns" class="btn">Aggiungi <i class="icon-plus"></i></button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- allegati end -->
			<!-- FINE STEP 1  -->
			</c:if>
			<c:if test="${currentStep eq 2}">
			<!-- STEP 2  -->
			<div class="row">
				<div class="span10 offset2">
						<div class="control-group">
						<label class="control-label" for="organoCapofila">${capofilaHeader}</label>
						<div class="controls">
							<springform:select path="organoCapofila" id="organoCapofila" cssClass="input-xlarge" >
								<springform:options items="${listaOrganoCapofila}" itemValue="id" itemLabel="denominazione" />
							</springform:select>
						</div>
						</div>
				</div>
			</div>			
			<!-- FINE STEP 2  -->
			</c:if>
			<!-- STEP 3  -->
			<c:if test="${currentStep eq 3}">
			<springform:hidden path="idAssegnatariUpdList" id="idAssegnatariUpdList" />
			<div class="row">
				<div class="span12">
						<display:table 	name="${listaAssegnazione}" 
											requestURI="" sort="external" partialList="false"
											 id="assegnazione" 
											class="table table-hover table-bordered"
											summary="Elenco Assegnatari" style="width: 100%">
	
								<display:column title="${idHeader}" property="id" headerClass="hidden" headerScope="col" class="hidden" />
								<display:column title="${organoHeader}" property="organo.denominazione" headerScope="col" />
								<display:column title="${eliminaHeader}"  headerScope="col" headerClass="medium center" class="medium vcenter center">
									<i class="icon-trash icon-large" title="Elimina assegnazione"></i>
								</display:column>
						</display:table>
				</div>
				<div id="assegnazioneForm">
<%--  				<springform:form cssClass="form-horizontal" commandName="assegnatarioNew" id="assegnazioneForm" name="assegnazioneForm" action="#" method="GET"> --%>
					<div class="control-group">
						<label class="control-label" for="organo">Nuovo assegnatario</label>
						<div class="controls">
							<springform:select path="assegnatario" id="assegnatario" cssClass="input-xlarge" >
								<springform:options items="${listaOrgani}" itemValue="id" itemLabel="denominazione" />
							</springform:select>
							<button type="button" id="insertAssegnatarioFromInserimento" class="btn">Aggiungi &nbsp;<i class="icon-plus"></i></button>
						</div>
					</div>
				</div>
<%-- 				</springform:form> --%>
			</div>	
			</c:if>
			<!-- FINE STEP 3 -->
			<!-- STEP 4  -->
			<c:if test="${currentStep eq 4}">
			<div class="row">
				<div class="span12">
					<div class="span2 offset2">
<%-- 						<springform:select path="provvedimentiSelected" id="provvedimentiSelected" cssClass="input-xlarge" multiple="multiple">
							<springform:options items="${listaProvvedimenti}" itemValue="id" itemLabel="oggetto" />
						</springform:select> --%>
						<springform:select path="provvedimentiSelected" id="custom-headers" cssClass="input-xlarge" multiple="multiple">
							<springform:options items="${listaProvvedimenti}" itemValue="id" itemLabel="oggetto" />
						</springform:select>
					</div>
				</div>
			</div>
			<!-- FINE STEP 4 -->
			</c:if>
			<div class="row">
				<div class="span12">
					<div class="form-horizontal">
						<div class="control-group">
							<div class="form-actions pull-right">
								<c:if test="${currentStep eq 1}">
									<button type="submit" class="btn" id="annulla" name="indietroPagina">Annulla &nbsp;<i class="icon-undo"></i></button>
								</c:if>
								<c:if test="${currentStep eq 2 || currentStep eq 3 || currentStep eq 4}">
									<button type="submit" class="btn" name="action" value="Indietro" id="indietro">Indietro &nbsp;<i class="icon-arrow-left"></i></button>
								</c:if>
								<c:if test="${currentStep eq 1 || currentStep eq 2 || currentStep eq 3}">
									<button type="submit" class="btn btn-primary" name="action" value="Avanti" id="avantiStep">Avanti &nbsp;<i class="icon-arrow-right"></i></button>
								</c:if>
								<c:if test="${currentStep eq 4}">
									<button type="submit" class="btn btn-primary" name="action" value="Salva" id="salva">Inserisci provvedimento &nbsp;<i class="icon-save"></i></button>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</springform:form>
	</div>