'use strict';
angular.module('controller')
.controller('myScoreCtrl',[
	"$scope",
	function($scope){
		console.log("This is myScoreCtrl");
		$('#myRoundabout').roundabout({
		 	shape: 'figure8',
		 	minOpacity: 1
		});
}])