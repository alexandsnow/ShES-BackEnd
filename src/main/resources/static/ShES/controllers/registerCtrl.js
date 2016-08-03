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
			/*var url="http://120.27.30.105:8080/GaH/v1/registerUser";
			var params = {
				"Name":$scope.userName,
				"Password":$scope.password,
				"Department":$scope.partment
			};
			$http.post({
				url:url,
				data:params,
				headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
			}).success(function (data,status,headers,config) { 
				console.log(data);
				//deferred.resolve(data);  
            }).error(function (data,status,headers,config) {  
            	console.log(data);
                //deferred.reject(data);  
            });*/
			var deferred=$q.defer();
			var url="http://120.27.30.105:8080/GaH/v1/registerUser?Name=test12345612&Password=test&Department=test";
			/*var params = {
				"Name":$scope.userName,
				"Password":$scope.password,
				"Department":$scope.partment
			};*/
			//var data = "&Name="+$scope.userName+"&Password="+$scope.password+"&Department="+$scope.partment;
			var promise = $http(
			{
				method : 'post',
				url : url,
				//data : params,
				headers : {
					'Content-Type' : 'application/json;charset=UTF-8'
				}		
			}).success(function (data,status,headers,config) { 
				console.log(data);
				deferred.resolve(data);  
            })  
            .error(function (data,status,headers,config) {  
            	console.log(headers);
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