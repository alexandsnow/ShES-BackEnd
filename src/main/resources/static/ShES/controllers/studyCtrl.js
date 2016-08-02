'use strict';
angular.module('controller')
.controller('studyCtrl',[
	"$scope",
	function($scope){
		console.log("This is studyCtrl");
		$scope.studyType=true;
		$scope.createType=false;
		$scope.scureType = false;
		$scope.cleanType = false;
		$scope.saveType = false;
		$scope.chooseStudy=function(index) {
			if(index==0){
				$scope.studyType=true;
				$scope.createType=false;
				$scope.scureType = false;
				$scope.cleanType = false;
				$scope.saveType = false;
			}else if(index==1) {
				$scope.studyType=false;
				$scope.createType=true;
				$scope.scureType = false;
				$scope.cleanType = false;
				$scope.saveType = false;
			}else if(index==2) {
				$scope.studyType=false;
				$scope.createType=false;
				$scope.scureType = true;
				$scope.cleanType = false;
				$scope.saveType = false;
			}else if(index==3) {
				$scope.studyType=false;
				$scope.createType=false;
				$scope.scureType = false;
				$scope.cleanType = true;
				$scope.saveType = false;
			}else{
				$scope.studyType=false;
				$scope.createType=false;
				$scope.scureType = false;
				$scope.cleanType = false;
				$scope.saveType = true;
			}
		}
}])