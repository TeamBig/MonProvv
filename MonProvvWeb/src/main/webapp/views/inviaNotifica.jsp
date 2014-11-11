<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<c:url value="/private/provvedimenti/ricerca/salvaenotifica" var="formPath" />

<springform:form action="${formPath}" method="GET" modelAttribute="salvaenotifica" cssClass="bo clfix" id="formEmailSalvaENotifica">

	<springform:hidden path="idProvvedimento" id="idProvvedimento" />

	<div class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="email">Email</label>
			<div class="controls">

				<springform:input path="destinatari" data-role="tagsinput" id="tokenfieldemail" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="oggetto">Oggetto</label>
			<div class="controls">
				<springform:input path="oggetto" cssClass="input-xlarge" id="oggetto" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="art">Testo</label>
			<div class="controls">
			<springform:textarea path="testo" cssClass="input-xlarge" rows="10" id="testo" />
			</div>
		</div>
	</div>

</springform:form>
