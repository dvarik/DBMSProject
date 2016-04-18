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
	
	this.getPastAppointmentForPat = function(){
		var myurl = "getPastAppointmentsForPatient";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getRevenueStats = function(){
		var myurl = "getBranchRevenueReport";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getBranches = function(){
		var myurl = "getBranches";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getIllnessStats = function(){
		var myurl = "getIllnessStatsReport";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getHistoryForPat = function(a){
		var myurl = "getDiagnosisHistoryOfPatient";
		console.log(a);
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {appointmentid:a}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	};
	
	this.getLocation = function(){
		var myurl = "getLocation";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	};
	
	this.getTimeSlot = function(rep,rep2){
		var myurl = "getTimeSlot";
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {dateStr: rep,docId:rep2}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	};
	
	this.getDept = function(a,b){
		var myurl = "getDept";
		console.log(a);
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {state:a,city:b}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	};

	this.getDoc = function(a,b,c){
		var myurl = "getDoc";
		console.log(a);
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {state:a,city:b,dept:c}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	};

	this.saveAppointment = function(rep){
		var myurl = "saveAppointment";
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {docId: rep.doc, aptdate:rep.date, time:rep.time}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};

	this.cancelAppointment = function(rep){
		var myurl = "cancelAppointment";
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params: {aptId: rep}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getRank = function(){
		var myurl = "getRank";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	};
	
	this.registerDoc = function(rep){
		var myurl = "registerDoc";
		var date = "";
		var k = rep.day.toString();
		var s = "0";
		if(rep.day < 10)
			k = s.concat(k);
		date = (rep.year.toString()).concat("-",rep.month,"-",k);
		var z = rep.zip.toString();
		var p = rep.password.toString();
		 var promise = $http({
		        method : "GET",
		        url : myurl,
		        params : {fname:rep.fname, lname:rep.lname,
		        	dcity:rep.city, dstate:rep.state, zip:z,
		        	gender:rep.gender, dob:date,
		        	email:rep.email, pass:p,
		        	reg:rep.regnum,
		        	state:rep.stateB, branch:rep.branch, dept:rep.dept,
		        	rankid:rep.rankid}
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 return promise;
	}
	
	this.deregisterDoc = function(rep){
		var myurl = "deRegisterDoc";
		var promise = $http({
	        method : "GET",
	        url : myurl,
	        params : {doctorId: rep}
	    }).then(function(response) {
	    	console.log(response.data);
	        return response.data;
	    });
		return promise;
	}
	
	this.getInsuranceStats = function(){
		var myurl = "getInsuranceStatsReport";
		 var promise = $http({
		        method : "GET",
		        url : myurl
		    }).then(function(response) {
		    	console.log(response.data);
		        return response.data;
		    });
		 
		 return promise;
	};
	
	this.getPatientCountStats = function(){
		var myurl = "getTotalDeptPatientsReport";
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
