angular.module(app_name).controllerProvider.register('ObjectPageCtrl', function($scope, $injector) {
	
	$injector.invoke(angular.module(app_name)['appCtrl'], this, {$scope: $scope});

});