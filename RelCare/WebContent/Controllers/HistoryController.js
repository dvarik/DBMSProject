angular.module('hospApp').controller('HistoryController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
	$scope.loadingData = true;
	$scope.selectedPat = null;

	getDataSvc.getPatientsForDoc().then(function(res) {
        if (res != null) {
           $scope.pats = res;
           $scope.loadingData = false;
        } else {
            console.log("Error");
        }
    });
	
	$scope.showHistory = function(){
		$scope.loadingData = true;
		getDataSvc.getHistory($scope.selectedPat.id).then(function(res) {
	        if (res != null) {
	           $scope.historyDetail = res;
	           $scope.loadingData = false;
	        } else {
	            console.log("Error");
	        }
	    });
	}
	
	
}]);