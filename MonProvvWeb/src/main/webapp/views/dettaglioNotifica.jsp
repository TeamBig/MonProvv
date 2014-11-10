<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="container" id="risultatiRicerca">
	<div class="row">
		<div class="span12">
			<h3 class="text-left underline"><span>Dettaglio notifica</span></h3>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<div class="form-horizontal dettaglio">
				<div class="control-group">
					<label class="control-label">Oggetto</label>
					<div class="controls">
						<span class="${notifica.flagLettura == 'S' ? 'letta' : 'nonletta' }" >${notifica.oggetto}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Testo</label>
					<div class="controls">
						<span>${notifica.testo}</span>
					</div>
				</div>		
					
				<c:if test="${notifica.tipoNotifica == 'O'}">
					<div class="control-group">
						<div class="controls">
							<spring:url value="/private/notifiche/gestione?id=${notifica.id}" var="gestioneNotifica"  />
							<a href="${gestioneNotifica}">Clicca qui</a> per gestire la richiesta
						</div>
					</div>		
				</c:if>
			</div>	
		</div>
	</div>
	

</div>