angular.module('hospApp').controller('PaymentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
		
	getDataSvc.getPaymentHistory().then(function(res) {
        if (res != null) {
           $scope.paymentDetail = res;
        } else {
            console.log("Error");
        }
    });
	
}]);