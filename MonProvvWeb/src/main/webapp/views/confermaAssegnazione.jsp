<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">
			<h3 class="text-left underline"><span>Richiesta assegnazione</span></h3>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<div class="form-horizontal dettaglio">
				<div class="control-group">
					<label class="control-label">Provvedimento</label>
					<div class="controls">
						<span>${assegnazione.provvedimento.governo.denominazione}&nbsp;${assegnazione.provvedimento.id}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Fonte normativa</label>
					<div class="controls">
						<span>${assegnazione.provvedimento.fonteNormativa}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Titolo/Oggetto</label>
					<div class="controls">
						<span>${assegnazione.provvedimento.oggettoAsText}</span>
					</div>
				</div>				
				<div class="control-group">
					<label class="control-label">Organo richiedente</label>
					<div class="controls">
						<span>${assegnazione.organo.denominazione}</span>
					</div>
				</div>		
				<div class="control-group">
					<label class="control-label">Utente richiedente</label>
					<div class="controls">
						<span>${notifica.utenteMittente.nome}&nbsp;${notifica.utenteMittente.cognome}</span>
					</div>
				</div>							

				<div class="control-group">
					<label class="control-label">Motivazione richiesta</label>
					<div class="controls">
						<span>${assegnazione.motivazioneRichiestaAsText}</span>
					</div>
				</div>		
				
				
			</div>	
		</div>
	</div>
	<security:authorize access="hasPermission(#notifica, 'gestioneNotificaOperativa')">
		<div class="row">
			<div class="span12">
				<div class="form-horizontal">
					<div class="control-group">
						<div class="form-actions pull-right">
							
							<springform:form modelAttribute="assegnazione"> 
								<button type="submit" class="btn btn-primary" name="accettaRichiestaAssegnazione">Accetta&nbsp;<i class="icon-ok"></i></button>
								<button type="submit" class="btn" name="rifiutaRichiestaAssegnazione">Rifiuta&nbsp;<i class="icon-remove"></i></button>
							</springform:form> 

						

						</div>
					</div>
				</div>
			</div>
		</div>	
	</security:authorize>	

</div>