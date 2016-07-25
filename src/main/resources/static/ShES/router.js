'use strict';
indexApp.config([
	'$stateProvider',
	'$urlRouterProvider',
	function($stateProvider, $urlRouterProvider){
		$urlRouterProvider.when("","/login")
		.when("/","/login")
        .otherwise("/header/indexpage");
        $stateProvider
        .state("login", {
            url: "/login",         
            templateUrl: "views/login.html",
            controller: "loginCtrl"
        })
        .state("register", {
            url: "/register",         
            templateUrl: "views/register.html",
            controller: "registerCtrl"
        })
        .state("forgetPswd", {
            url: "/forgetPswd",         
            templateUrl: "views/forgetPswd.html",
            controller: "forgetPswdCtrl"
        })
        .state("header", {
            url: "/header",         
            templateUrl: "header.html",
            controller: "headerCtrl"
        })
        .state("header.indexpage", {
            url:"/indexpage",
            views:{
                "nav-indexpage":{
                    templateUrl: "views/indexpage.html",
                    controller: "indexpageCtrl"
                }
             }           
        })
        .state("header.study", {
            url:"/study",
            views:{
                "nav-study":{
                    templateUrl: "views/study.html",
                    controller: "studyCtrl"
                }
             }           
        })
        .state("header.answer", {
            url:"/answer",
            views:{
                "nav-answer":{
                    templateUrl: "views/answer.html",
                    controller: "answerCtrl"
                }
             }           
        })
        .state("header.subAnswer1", {
            url:"/subAnswer1",
            views:{
                "nav-answer":{
                    templateUrl: "views/subAnswer1.html",
                    controller: "subAnswer1Ctrl"
                }
             }           
        })
        .state("header.subAnswer2", {
            url:"/subAnswer2",
            views:{
                "nav-answer":{
                    templateUrl: "views/subAnswer2.html",
                    controller: "subAnswer2Ctrl"
                }
             }           
        })
        .state("header.subAnswer3", {
            url:"/subAnswer3",
            views:{
                "nav-answer":{
                    templateUrl: "views/subAnswer3.html",
                    controller: "subAnswer3Ctrl"
                }
             }           
        })
        .state("header.subAnswer4", {
            url:"/subAnswer4",
            views:{
                "nav-answer":{
                    templateUrl: "views/subAnswer4.html",
                    controller: "subAnswer4Ctrl"
                }
             }           
        })
        .state("header.subAnswer5", {
            url:"/subAnswer5",
            views:{
                "nav-answer":{
                    templateUrl: "views/subAnswer5.html",
                    controller: "subAnswer5Ctrl"
                }
             }           
        })
        .state("header.allUserRank", {
            url:"/allUserRank",
            views:{
                "nav-rank":{
                    templateUrl: "views/allUserRank.html",
                    controller: "allUserRankCtrl"
                }
             }           
        })
        .state("header.myScore", {
            url:"/myScore",
            views:{
                "nav-score":{
                    templateUrl: "views/myScore.html",
                    controller: "myScoreCtrl"
                }
             }           
        })
        .state("header.analysis",{
            url:"/analysis",
            views:{
                "nav-score":{
                    templateUrl:"views/analysis.html",
                    controller:"analysisCtrl"
                }
            }
        })
	

	}])