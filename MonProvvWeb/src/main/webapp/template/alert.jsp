<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:if test="${not empty alertType }">
	<div class="container">
		<div class="row top10">
			<div class="span12">
				<div id="alert" class="alert ${alertType}">
					<c:if test="${alertStyle == 'single'  }">
						<span ><c:out value="${alertMessage}" /></span>
					</c:if>
					<c:if test="${alertStyle == 'multiple' }">
						<ul><c:forTokens items="${alertMessage}" delims="${alertDelimeter}"
								var="message"><li><c:out value="${message}" /></li></c:forTokens>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</c:if>