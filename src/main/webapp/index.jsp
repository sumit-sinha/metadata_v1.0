<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="images/favicon.png">
		<title>Metadata</title>

		<link href="styles/bootstrap.min.css" rel="stylesheet">
		<link href="styles/application.css" rel="stylesheet">
	</head>
	<body>
		
		<div ng-app="metadata">
			<header-html></header-html>
			<div ng-view></div>
			<div class="msk loading hidden">&nbsp;</div>
		</div>
		
		<script src="scripts/app/scripts.js"></script>
		<script src="scripts/app/angular.min.js"></script>
        <script src="scripts/app/angular-route.min.js"></script>
        
        <script type="text/javascript">
        	onLoad();
        </script>
	</body>
</html>