var hospApp = angular.module('hospApp', [ 'ngRoute']);

hospApp.config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/profile', {
		templateUrl : 'Partials/profile.html',
		controller : 'ProfileController'
	}).when('/docappointment', {
		templateUrl : 'Partials/doc_appointment.html',
		controller : 'DocAppointmentController'
	}).when('/history', {
		templateUrl : 'Partials/doc_record.html',
		controller : 'HistoryController'
	}).when('/billing', {
		templateUrl : 'Partials/pat_payment.html',
		controller : 'PaymentController'
	}).when('/patientappointments', {
		templateUrl : 'Partials/pat_appointment.html',
		controller : 'PatientAppointmentController'
	}).when('/stats', {
		templateUrl : 'Partials/stats.html',
		controller : 'StatisticsController'
	}).when('/registration', {
		templateUrl : 'Partials/register.html',
		controller : 'RegistrationController'
	}).otherwise('/profile');
	
});

hospApp.run(['$rootScope', '$location','getDataSvc', function ($rootScope, $location, getDataSvc) {
    $rootScope.$on('$routeChangeStart', function (event) {

        $rootScope.loggedrole = {};
        
        getDataSvc.getRole().then(function(res) {
	        if (res != null) {
	        	$rootScope.loggedrole = res;
	        } else {
	            console.log("Error");
	        }
	    });
        
    });
}]);