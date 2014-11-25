<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message var="titoloHeader" code="tipologicheHome.title" />
<spring:message var="tipologicaHeader" code="tipologicheHome.tipologica" />
<spring:message var="idHeader" code="tipologicheHome.id" />
<spring:message var="denominazioneHeader" code="tipologicheHome.denominazione" />
<spring:message var="codiceHeader" code="tipologicheHome.codice" />
<spring:message var="descrizioneHeader" code="tipologicheHome.descrizione" />
<spring:message var="eliminaHeader" code="tipologicheHome.elimina" />

<div class="container" id="gestioneTipologiche">
	<div class="row">
		<div class="span12">
			
			<c:url value="/private/admin/tipologiche" var="formPath" />
			<springform:form action="${formPath}" method="POST" commandName="gestioneTipologiche" cssClass="form-horizontal" id="formGestioneTipologiche">
			<h3 class="text-left underline">
				<span>${titoloHeader}</span>
			</h3>
			
			<div class="row">
				<div class="span10 offset2 dettaglio">
	
					<div class="control-group">
						<label class="control-label" for="tipoTipologica">${tipologicaHeader}</label>
						<div class="controls">
							<springform:select path="idSchelta" cssClass="input-xlarge" id="tipoTipologica">
								<springform:option value="">&nbsp;</springform:option>
								<springform:options items="${sceltas}" itemLabel="descrizione" itemValue="id" />
							</springform:select>
						</div>
					</div>
				</div>
			</div>
			</springform:form>


			<c:choose>
				<c:when test="${gestioneTipologiche.idSchelta == 1}">
					<h3 class="text-left underline">
						<span>Governo</span>
						<button type="button" class="btn btn-primary pull-right" id="nuovaTipologica" name="buttonNew" value="new">
							Nuovo Governo &nbsp;<i class="icon-plus"></i>
						</button>
					</h3>
					<div class="row">
						<div class="span12">										
							<c:url value="/private/admin/tipologiche/governo/cambiastato" var="deletePath" />
							<display:table name="${gestioneTipologiche.governi}"
								requestURI="" sort="external" partialList="false" id="governo"
								class="table table-hover table-bordered">
								<display:column title="${idHeader}" property="id" headerScope="col" class="hidden" headerClass="hidden" />
								<display:column title="${denominazioneHeader}" property="denominazione" headerScope="col" />
								<display:column title="${eliminaHeader}" headerScope="col" class="center medium cambiaStatoTipologica" headerClass="center">
									<a href="${deletePath}?id=${governo.id}" id="cambiaStato_${governo.id}" >
										<c:choose>
											<c:when test="${ governo.flagAttivo eq 'S' }">
												<i class="icon-check-empty icon-large gray" title="Disattiva Governo"></i>
											</c:when>
											<c:otherwise>
												<i class="icon-check icon-large gray" title="Attiva Governo"></i>
											</c:otherwise>
										</c:choose>
									</a>
								</display:column>
							</display:table>
						</div>
					</div>
				</c:when>
				<c:when test="${gestioneTipologiche.idSchelta == 2}">
					<h3 class="text-left underline">
						<span>Tipo Atto</span>
						<button type="button" class="btn btn-primary pull-right" id="nuovaTipologica" name="buttonNew" value="new">
							Nuovo Tipo Atto &nbsp;<i class="icon-plus"></i>
						</button>
					</h3>
					<div class="row">
						<div class="span12">
							<c:url value="/private/admin/tipologiche/tipoatto/cambiastato" var="deletePath" />
							<display:table name="${gestioneTipologiche.tipiAtto}"
								requestURI="" sort="external" partialList="false" id="tipoAtto"
								class="table table-hover table-bordered">
								<display:column title="${idHeader}" property="id" headerScope="col" class="hidden" headerClass="hidden" />
								<display:column title="${codiceHeader}" property="codice" headerScope="col" />
								<display:column title="${descrizioneHeader}" property="descrizione" headerScope="col" />
								<c:choose>
									<c:when test="${tipoAtto.disattivabile}">	
										<display:column title="${eliminaHeader}" headerScope="col" class="center medium cambiaStatoTipologica" headerClass="center">
											<a href="${deletePath}?id=${tipoAtto.id}" id="cambiaStato_${tipoAtto.id}" >
												<c:choose>
													<c:when test="${ tipoAtto.flagAttivo eq 'S' }">
														<i class="icon-check-empty icon-large gray" title="Disattiva Tipo Atto"></i>
													</c:when>
													<c:otherwise>
														<i class="icon-check icon-large gray" title="Attiva Tipo Atto"></i>
													</c:otherwise>
												</c:choose>
											</a>
										</display:column>
									</c:when>
									<c:otherwise>
										<display:column title="${eliminaHeader}" headerScope="col" class="center medium" headerClass="center">
											Non disattivabile
										</display:column>
									</c:otherwise>
								</c:choose>
							</display:table>
						</div>
					</div>
				</c:when>
				<c:when test="${gestioneTipologiche.idSchelta == 3}">
					<h3 class="text-left underline">
						<span>Tipo Provvedimento da Adottare</span>
						<button type="button" class="btn btn-primary pull-right" id="nuovaTipologica" name="buttonNew" value="new">
							Nuovo Tipo Provvedimento &nbsp;<i class="icon-plus"></i>
						</button>
					</h3>
					<div class="row">
						<div class="span12">
							<c:url value="/private/admin/tipologiche/tipiprovv/cambiastato" var="deletePath" />
							<display:table name="${gestioneTipologiche.tipiProvv}"
								requestURI="" sort="external" partialList="false" id="tipoProvv"
								class="table table-hover table-bordered">
								<display:column title="${idHeader}" property="id" headerScope="col" class="hidden" headerClass="hidden" />
								<display:column title="${descrizioneHeader}" property="descrizione" headerScope="col" />
								<display:column title="${eliminaHeader}" headerScope="col" class="center medium cambiaStatoTipologica" headerClass="center">
									<a href="${deletePath}?id=${tipoProvv.id}" id="cambiaStato_${tipoProvv.id}" >
										<c:choose>
											<c:when test="${ tipoProvv.flagAttivo eq 'S' }">
												<i class="icon-check-empty icon-large gray" title="Disattiva Tipo Provvedimento da Adottare"></i>
											</c:when>
											<c:otherwise>
												<i class="icon-check icon-large gray" title="Attiva Tipo Provvedimento da Adottare"></i>
											</c:otherwise>
										</c:choose>
									</a>
								</display:column>
							</display:table>
						</div>
					</div>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>				
		</div>
	</div>
	
	<div id="modalNuovaTipologia" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header modal-header-tipologica">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel"></h3>
		</div>
		<c:choose>
			<c:when test="${gestioneTipologiche.idSchelta == 1}">	
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="denominazioneGoverno">Governo</label>
							<div class="controls">
								<input type="hidden" class="input-xlarge" id="idGoverno"></input>
								<input type="text" class="input-xlarge" id="denominazioneGoverno"></input>
							</div>
						</div>
					</div>
				</div>		
			</c:when>
			<c:when test="${gestioneTipologiche.idSchelta == 2}">	
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="codice">Codice</label>
							<div class="controls">
								<input type="hidden" class="input-xlarge" id="idTipoAtto"></input>
								<input type="text" class="input-xlarge" id="codiceTipoAtto"></input>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="descrizione">Descrizione</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="descrizioneTipoAtto"></input>
							</div>
						</div>
					</div>
				</div>
			</c:when>
			<c:when test="${gestioneTipologiche.idSchelta == 3}">	
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="descrizione">Descrizione</label>
							<div class="controls">
								<input type="hidden" class="input-xlarge" id="idTipoProv"></input>
								<input type="text" class="input-xlarge" id="descrizioneTipoProv"></input>
							</div>
						</div>
					</div>
				</div>		
			</c:when>
		</c:choose>
		<div class="modal-footer">
			<div class="pull-right">
				<button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" name="buttonSave" value="save" id="saveTipologica" >Salva &nbsp;<i class="icon-save"></i></button>
				<button type="button" class="btn" data-dismiss="modal" aria-hidden="true" name="buttonCancel" value="cancel" id="clean" >Annulla &nbsp;<i class="icon-undo"></i></button>
			</div>
		</div>
	</div>

</div>
	