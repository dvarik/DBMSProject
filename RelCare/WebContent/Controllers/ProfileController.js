angular.module('hospApp').controller('ProfileController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	getDataSvc.getProfile().then(function(res) {
        if (res != null) {
           $scope.user = res;
        } else {
            console.log("Error");
        }
    });


}]);