angular.module('hospApp').controller('ProfileController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	$scope.loadingData = true;
	$scope.saved = false;
	
	getDataSvc.getProfile().then(function(res) {
        if (res != null) {
           $scope.user = res;
           $scope.loadingData = false;
        } else {
            console.log("Error");
        }
    });

	$scope.saveProfile = function(){

    	$scope.loadingData = true;
		console.log($scope.user);
		getDataSvc.saveProfile($scope.user.state,$scope.user.city,$scope.user.zip,
				$scope.user.insurance,$scope.user.role).then(function(res) {
			if(res!= 'true'){
				console.log("Error");
			}
			else{
				$scope.saved = true;
				$scope.loadingData = false;
			}
	    });
		
	}

}]);