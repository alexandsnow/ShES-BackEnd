'use strict';
var cloudBaseUrl="http://120.27.30.105:8080/GaH/v1";
var localBaseUrl="http://192.168.1.119:8080/v1";
var baseUrl=localBaseUrl;
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