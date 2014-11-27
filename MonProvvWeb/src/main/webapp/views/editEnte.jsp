<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />


<spring:message var="idHeader" code="listaOrgani.header.id" />
<spring:message var="denominazioneHeader" code="listaOrgani.header.denominazione" />
<spring:message var="denominazioneEstesaHeader" code="listaOrgani.header.denominazione.estesa" />
<spring:message var="enteConcertanteHeader" code="listaOrgani.header.ente.concertante" />
<spring:message var="enteTipoHeader" code="listaOrgani.header.ente.tipo" />


<div class="container" id="editEnte">

	<c:url value="/private/admin/enti/modifica" var="formPath" />

	<springform:form action="${formPath}" method="POST" commandName="organoToEdit" cssClass="form-horizontal">
		<div class="row">
			<div class="span12">
				<springform:hidden path="id"/>
				<springform:hidden path="flgInternoEsterno"/>
				<springform:hidden path="unitaOrgAstage"/>
				<springform:hidden path="versione"/>
				
				<h3 class="text-left underline">
					<span>Modifica Organo</span>
				</h3>

				<div class="row">
					<div class="offset0 text-left">
						<p>&nbsp;(<sup id="n2">*</sup>) <em>Campi obbligatori</em></p>
					</div>
					<div class="span10 offset2 dettaglio">
						<%-- 
						<div class="control-group">
							<span class="control-label">Id</span>
							<div class="controls">
								<span>${organoToEdit.id}</span>
							</div>
						</div>
						--%>	
						
						<c:set value="OK" var="errore"  />
						<spring:bind path="unitaOrgAstage" >
							<c:if test="${(not empty status.errorMessage)}">
								<c:set value="ERRORE" var="errore" />
							</c:if>
						</spring:bind>
						<spring:bind path="denominazione" >
							<c:if test="${(not empty status.errorMessage)}">
								<c:set value="ERRORE" var="errore" />
							</c:if>
						</spring:bind>
					
						<div class="control-group ${('ERRORE' == errore) ? ' error':''}">
							<label class="control-label ${('ERRORE' == errore) ? ' text-error':''}" for="denominazione">${denominazioneHeader} <sup style="font-weight: normal!important">(*)</sup></label>
							<div class="controls">
								<span>
									<c:if test="${not empty organoToEdit.unitaOrgAstage}">
										<springform:input path="denominazione" cssClass="input-xlarge" readonly="true"/>
									</c:if>
									
									<c:if test="${empty organoToEdit.unitaOrgAstage}">
										<springform:input path="denominazione" cssClass="input-xlarge"/>
									</c:if>
									
									<springform:errors path="unitaOrgAstage" cssClass="text-error help-inline"></springform:errors>
									<springform:errors path="denominazione" cssClass="text-error help-inline"></springform:errors>
									
								</span>
							</div>
						</div>	
						
						<div class="control-group">
							<label class="control-label" for="denominazioneEstesa">${denominazioneEstesaHeader}</label>
							<div class="controls">
								<span>
									<c:if test="${not empty organoToEdit.unitaOrgAstage}">
										<springform:textarea path="denominazioneEstesa" id="denominazioneEstesa" cssClass="input-xlarge" cols="30" rows="4" readonly="true"/>
									</c:if>
									
									<c:if test="${empty organoToEdit.unitaOrgAstage}">
										<springform:textarea path="denominazioneEstesa" id="denominazioneEstesa" cssClass="input-xlarge" cols="30" rows="4"/>
									</c:if>
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
						
						<div class="control-group">
							<span class="control-label">${enteTipoHeader}</span>
							<div class="controls">
								<span>${organoToEdit.tipo}</span>
							</div>
						</div>

				
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