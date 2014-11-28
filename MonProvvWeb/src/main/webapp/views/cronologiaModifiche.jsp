<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<display:table 	name="${listaStorico}" 
					requestURI="" sort="external" partialList="false"
					 id="storico" 
					class="table table-hover table-bordered"
					summary="Elenco Allegati" style="width: 100%">

		<display:column title="Data" headerScope="col">
			<fmt:formatDate value="${storico.dataOperazione}" pattern="dd/MM/yyyy HH:mm" />
		</display:column>
		<display:column title="Operazione" headerScope="col" >
			<c:out value="${storico.tipoOperazione} ${storico.tipoEntita}"></c:out>
		</display:column>
		<display:column title="Organo" property="utenteOperazione.organo.denominazione" headerScope="col" />
		<display:column title="Utente" headerScope="col" >
			${storico.utenteOperazione.nome}&nbsp;${storico.utenteOperazione.cognome}
		</display:column>
</display:table>

