function getData(){
    var arr=[];
        $.ajax({
        type : "post",
        url : "http://localhost:8080/v1/login?Id=1&password=alex",
        data : {},
        header :"localhost:8080",
        dataType : "json", //返回数据形式为json
        success : function(result) {
               console.log(result)      
        },
        error : function(errorMsg) {
        alert(errorMsg.heading);
    }
   })
   return arr;
}
getData();