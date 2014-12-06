<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="apple-touch-icon" href='images/favicon.png'>
		<link rel='icon' type='image/png' href='images/favicon.png'/>
		<link rel="apple-touch-icon-precomposed" href='images/favicon.png'>
		<title>Sign In</title>
		<link href="styles/application.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<form class="form-signin" action="MetadataApi" method="POST">
				<h2 class="form-signin-heading">Please sign in</h2>
				<label for="inputEmail" class="sr-only">Email address</label>
				<input type="email" id="inputEmail" name="USER_NAME_1" class="form-control" placeholder="Email address" autofocus>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword"  name="USER_PASSWORD_1" class="form-control" placeholder="Password">
				<div class="checkbox">
					<label>
						<input name="FULL_OBJECT_DESCRIPTION" id="chkDescription" type="checkbox" value="false" onclick="onCheckBoxClick()"> Show Full Description
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
		</div>
		<script type="text/javascript">
			function onCheckBoxClick() {
				var chkRemember = document.getElementById("chkDescription");
				if (chkRemember != null) {
					if (chkRemember.checked) {
						chkRemember.value = "true";
					} else {
						chkRemember.value = "false";
					}
				}
			}
		</script>
	</body>
</html>