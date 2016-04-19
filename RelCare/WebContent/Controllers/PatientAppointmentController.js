angular.module('hospApp').controller('PatientAppointmentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
	$scope.loadingData = true;
	$scope.selectedPat = null;
	$scope.selectedDoc = null;
	$scope.selectedState = null;
	$scope.selectedBranch = null;
	$scope.historyDetail = {};
	$scope.loc = {};
	$scope.range = [];
	$scope.selectedDay = null;
	$scope.selectedMonth = null;
	$scope.month = [{"id":"05","Name":"May"},
	                {"id":"06","Name":"June"},
	                {"id":"07","Name":"July"},
	                {"id":"08","Name":"Aug"},
	                {"id":"09","Name":"Sept"},
	                {"id":"10","Name":"Oct"},
	                {"id":"11","Name":"Nov"},
	                {"id":"12","Name":"Dec"}];

	
    $scope.getDay = function(rep) {
    	$scope.loadingData = true;
    	$scope.selectedMonth = rep;
    	n = 31;
        if(rep == "02"){
        	n = 28;
        }
        if(rep == "04" || rep == "06" || rep == "09" || rep == "11"){
        	n = 30;
        }
        $scope.range = new Array(n);
        $scope.apt.month = rep;
        $scope.loadingData = false;
    };
    
    
	
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
           $scope.loadingData = false;
        } else {
            console.log("Error");
        }
    });
	
	$scope.showAptModal = function(rep){
		$('#aptModal').modal('show');
		$scope.loadingData = true;
        getDataSvc.getLocation().then(function(res) {
            if (res != null) {
               $scope.loc = res;
               $scope.loadingData = false;
            } else {
                console.log("Error");
            }
        });
	}
	
	$scope.loadBranch = function(rep){
		$scope.loadingData = true;
		$scope.selectedState = rep;
		$scope.branch = [];
		 $.each($scope.loc, function() {
		        if(this["state"] == rep){
			        var item = this["city"];
			        $scope.branch.push(item);
		        }
		    });
		 $scope.loadingData = false;
	}

	$scope.getDept = function(rep){
		$scope.loadingData = true;
		$scope.selectedBranch = rep;
		getDataSvc.getDept($scope.selectedState,rep).then(function(res) {
	        if (res != null) {
	           $scope.dept = res;
	           $scope.loadingData = false;
	        } else {
	            console.log("Error");
	        }
	    });
	};
	
	$scope.getDoc = function(rep){
		$scope.loadingData = true;
		$scope.selectedDoc = rep;
		getDataSvc.getDoc($scope.selectedState,$scope.selectedBranch,rep).then(function(res) {
	        if (res != null) {
	           $scope.doc = res;
	           $scope.loadingData = false;
	        } else {
	            console.log("Error");
	        }
	    });
	};

	$scope.getTimeSlot = function(){
		var str = "";
		$scope.loadingData = true;
		str = str.concat($scope.selectedMonth + "-" + $scope.apt.day + "-2016");
		$scope.apt.date = str;
		getDataSvc.getTimeSlot(str,$scope.apt.doc).then(function(res) {
	        if (res != null) {
	           $scope.times = res;
	           $scope.loadingData = false;
	        } else {
	            console.log("Error");
	        }
	    });
	};
	
	$scope.saveAppointment = function(){
		console.log($scope.apt);
		$scope.loadingData = true;
		getDataSvc.saveAppointment($scope.apt).then(function(res) {
	        if (res == 'true') {
	           $('#aptModal').modal('hide');
	       	   $scope.apt = {};
	       	   $scope.loadingData = false;
	        } else {
	            console.log("Error");
	            $('#aptModal').modal('hide')
		       	$scope.apt = {};
	        }
	    });
	};
	
	$scope.cancelAppointment = function(rep,index){
		console.log($scope.apt);
		$scope.loadingData = true;
		getDataSvc.cancelAppointment(rep).then(function(res) {
	        if (res == 'true') {
	        } else {
	            console.log("Error");
	        }
	    });
		$scope.apt1.splice(index,1);
    	$scope.loadingData = false;
	};
	
	$scope.showReportModal = function(rep){
	    $('#reportModal').modal('show');
	    $scope.loadingData = true;
		getDataSvc.getHistoryForPat(rep).then(function(res1) {
	        if (res1 != null) {
	           $scope.historyDetail = res1;
	        	$scope.loadingData = false;
	        } else {
	            console.log("Error");
	        }
	    });
	}
}]);
