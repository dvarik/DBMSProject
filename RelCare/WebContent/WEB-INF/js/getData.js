var app = angular.module('hospApp', []);

app.service('services', function(){
	
	this.getAppointmentForDoc = function($http){
		var url = "/relcare/getAppointments";
		$http.get(url).success( function(response) {
			return response.data; 
		});
	};
	
	this.getProfile = function($http){
		var url = "/relcare/getProfile";
		$http.get(url).success( function(response) {
			return response.data; 
		});
	};
	
});


