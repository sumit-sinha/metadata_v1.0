angular.module(app_name).controllerProvider.register('ObjectPageCtrl', function($scope, $injector) {
	
	$injector.invoke(angular.module(app_name)['appCtrl'], this, {$scope: $scope});
	
	/**
	 * function triggered when cross button with error is clicked
	 * @param args JSON object with parameters
	 */
	$scope.dismissError = function(args) {
		$scope[args.key] = null;
	};
	
	/**
	 * function called when user types anything in text box
	 * @param event JSON object with event information
	 */
	$scope.onTextInput = function(event) {
		
		var items = [];
		var objectEl = document.getElementById('txObject');
		var objectList = $scope.data.getData({key: 'index'});
		
		if (objectEl !== null && objectEl.value != '') {
			for (var i = 0; i < objectList.length; i++) {
				if (objectList[i].object.name.toLowerCase().indexOf(objectEl.value.toLowerCase()) !== -1) {
					items.push(objectList[i].object.name);
				}
			}
		}
		
		if (items.length == 0) {
			items = null;
		}
		
		$scope.items = items;
	};
	
	/**
	 * function triggered when any item on autocomplete is clicked
	 * @param args JSON object with inputs
	 */
	$scope.onItemClick = function(args) {
		var objectEl = document.getElementById('txObject');
		if (objectEl != null) {
			objectEl.value = args;
		}
		
		$scope.items = null;
	};
	
	/**
	 * function triggered when find button is clicked
	 * @param event JSON object with event information
	 */
	$scope.onFindClick = function(event) {
		
		var postData = {};
		var actionName = "MetadataApi";
		var objectEl = document.getElementById('txObject');
		if (objectEl != null) {
			postData['LOGGED_IN'] = 'TRUE';
			postData['OBJECT_NAME_1_1'] = objectEl.value;
			postData['FULL_OBJECT_DESCRIPTION_1_1'] = 'TRUE';
		} else {
			var errors = [{
				message: 'Please select a valid object',
				number: '110003'
			}];
			
			$scope.errors = errors;
			return;
		}
		
		$scope.items = null;
		$scope.errors = null;
		$scope.childRelations = null;
		
		
		this.doPost({
			url: actionName,
			data: postData,
			onSuccessCallback: this._onSuccessCallback
		});
	};
	
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
			
			// set data
			$scope.data.setData({
				key: 'object',
				value: data.users[0].objects
			});
			
			// set data to print on UI
			$scope.childRelations = data.users[0].objects[0].result.childRelationships;
		}
		
		$scope.hideOverlay();
	};
});