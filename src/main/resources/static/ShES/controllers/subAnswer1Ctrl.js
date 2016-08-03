'use strict';
angular.module('controller')
.controller('subAnswer1Ctrl',[
	'$scope',
	'$state',
	'$http',
	'$q',
	function($scope,$state,$http,$q){
	//倒计时部分开始
	// 定义剩余时间 表示总秒数// todo 这个值应当从数据库读取
	var leftTime=6000;
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
			$("#mymodal-data1").modal("toggle");
			$(".tic-toc").hide();
			$(".ticTocTitle").hide()
			$(".disabled-cover").fadeIn();
			window.clearInterval(Time_ID);
		}
		$("#answerOver").click(function(){
			$('#mymodal-data1').on('hidden.bs.modal', function (e) {
				$state.go("header.answer");
			});				
		});
	}
	// 倒计时结束
	//答题部分开始
	var deferred = $q.defer(); 
	var url="http://120.27.30.105:8080/GaH/v1/getQuestions/gaoyang/1"
	var promise = $http({
		url:url,
		method:"GET"
	}).success(function (data,status,headers,config) { 
		deferred.resolve(data);  
    })  
    .error(function (data,status,headers,config) {  
        deferred.reject(data);  
    });
    deferred.promise.then(function(data){
    	console.log(data);
    	$scope.questions=data.content;
    	angular.forEach($scope.questions,function(data,index,questions){   		
    	})
    },function(err){
		console.log(err);
    })

	var selectedItem=[];
	var answer="";
	function removeByValue(arr, val) {
  		for(var i=0; i<arr.length; i++) {
   			if(arr[i] == val) {
      			arr.splice(i, 1);
     			 break;
    		}
  		}
	}
	$scope.isSelected=function(index,aIndex){
		var selectInfo=index+','+aIndex;
		if (this.choosed0==true) {
			selectedItem.push(selectInfo);
		}else if (this.choosed0==false) {
			removeByValue(selectedItem,selectInfo);
		}else if (this.choosed1==true) {
			selectedItem.push(selectInfo);
		}else if (this.choosed1==false) {
			removeByValue(selectedItem,selectInfo);
		}else if (this.choosed2==true) {
			selectedItem.push(selectInfo);
		}else if (this.choosed2==false) {
			removeByValue(selectedItem,selectInfo);
		}else if (this.choosed3==true) {
			selectedItem.push(selectInfo);
		}else if (this.choosed3==false) {
			removeByValue(selectedItem,selectInfo);
		}
	};
	$scope.QaForm=function(){
		for(var i=0;i<selectedItem.length;i++){
			if(i<selectedItem.length-1)
				answer = answer + selectedItem[i]+"|";
			else
				answer = answer + selectedItem[i];
		}
		console.log(answer);
		answer="";
	}

}])