angular.module('hospApp').service('getDataSvc', ['$http', function($http){
	
	this.getAppointmentForDoc = function(){
		var myurl = "getAppointments";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getProfile = function(){
		var myurl = "getProfile";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getPatientsForDoc = function(){
		var myurl = "getPatientsForDoc";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};

	this.getHistory = function(patientId){
		var myurl = "getDiagnosisHistory";
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {patientid:patientId}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getPaymentHistory = function(){
		var myurl = "getPaymentHistory";
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getUpcomingAppointmentForPat = function(){
		var myurl = "getUpcomingAppointmentsForPatient";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getCompletedAppointmentForPat = function(){
		var myurl = "getCompletedAppointmentsForPatient";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
}]);