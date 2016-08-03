'use strict';
var indexApp = angular.module('indexApp', ['ui.router',	'controller']);
indexApp.run([
	'$rootScope', 
	'$state', 
	'$stateParams',
	function($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
]);
angular.module('controller',[]);