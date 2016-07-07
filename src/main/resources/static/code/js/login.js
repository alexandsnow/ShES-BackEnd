var userId;
var userPsd;
function login(){
  userId=document.getElementById("in1").value;
  userPsd=document.getElementById("in2").value;
  if(userId=="" && userPsd==""){
    alert("用户名和密码不能为空");
  }
  else{
    var method="post";
    var url="http://localhost:8080/v1/login?email="+userId+"&password="+userPsd;
    getData(method,url);
    debug("run over");
  }
}
function getData(method,url){
        $.ajax({
        type : method,
        url : url,
        data : {},
        dataType : "json", //返回数据形式为json
        success : function(result) {
        	  pageJump(result);
        },
        error : function(errorMsg) {
        	alert(errorMsg.heading);
    	}
   		});
}

function pageJump(choise){
	switch(choise){
		case "Success":
		debug("success page");
    setUserprofile(0);
    window.open("index.html");
		break;

		case "UserNotFound":
		debug("UserNotFound page");
    setUserprofile(1);
		break;

		case "WrongPasswod":
		debug("WrongPasswod page");
    setUserprofile(1);
		break;
	}
}

function debug(str){
	console.log(str);
}

function setUserprofile(index){
  var element = document.getElementById("headimage");
  switch(index){
    case 0:
      element.src="http://localhost:8080/v1/user/"+userId+"/pic";
    break;

    case 1:
      element.src="./img/wrong1.gif";
    break;
  }
}

function register(){
  window.open("register.html");
}
function forgetPsd(){
  debug("forget password");
}