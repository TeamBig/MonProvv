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
						
						<spring:bind path="flagIntEst" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
							<springform:label path="flagIntEst" cssClass="control-label" cssErrorClass="control-label text-error" for="tipoNuovoUtente">${tipoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:select path="flagIntEst" cssClass="input-xlarge" id="tipoNuovoUtente">
											<springform:option value="">Sceglierne uno...</springform:option>
											<springform:options items="${tipos}" itemLabel="descrizione" itemValue="codice" />
										</springform:select>
									</span>
									<springform:errors path="flagIntEst" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>

						<div id="inserimentoUtenteInternoNominativoDiv">
							<spring:bind path="nominativoUtente" >
								<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
									<springform:label path="nominativoUtente" cssClass="control-label" cssErrorClass="control-label text-error" for="nominativoUtente">${nominativoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
									<div class="controls">
										<span>
											<springform:input path="nominativoUtente" id="nominativoUtente" cssClass="input-xlarge"/>	
										</span>
										<springform:errors path="nominativoUtente" cssClass="text-error help-inline"></springform:errors>
									</div>
								</div>
							</spring:bind>
						</div>
						
						<spring:bind path="cognome" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="cognome" cssClass="control-label" cssErrorClass="control-label text-error" for="cognome">${cognomeHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:input path="cognome" id="cognome" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="cognome" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="nome" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="nome" cssClass="control-label" cssErrorClass="control-label text-error" for="nome">${nomeHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:input path="nome" id="nome" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="nome" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="dataNascitaHidden" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="dataNascitaHidden" cssClass="control-label" cssErrorClass="control-label text-error" for="dataNascitaV">${datanascitaHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:input path="dataNascita" id="dataNascitaV" cssClass="input-xlarge dataValid"/>&nbsp;<i class="icon-calendar icon-large" id="dataNascita"></i>	
									</span>
									<springform:hidden path="dataNascitaHidden" id="dataNascitaHidden" />
									<springform:errors path="dataNascitaHidden" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="sesso" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="sesso" cssClass="control-label" cssErrorClass="control-label text-error" for="sesso">${sessoHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:select path="sesso" cssClass="input-xlarge" id="sesso">
											<springform:option value="">Sceglierne uno...</springform:option>
											<springform:options items="${sessos}" itemLabel="descrizione" itemValue="codice" />
										</springform:select>
									</span>	
									<springform:hidden path="sessoHidden" id="sessoHidden" cssClass="input-xlarge"/>
									<springform:errors path="sesso" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="codiceFiscale" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="codiceFiscale" cssClass="control-label" cssErrorClass="control-label text-error" for="codiceFiscale">${cfHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:input path="codiceFiscale" id="codiceFiscale" cssClass="input-xlarge"/>
									</span>
									<springform:errors path="codiceFiscale" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
						<spring:bind path="email" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="email" cssClass="control-label" cssErrorClass="control-label text-error" for="email">${emailHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:input path="email" id="email" cssClass="input-xlarge"/>	
									</span>
									<springform:errors path="email" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
							
						<div id="inserimentoUtenteInternoOrganoDiv">
							<spring:bind path="organo" >
								<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
									<springform:label path="organo" cssClass="control-label" cssErrorClass="control-label text-error" for="organoDenominazioneInterni">${enteHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
									<div class="controls">
										<span>
											<springform:input path="organoDenominazioneInterni" id="organoDenominazioneInterni" cssClass="input-xlarge" readonly="true"/>										
										</span>
										<springform:errors path="organo" cssClass="text-error help-inline"></springform:errors>
									</div>
								</div>
							</spring:bind>
						</div>
						
						<div id="inserimentoUtenteEsternoOrganoDiv">
							<spring:bind path="organo" >
								<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
									<springform:label path="organo" cssClass="control-label" cssErrorClass="control-label text-error" for="organoUteEsterno">${enteHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
									<div class="controls">
										<span>
											<springform:select path="organoUteEsterno" id="organoUteEsterno" cssClass="input-xlarge">
												<springform:option value="">Sceglierne uno...</springform:option>
												<springform:options items="${organiEsterni}" itemLabel="descrizione" itemValue="id" />
											</springform:select>
										</span>
										<springform:errors path="organo" cssClass="text-error help-inline"></springform:errors>
									</div>
								</div>
							</spring:bind>
						</div>
						
						<spring:bind path="ruolo" >
							<div class="control-group ${(not empty status.errorMessage) ? ' error':''}" >
								<springform:label path="ruolo" cssClass="control-label" cssErrorClass="control-label text-error" for="ruolo">${ruoloHeader} <sup style="font-weight: normal!important">(*)</sup></springform:label>
								<div class="controls">
									<span>
										<springform:select path="ruolo" cssClass="input-xlarge" id="ruolo">
											<springform:option value="">Sceglierne uno...</springform:option>
											<springform:options items="${ruoli}" itemLabel="descrizione" itemValue="id" />
										</springform:select>
									</span>
									<springform:errors path="ruolo" cssClass="text-error help-inline"></springform:errors>
								</div>
							</div>
						</spring:bind>
						
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