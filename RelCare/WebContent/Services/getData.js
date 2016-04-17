angular.module('hospApp').service('getDataSvc', ['$http', function($http){
	
	this.getAppointmentForDoc = function(){
		var url = "getAppointments";
		$http.get(url).success( function(response) {
			return response.data; 
		});
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
	
}]);