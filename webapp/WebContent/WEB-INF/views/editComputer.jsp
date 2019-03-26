<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt"%>
<spring:setLocale value="${lang}" />
<spring:setBundle basename="com.computerDatabase.excilys.i18n.translate" />
<html lang="${lang}">
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link href="../static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="../static/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="../static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="../dashboard"> Application -
				Computer Database </a>
			<div class="pull-right">
				<a class="btn btn-default" href="logout"><spring:message
						key="label.logout" /></a>
			</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Edit Computer</h1>

					<form:form method="POST" modelAttribute="computerDTO" action="">
						<input type="hidden" value="0" id="id" />
						<fieldset>
							<div class="form-group">
								<form:label path="name">
									<spring:message key="label.name" />*</form:label>
								<form:input type="text" class="form-control" path="name"
									id="name" value="${computerDTO.name}" />
							</div>
							<div class="form-group">
								<form:label path="introduced">
									<spring:message key="label.introducedDate" />
								</form:label>
								<form:input type="date" class="form-control" path="introduced"
									id="introduced" value="${computerDTO.introduced}" />
							</div>
							<div class="form-group">
								<form:label path="discontinued">
									<spring:message key="label.discontinuedDate" />
								</form:label>
								<form:input type="date" class="form-control" path="discontinued"
									id="discontinued" />
							</div>
							<div class="form-group">
								<form:label path="companyId">
									<spring:message key="label.company" />
								</form:label>
								<form:select class="form-control" path="companyId"
									id="companyId" name="companyId">
									<form:option value="0">--</form:option>
									<c:forEach items="${companies}" var="company">
										<form:option value="${company.id}">${company.id}-
											${company.name}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message key="label.edit" />"
								class="btn btn-primary"> <a href="../dashboard"
								class="btn btn-default"><spring:message key="label.cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button"
					onclick="location.href='/${computerId}?lang=EN'"
					class="btn btn-default">EN</button>
				<button type="button"
					onclick="location.href='/${computerId}?lang=FR'"
					class="btn btn-default">FR</button>
			</div>
		</div>
	</footer>
</body>
</html>