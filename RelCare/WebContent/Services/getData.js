angular.module('hospApp').service('getDataSvc', ['$http', function($http){
	
	this.getAppointmentForDoc = function(){
		var myurl = "getAppointmentsForDoctor";
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
	
	this.saveRecord = function(rep){
		var myurl = "saveDiagnosisReport";
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {id: rep.id, meds:rep.meds, illness:rep.illness}
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