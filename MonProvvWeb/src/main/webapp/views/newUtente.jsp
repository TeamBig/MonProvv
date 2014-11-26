<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

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


<div class="container" id="inserimentoUtente">

	<c:url value="/private/admin/utenti/nuovo" var="formPath" />

	<springform:form action="${formPath}" method="POST" commandName="utenteToEdit" cssClass="form-horizontal" id="formIsertUtente">
		<div class="row">
			<div class="span12">
			
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>
				
				<h3 class="text-left underline">
					<span>Nuovo Utente</span>
				</h3>
				
				<div class="row">
					<div class="offset0 text-left">
						<p>(<sup id="n2">*</sup>) <em>Campi obbligatori</em></p>
					</div>
					<div class="span10 offset2 dettaglio">	
						<div class="control-group">
							<label class="control-label" for="tipoNuovoUtente">${tipoHeader}</label>
							<div class="controls">
								<span>
									<springform:select path="flagIntEst" cssClass="input-xlarge" id="tipoNuovoUtente">
										<springform:option value=""></springform:option>
										<springform:options items="${tipos}" itemLabel="descrizione" itemValue="codice" />
									</springform:select>
								</span>
								<springform:errors path="flagIntEst" cssClass="text-error"></springform:errors>
							</div>
						</div>

						<div id="inserimentoUtenteInternoNominativoDiv">
							<div class="control-group" >
								<label class="control-label" for=nominativoUtente>${nominativoHeader}</label>
								<div class="controls">
									<span>
										<springform:input path="nominativoUtente" id="nominativoUtente" cssClass="input-xlarge"/>	
									</span>
									<springform:errors path="nominativoUtente" cssClass="text-error"></springform:errors>
								</div>
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
							<label class="control-label" for="nome">${nomeHeader}<sup style="font-weight: normal!important">(*)</sup> </label>
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
										<springform:option value=""></springform:option>
										<springform:options items="${sessos}" itemLabel="descrizione" itemValue="codice" />
									</springform:select>
								</span>	
								<springform:hidden path="sessoHidden" id="sessoHidden" cssClass="input-xlarge"/>
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
							
						<div id="inserimentoUtenteInternoOrganoDiv">
							<div class="control-group" >	
								<label class="control-label" for="organoDenominazioneInterni">${enteHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<springform:input path="organoDenominazioneInterni" id="organoDenominazioneInterni" cssClass="input-xlarge" readonly="true"/>										
									</span>
									<springform:errors path="organo" cssClass="text-error"></springform:errors>
								</div>
							</div>
						</div>
						
						<div id="inserimentoUtenteEsternoOrganoDiv">
							<div class="control-group" >	
								<label class="control-label" for="organoUteEsterno">${enteHeader} <sup style="font-weight: normal!important">(*)</sup></label>
								<div class="controls">
									<span>
										<%-- 
										<springform:input path="organoDenominazione" id="organoDenominazioneEst" cssClass="input-xlarge"/>
										--%>
										<springform:select path="organoUteEsterno" id="organoUteEsterno" cssClass="input-xlarge">
											<springform:option value=""></springform:option>
											<springform:options items="${organiEsterni}" itemLabel="descrizione" itemValue="id" />
										</springform:select>
									</span>
									<springform:errors path="organo" cssClass="text-error"></springform:errors>
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="ruolo">${ruoloHeader} <sup style="font-weight: normal!important">(*)</sup></label>
							<div class="controls">
								<span>
									<springform:select path="ruolo" cssClass="input-xlarge" id="ruolo">
										<springform:option value=""></springform:option>
										<springform:options items="${ruoli}" itemLabel="descrizione" itemValue="id" />
									</springform:select>
								</span>
								<springform:errors path="ruolo" cssClass="text-error"></springform:errors>
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
			</div>
		</div>
	
	</springform:form>
	<div class="row">
		<div class="span12"></div>
	</div>
</div>