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


<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">
			<springform:form action="/private/admin/enti/edit" method="post" commandName="organoToEdit" cssClass="form-horizontal">

				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>
				
				<h3 class="text-left underline">
					<span>Modifica Ente</span>
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
							<label class="control-label" for="flagConcertante">${enteConcertanteHeader}</label>
							<div class="controls">
								<span><springform:radiobuttons path="flagConcertante" items="${cdDto}" itemValue="codice" itemLabel="descrizione"/></span>
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

			</springform:form>
		</div>
	</div>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>