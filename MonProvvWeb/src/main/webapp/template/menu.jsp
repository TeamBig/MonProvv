<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"	%>

<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.servlet_path']}" />
<c:forEach items="${firstLevelMenu}" var="menu">
	<c:forEach items="${menu.secondLevelList}" var="menu2liv">
		<c:if test="${fn:startsWith(currentUrl, menu2liv.funzione.url) }">
			<c:set var="idMenu2livSelected" value="${menu2liv.funzione.id }" />
			<c:set var="idMenu1livSelected" value="${menu.funzione.id }" />
		</c:if>
	</c:forEach>

</c:forEach>


<div class="navbar navbar-inner navbar-custom">
	<ul class="nav navbar-nav">
		<!-- 
		<li class="dropdown">
		    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		      Provvedimenti
		      <i class="icon-caret-down"></i>
		    </a>
		    <ul class="dropdown-menu">
      			<li><a href="#" id="vaiaricerca">Ricerca</a></li>
    		</ul>
  		</li>
  		 -->
  		 
  		 <li>
  		 	<spring:url value="/private/home" var="url_home" />
  		 	<a href="${url_home}" id="vaiaricerca">Home <i class="icon-home"></i></a>
  		 </li>
  		 
		<c:forEach items="${firstLevelMenu}" var="menu">
			<security:authorize
				access="hasPermission(#menu.funzione.url, 'urlPermission')">
				
				<c:if test="${not empty menu.secondLevelList}">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					      <c:out value="${menu.descrizione}" />
					      <i class="icon-caret-down"></i>
					    </a>
					    <ul class="dropdown-menu">
					    	<c:forEach items="${menu.secondLevelList}" var="menu2liv">
								<security:authorize  access="hasPermission(#menu2liv.funzione.url, 'urlPermission')">

							      	<spring:url var="rewriteUrl" value="${fn:replace(menu2liv.funzione.url, '/**', '')}" />

									<li>
										<a href="${rewriteUrl}" title="vai alla funzionalit&agrave; ${menu2liv.descrizione}"><c:out value="${menu2liv.descrizione}"/></a>
								    </li>
								</security:authorize>
							</c:forEach>
					    
			    		</ul>
					</li>
				</c:if>
				<c:if test="${empty menu.secondLevelList}">
					<li>
						<a href="${rewriteUrl}" title="vai alla funzionalit&agrave; ${menu.descrizione}"><c:out value="${menu.descrizione}"/></a>
					</li>
				</c:if>
			</security:authorize>
		</c:forEach>			
	</ul>
	<ul class="nav navbar-nav pull-right">	
		 
		  <li>
  		 	<spring:url value="/private/legenda/home" var="url_legenda" />
  		 	<a href="${url_legenda}">Legenda <i class="icon-question-sign"></i></a>
  		 </li>
  		 
		 <li>
  		 	<a href="https://portaleservizi.mef.gov.it/ps/aree_applicative/Rubrica/app/index_pers.html" target="_blank">Rubrica <i class="icon-book"></i></a>
  		 </li>
  		 
  		 <li>
  		 	<spring:url value="/private/provvedimenti/ricerca/downloadAllegato?id=-1" var="url_guida" />
  		 	<a href="${url_guida}">Guida <i class="icon-book"></i></a>
  		 </li>
  		
	</ul>		  		
	
	 

</div>