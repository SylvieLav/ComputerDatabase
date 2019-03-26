<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
			<div class="pull-right">
				<a class="btn btn-default" href="logout"><spring:message
						key="label.logout" /></a>
			</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${computersSize}" />
				<spring:message key="label.computersFound" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							onclick="search=" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							key="label.addComputer" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							key="label.deleteComputer" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
			<div class="container" style="margin-top: 10px;">
				<table id="table" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th class="editMode" style="width: 60px; height: 22px;"><input
								type="checkbox" id="selectall" /> <span
								style="vertical-align: top;"> <a href="#"
									id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
										class="fa fa-trash-o fa-lg"></i>
								</a>
							</span></th>
							<th><a type="text"
								onclick="location.href='?sortElement=name&orderBy=${orderBy}'"><spring:message
										key="label.name" /></a></th>
							<th><a type="text"
								onclick="location.href='?sortElement=introduced&orderBy=${orderBy}'"><spring:message
										key="label.introducedDate" /></a></th>
							<th><a type="text"
								onclick="location.href='?sortElement=discontinued&orderBy=${orderBy}'"><spring:message
										key="label.discontinuedDate" /></a></th>
							<th><a type="text"
								onclick="location.href='?sortElement=companyName&orderBy=${orderBy}'"><spring:message
										key="label.company" /></a></th>

						</tr>
					</thead>
					<tbody id="results">
						<c:forEach items="${computers}" var="item">
							<tr>
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value="${item.id}"></td>
								<td><a href="editComputer/${item.id}" onclick=""><c:out
											value="${item.name}" /></a></td>
								<td><c:out value="${item.introduced}" /></td>
								<td><c:out value="${item.discontinued}" /></td>
								<td><c:out value="${item.company.name}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a onclick="location.href='?number=${number}&pageNumber=1'"
					aria-label="First Page"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach items="${pageArray}" var="item">
					<li><a
						onclick="location.href='?number=${number}&pageNumber=${item}';">${item}</a></li>
				</c:forEach>
				<li><a
					onclick="location.href='?number=${number}&pageNumber=${lastPage}'"
					aria-label="Last Page"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button"
					onclick="location.href='?number=10&pageNumber=1'"
					class="btn btn-default">10</button>
				<button type="button"
					onclick="location.href='?number=50&pageNumber=1'"
					class="btn btn-default">50</button>
				<button type="button"
					onclick="location.href='?number=100&pageNumber=1'"
					class="btn btn-default">100</button>
			</div>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" onclick="location.href='?lang=EN'"
					class="btn btn-default">EN</button>
				<button type="button" onclick="location.href='?lang=FR'"
					class="btn btn-default">FR</button>
			</div>
		</div>
	</footer>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>
</body>
</html>