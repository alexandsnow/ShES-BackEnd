'use strict';
angular.module('controller')
.controller('registerCtrl',[
	'$scope',
	'$state',
	'$http',
	'$q',
	function($scope,$state,$http,$q){
		console.log("This is registerCtrl");
		$scope.Register=function(){
			console.log($scope.userName);
			console.log($scope.password);
			console.log($scope.passwordagain);
			console.log($scope.partment);
			
			var deferred=$q.defer();
			var url=baseUrl+"/registerUser";
			//var url='http://192.168.1.119:8080/v1/registerUser';
			var params = {
				"userName":$scope.userName,
				"password":$scope.password,
				"department":$scope.partment
			};
			var promise = $http(
			{
				method : 'post',
				url : url,
				params : params,
				headers : {
					'Content-Type' : 'application/json;charset=UTF-8'
				}		
			}).success(function (data,status,headers,config) { 			
				deferred.resolve(data);  
            })  
            .error(function (data,status,headers,config) {              	
                deferred.reject(config);  
            });
            deferred.promise.then(function(data){
            	alert("恭喜注册成功");
				$state.go("login");
            	console.log(data);
            },function(err){
            	console.log(err);
            })
			
		}
}])