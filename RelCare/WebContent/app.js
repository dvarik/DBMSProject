var hospApp = angular.module('hospApp', [ 'ngRoute' ]);

hospApp.config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/profile', {
		templateUrl : 'Partials/profile.html',
		controller : 'ProfileController'
	}).when('/payment', {
		templateUrl : 'payment.html',
		controller : 'paymentController'
	}).when('/appointment', {
		templateUrl : 'appointment.html',
		controller : 'appointmentController'
	}).otherwise('/profile');

});

