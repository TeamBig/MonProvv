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

<spring:message var="termineDiScadenzaHeader" code="label.termineDiScadenza" />
<spring:message var="parereHeader" code="label.parere" />


<c:url value="/private/ricercaProv/nuovo/step/" var="formPath" />

	<div class="container inserimento">
		<div class="row">
			<div class="span12">
				<h3 class="text-left underline"><span><c:out value="${titolo}" /></span></h3>
			</div>
		</div>
		<springform:form action="${formPath}" modelAttribute="provvedimentoInserisci" commandName="provvedimentoInserisci" cssClass="form-horizontal" method="POST">
		<c:if test="${step eq 1}">
		<!-- STEP 1  -->
			<div class="row">
				<div class="span10 offset2">
					<div class="control-group">
						<springform:label path="tipoGoverno" cssClass="control-label" for="governo"><spring:message code="label.tipoGoverno"/></springform:label>
						<div class="controls">
							<springform:select path="tipoGoverno" id="governo" cssClass="input-xlarge" >
								<springform:option value="">Tutti</springform:option>
								<springform:options items="${listaGoverno}" itemValue="id" itemLabel="denominazione" />
							</springform:select>
						</div>
					</div>
					<div class="control-group">
						<springform:label path="tipologia" cssClass="control-label" for="tipologia"><spring:message code="label.tipologia"/></springform:label>
						<div class="controls">
							<springform:select path="tipologia" id="tipologia" cssClass="input-xlarge" >
								<springform:option value="">Tutti</springform:option>
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
						<springform:label path="tipoProvvDaAdottare" cssClass="control-label" for="tipoProvvDaAdottare"><spring:message code="label.provvDaAdottare"/></springform:label>
						<div class="controls">
							<springform:select path="tipoProvvDaAdottare" id="tipoProvvDaAdottare" cssClass="input-xlarge" >
								<springform:option value="">Tutti</springform:option>
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
						<label class="control-label" for="dataDa">Termine di
							scadenza</label>
						<div class="controls form-inline">
							<label for="dp1v">Da&nbsp;</label> <input type="text" id="dp1v"
								class="input-small" maxlength="10" placeholder="GG/MM/AAAAA">&nbsp;
							<i class="icon-calendar icon-large" id="dp1"></i> <label
								for="dp2v">&nbsp;&nbsp;A&nbsp;</label> 
								<springform:input path="dtTermineScadenzaA" id="dp2v" cssClass="input-small" placeholder="GG/MM/AAAAA"/>&nbsp;<i
								class="icon-calendar icon-large" id="dp2"></i>
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
				</div>
			</div>
			<div class="row">
				<div class="span12">	
					<table class="table table-hover table-bordered" style="width: 100%">
						<thead>
							<tr>
								<th>Id</th>
								<th>Descrizione</th>
								<th>Dimensione</th>
								<th class="center">Elimina</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									Nessun allegato aggiunto.
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="span12">	
					<div class="control-group">
						<label class="control-label" for="allegato">File da allegare</label>
						<div class="controls">
							<input type="text" id="allegato" class="input-xlarge" />
							<button type="button" id="insertEnte" class="btn">Sfoglia</button>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="descrizione">Descrizione</label>
						<div class="controls">
							<input type="text" id="allegato" class="input-xxlarge" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="button" id="insertEnte" class="btn">
								Aggiungi <i class="icon-plus"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- allegati end -->
			<!-- FINE STEP 1  -->
			</c:if>
			<c:if test="${step eq 2}">
			<!-- STEP 2  -->
			<div class="row">
				<div class="span10 offset2">
						<div class="control-group">
							<label class="control-label" for="enteCapofila">Capofila (DD)</label>
							<div class="controls">
								<select id="enteCapofila" class="input-xlarge">
									<option>Sceglierne uno...</option>  
									<option>Agenzia Entrate e Territorio                                                                                                 </option>
									<option>Agenzia Dogane e Monopoli                                                                                                    </option>
									<option>Agenzia Entrate                                                                                                              </option>
									<option>Dipartimento finanze (DLTFF)                                                                                                 </option>
									<option>Dip.to Tesoro                                                                                                                </option>
									<option>Guardia di finanza                                                                                                           </option>
									<option>Ragioneria Generale dello Stato                                                                                              </option>
								</select>
							</div>
						</div>
				</div>
			</div>			
			<!-- FINE STEP 2  -->
			</c:if>
			<!-- STEP 3  -->
			<c:if test="${step eq 3}">
			<div class="row">
				<div class="span12">
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th style="width: 80%">
									Organo
								</th>
								<th class="center">
									Elimina
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="assegnatarioDipTesoro">
								<td style="width: 80%">
									Dip.to Tesoro
								</td>			
								
								<td class="vcenter center">
									<a href="#" id="deleteEnte"><i class="icon-trash icon-large"></i></a>
								</td>
							</tr>
							<tr id="noRecord">
								<td colspan="2" class="vcenter">
									Nessun record trovato.
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="enteAssegnatario">Nuova assegnazione</label>
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
			</div>
			<!-- FINE STEP 3 -->
			</c:if>
			<div class="row">
				<div class="span12">
					<div class="form-horizontal">
						<div class="control-group">
							<div class="form-actions pull-right">
								<button type="button" class="btn" id="annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
								<button type="submit" class="btn btn-primary" id="avantiStep2">Avanti &nbsp;<i class="icon-arrow-right"></i></button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</springform:form>
	</div>