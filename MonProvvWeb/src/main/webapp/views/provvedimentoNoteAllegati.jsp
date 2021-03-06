<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />


<spring:message var="idHeader" code="listaProvvedimenti.header.id" />
<spring:message var="noteHeader" code="listaAssegnatari.header.note" />
<spring:message var="organoHeader" code="listaAssegnatari.header.organo" />
<spring:message var="descrizioneHeader" code="listaAllegati.header.descrizione" />
<spring:message var="dimensioneHeader" code="listaAllegati.header.dimensione" />
<spring:message var="eliminaHeader" code="listaAssegnatari.header.elimina" />


<div class="container" id="notaAllegatiProv">
	
	<c:url value="/private/provvedimenti/ricerca/noteAllegatiProv" var="formPath" />
	
	<springform:form action="${formPath}" method="POST" id="provvedimento" modelAttribute="provvedimento" cssClass="form-horizontal">
		
		<div class="row">
			<div class="span12">
				
				<springform:hidden path="id" id="idProvvedimento" />
				<springform:hidden path="versione" id="versioneProvvedimento" />
				
				<springform:hidden path="idAllegatiUpdList" id="idAllegatiUpdList" />
				<springform:hidden path="idAllegatiDelList" id="idAllegatiDelList" />
				<springform:hidden path="idNotaAssegnazione" id="idNotaAssegnazione" />
				
				<h3 class="text-left underline">
					<span>Nota</span>
				</h3>
				
				<div class="row">
					<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<span class="control-label">${organoHeader}</span>
							<div class="controls">
								<span><spring:eval expression="T(it.tesoro.monprovv.utils.SicurezzaUtils).assegnazioneCorrente(provvedimento.assegnazioneList).organo.denominazione"></spring:eval></span>
							</div>
						</div>	
						<div class="control-group">
							<label class="control-label" for="testoNotaAssegnazione">${noteHeader}</label>
							<div class="controls">
								<span>
									<springform:textarea path="testoNotaAssegnazione" id="testoNotaAssegnazione" cssClass="input-xxlarge" cols="30" rows="5"/>
								</span>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		
		
	</springform:form>
	
	<!-- Allegati insert -->
	<div class="row">
		<div class="span12">
			<h3 class="text-left underline">
				<span>Allegati</span>
			</h3>
	
			<display:table
				name="${listaAllegati}" 
				requestURI="" sort="external" partialList="false"
				id="allegato" 
				class="table table-hover table-bordered"
				summary="Elenco Allegati" 
				style="width: 100%">

				<display:column title="${idHeader}" property="id" headerClass="hidden" headerScope="col" class="hidden" />
				<display:column title="${descrizioneHeader}" headerScope="col" class="vcenter">
					<spring:url value="/private/provvedimenti/ricerca/downloadAllegato?id=${allegato.id}" var="urlDownload" />
					<a href="${urlDownload}" class="download">${allegato.descrizione}</a>
				</display:column>
				<display:column title="${dimensioneHeader}" headerScope="col">
					<c:if test="${not empty allegato.dimensione }">
						<spring:eval expression="T(it.tesoro.monprovv.utils.StringUtils).convertBytesToKb(${allegato.dimensione},true)"/>
					</c:if>
				</display:column>
				<display:column title="${eliminaHeader}" headerScope="col" class="vcenter center">
					<a href="javascript:void(0)" id="eliminaAllegato" ><i class="icon-trash icon-large gray" title="Elimina allegato"></i></a>
				</display:column>
			</display:table>
		</div>
	</div>
	
	<c:url value="/private/provvedimenti/ricerca/noteAllegatiProv/inserisciAllegato" var="formAllegatiPath" />

	<springform:form cssClass="form-horizontal" id="allegatoForm" name="allegatoForm" action="${formAllegatiPath}" method="POST" enctype="multipart/form-data">

 		<div class="progress progress-striped active">
			<div class="bar" style="width: 0%;">
				<div class="percent">0%</div >
			</div>
		</div>

		<input type="hidden" name="idProvvedimento" id="idProvvedimentoAllegato" />
		<div class="row">
			<div class="span12">

				<div class="control-group">		
					<label class="control-label" for="allegato">File da allegare</label>
					<div class="controls">
						<input type="text" class="form-control input-xlarge" readonly="readonly" name="textAllegato" id="textAllegato">
						<span class="input-group-btn"> 
							<span class="btn btn-file"> Browse...<input type="file" name="allegatoProvvedimento" id="allegatoProvvedimento">
							</span>
						</span> 
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="descrizione">Descrizione</label>
					<div class="controls">
						<input type="text" id="descrizioneAllegato" name="descrizioneAllegato" class="input-xxlarge" />
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<button type="button" id="allegatoInserisci" class="btn">Aggiungi <i class="icon-plus"></i></button>
					</div>
				</div>
				
			</div>
		</div>
		
	</springform:form>

	<!-- allegati end -->
	
	<div class="row">
		<div class="span12">
			<div class="control-group">
				<div class="pull-right">
					<button type="button" class="btn btn-primary" id="saveNoteAllegati" value="Salva">Salva &nbsp;<i class="icon-save"></i></button>
					<button type="button" class="btn" id="annullaNoteAllegati" value="Annulla">Annulla &nbsp;<i class="icon-undo"></i></button>
				</div>
			</div>
		</div>
	</div>

	<div class="row" style="height: 30px;">
		<div class="span12"></div>
	</div>
</div>
