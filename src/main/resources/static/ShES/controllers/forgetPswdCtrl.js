'use strict';
angular.module('controller')
.controller('forgetPswdCtrl',[
	'$scope',
	'$state',
	function($scope,$state){
		console.log("This is forgetPswdCtrl");
		$scope.ForgetPswd=function(){
			alert("恭喜密码修改成功");
			$state.go("login");
		}
}])