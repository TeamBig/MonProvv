<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<div class="container appbreadcrumb">
		<div class="row">
			<div class="span12">
				<span class="titoloBreadcrumb">Ti trovi in: </span>
				<span>Home &gt;&nbsp;
				
					<c:set var="path" value="${fn:replace(requestScope['javax.servlet.forward.request_uri'], '/home', '')}" />
					
					<c:forTokens
						items="${path}"
						delims="/" var="current" begin="1" varStatus="status">
						
						
						<c:set var="breadcrumb"
							value="${status.first ? '' : breadcrumb}${'/'}${current}" />
							
						<spring:url value="/{breadcrumb_url}" var="url">
							<spring:param name="breadcrumb_url" value="${breadcrumb}" />
						</spring:url>
					
						<c:set var="funzione_descrizione" value="" />
						<c:set var="funzione_linkable" value="" />
					
					
						<c:forEach items="${funzioni}" var="funzione">
							
							<c:set var="funzione_url" value="${fn:replace(funzione.url, '/**', '')}" />
							<c:if test="${breadcrumb == funzione_url}">
								<c:set var="funzione_descrizione" value="${funzione.descrizione}" />
								<c:set var="funzione_linkable" value="${funzione.linkable}" />
							</c:if>
					
						</c:forEach>
						<c:if test="${not empty funzione_descrizione}">
							<c:if test="${not status.last}">
								<c:choose>
									<c:when test="${not empty funzione_linkable}">
										<a href="${url}" title="vai alla pagina ${funzione_descrizione}"><c:out value="${funzione_descrizione}" /></a>
									</c:when>
									<c:otherwise>
										<c:out value="${funzione_descrizione}" />
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${status.last}">
								<c:out value="${funzione_descrizione}" />
							</c:if>
						</c:if>
						<c:if test="${not status.last}">
							 &nbsp;&gt;&nbsp; 
						</c:if>
					
					
					</c:forTokens>				
				
				
				
				
				</span>
			</div>
		</div>
	</div>



	