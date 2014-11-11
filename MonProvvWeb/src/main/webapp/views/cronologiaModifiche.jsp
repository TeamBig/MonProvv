<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

			<display:table 	name="${listaStorico}" 
								requestURI="" sort="external" partialList="false"
								 id="storico" 
								class="table table-hover table-bordered"
								summary="Elenco Allegati" style="width: 100%">

					<display:column title="Data" property="dataOperazione" headerScope="col" />
					<display:column title="Operazione" headerScope="col" >
						<c:out value="${storico.tipoOperazione} ${storico.tipoEntita}"></c:out>
					</display:column>
					<display:column title="Organo" property="idUtenteOperazione.organo.denominazione" headerScope="col" />
					<display:column title="Utente" property="utenteInserimento" headerScope="col" />
			</display:table>

</body>
</html>