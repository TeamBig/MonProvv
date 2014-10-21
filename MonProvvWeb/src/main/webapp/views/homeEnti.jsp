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



<div class="container collapse" id="campiRicerca">
			
	<c:url value="/private/admin/enti" var="formPath" />
	<springform:form action="${formPath}" method="POST" modelAttribute="ricercaEnte" cssClass="bo clfix" id="formCampiRicerca">
		<div class="row">
			<div class="span12">
				<h3 class="underline"><span>Ricerca Ente</span></h3>
			</div>
		</div>			
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="denominazione">${denominazioneHeader}</label>
						<div class="controls">
							<springform:input path="denominazione" cssClass="input-xlarge"/>
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
							<button type="submit" class="btn " id="pulisci" name="pulisci" value="OK">
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
			<c:url value="/private/admin/enti" var="formPath" />
			<springform:form action="${formPath}" method="POST" cssClass="form-horizontal">
				<h3 class="text-left underline">
					<span>Elenco provvedimenti</span>
					<button type="submit" class="btn btn-primary pull-right" id="buttonNew" name="buttonNew" value="OK">
						Nuovo Ente &nbsp;<i class="icon-plus"></i>
					</button>
					<button type="button" class="btn pull-right" id="toggleRicerca"
						data-toggle="collapse" data-target="#campiRicerca"
						style="margin-right: 10px;">
						Toggle ricerca &nbsp;<i class="icon-search"></i>
					</button>
				</h3>
			</springform:form>
		</div>
	</div>
	
	<div class="row">
		<div class="span12">
			<c:if test="${tableOrganiRisultatiSize gt 0}">
				<div class="row">
					<div class="span12">
						
						<display:table 	name="${listaOrgani}" 
										pagesize="${risultatiPerPagina}" 
										requestURI="" sort="external" partialList="true" 
										size="${tableOrganiRisultatiSize}" id="organo" 
										class="table table-hover table-bordered"
										summary="Elenco Enti">

							<display:column title="${idHeader}" property="id" headerScope="col" />
							<display:column title="${denominazioneHeader}" property="denominazione" headerScope="col" />
							<display:column title="${denominazioneEstesaHeader}" property="denominazioneEstesa" headerScope="col" />
							<display:column title="${enteConcertanteHeader}" property="concertante" headerScope="col" />
							<display:column title="${enteConcertanteHeader}" property="concertante" headerScope="col" />
							<display:column title="${enteTipoHeader}" property="tipo" headerScope="col" />
							
						</display:table>
						
					</div>
				</div>
			
			</c:if>

		</div>
	</div>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>