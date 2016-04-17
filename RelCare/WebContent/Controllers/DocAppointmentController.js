angular.module('hospApp').controller('DocAppointmentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{

	$scope.apts = {};
	$scope.selectedPt = {};
	$scope.newReport = {};
	
	init();
	
	function init(){
		getDataSvc.getAppointmentForDoc().then(function(res) {
	        if (res != null) {
	           $scope.apts = res;
	        } else {
	            console.log("Error");
	        }
	    });

	}
	
	$scope.showEditModal = function(rep){
		$('#editModal').modal('show');
		$scope.selectedPt = rep;
		console.log(rep);
	}
	
	$scope.cancelDiagnosisReport = function(){
		$('#editModal').modal('hide');
	}
	
	$scope.saveDiagnosisReport = function(){
		
		$scope.newReport.id = $scope.selectedPt.appointmentId;
		console.log($scope.newReport);
		
		getDataSvc.saveRecord($scope.newReport).then(function(res) {
	        if (res == 'true') {
	           $('#editModal').modal('hide');
	           $scope.selectedPt = {};
	       	   $scope.newReport = {};
	       	   init();
	        } else {
	            console.log("Error");
	            $('#editModal').modal('hide');
	            $scope.selectedPt = {};
	        	$scope.newReport = {};
	        }
	    });
	    
	}

}]);