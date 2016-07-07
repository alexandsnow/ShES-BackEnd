function register(){
	var userEmail=document.getElementById("in1").value;
	var password=document.getElementById("in2").value;
	var password_confirm=document.getElementById("in3").value;

	if(password != password_confirm){
		alert("please rewrite your password");
	}
	else if(userEmail=="" || password=="" || password_confirm==""){
		alert("null user Email and user password is not permmited");
	}
	else{
		uploadInfo(userEmail,password);
	}
}
function uploadInfo(userEmail,password){
	var MyUrl="http://localhost:8080/v1/user/register/"+userEmail+"/"+password;
    $.ajax({
	    type : "post",
	    url : MyUrl,
	    data : {},
	    dataType : "json", //返回数据形式为json
	    success : function(result) {
	    	  alert("success");
	    },
	    error : function(errorMsg) {
	    	alert(errorMsg.heading);
		}
	});
}