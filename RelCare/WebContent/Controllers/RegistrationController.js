angular.module('hospApp').controller('RegistrationController', ['$rootScope', '$scope', '$q', '$http', 'getDataSvc', function($rootScope, $scope, $q, $http, getDataSvc)
{
	$scope.loadingData = true;
	$scope.register = false;
	$scope.deregister = false;
	$scope.registered = false;
	$scope.deregistered = false;
	$scope.selectedDoc = null;
	$scope.selectedState = null;
	$scope.selectedBranch = null;
	$scope.loc = {};
	$scope.range = [];
	$scope.selectedDay = null;
	$scope.selectedMonth = null;
	$scope.month = [{"id":"05","Name":"Jan"},
	                {"id":"05","Name":"Feb"},
	                {"id":"05","Name":"March"},
	                {"id":"05","Name":"April"},
	                {"id":"05","Name":"May"},
	                {"id":"06","Name":"June"},
	                {"id":"07","Name":"July"},
	                {"id":"08","Name":"Aug"},
	                {"id":"09","Name":"Sept"},
	                {"id":"10","Name":"Oct"},
	                {"id":"11","Name":"Nov"},
	                {"id":"12","Name":"Dec"}];
	
	getDataSvc.getLocation().then(function(res) {
        if (res != null) {
           $scope.loc = res;
        } else {
            console.log("Error");
        }
    });
	
	getDataSvc.getRank().then(function(res) {
        if (res != null) {
           $scope.rank = res;
           $scope.loadingData = false;
        } else {
            console.log("Error");
        }
    });
	
	$scope.showRegisterDoc = function(){
		$scope.deregister = false;
		$scope.register = true;
		console.log($scope.register);
		$scope.year = new Array(100);
	}
	
	$scope.showDeregisterDoc = function(){
		$scope.deregister = true;
		$scope.register = false;
	}
	
    $scope.getDay = function(rep) {
    	$scope.selectedMonth = rep;
    	n = 31;
        if(rep == "02"){
        	n = 28;
        	if($scope.user.year % 4 == 0)
        		n = 29;
        }
        if(rep == "04" || rep == "06" || rep == "09" || rep == "11"){
        	n = 30;
        }
        $scope.range = new Array(n);
    };

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
		
	$scope.registerDoc = function(){
		$scope.loadingData = true;
		getDataSvc.registerDoc($scope.user).then(function(res) {
	        if (res != null) {
	        	$scope.loadingData = false;
	        	$scope.registered = true;
	        	$scope.selectedDoc = null;
	        	$scope.selectedState = null;
	        	$scope.selectedBranch = null;
	        	$scope.loc = {};
	        	$scope.range = [];
	        	$scope.selectedDay = null;
	        	$scope.selectedMonth = null;
	        	$scope.user = {};
	        	$scope.doctor = {};
	        } else {
	            console.log("Error");
	        }
	    });
	}

	$scope.deregisterDoc = function(){
		$scope.loadingData = true;
		getDataSvc.deregisterDoc($scope.doctor.id).then(function(res) {
	        if (res != null) {
	        	$scope.loadingData = false;
	        	$scope.deregistered = true;
	        	$scope.selectedDoc = null;
	        	$scope.selectedState = null;
	        	$scope.selectedBranch = null;
	        	$scope.loc = {};
	        	$scope.range = [];
	        	$scope.selectedDay = null;
	        	$scope.selectedMonth = null;
	        	$scope.user = {};
	        	$scope.doctor = {};
	        } else {
	            console.log("Error");
	        }
	    });
	}
}]);
