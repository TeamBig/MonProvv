<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message var="idHeader" code="gestione.utente.id" />
<spring:message var="cognomeHeader" code="gestione.utente.cognome" />
<spring:message var="nominativoHeader" code="gestione.utente.nominativo" />
<spring:message var="nomeHeader" code="gestione.utente.nome" />
<spring:message var="cfHeader" code="gestione.utente.cf" />
<spring:message var="emailHeader" code="gestione.utente.email" />
<spring:message var="tipoHeader" code="gestione.utente.tipo" />
<spring:message var="enteHeader" code="gestione.utente.ente" />
<spring:message var="ruoloHeader" code="gestione.utente.ruolo" />
<spring:message var="amministratoreHeader" code="gestione.utente.amministratore" />
<spring:message var="datanascitaHeader" code="gestione.utente.data.nascita" />
<spring:message var="sessoHeader" code="gestione.utente.sesso" />


<div class="container" id="editUtente">

	<c:url value="/private/admin/utenti/modifica" var="formPath" />

	<springform:form action="${formPath}" method="POST" commandName="utenteToEdit" cssClass="form-horizontal" id="utenteToEdit-form">
		<div class="row">
			<div class="span12">
			
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>
				
				<h3 class="text-left underline">
					<span>Modifica Utente</span>
				</h3>
				
				<c:if test="${utenteToEdit.flagIntEst == 'I'}">
				
					<div class="row">
						<div class="offset0 text-left">
							<p>&nbsp;(<sup id="n2">*</sup>) <em>Campi obbligatori</em></p>
						</div>
						<div class="span10 offset2 dettaglio">
							<div class="control-group">
								<span class="control-label">${tipoHeader}</span>
								<div class="controls">
									<span>
										${utenteToEdit.tipo}
										<springform:hidden path="flagIntEst" id="tipoNuovoUtente" />
									</span>
								</div>
							</div>
	
							<div class="control-group" >
								<span class="control-label">${cognomeHeader}</span>
								<div class="controls">
									<span>
										${utenteToEdit.cognome}
										<springform:hidden path="cognome" id="cognome" />
									</span>
								</div>
							</div>
							
							<div class="control-group" >
								<span class="control-label">${nomeHeader}</span>
								<div class="controls">
									<span>
										${utenteToEdit.nome}
										<springform:hidden path="nome" id="nome" />
									</span>
								</div>
							</div>
							
							<div class="control-group" >
								<label class="control-label">${datanascitaHeader}</label>
								<div class="controls">
									<span>	
										<fmt:formatDate value="${utenteToEdit.dataNascita}" pattern="dd/MM/yyyy" />
										<springform:hidden path="dataNascita" id="dataNascitaV" />
									</span>
								</div>
							</div>
							
							<div class="control-group" >
								<label class="control-label" for="sesso">${sessoHeader}</label>
								<div class="controls">
									<span>
										<c:set var = "MASCHIO" value="M" />
										<c:set var = "FEMMINA" value="F" />
										<c:choose>
											<c:when test="${ utenteToEdit.sesso eq MASCHIO }">Maschile</c:when>
											<c:when test="${ utenteToEdit.sesso eq FEMMINA }">Femminile</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
										<springform:hidden path="sesso" id="sesso" />
									</span>
								</div>
							</div>
							
							<div class="control-group" >
								<span class="control-label">${cfHeader}</span>
								<div class="controls">
									<span>
										${utenteToEdit.codiceFiscale}
										<springform:hidden path="codiceFiscale" id="codiceFiscale" />
									</span>
								</div>
							</div>
							
							<div class="control-group" >	
								<span class="control-label">${emailHeader}</span>
								<div class="controls">
									<span>
										${utenteToEdit.email}
										<springform:hidden path="email" id="email" />
									</span>
								</div>
							</div>
							
							<div class="control-group" >	
								<span class="control-label">${enteHeader}</span>
								<div class="controls">
									<span>
										<c:if test="${not empty utenteToEdit.organo}">
											${utenteToEdit.organo.denominazione}
										</c:if>
										<springform:hidden path="organo" id="organo"/>
									</span>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="ruoloUtente">${ruoloHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<springform:select path="ruolo" cssClass="input-xlarge" id="ruoloUtente">
										<springform:option value=""></springform:option>
										<springform:options items="${ruoli}" itemLabel="descrizione" itemValue="id" />
									</springform:select>
									<springform:errors path="ruolo" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="flgAmministratore">${amministratoreHeader}</label>
								<div class="controls">
									<springform:checkbox path="amministratore" id="flgAmministratore" value="S"/>
								</div>
							</div>
							
							<springform:hidden path="utenteAstage" id="hiddenUtenteAstage"/>
	
						</div>
					</div>
					
					<div class="control-group">
						<div class="pull-right">
							<button type="submit" class="btn btn-primary" id="salva" name="buttonSave" value="save">Salva &nbsp;<i class="icon-save"></i></button>
							<button type="submit" class="btn" id="annulla" name="buttonCancel" value="cancel">Annulla &nbsp;<i class="icon-undo"></i></button>
						</div>
					</div>
				
				</c:if>
				
				<c:if test="${utenteToEdit.flagIntEst == 'E'}">
				
					<div class="row">
						<div class="offset0 text-left">
							<p>&nbsp;(<sup id="n2">*</sup>) <em>Campi obbligatori</em></p>
						</div>
						<div class="span10 offset2 dettaglio">
							<div class="control-group">
								<span class="control-label">${tipoHeader}</span>
								<div class="controls">
									<span>
										${utenteToEdit.tipo}
										<springform:hidden path="flagIntEst" id="tipoNuovoUtente" />
									</span>
								</div>
							</div>		
							
							<div class="control-group" >
								<label class="control-label" for="cognome">${cognomeHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:input path="cognome" id="cognome" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="cognome" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group" >
								<label class="control-label" for="nome">${nomeHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:input path="nome" id="nome" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="nome" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group" >
								<label class="control-label" for="dataNascitaV">${datanascitaHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:input path="dataNascita" id="dataNascitaV" cssClass="input-xlarge dataValid"/>&nbsp;<i class="icon-calendar icon-large" id="dataNascita"></i>
									</span>
									<springform:hidden path="dataNascitaHidden" id="dataNascitaHidden" />
									<springform:errors path="dataNascitaHidden" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group" >
								<label class="control-label" for="sesso">${sessoHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:select path="sesso" cssClass="input-xlarge" id="sesso">
											<springform:options items="${sessos}" itemLabel="descrizione" itemValue="codice" />
										</springform:select>
									</span>
									<springform:errors path="sesso" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group" >
								<label class="control-label" for="codiceFiscale">${cfHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:input path="codiceFiscale" id="codiceFiscale" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="codiceFiscale" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group" >	
								<label class="control-label" for="email">${emailHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:input path="email" id="email" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="email" cssClass="text-error"></springform:errors>
								</div>
							</div>					
							
							<div class="control-group" >	
								<label class="control-label" for="organoUteEsterno">${enteHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<%-- 
										<springform:input path="organoDenominazione" id="organoDenominazioneEst" cssClass="input-xlarge"/>
										--%>
										<springform:select path="organoUteEsterno" id="organoUteEsterno" cssClass="input-xlarge">
											<springform:options items="${organiEsterni}" itemLabel="descrizione" itemValue="id" />
										</springform:select>
										
									</span>
									<springform:errors path="organo" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="ruoloUtente">${ruoloHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:select path="ruolo" cssClass="input-xlarge" id="ruoloUtente">
											<springform:options items="${ruoli}" itemLabel="descrizione" itemValue="id" />
										</springform:select>
									</span>
									<springform:errors path="organoUteEsterno" cssClass="text-error"></springform:errors>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="flgAmministratore">${amministratoreHeader}</label>
								<div class="controls">
									<springform:checkbox path="amministratore" id="flgAmministratore" value="S"/>
								</div>
							</div>
							
							<springform:hidden path="organo" id="hiddenIdOrgano"/>
							<springform:hidden path="utenteAstage" id="hiddenUtenteAstage"/>
	
							
						</div>
					</div>
					
					<div class="control-group">
						<div class="pull-right">
							<button type="submit" class="btn btn-primary" id="salva" name="buttonSave" value="save">Salva &nbsp;<i class="icon-save"></i></button>
							<button type="submit" class="btn" id="annulla" name="buttonCancel" value="cancel">Annulla &nbsp;<i class="icon-undo"></i></button>
						</div>
					</div>
					
				</c:if>
				
			</div>
		</div>
	
	</springform:form>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>