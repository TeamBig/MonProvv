<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:eval expression="@config.getProperty('paginazione.risultatiPerPagina')" var="risultatiPerPagina" />

<spring:message var="idHeader" code="gestione.utente.id" />
<spring:message var="nomeHeader" code="gestione.utente.nome" />
<spring:message var="cognomeHeader" code="gestione.utente.cognome" />
<spring:message var="cfHeader" code="gestione.utente.cf" />
<spring:message var="emailHeader" code="gestione.utente.email" />
<spring:message var="tipoHeader" code="gestione.utente.tipo" />
<spring:message var="enteHeader" code="gestione.utente.ente" />
<spring:message var="eliminaHeader" code="gestione.utente.elimina" />




<div class="container collapse" id="campiRicerca">
			
	<c:url value="/private/admin/utenti" var="formPath" />
	<springform:form action="${formPath}" method="POST" modelAttribute="ricercaUtente" cssClass="bo clfix" id="formCampiRicerca">
		<div class="row">
			<div class="span12">
				<h3 class="underline"><span>Ricerca Utenti</span></h3>
			</div>
		</div>			
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="cognome">${cognomeHeader}</label>
						<div class="controls">
							<springform:input path="cognome" id="cognome" cssClass="input-xlarge"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="nome">${nomeHeader}</label>
						<div class="controls">
							<springform:input path="nome" id="nome" cssClass="input-xlarge"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="codiceFiscale">${cfHeader}</label>
						<div class="controls">
							<springform:input path="codiceFiscale" id="codiceFiscale" cssClass="input-xlarge"/>
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
							<button type="submit" class="btn " id="ricerca" name="buttonFind" value="find">
								Ricerca &nbsp;<i class="icon-search"></i>
							</button>
							<button type="submit" class="btn " id="pulisci" name="buttonClean" value="clean">
								Pulisci &nbsp;<i class="icon-eraser"></i>
							</button>
							<input type="hidden" name="showCampiRicerca" value="${filtriImpostati}" id="showCampiRicerca" />
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
			<c:url value="/private/admin/utenti" var="formPath" />
			<springform:form action="${formPath}" method="POST" cssClass="form-horizontal">
				<h3 class="text-left underline">
					<span>Elenco Utenti</span>
					<button type="submit" class="btn btn-primary pull-right" id="nuovo" name="buttonNew" value="new">
						Nuovo Utente &nbsp;<i class="icon-plus"></i>
					</button>
					<button type="button" class="btn pull-right" id="toggleRicerca"	data-toggle="collapse" data-target="#campiRicerca" style="margin-right: 10px;">
						Toggle ricerca &nbsp;<i class="icon-search"></i>
					</button>
				</h3>
			</springform:form>
		</div>
	</div>
	
	<div class="row">
		<div class="span12">
			
				<div class="row">
					<div class="span12">
						
						<c:url value="/private/admin/utenti/delete" var="deletePath" />
						
						<display:table 	name="${listaUtenti}" 
										pagesize="${risultatiPerPagina}" 
										requestURI="" 
										sort="external" 
										partialList="true" 
										size="${tableRisultatiSize}" 
										id="utente" 
										class="table table-hover table-bordered">
							
							<display:column title="${idHeader}" property="id" headerScope="col" class="hidden" headerClass="hidden" />
							<display:column title="${cognomeHeader}" property="cognome" headerScope="col" />
							<display:column title="${nomeHeader}" property="nome" headerScope="col" />
							<display:column title="${cfHeader}" property="codiceFiscale" headerScope="col" />
							<display:column title="${emailHeader}" property="email" headerScope="col" />
							<display:column title="${tipoHeader}" property="tipo" headerScope="col" />
							<display:column title="${enteHeader}" property="organo.denominazione" headerScope="col" />
							<display:column title="${eliminaHeader}" headerScope="col" class="center deleteUtente" headerClass="center">
								<a href="${deletePath}?id=${utente.id}" id="delete4risultatiRicerca_${utente.id}" ><i class="icon-trash icon-large gray" title="Elimina Utente"></i></a>
							</display:column>
							
						</display:table>
						
					</div>
				</div>
			
	

		</div>
	</div>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>
