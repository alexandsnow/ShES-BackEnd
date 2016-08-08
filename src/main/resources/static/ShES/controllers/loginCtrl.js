'use strict';
angular.module('controller')
.controller('loginCtrl',[
	'$scope',
	'$state',
	'$http',
	'$q',
	function($scope,$state,$http,$q){
		console.log("This is login");
		$scope.userData={};
		$scope.Login=function(){
			var deferred = $q.defer(); 
			var url=baseUrl+"/login";
			console.log($scope.userData.userName);
			console.log($scope.userData.password);
			var params = {
				userName:$scope.userData.userName,
				password:$scope.userData.password
			}
			var promise = $http({
				url:url,
				method:"GET",
				params:params
			}).success(function (data,status,headers,config) { 
				deferred.resolve(data);  
            })  
            .error(function (data,status,headers,config) {  
                deferred.reject(data);  
            });
            deferred.promise.then(function(data){
            	console.log(data);
            	if (data==1) {
            		window.localStorage["userName"]=$scope.userData.userName;
            		$state.go("header.indexpage");
            	}else if(data==0) {
            		$scope.wrongPswd=true;
            	}           	
            },function(err){
            	$scope.wrongPswd=true;
    			console.log(err);
            });
	}
	
}])