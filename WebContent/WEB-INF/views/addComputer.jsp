<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="com.computerDatabase.excilys.i18n.translate" />
<html lang="${lang}">
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="css/font-awesome.css" rel="stylesheet" media="screen">
		<link href="css/main.css" rel="stylesheet" media="screen">
	</head>
	<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><fmt:message key="label.addComputer" /></h1>
                    <form action="addComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><fmt:message key="label.computerName" /></label>
                                <input type="text" class="form-control" id="computerName" placeholder="<fmt:message key="label.computerName" />">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><fmt:message key="label.introducedDate" /></label>
                                <input type="date" class="form-control" id="introduced" placeholder="<fmt:message key="label.introducedDate" />e">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><fmt:message key="label.discontinuedDate" /></label>
                                <input type="date" class="form-control" id="discontinued" placeholder="<fmt:message key="label.discontinuedDate" />">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><fmt:message key="label.company" /></label>
                                <select class="form-control" id="companyId" >
                                    <option value="0">--</option>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<fmt:message key="label.add" />" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default"><fmt:message key="label.cancel" /></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <footer class="navbar-fixed-bottom">
		<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" onclick="location.href='?lang=EN';" class="btn btn-default">EN</button>
			<button type="button" onclick="location.href='?lang=FR';" class="btn btn-default">FR</button>
		</div>
	</footer>
	</body>
</html>