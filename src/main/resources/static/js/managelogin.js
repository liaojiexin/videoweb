$(document).ready(function () {
    $("form").bind("submit",            //处理ajax和form冲突问题，required失效问题，https://blog.csdn.net/weixin_41866607/article/details/104082035
        function () {
            $.ajax({
                url:"/managelogin/login",       //请求路径，和Controller类中的@PostMapping对应
                contentType: 'application/x-www-form-urlencoded',       //客户端实际返回的内容的内容类型,form默认application/x-www-form-urlencoded
                type: "POST",                       //请求方式
                dataType: "JSON",               //预期服务器返回的数据类型。
                data:{
                    mname:$('input[name="mname"]').val(),
                    mpassword:$('input[name="mpassword"]').val(),
                },
                success: function (data) {      //function的参数为controller返回的json值
                    if(data.code==0){
                        //重定向到登录页面，在MyMvcConfig中配置好映射 registry.addViewController("/manage").setViewName("manage");
                        window.location.href="/manage";
                    }
                    else if(data.code==-1){
                        $("#msgmanagelogin").text(data.data);      //显示错误信息
                    }
                },
                error:function () {
                    alert("请求失败");
                }
            });
            return false;
        })
})