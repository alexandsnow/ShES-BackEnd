'use strict';
angular.module('controller')
.controller('answerCtrl',[
	"$scope",
	"$state",
	function($scope,$state){
		console.log("This is answerCtrl");
		// 按钮白色蒙层样式
		$(".begin-btn").click(function(){
			$(".begin-btn").attr("disabled",true);
			$(".wram-note").fadeIn();
			$(".disabled-cover").fadeOut();
		});
		// 跳转
		Jump2AnswerPage("MyColor1");
		Jump2AnswerPage("MyColor2");
		Jump2AnswerPage("MyColor3");
		Jump2AnswerPage("MyColor4");
		Jump2AnswerPage("MyColor5");
		function Jump2AnswerPage(index){
		$("."+index).click(
			function(){
			    $("#mymodal-data").modal("toggle");
			    var IdName;
			    $(document).click(function (e) {
					IdName=($(e.target).attr('id'));  // 获取id
				})
			    $("#answerJump").click(
			    	function(){
			    		$("#mymodal-data").modal("hide");
			    		switch(IdName){
			    			case "itme1":
			    			$('#mymodal-data').on('hidden.bs.modal', function (e) {
 								$state.go("header.subAnswer1");
							});			    			
			    			break;
			    			case "itme2":
			    			$('#mymodal-data').on('hidden.bs.modal', function (e) {
			    				$state.go("header.subAnswer2");
			    			});	
			    			break;
			    			case "itme3":
			    			$('#mymodal-data').on('hidden.bs.modal', function (e) {
			    				$state.go("header.subAnswer3");
			    			});	
			    			break;
			    			case "itme4":
			    			$('#mymodal-data').on('hidden.bs.modal', function (e) {
			    				$state.go("header.subAnswer4");
			    			});	
			    			break;
			    			case "itme5":
			    			$('#mymodal-data').on('hidden.bs.modal', function (e) {
			    				$state.go("header.subAnswer5");
			    			});	
			    			break;
			    		}
			    	
			    	});
			});
		}
}])