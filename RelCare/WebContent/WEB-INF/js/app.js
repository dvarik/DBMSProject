var routerApp = angular.module('routerApp', ['ui.router', "ngMaterial", "ngMessages"]);

routerApp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('home', {
            url: '/home',
            views: {
                'main@': {
                    templateUrl: 'home.html'
                }
            }
        })
		.state('profile', {
            url: '/profile',
            views: {
                'main@': {
                    templateUrl: 'profile.html',
					controller: 'profileController'
                }
            }
        })
		.state('payment', {
            url: '/payment',
            views: {
                'main@': {
                    templateUrl: 'payment.html'
                }
            }
        })
		.state('appointment', {
            url: '/appointment',
            views: {
                'main@': {
                    templateUrl: 'appointment.html',
					controller: 'appointmentController'
                }
            }
        })

});
 
  
routerApp.controller('profileController', function($scope) {
    
    $scope.user = {
      name: 'John Doe',
    };
	$scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
    'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
    
});

routerApp.controller('appointmentController', function($scope) {
  $scope.status = '  ';
  //$scope.customFullscreen = $mdMedia('md') || $mdMedia('gt-sm');
  $scope.states = ('AL AK AZ AR CA CO CT DE FL GA HI ID IL IN IA KS KY LA ME MD MA MI MN MS ' +
    'MO MT NE NV NH NJ NM NY NC ND OH OK OR PA RI SC SD TN TX UT VT VA WA WV WI ' +
    'WY').split(' ').map(function(state) {
        return {abbrev: state};
      });
});

