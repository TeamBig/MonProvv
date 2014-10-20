<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

<div class="container collapse" id="campiRicerca">
	<springform:form modelAttribute="ricercaProvvedimenti" cssClass="bo clfix" action="#" method="POST">
		<div class="row">
			<div class="span12">
				<h3 class="underline">
					<span>Ricerca provvedimento</span>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="span6">
				<div class="form-horizontal">
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
						<label class="control-label" for="art">Art.</label>
						<div class="controls">
							<springform:input path="art" cssClass="input-small"/>
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
						<div class="controls">
							<h5>
								<a data-toggle="collapse" data-target="#ricercaAvanzata"
									id="btnRicercaAvanzata"> Ricerca avanzata <i
									class="icon-caret-down icon-large" id="btnRicAvDown"></i> <i
									class="icon-caret-up icon-large" id="btnRicAvUp"></i>
								</a>
							</h5>
						</div>
					</div>

				</div>
			</div>
			<div class="span6">
				<div class="form-horizontal">
					<div class="control-group">
						<springform:label path="tipologia" cssClass="control-label" for="tipologia"><spring:message code="label.tipologia"/></springform:label>
						<div class="controls">
							<springform:select path="tipologia" id="tipologia" cssClass="input-xlarge" >
								<springform:option value="">Tutti</springform:option>
								<springform:options items="${listaTipologia}" itemValue="codice" itemLabel="descrizione" />
							</springform:select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="comma">Comma</label>
						<div class="controls">
							<springform:input path="comma" cssClass="input-small"/>
						</div>
					</div>
					<div class="control-group">
						<springform:label path="statoDiAttuazione" cssClass="control-label" for="statoDiAttuazione"><spring:message code="label.statoAttuazione"/></springform:label>
						<div class="controls">
							<springform:select path="statoDiAttuazione"  id="statoDiAttuazione" cssClass="input-xlarge" >
								<springform:option value="">Tutti</springform:option>
								<springform:options items="${listaStatoDiAttuazione}" itemValue="codice" itemLabel="descrizione" />
							</springform:select>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row collapse" id="ricercaAvanzata">
			<div class="span6">
				<div class="form-horizontal">
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
						<label class="control-label" for="inputFonte">Fonte
							normativa</label>
						<div class="controls">
							<springform:input path="fonteNormativa" cssClass="input-xlarge"/>
						</div>
					</div>

				</div>
			</div>
			<div class="span6">
				<div class="form-horizontal">

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
						<label class="control-label" for="enteAssegnatario">Amm./uffici
							coinvolti</label>
						<div class="controls">
							<select id="enteAssegnatario" class="input-xlarge multiselect"
								multiple="multiple">
								<option>Tutti</option>
								<option>Agenzia Entrate e Territorio</option>
								<option>Agenzia Dogane e Monopoli</option>
								<option>Agenzia Entrate</option>
								<option>Dipartimento finanze (DLTFF)</option>
								<option>Dip.to Tesoro</option>
								<option>Guardia di finanza</option>
								<option>Ragioneria Generale dello Stato</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<div class="form-actions pull-right">
							<button type="submit" class="btn " id="ricerca" name="ricerca" value="OK">
								Ricerca &nbsp;<i class="icon-search"></i>
							</button>
							<button type="button" class="btn " id="annulla">
								Pulisci &nbsp;<i class="icon-eraser"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</springform:form>
</div>
<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">
			<h3 class="text-left underline">
				<span>Elenco provvedimenti</span>
				<button type="submit" class="btn btn-primary pull-right" id="nuovo">
					Nuovo provvedimento &nbsp;<i class="icon-plus"></i>
				</button>
				<button class="btn pull-right" id="toggleRicerca"
					data-toggle="collapse" data-target="#campiRicerca"
					style="margin-right: 10px;">
					Toggle ricerca &nbsp;<i class="icon-search"></i>
				</button>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="span12">
		
			<c:if test="${tableProvvedimentiSize gt 0}">
				<div class="row">
					<div class="span12">
						
						<display:table 	name="${listaProvvedimenti}" 
										pagesize="${risultatiPerPagina}" 
										requestURI="" sort="external" partialList="true"
										size="${tableProvvedimentiSize}" id="provvedimento" 
										class="table table-hover table-bordered"
										summary="Elenco Provvedimenti">

							<display:column title="${idHeader}" property="id" headerScope="col" />
							<display:column title="${governoHeader}" property="governo.denominazione" headerScope="col" />
							<display:column title="${tipologiaHeader}" property="tipoProvvedimento.descrizione" headerScope="col" />
							<display:column title="${fonteNormativaHeader}" property="fonteNormativa" headerScope="col" />
							<display:column title="${artHeader}" property="articolo" headerScope="col" />
							<display:column title="${commaHeader}" property="comma" headerScope="col" />
							<display:column title="${provvDaAdottareHeader}" property="tipoProvvDaAdottare.descrizione" headerScope="col" />
							<display:column title="${titoloOggettoHeader}" property="oggetto" headerScope="col" />
							<display:column title="${statoDiAttuazioneHeader}" property="stato.descrizione" headerScope="col" />
							<display:column title="${capofilaHeader}" property="organoCapofila.denominazione" headerScope="col" />
							<display:column title="${proponenteHeader}" property="organoConcertante.denominazione" headerScope="col" />
							<display:column title="${assegnazioneHeader}" property="organoConcertante.denominazione" headerScope="col" />
							<display:column title="${allegatiHeader}" property="organoConcertante.denominazione" headerScope="col" />

						</display:table>
						
					</div>
				</div>
			
			</c:if>
		</div>
		<div class="span12">
			<button type="submit" class="btn pull-right" id="esportaXLS"
				style="margin-right: 10px;">
				Esporta in excel &nbsp;<i class="icon-ms-excel"></i>
			</button>

			<ul class="unstyled">
				<li><span class="icon-stack"> <i
						class="icon-sign-blank icon-stack-base color-capofila"></i>
				</span> <span> Provvedimenti inseriti o di cui si e' capofila </span></li>
				<li><span class="icon-stack"> <i
						class="icon-sign-blank icon-stack-base color-assegnatario"></i>
				</span> <span> Provvedimenti assegnati </span></li>
			</ul>


			<!-- <div class="pagination pagination-centered">
				<ul>
					<li><a class="disabled" href="#">&#171;</a></li>
					<li><a class="active" href="#">1</a></li>
					<li><a class="disabled" href="#">&#187;</a></li>
				</ul>
			</div> -->
		</div>
	</div>
</div>