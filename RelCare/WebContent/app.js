var hospApp = angular.module('hospApp', [ 'ngRoute' ]);

hospApp.config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/profile', {
		templateUrl : 'Partials/profile.html',
		controller : 'ProfileController'
	}).when('/appointment', {
		templateUrl : 'Partials/doc_appointment.html',
		controller : 'AppointmentController'
	}).when('/history', {
		templateUrl : 'Partials/doc_record.html',
		controller : 'HistoryController'
	}).when('/billing', {
		templateUrl : 'Partials/pat_payment.html',
		controller : 'PaymentController'
	}).otherwise('/profile');

});

