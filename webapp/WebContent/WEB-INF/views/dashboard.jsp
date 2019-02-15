<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
					<c:out value="${computersSize}" /> computers found.
				</h1>
				<div id="actions" class="form-horizontal">
					<div class="pull-left">
						<form id="searchForm" action="#" method="GET" class="form-inline">
	                    	<input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
	                        <input type="submit" id="searchsubmit" value="Filter by name" onclick="location.href='?search=" class="btn btn-primary" />
	                    </form>
	                </div>
	                <div class="pull-right">
	                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
	                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Delete</a>
	                </div>
	            </div>
	        </div>
	
	        <form id="deleteForm" action="#" method="POST">
	            <input type="hidden" name="selection" value="">
	        	<div class="container" style="margin-top: 10px;">
	            	<table id="table" class="table table-striped table-bordered" >
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
									Computer name
								</th>
	                        	<th>
	                            	Introduced date
								</th>
								<th>
	                            	Discontinued date
	                        	</th>
	                        	<th>
									Company
								</th>
	
							</tr>
	                	</thead>
	                	<!-- Browse attribute computers -->
	                	<tbody id="results">
	                		<c:forEach items="${computers}" var="item">
	                			<tr>
	                        		<td class="editMode">
	                            		<input type="checkbox" name="cb" class="cb" value="${item.id}">
	                        		</td>
	                        		<td>
	                            		<a href="editComputer?computerId=${item.id}" onclick=""><c:out value="${item.name}"/></a>
	                        		</td>
	                        		<td><c:out value="${item.introduced}"/></td>
									<td><c:out value="${item.discontinued}"/></td>
	                        		<td><c:out value="${item.companyId}"/></td>
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
	                <a href="#" onclick="location.href='?number=${number}&pageNumber=${previousPage}'" aria-label="Previous">
	                	<span aria-hidden="true">&laquo;</span>
	                </a>
	              </li>
	              <c:forEach items="${pageArray}" var="item">
	              	<li><a href="#" onclick="location.href='?number=${number}&pageNumber=${item}';">${item}</a></li>
	              </c:forEach>
	              <li>
	                <a href="#" onclick="location.href='?number=${number}&pageNumber=${nextPage}'" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>
	              </li>
	            </ul>
	            
	       	<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" onclick="location.href='?number=10&pageNumber=1';" class="btn btn-default">10</button>
				<button type="button" onclick="location.href='?number=50&pageNumber=1';" class="btn btn-default">50</button>
				<button type="button" onclick="location.href='?number=100&pageNumber=1';" class="btn btn-default">100</button>
			</div>
	        </div>
	    </footer>
	    
	    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/dashboard.js"></script>
	    <script src="js/jquery.tablesorter.js"></script>
	    <script src="js/sort.js"></script>
	</body>
</html>