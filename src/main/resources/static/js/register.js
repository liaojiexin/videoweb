$(document).ready(function () {
    $("form").bind("submit",            //处理ajax和form冲突问题，required失效问题，https://blog.csdn.net/weixin_41866607/article/details/104082035
        function () {
            $.ajax({
                url:"/user/register",       //请求路径，和Controller类中的@PostMapping对应
                contentType: 'application/x-www-form-urlencoded',       //客户端实际返回的内容的内容类型,form默认application/x-www-form-urlencoded
                type: "POST",                       //请求方式
                dataType: "JSON",               //预期服务器返回的数据类型。
                data:{
                    username:$('input[name="username"]').val(),
                    password:$('input[name="password"]').val(),
                    repassword:$('input[name="repassword"]').val(),
                    email:$('input[name="email"]').val(),
                },
                success: function (data) {      //function的参数为controller返回的json值
                    // var jsonData=JSON.stringify(data);    //stringify()用于从一个json对象解析出json字符串,alert可以完整的输出内容，
                    // alert(jsonData);                      //但是js操作的是对象，所以此处不能转化成字符串
                    if(data.code==0){
                        alert("注册成功");
                        //重定向到登录页面，在MyMvcConfig中配置好映射 registry.addViewController("/login").setViewName("login");
                        window.location.href="/login";
                    }
                    else if(data.code==-1){
                        $("#msgregister").text(data.data);      //显示错误信息
                        alert("注册失败");
                    }
                },
                error:function () {
                    alert("请求失败");
                }
            });
            return false;
        })
})