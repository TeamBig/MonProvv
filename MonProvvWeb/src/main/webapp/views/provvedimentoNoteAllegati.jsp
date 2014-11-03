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
	
	<springform:form action="${formPath}" method="POST" id="provvedimento" commandName="provvedimento" cssClass="form-horizontal">
		
		<div class="row">
			<div class="span12">
				
				<springform:hidden path="id" id="idProvvedimento" />
				<springform:hidden path="versione" id="versioneProvvedimento" />
				
				<springform:hidden path="idAllegatiUpdList" id="idAllegatiUpdList" />
				<springform:hidden path="idAllegatiDelList" id="idAllegatiDelList" />
				
				<h3 class="text-left underline">
					<span>Nota</span>
				</h3>
				
				<div class="row">
					<div class="span10 offset2 dettaglio">
						<div class="control-group">
							<span class="control-label">${organoHeader}</span>
							<div class="controls">
								<span>${provvedimento.organoInseritore.denominazione}</span>
							</div>
						</div>	
						<div class="control-group">
							<label class="control-label" for="denominazioneEstesa">${noteHeader}</label>
							<div class="controls">
								<span>
									<springform:textarea path="noteInterne" id="noteInterne" cssClass="input-xxlarge" cols="30" rows="5"/>
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

				<display:column title="${idHeader}" property="id" headerScope="col" />
				<display:column title="${descrizioneHeader}" headerScope="col" class="vcenter">
					<spring:url value="/private/provvedimenti/ricerca/downloadAllegato/${allegato.id}" var="urlDownload" />
					<a href="${urlDownload}" class="download">${allegato.descrizione}</a>
				</display:column>
				<display:column title="${dimensioneHeader}" headerScope="col">
					<c:out value="${allegato.dimensioneAsText}"></c:out>
				</display:column>
				<display:column title="${eliminaHeader}" headerScope="col" class="vcenter center">
					<a href="javascript:void(0)" id="eliminaAllegato" ><i class="icon-trash icon-large gray" title="Elimina allegato"></i></a>
				</display:column>
			</display:table>
		</div>
	</div>
	
	<c:url value="/private/ricercaProv/noteAllegatiProv/inserisciAllegato" var="formAllegatiPath" />

	<springform:form cssClass="form-horizontal" id="allegatoForm" name="allegatoForm" action="${formAllegatiPath}" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="idProvvedimento" id="idProvvedimentoAllegato" />
		<div class="row">
			<div class="span12">
				
				<div class="control-group">		
					<label class="control-label" for="allegato">File da allegare</label>
					<div class="controls">
						<input type="file" name="allegatoProvvedimento" style="display:none;" id="allegatoProvvedimento" />
						<input type="text" name="textAllegato" id="textAllegato" class="input-xlarge" />
						<button type="button" onclick="$('#allegatoProvvedimento').click();" class="btn">Sfoglia</button>
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
						<button type="submit" id="allegatoInserisci" class="btn">Aggiungi <i class="icon-plus"></i></button>
					</div>
				</div>
				
			</div>
		</div>
		
		<%--
		<input type="file" id="allegatoProvvedimento" name="allegatoProvvedimento" class="input-xxlarge" /><br/> 
		<input type="text" id="descrizioneAllegato" name="descrizioneAllegato" class="input-xxlarge" /><br/>
		<input type="submit" value="Upload File to Server">
		--%>
	</springform:form>



	<%-- 
	<div class="progress">
		<div class="bar"></div>
		<div class="percent">0%</div>
	</div>
	
	<springform:form cssClass="form-horizontal" id="allegatoForm" name="allegatoForm" action="${formAllegatiPath}" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="idProvvedimento" id="idProvvedimentoAllegato" />
		<div class="row">
			<div class="span12">	
			
				<div class="control-group">		
					<label class="control-label" for="allegato">File da allegare</label>
					<div class="controls">
						<input type="file" name="allegatoProvvedimento" style="display:none;" id="allegatoProvvedimento" />
						<input type="text" name="textAllegato" id="textAllegato" class="input-xlarge" />
						<button type="button" onclick="$('#allegatoProvvedimento').click();" class="btn">Sfoglia</button>
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
	--%>
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

	<div class="row">
		<div class="span12"></div>
	</div>
</div>
