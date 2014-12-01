<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message var="idHeader" code="listaProvvedimenti.header.id" />
<spring:message var="noteHeader" code="listaAssegnatari.header.note" />
<spring:message var="organoHeader" code="listaAssegnatari.header.organo" />
<spring:message var="descrizioneHeader" code="listaAllegati.header.descrizione" />
<spring:message var="dimensioneHeader" code="listaAllegati.header.dimensione" />
<spring:message var="eliminaHeader" code="listaAssegnatari.header.elimina" />

<spring:url value="/private/admin/manuale?{p}={t}" var="url_post" >
	<spring:param name="p">${_csrf.parameterName}</spring:param>
	<spring:param name="t">${_csrf.token}</spring:param>
</spring:url>
	
<springform:form name="allegatoForm" method="POST" enctype="multipart/form-data" action="${url_post}">

	<div class="container">

		<div class="row">
			<div class="span12">

				<h3 class="text-left underline">
					<span>Aggiornamento manuale d'uso</span>
				</h3>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="form-horizontal"> 
			
					<div class="control-group">		
						<label class="control-label" for="allegato">Manuale</label>
						<div class="controls">
							<div class="input-group">
								<input type="text" class="form-control input-xlarge" readonly="readonly" name="textAllegato" id="textAllegato">
								<span class="input-group-btn"> 
									<span class="btn btn-file"> Scegli...<input type="file" name="allegato" id="allegato">
									</span>
								</span> 
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="control-group">
					<div class="pull-right">
						<button type="submit" class="btn btn-primary" name="procedi" >Carica &nbsp;<i class="icon-save"></i></button>
					</div>
				</div>
			</div>
		</div>

	</div>
		
</springform:form>

