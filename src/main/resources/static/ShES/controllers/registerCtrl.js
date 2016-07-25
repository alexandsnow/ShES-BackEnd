'use strict';
angular.module('controller')
.controller('registerCtrl',[
	'$scope',
	'$state',
	function($scope,$state){
		console.log("This is registerCtrl");
		$scope.Register=function(){
			alert("恭喜注册成功");
			$state.go("login");
		}
}])