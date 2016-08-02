'use strict';
angular.module('controller')
.controller('allUserRankCtrl',[
	"$scope",
	function($scope){
		console.log("This is allUserRankCtrl");
		$scope.rankInfo = {
			"rank1":{"level":"1","apartment":"TB1","date":"01/04/2012","score":"30"},
			"rank2":{"level":"2","apartment":"TB2","date":"01/07/2012","score":"60"},
			"rank3":{"level":"3","apartment":"TB2","date":"01/07/2012","score":"70"},
			"rank4":{"level":"4","apartment":"TB3","date":"01/05/2012","score":"80"},
			"rank5":{"level":"5","apartment":"TB1","date":"02/07/2012","score":"90"},
			"rank6":{"level":"6","apartment":"TB2","date":"01/07/2012","score":"40"},
			"rank7":{"level":"7","apartment":"TB3","date":"09/08/2012","score":"30"},
			"rank8":{"level":"8","apartment":"TB1","date":"03/07/2012","score":"50"},
			"rank9":{"level":"9","apartment":"TB3","date":"10/07/2012","score":"10"},
			"rank10":{"level":"10","apartment":"TB2","date":"01/07/2012","score":"100"}
		}
	
}])