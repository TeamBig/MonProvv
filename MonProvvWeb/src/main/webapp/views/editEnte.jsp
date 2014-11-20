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
					<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<span class="control-label">Id</span>
							<div class="controls">
								<span>${organoToEdit.id}</span>
							</div>
						</div>	
						<div class="control-group">
							<label class="control-label" for="denominazione">${denominazioneHeader}</label>
							<div class="controls">
								<span>
									<c:if test="${not empty organoToEdit.unitaOrgAstage}">
										<springform:input path="denominazione" cssClass="input-xlarge" readonly="true"/>
									</c:if>
									
									<c:if test="${empty organoToEdit.unitaOrgAstage}">
										<springform:input path="denominazione" cssClass="input-xlarge"/>
									</c:if>
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
				
						<div class="control-group">
							<label class="control-label" for="flagConcertante1">${enteConcertanteHeader}</label>
							<div class="controls">
								<span class="form-inline"><springform:radiobuttons id="flagConcertante" path="flagConcertante" items="${cdDto}" itemValue="codice" itemLabel="descrizione"/></span>
							</div>
						</div>
						
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