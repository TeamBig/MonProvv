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


<div class="container" id="inserimentoEnte">

	<c:url value="/private/admin/enti/nuovo" var="formPath" />

	<springform:form action="${formPath}" method="POST" commandName="organoToEdit" cssClass="form-horizontal">
		<div class="row">
			<div class="span12">
			
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>

				
				<h3 class="text-left underline">
					<span>Nuovo Organo</span>
				</h3>
				
				<div class="row">
					<div class="offset0 text-left">
						<p>&nbsp;(<sup id="n2">*</sup>) <em>Campi obbligatori</em></p>
					</div>
					<div class="span10 offset2 dettaglio">
						
						<spring:bind path="flgInternoEsterno" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="flgInternoEsterno" cssClass="control-label" cssErrorClass="control-label text-error" for="tipoNuovoOrgano">${enteTipoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<springform:select path="flgInternoEsterno" cssClass="input-xlarge" id="tipoNuovoOrgano">
										<springform:option value="">Sceglierne uno...</springform:option>
										<springform:options items="${tipos}" itemLabel="descrizione" itemValue="codice" />
									</springform:select>
									<springform:errors path="flgInternoEsterno" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<c:set value="OK" var="errore"  />
						<spring:bind path="unitaOrgAstage" >
							<c:if test="${(not empty status.errorMessage)}">
								<c:set value="ERRORE" var="errore" />
							</c:if>
						</spring:bind>
						<spring:bind path="denominazioneAstage" >
							<c:if test="${(not empty status.errorMessage)}">
								<c:set value="ERRORE" var="errore" />
							</c:if>
						</spring:bind>

						<div class="control-group ${('ERRORE' == errore) ? ' error':''}" id="listaOrganiInterniNuovoOrganoDiv">
							<label class="control-label ${('ERRORE' == errore) ? ' text-error':''}" for="autocompleteUo">${enteInternoHeader} <sup style="font-weight: normal!important">(*)</sup></label>
							<div class="controls">
								<springform:input path="denominazioneAstage" id="autocompleteUo" cssClass="input-xlarge"/>
								<springform:errors path="denominazioneAstage" cssClass="text-error help-inline"></springform:errors>
								<springform:hidden path="unitaOrgAstage" id="hiddenIdUo"/>
								<springform:errors path="unitaOrgAstage" cssClass="text-error help-inline"></springform:errors>
							</div>
						</div>
						
						<spring:bind path="denominazione" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" id="denominazioneNuovoOrganoDiv">
								<springform:label path="denominazione" cssClass="control-label" cssErrorClass="control-label text-error" for="denominazione">${denominazioneHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:input path="denominazione" id="denominazione" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="denominazione" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>	
						</spring:bind>
						
						<div class="control-group" id="denominazioneEstesaNuovoOrganoDiv">
							<label class="control-label" for="denominazioneEstesa">${denominazioneEstesaHeader}</label>
							<div class="controls">
								<span>
									<springform:textarea path="denominazioneEstesa" id="denominazioneEstesa" cssClass="input-xlarge" cols="30" rows="4"/>
								</span>
							</div>
						</div>
						
						<spring:bind path="flagConcertante" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}">
								<springform:label path="flagConcertante" cssClass="control-label" cssErrorClass="control-label text-error" for="flagConcertante1">${enteConcertanteHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span class="form-inline"><springform:radiobuttons id="flagConcertante" path="flagConcertante" items="${cdDto}" itemValue="codice" itemLabel="descrizione"/></span>
									<springform:errors path="flagConcertante" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
					</div>
				</div>
				
				<div class="control-group">
					<div class="pull-right">
						<button type="submit" class="btn btn-primary" id="salva" name="buttonSave" value="save">Salva &nbsp;<i class="icon-save"></i></button>
						<button type="submit" class="btn" id="annulla" name="buttonCancel" value="cancel">Annulla &nbsp;<i class="icon-undo"></i></button>
					</div>
				</div>
			</div>
		</div>
	
	</springform:form>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>