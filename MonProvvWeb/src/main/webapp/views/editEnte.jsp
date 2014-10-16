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

				<h3 class="text-left underline">
					<span>Dettaglio Ente</span>
				</h3>
				
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>
				
				<div class="control-group">
					<label class="control-label" for="denominazione">${denominazioneHeader}</label>
					<div class="controls">
					
					>>>>${unitaOrgAstage}<<<<
						<c:if test="${not empty unitaOrgAstage}">
							<springform:input path="denominazione" cssClass="input-large" readonly="true"/>
						</c:if>
						
						<c:if test="${empty unitaOrgAstage}">
							<springform:input path="denominazione" cssClass="input-large"/>
						</c:if>
					
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="denominazioneEstesa">${denominazioneEstesaHeader}</label>
					<div class="controls">
						<c:choose>
							<c:when test="tipo = 'Interno'">
								<springform:textarea path="denominazioneEstesa" id="denominazioneEstesa" cssClass="input-xlarge" cols="30" rows="4" readonly="true"/>
							</c:when>
							<c:otherwise>
								<springform:textarea path="denominazioneEstesa" id="denominazioneEstesa" cssClass="input-xlarge" cols="30" rows="4"/>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="flagConcertante">${enteConcertanteHeader}</label>
					<div class="controls">
						<springform:radiobuttons path="flagConcertante" items="${cdDto}" itemValue="codice" itemLabel="descrizione"/>
					</div>
				</div>

				<div class="control-group">
					<span class="control-label">${enteTipoHeader}</span>
					<div class="controls">
						<span>${organoToEdit.tipo}</span>
					</div>
				</div>
				
				
				
				

			</springform:form>
		</div>
	</div>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>