angular.module('hospApp').controller('PaymentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	$scope.loadingData = true;
	getDataSvc.getPaymentHistory().then(function(res) {
        if (res != null) {
           $scope.paymentDetail = res;
           $scope.loadingData = false;
        } else {
            console.log("Error");
        }
    });
	
}]);