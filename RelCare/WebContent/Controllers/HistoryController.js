angular.module('hospApp').controller('HistoryController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	$scope.selectedPat = null;

	getDataSvc.getPatientsForDoc().then(function(res) {
        if (res != null) {
           $scope.pats = res;
        } else {
            console.log("Error");
        }
    });
	
	$scope.showHistory = function(){
		
		getDataSvc.getHistory($scope.selectedPat.id).then(function(res) {
	        if (res != null) {
	           $scope.historyDetail = res;
	        } else {
	            console.log("Error");
	        }
	    });
	}
	
	
}]);