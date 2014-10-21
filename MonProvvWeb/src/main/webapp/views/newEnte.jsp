<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />


<spring:message var="idHeader" code="listaOrgani.header.id" />
<spring:message var="enteInternoHeader" code="listaOrgani.header.ente.interno" />
<spring:message var="denominazioneHeader" code="listaOrgani.header.denominazione" />
<spring:message var="denominazioneEstesaHeader" code="listaOrgani.header.denominazione.estesa" />
<spring:message var="enteConcertanteHeader" code="listaOrgani.header.ente.concertante" />
<spring:message var="enteTipoHeader" code="listaOrgani.header.ente.tipo" />


<div class="container" id="risultatiRicerca">

	<c:url value="/private/admin/enti/nuovo" var="formPath" />

	<springform:form action="${formPath}" method="POST" commandName="organoToEdit" cssClass="form-horizontal">
		<div class="row">
			<div class="span12">
			
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>

				
				<h3 class="text-left underline">
					<span>Nuovo Ente</span>
				</h3>
				
				<div class="row">
					<div class="span10 offset2 dettaglio">

						<div class="control-group">
							<label class="control-label" for="flgInternoEsterno">${enteTipoHeader}</label>
							<div class="controls">
								<springform:select path="flgInternoEsterno" cssClass="input-xlarge" id="tipoNuovoOrgano">
									<springform:option value=""></springform:option>
									<springform:options items="${tipos}" itemLabel="descrizione" itemValue="codice" />
								</springform:select>
							</div>
						</div>
						
						<div class="control-group" id="listaOrganiInterniNuovoOrganoDiv">
							<label class="control-label" for="unitaOrgAstage">${enteInternoHeader}</label>
							<div class="controls">
								<springform:input path="denominazione" id="autocompleteUo" cssClass="input-xlarge"/>
								
								<springform:hidden path="unitaOrgAstage" id="hiddenIdUo"/>
								<%--
								<springform:select path="unitaOrgAstage" cssClass="input-xlarge" id="tipoNuovoOrgano">
									<springform:option value=""></springform:option>
									<springform:options items="${organiInterni}" itemLabel="nome" itemValue="id" />
								</springform:select>
								--%>
								
							</div>
						</div>
						
						<div class="control-group" id="denominazioneNuovoOrganoDiv">
							<label class="control-label" for="denominazione">${denominazioneHeader}</label>
							<div class="controls">
								<span>
									<springform:input path="denominazione" cssClass="input-xlarge"/>
								</span>
							</div>
						</div>	
						
						<div class="control-group" id="denominazioneEstesaNuovoOrganoDiv">
							<label class="control-label" for="denominazioneEstesa">${denominazioneEstesaHeader}</label>
							<div class="controls">
								<span>
									<springform:textarea path="denominazioneEstesa" id="denominazioneEstesa" cssClass="input-xlarge" cols="30" rows="4"/>
								</span>
							</div>
						</div>
				
						<div class="control-group">
							<label class="control-label" for="flagConcertante">${enteConcertanteHeader}</label>
							<div class="controls">
								<span class="form-inline"><springform:radiobuttons path="flagConcertante" items="${cdDto}" itemValue="codice" itemLabel="descrizione"/></span>
							</div>
						</div>
						
					</div>
				</div>
				
				<div class="control-group">
					<div class="pull-right">
						<button type="submit" class="btn btn-primary" id="indietro" name="buttonSave" value="OK">Salva &nbsp;<i class="icon-save"></i></button>
						<button type="submit" class="btn" id="modifica" name="buttonCancel" value="OK">Annulla &nbsp;<i class="icon-undo"></i></button>
					</div>
				</div>
			</div>
		</div>
	
	</springform:form>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>