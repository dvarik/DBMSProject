angular.module('hospApp').controller('PatientAppointmentController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
	$scope.loadingData = false;
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
    };
    
    
	init();
	function init(){
	getDataSvc.getUpcomingAppointmentForPat().then(function(res) {
		$scope.loadingData = true;
		if (res != null) {
           $scope.apt1 = res;
           $scope.loadingData = false;
        } else {
            console.log("Error");
        }
    });
	}
	
	getDataSvc.getPastAppointmentForPat().then(function(res) {
		$scope.loadingData = true;
		if (res != null) {
           $scope.apt2 = res;
           $scope.loadingData = false;
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
		$scope.selectedDoc = rep;
		getDataSvc.getDoc($scope.selectedState,$scope.selectedBranch,rep).then(function(res) {
	        if (res != null) {
	           $scope.doc = res;
	        } else {
	            console.log("Error");
	        }
	    });
	};

	$scope.getTimeSlot = function(){
		var str = "";
		str = str.concat($scope.selectedMonth + "-" + $scope.apt.day + "-2016");
		$scope.apt.date = str;
		getDataSvc.getTimeSlot(str,$scope.apt.doc).then(function(res) {
	        if (res != null) {
	           $scope.times = res;
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
	       	   init();
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
	        	$scope.loadingData = false;
	        } else {
	            console.log("Error");
	            $scope.loadingData = false;
	        }
	    });
		$scope.apt1.splice(index,1);
    	
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
