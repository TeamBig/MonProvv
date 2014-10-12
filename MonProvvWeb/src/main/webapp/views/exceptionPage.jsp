<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div id="alert" class="alert alert-error">
					<span ><spring:message code="500.messaggio" /></span>
				</div>
			</div>
		</div>
	</div>
	
	<spring:eval expression="@config.getProperty('oam.abilitato')" var="oamAbilitato"/>
	<c:if test="${oamAbilitato == 'N'}">
		<hr/>
		<div class="container">
			<div class="row">
				<div class="span12">
					<pre>
						<spring:eval expression="T(org.apache.commons.lang3.exception.ExceptionUtils).getStackTrace(exception)"/>
					</pre>
				</div>
			</div>				
		</div>
	</c:if>
</html>