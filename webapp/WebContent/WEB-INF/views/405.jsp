<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:setLocale value="${lang}" />
<spring:setBundle basename="com.computerDatabase.excilys.i18n.translate" />
<html lang="${lang}">
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message key="label.405" />
				<br />
			</div>
		</div>
	</section>

	<script src="../../js/jquery.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/dashboard.js"></script>

</body>
<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" onclick="location.href='?lang=EN'"
				class="btn btn-default">EN</button>
			<button type="button" onclick="location.href='?lang=FR'"
				class="btn btn-default">FR</button>
		</div>
	</div>
</footer>
</html>