angular.module('hospApp').controller('ProfileController', ['$rootScope', '$scope', '$q', '$http', function($rootScope, $scope, $q, $http)
{
	init();
	
	function init() {

       $scope.res = 42;
    }
	
}]);