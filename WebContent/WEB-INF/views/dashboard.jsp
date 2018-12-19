<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:setLocale value="${lang}"/>
<spring:setBundle basename="com.computerDatabase.excilys.i18n.translate" />
<html lang="${lang}">
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
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
			<h1 id="homeTitle">
				<c:out value="${computersSize}" /> <spring:message key="label.searchNumber" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
                    	<input type="search" id="searchbox" name="search" class="form-control" placeholder="<spring:message key="label.searchInput" />" />
                        <input type="submit" id="searchsubmit" value="<spring:message key="label.searchFilter" />" onclick="location.href='?search=" class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer?lang=${lang}"><spring:message key="label.addComputer" /></a>
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message key="label.deleteComputer" /></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        	<div class="container" style="margin-top: 10px;">
            	<table class="table table-striped table-bordered" >
                	<thead>
						<tr>
                        	<!-- Variable declarations for passing labels as parameters -->
                        	<!-- Table header for Computer Name -->

                        	<th class="editMode" style="width: 60px; height: 22px;">
                            	<input type="checkbox" id="selectall" />
                            	<span style="vertical-align: top;">
                                	<a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                    	<i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            	</span>
                        	</th>
                        	<th>
								<a href="#" onclick="location.href='?sortBy=name&orderBy=${orderBy}'"><spring:message key="label.computerName" /></a>
							</th>
                        	<th>
                            	<a href="#" onclick="location.href='?sortBy=introduced&orderBy=${orderBy}'"><spring:message key="label.introducedDate" /></a>
							</th>
							<th>
                            	<a href="#" onclick="location.href='?sortBy=discontinued&orderBy=${orderBy}'"><spring:message key="label.discontinuedDate" /></a>
                        	</th>
                        	<th>
								<a href="#" onclick="location.href='?sortBy=companyName&orderBy=${orderBy}'"><spring:message key="label.company" /></a>
							</th>

						</tr>
                	</thead>
                	<!-- Browse attribute computers -->
                	<tbody id="results">
                		<c:forEach items="${computers}" var="computer">
                			<tr>
                        		<td class="editMode">
                            		<input type="checkbox" name="cb" class="cb" value="${computer.id}">
                        		</td>
                        		<td>
                            		<a href="editComputer?computerId=${computer.id}&lang=${lang}" onclick=""><c:out value="${computer.name}"/></a>
                        		</td>
                        		<td><c:out value="${computer.introduced}"/></td>
								<td><c:out value="${computer.discontinued}"/></td>
                        		<td><c:out value="${computer.companyName}"/></td>
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
                <li>
                <a href="#" onclick="location.href='?number=${number}&page=${previousPage}'" aria-label="Previous">
                	<span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <c:forEach items="${pageArray}" var="page">
              	<li><a href="#" onclick="location.href='?number=${number}&page=${page}';">${page}</a></li>
              </c:forEach>
              <li>
                <a href="#" onclick="location.href='?number=${number}&page=${nextPage}'" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
              </li>
            </ul>
            
       	<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" onclick="location.href='?number=10&page=1&lang=${lang}';" class="btn btn-default">10</button>
			<button type="button" onclick="location.href='?number=50&page=1&lang=${lang}';" class="btn btn-default">50</button>
			<button type="button" onclick="location.href='?number=100&page=1&lang=${lang}';" class="btn btn-default">100</button>
		</div>
		<div class="btn-group btn-group-sm pull-right" role="group">
			<button type="button" onclick="location.href='?number=${number}&page=${pageNumber}&lang=EN';" class="btn btn-default">EN</button>
			<button type="button" onclick="location.href='?number=${number}&page=${pageNumber}&lang=FR';" class="btn btn-default">FR</button>
		</div>
        </div>
    </footer>
    
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

	</body>
</html>