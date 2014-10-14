<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html lang="en">
<head>
	<meta charset="utf-8">
	<tiles:importAttribute name="titolo" />
	<title><spring:message code="${titolo}" text="#Template#" /> - Monitoraggio Provvedimenti</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="content-language" content="it" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<spring:url value="/" var="base" />

	<link href="${base}css/normalize.css" rel="stylesheet" />
	<link href="${base}css/bootstrap.min.css" rel="stylesheet">
	<link href="${base}css/font-awesome.min.css" rel="stylesheet" >
	<link href="${base}css/font-awesome-ext.css" rel="stylesheet" >
	<link href="${base}css/bootstrap-multiselect.css" rel="stylesheet" >
	<link href="${base}css/bootstrap-datepicker.css" rel="stylesheet" >
	<link href="${base}css/style.css" rel="stylesheet">

	<!--[if IE 7]>
	  <link href="${base}/css/font-awesome-ie7.min.css" rel="stylesheet" >
	<![endif]-->

	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="${base}/js/html5shiv.js"></script>
	<![endif]-->

	<!-- Fav icons -->
	<link rel="shortcut icon" href="${base}img/favicon.ico" type="x-icon">
	<link rel="icon" href="${base}img/favicon.ico" type="x-icon">
  
	<script type="text/javascript" src="${base}js/jquery.min.js"></script>
	<script type="text/javascript" src="${base}js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${base}js/bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="${base}js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${base}js/locales/bootstrap-datepicker.it.js"></script>
	<script type="text/javascript" src="${base}js/scripts.js"></script>
	
</head>

<body>
	<div class="hide">
		<ul>
			<li >Menu di accesso rapido del sito MONITORAGGIO PROVVEDIMENTI:
				<ul>
					<li><a href="#login" title="Vai all'area informativa utente">Vai all'area informativa utente</a> </li>
					<li><a href="#menu" title="Vai al menu delle funzionalit&agrave; applicative">Vai al menu delle funzionalit&agrave; applicative</a> </li>
					<li><a href="#breadcrumb" title="Vai al menu di navigazione">Vai al menu di navigazione</a> </li>
					<li><a href="#headerAnno" title="Vai al titolo della pagina">Vai al titolo della pagina</a> </li>
					<li><a href="#content" title="Vai al contenuto della pagina">Vai al contenuto della pagina</a> </li>
					<li><a href="#footer" title="Vai alla fine della pagina">Vai alla fine della pagina</a> </li>
				</ul>
			</li>
		</ul>
	</div>

	<div class="container header">
		<div class="row">
			<div class="span7">
				<h1 class="titolo"><img alt="Monitoraggio Provvedimenti" src="${base}/img/mon_prov_header.png"/>€</h1>
			</div>
			<div class="span5" id="login">
				<tiles:insertAttribute name="header" />
				
				<div class="pull-right img_logomef">
					<img class="pull-right" src="${base}img/logo_mef.png" alt="Logo - MEF (ministero dell'Economia e delle Finanze)" />
				</div>	
			</div>
		</div>
		<div class="row">
			<div class="span12">		
				<tiles:insertAttribute name="menu" />	
			</div>
		</div>
	</div>

	
	<tiles:insertAttribute name="breadcrumb" />
	
	<tiles:insertAttribute name="alert" />
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>
	
	
	<tiles:insertAttribute name="footer" />
	
</body>
</html>
