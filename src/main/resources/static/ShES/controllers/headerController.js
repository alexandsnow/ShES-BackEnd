'use strict';
angular.module('controller')
.controller('headerCtrl',[
	"$scope",
	"$state",
	function($scope){
		console.log("This is headerCtrl");
		$scope.username=window.localStorage["userName"];
		$scope.currentUrl=baseUrl+"/userPic/"+$scope.username;
		var positions="班组长";
		$scope.navActived1=true;
		$scope.navActived2=false;
		$scope.navActived3=false;
		$scope.navActived4=false;
		$scope.navActived5=false;
		$scope.navSelect=function(index){
			if (index==0) {
				$scope.navActived1=true;
				$scope.navActived2=false;
				$scope.navActived3=false;
				$scope.navActived4=false;
				$scope.navActived5=false;
			}else if(index==1) {
				$scope.navActived1=false;
				$scope.navActived2=true;
				$scope.navActived3=false;
				$scope.navActived4=false;
				$scope.navActived5=false;
			}else if(index==2) {
				$scope.navActived1=false;
				$scope.navActived2=false;
				$scope.navActived3=true;
				$scope.navActived4=false;
				$scope.navActived5=false;
			}else if(index==3) {
				$scope.navActived1=false;
				$scope.navActived2=false;
				$scope.navActived3=false;
				$scope.navActived4=true;
				$scope.navActived5=false;
			}else{
				$scope.navActived1=false;
				$scope.navActived2=false;
				$scope.navActived3=false;
				$scope.navActived4=false;
				$scope.navActived5=true;
			}
		}
		$(".user-pic").tooltip({
			html:true,
			title:'<div>姓名：'+$scope.username+'<br/>'+'职位：'+positions+'</div>'
		});
}])