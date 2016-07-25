'use strict';
angular.module('controller')
.controller('subAnswer4Ctrl',['$scope','$state',function($scope,$state){
	// 定义剩余时间 表示总秒数// todo 这个值应当从数据库读取
	var leftTime=6;
	// 定时器ID
	var Time_ID;
	CalcLeftTime();
	function CalcLeftTime(){
		Time_ID=window.setInterval(UpdateTime,1000);
	}
	function UpdateTime(){
		var day=0,hour=0,minute=0,second=0;//时间默认值
		if(leftTime > 0){
	    	day = Math.floor(leftTime / (60 * 60 * 24));
	    	hour = Math.floor(leftTime / (60 * 60)) - (day * 24);
	    	minute = Math.floor(leftTime / 60) - (day * 24 * 60) - (hour * 60);
	    	second = Math.floor(leftTime) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		} 
		if (minute <= 9) minute = '0' + minute;
		if (second <= 9) second = '0' + second; 
		$("#days").text(day+"天");
		$("#hours").text(hour+"时");
		$("#minutes").text(minute+"分");
		$("#seconds").text(second+"秒");
		leftTime--;
		
		if(leftTime==0) {
			$("#LeftTime").show();
			$("#mymodal-data4").modal("toggle");
			$(".tic-toc").hide();
			$(".ticTocTitle").hide()
			$(".disabled-cover").fadeIn();
			window.clearInterval(Time_ID);
		}
		$("#answerOver").click(function(){
			$('#mymodal-data4').on('hidden.bs.modal', function (e) {
				$state.go("header.answer");
			});	
		});
	}
	// 倒计时结束
	$scope.questions = {
		"groupName":"清洁型班组",
		"questionGroup":{
			"question1":{
				"id":"Q1",
				"title":"世界上种类最多，数量最多的动物是？",
				"answers": {
					"optionA":{
						"id":"A",
						"content":"鸟类"
					},
					"optionB":{
						"id":"B",
						"content":"哺乳类"
					},
					"optionC":{
						"id":"C",
						"content":"爬行类"
					},
					"optionD":{
						"id":"D",
						"content":"脊椎动物"
					}
				}
			},
			"question2":{
				"id":"Q2",
				"title":"世界上种类最多，数量最多的动物是？",
				"answers": {
					"optionA":{
						"id":"A",
						"content":"鸟类"
					},
					"optionB":{
						"id":"B",
						"content":"哺乳类"
					},
					"optionC":{
						"id":"C",
						"content":"爬行类"
					},
					"optionD":{
						"id":"D",
						"content":"脊椎动物"
					}
				}
			},
			"question3":{
				"id":"Q3",
				"title":"世界上种类最多，数量最多的动物是？",
				"answers": {
					"optionA":{
						"id":"A",
						"content":"鸟类"
					},
					"optionB":{
						"id":"B",
						"content":"哺乳类"
					},
					"optionC":{
						"id":"C",
						"content":"爬行类"
					},
					"optionD":{
						"id":"D",
						"content":"脊椎动物"
					}
				}
			}
		}
	};



}])