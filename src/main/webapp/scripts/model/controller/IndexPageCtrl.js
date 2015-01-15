angular.module(app_name).controllerProvider.register('IndexPageCtrl', function($scope, $injector) {
	
	$injector.invoke(angular.module(app_name)['appCtrl'], this, {$scope: $scope});
	
	/**
	 * function to initialize required parameters
	 */
	$scope.init = function() {
		// read user name
		this.userName = localStorage.getItem('user_name');
	};
	
	/**
	 * function triggered when cross button with error is clicked
	 * @param event JSON object with event information
	 */
	$scope.dismissError = function(event) {
		$scope.errors = null;
	};
	
	/**
	 * function to submit login form
	 * @param event JSON object with event information
	 */
	$scope.onFormSubmission = function(event) {

		var postData = {};
		$scope.errors = null;
		var actionName = "MetadataApi";
		
		var usrNameEl = document.getElementById('inputEmail');
		if (usrNameEl != null 
				&& usrNameEl.value != null 
				&& usrNameEl.value.trim() != '') {
			postData['USER_NAME_1'] = usrNameEl.value;
		} else {
			var errors = [{
				message: 'Please provide a valid user name',
				number: '110001'
			}];
			
			$scope.errors = errors;
			
			return;
		}
		
		var passwordEl = document.getElementById('inputPassword');
		if (passwordEl != null 
				&& passwordEl.value != null 
				&& passwordEl.value.trim() != '') {
			postData['USER_PASSWORD_1'] = encodeURI(passwordEl.value);
		} else {
			var errors = [{
				message: 'Please provide a valid password',
				number: '110002'
			}];
			
			$scope.errors = errors;
			return;
		}
		
		var chkRememberEl = document.getElementById('chkRemember');
		if (chkRememberEl != null && chkRememberEl.checked) {
			// save user name to local storage
			localStorage.setItem('user_name', postData['USER_NAME_1']);
		}
		
		this.doPost({
			url: actionName,
			data: postData,
			onSuccessCallback: this._onSuccessCallback
		});
	};
	
	/**
	 * callback function after login response is received
	 * @param data JSON response
	 */
	$scope._onSuccessCallback = function(data) {
		
		// look for errors
		if (data.users != null 
				&& data.users[0] != null 
				&& data.users[0].messages != null 
				&& data.users[0].messages.length > 0) {
			$scope.errors = data.users[0].messages;
		}
		
		// don't navigate if errors are present
		if ($scope.errors == null || $scope.errors.length == 0) {
			$scope.navigate({
				page: 'object'
			});
		}
	};
	
	$scope.init();
});