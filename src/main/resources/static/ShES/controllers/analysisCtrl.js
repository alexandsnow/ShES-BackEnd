'use strict';
angular.module('controller')
.controller("analysisCtrl",[
	"$scope",
	function($scope){
		console.log("This is analysisCtrl");
// echarts设置开始
		var myBarChart = echarts.init(document.getElementById('MyBar'));
		var myPieChart = echarts.init(document.getElementById('MyPie'));
		// 指定图表的配置项和数据
		var Baroption = {
		            title: {
		                text: '答题统计',
		                subtext: '总体分布',
		                x:"center",
		            },
		            tooltip : {
		                trigger : 'axis',
		                showDelay : 2,             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
		                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		                }
		            },
		            legend: {
		                orient : 'vertical',
		                x : 'right',
		                data:['正确','错误'],
		            },
		            xAxis: {
		                data: ["学习型","安全型","清洁型","和谐型","勤劳型"],
		                splitLine:{
		            　　　　show:false
		            　　}
		            },
		            yAxis: {
		                splitLine:{
		            　　　　show:false
		            　　}
		            },
		            series: [{
		                        name: '正确',
		                        type: 'bar',
		                        data: [5, 20, 36, 10, 10]
		                    },
		                    {
		                        name: '错误',
		                        type: 'bar',
		                        data: [30, 10, 16, 20, 50]
		                    }
		                    ]
		};

		var Pieoption = {
	            title : {
	                text: '正确率',
	                subtext: '五型班组正确率分布',
	                x:'center'
	            },
	            tooltip : {
	                trigger: 'item',
	                formatter: "{a} <br/>{b} : {c} ({d}%)"
	            },
	            grid:{show: false},
	            legend: {
	                orient : 'vertical',
	                x : 'left',
	                data:["学习型","安全型","清洁型","和谐型","勤劳型"]
	            },
	            toolbox: {
	                show : true,
	                feature : {
	                    mark : {show: true},
	                    dataView : {show: true, readOnly: false},
	                    magicType : {
	                        show: true, 
	                        type: ['pie', 'funnel'],
	                        option: {
	                            funnel: {
	                                x: '25%',
	                                width: '50%',
	                                funnelAlign: 'left',
	                                max: 1548
	                            }
	                        }
	                    },
	                    restore : {show: true},
	                    saveAsImage : {show: true}
	                }
	            },
	            calculable : true,
	            series : [
	                {
	                    name:'班组名称',
	                    type:'pie',
	                    radius : '55%',
	                    center: ['50%', '60%'],
	                    data:[
	                        {value:335, name:'学习型'},
	                        {value:310, name:'安全型'},
	                        {value:234, name:'清洁型'},
	                        {value:135, name:'和谐型'},
	                        {value:1548, name:'勤劳型'}
	                    ]
	                }
	            ]
	    };
		myBarChart.setOption(Baroption);
		myPieChart.setOption(Pieoption);
// echarts设置结束


}])