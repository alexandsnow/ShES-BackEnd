'use strict';
angular.module('controller')
.controller('loginCtrl',[
	'$scope',
	'$state',
	function($scope,$state){
		console.log("This is login");
		$scope.userData={};
		$scope.Login=function(){
		console.log($scope.userData);
		var result=checkLogin($scope.userData.userName,$scope.userData.password);
		if(!result){
			$scope.userData.password="";
		}
	}
	//网络验证
	function checkLogin(userName,password){
	
		if(userName==password){
			$state.go("header.indexpage");
			return true;
		}
		else{
			alert("用户名和密码错误");
		return false;
		}
	}
}])