angular.module('hospApp').controller('PatientAppointmentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
	$scope.selectedPat = null;
	$scope.selectedState = null;
	$scope.selectedBranch = null;
	$scope.historyDetail = {};
	$scope.loc = {};
	
	getDataSvc.getUpcomingAppointmentForPat().then(function(res) {
        if (res != null) {
           $scope.apt1 = res;
        } else {
            console.log("Error");
        }
    });
	
	getDataSvc.getPastAppointmentForPat().then(function(res) {
        if (res != null) {
           $scope.apt2 = res;
        } else {
            console.log("Error");
        }
    });
	
	$scope.showAptModal = function(rep){
		$('#aptModal').modal('show');
        getDataSvc.getLocation().then(function(res) {
            if (res != null) {
               $scope.loc = res;
            } else {
                console.log("Error");
            }
        });
	}
	
	$scope.loadBranch = function(rep){
		$scope.selectedState = rep;
		$scope.branch = [];
		 $.each($scope.loc, function() {
		        if(this["state"] == rep){
			        var item = this["city"];
			        $scope.branch.push(item);
		        }
		    });
	}

	$scope.getDept = function(rep){
		$scope.selectedBranch = rep;
		getDataSvc.getDept($scope.selectedState,rep).then(function(res) {
	        if (res != null) {
	           $scope.dept = res;
	        } else {
	            console.log("Error");
	        }
	    });
	};
	
	$scope.getDoc = function(rep){
		getDataSvc.getDoc($scope.selectedState,$scope.selectedBranch,rep).then(function(res) {
	        if (res != null) {
	           $scope.doc = res;
	        } else {
	            console.log("Error");
	        }
	    });
	};
	
	$scope.saveAppointment = function(){
		console.log($scope.apt);
		getDataSvc.saveAppointment($scope.apt).then(function(res) {
	        if (res == 'true') {
	           $('#aptModal').modal('hide');
	       	   $scope.apt = {};
	       	   init();
	        } else {
	            console.log("Error");
	            $('#aptModal').modal('hide')
		       	$scope.apt = {};
	        }
	    });
	};
	
	$scope.showReportModal = function(rep){
	    $('#reportModal').modal('show');
		getDataSvc.getHistoryForPat(rep).then(function(res1) {
	        if (res1 != null) {
	           $scope.historyDetail = res1;
	        } else {
	            console.log("Error");
	        }
	    });
	}
}]);