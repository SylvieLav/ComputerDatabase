<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form action="login" method="post">
			<div>
				<input type="text" name="username" placeholder="Username" />
			</div>
			<div>
				<input type="password" name="password" placeholder="Password" />
			</div>
			<div>
				<input type="submit" value="Connect" />
			</div>
		</form>
	</body>
</html>