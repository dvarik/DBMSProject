angular.module('hospApp', []).controller('profileController',function($scope) {
	var obj = services.getProfile();
	$scope.name = obj.fname+obj.lname;
	$scope.city = obj.city;
	$scope.state = obj.state;
	$scope.zipcode = obj.zip;
	$scope.gender = obj.gender;
	$scope.dob = obj.dob;
	$scope.insurance = obj.insurance;
});