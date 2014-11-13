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

	<springform:form action="${formPath}" method="POST" commandName="utenteToEdit" cssClass="form-horizontal">
		<div class="row">
			<div class="span12">
			
				<springform:hidden path="id"/>
				<springform:hidden path="versione"/>
				
				<h3 class="text-left underline">
					<span>Nuovo Utente</span>
				</h3>
				
				<div class="row">
					<div class="span10 offset2 dettaglio">

						<div class="control-group">
							<label class="control-label" for="flagIntEst">${tipoHeader}</label>
							<div class="controls">
								<springform:select path="flagIntEst" cssClass="input-xlarge" id="tipoNuovoUtente">
									<springform:option value=""></springform:option>
									<springform:options items="${tipos}" itemLabel="descrizione" itemValue="codice" />
								</springform:select>
							</div>
						</div>

						<div id="inserimentoUtenteInternoDiv">
							<div class="control-group" >
								<label class="control-label" for="cognome">${nominativoHeader}</label>
								<div class="controls">
									<span>
										<springform:input path="nominativo" id="nominativoUtente" cssClass="input-xlarge"/>
									</span>
								</div>
							</div>
						</div>
						
						
						<div class="control-group" >
							<label class="control-label" for="cognome">${cognomeHeader}</label>
							<div class="controls">
								<span>
									<springform:input path="cognome" id="cognome" cssClass="input-xlarge"/>
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<label class="control-label" for="nome">${nomeHeader}</label>
							<div class="controls">
								<span>
									<springform:input path="nome" id="nome" cssClass="input-xlarge"/>
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<label class="control-label" for="dataNascita">${datanascitaHeader}</label>
							<div class="controls">
								<span>
									<springform:input path="dataNascita" id="dataNascitaV" cssClass="input-xlarge"/>&nbsp;<i class="icon-calendar icon-large" id="dataNascita"></i>
								</span>
							</div>
						</div>
						
						<div class="control-group" >
							<label class="control-label" for="sesso">${sessoHeader}</label>
							<div class="controls">
								<springform:select path="sesso" cssClass="input-xlarge" id="sesso">
									<springform:option value=""></springform:option>
									<springform:options items="${sessos}" itemLabel="descrizione" itemValue="codice" />
								</springform:select>
							</div>
						</div>
						
						<div class="control-group" >
							<label class="control-label" for="codiceFiscale">${cfHeader}</label>
							<div class="controls">
								<span>
									<springform:input path="codiceFiscale" id="codiceFiscale" cssClass="input-xlarge"/>
								</span>
							</div>
						</div>
						
						<div class="control-group" >	
							<label class="control-label" for="email">${emailHeader}</label>
							<div class="controls">
								<span>
									<springform:input path="email" id="email" cssClass="input-xlarge"/>
								</span>
							</div>
						</div>
							
						<div id="inserimentoUtenteInternoDivOrg">
							<div class="control-group" >	
								<label class="control-label" for="ente">${enteHeader}</label>
								<div class="controls">
									<span>
										<springform:input path="organoDenominazioneInterni" id="organoDenominazioneInterni" cssClass="input-xlarge" readonly="true"/>
									</span>
								</div>
							</div>
						</div>
						
						<div id="inserimentoUtenteEsternoDiv">
							<div class="control-group" >	
								<label class="control-label" for="ente">${enteHeader}</label>
								<div class="controls">
									<span>
										<springform:input path="organoDenominazione" id="organoDenominazioneEst" cssClass="input-xlarge"/>
									</span>
								</div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="ruolo">${ruoloHeader}</label>
							<div class="controls">
								<springform:select path="ruolo" cssClass="input-xlarge" id="ruoloUtente">
									<springform:option value=""></springform:option>
									<springform:options items="${ruoli}" itemLabel="descrizione" itemValue="id" />
								</springform:select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="ruolo">${amministratoreHeader}</label>
							<div class="controls">
								<springform:checkbox path="amministratore" value="S"/>
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