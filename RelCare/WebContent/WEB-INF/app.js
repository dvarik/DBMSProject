var hospApp = angular.module('hospApp', []);

hospApp.controller('profileController',function($scope) {
	$scope.name = "john doe";
	$scope.city = "gainesville";
	$scope.state = "florida";
	$scope.zipcode = "32608";
	$scope.gender = "M";
	$scope.dob = "04/14/1991";
	$scope.insurance = "gold";
});