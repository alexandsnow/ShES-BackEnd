'use strict';
angular.module('controller')
.controller('forgetPswdCtrl',[
	'$scope',
	'$state',
	'$http',
	'$q',
	function($scope,$state,$http,$q){
		console.log("This is forgetPswdCtrl");
		$scope.ForgetPswd=function(){
			console.log($scope.userName);
			console.log($scope.password);
			var deferred=$q.defer()
			var url=baseUrl+"/modifyPassword/"+$scope.userName;
			var params={"password":$scope.password};
			var promise = $http({
				url : url,
				method : "PUT",
				params : params,
				headers : {
					'Content-Type' : 'application/json;charset=UTF-8'
				}	
			}).success(function (data,status,headers,config) { 				
				deferred.resolve(data);  
            })  
            .error(function (data,status,headers,config) {              	
                deferred.reject(data);  
            });
            deferred.promise.then(function(data){
            	alert("恭喜密码修改成功");
				$state.go("login");
            	console.log(data);
            },function(err){
            	console.log(err);
            })
		}
}])