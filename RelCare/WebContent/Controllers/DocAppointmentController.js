angular.module('hospApp').controller('DocAppointmentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	$scope.apts = {};
	getDataSvc.getAppointmentForDoc().then(function(res) {
        if (res != null) {
           $scope.apts = res;
        } else {
            console.log("Error");
        }
    });


}]);