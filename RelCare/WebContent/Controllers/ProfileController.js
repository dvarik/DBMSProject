angular.module('hospApp').controller('ProfileController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	$scope.saved = false;
	
	getDataSvc.getProfile().then(function(res) {
        if (res != null) {
           $scope.user = res;
        } else {
            console.log("Error");
        }
    });

	$scope.saveProfile = function(){
		
		console.log($scope.user);
		getDataSvc.saveProfile($scope.user.state,$scope.user.city,$scope.user.zip,
				$scope.user.insurance,$scope.user.role).then(function(res) {
			if(res!= 'true'){
				console.log("Error");
			}
			else{
				$scope.saved = true;
			}
	    });
		
	}

}]);