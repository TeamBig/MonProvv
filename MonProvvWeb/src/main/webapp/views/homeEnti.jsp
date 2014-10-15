<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />


<spring:message var="idHeader" code="listaOrgani.header.id" />
<spring:message var="denominazioneHeader" code="listaOrgani.header.denominazione" />
<spring:message var="denominazioneEstesaHeader" code="listaOrgani.header.denominazione.estesa" />
<spring:message var="enteConcertanteHeader" code="listaOrgani.header.ente.concertante" />


<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">

			<springform:form method="post">

				<h3 class="text-left underline">
					<span>Elenco Enti</span>
					<button type="submit" class="btn btn-primary pull-right" id="nuovo">Nuovo Ente &nbsp;<i class="icon-plus"></i></button>
					<button class="btn pull-right" id="toggleRicerca" data-toggle="collapse" data-target="#campiRicerca" style="margin-right: 10px;">Toggle ricerca &nbsp;<i class="icon-search"></i></button>
				</h3>

			</springform:form>
			

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