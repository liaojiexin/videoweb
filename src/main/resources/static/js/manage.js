//个人资料和视频审核
$(document).ready(function () {
    selectmanager();   //进入个人中心是显示管理员个人资料
    selectPersonalVideo(1,"");  //视频审核查出视频，分页

    //---------视频管理分页栏处理 按钮点击处理---------
    $("#personalfirstpage").click(function () {        //首页
            selectPersonalVideo(1,$("#personalvname").text());
        }
    );
    $("#personalprepage").click(function(){        //上一页
            selectPersonalVideo($("#personalapage").text(),$("#personalvname").text());
        }
    );
    $("#personalnextpage").click(function(){        //下一页
            selectPersonalVideo($("#personalzpage").text(),$("#personalvname").text());
        }
    );
    $("#personalendpage").click(function () {        //末页
            selectPersonalVideo($("#personalallpages").text(),$("#personalvname").text());
        }
    );
    $("#personalipage0 a").click(function(){        //数字页
            selectPersonalVideo($("#personalipage0 a").text(),$("#personalvname").text());
        }
    )
    $("#personalipage1 a").click(function(){        //数字页
            selectPersonalVideo($("#personalipage1 a").text(),$("#personalvname").text());
        }
    )
    $("#personalipage2 a").click(function(){        //数字页
            selectPersonalVideo($("#personalipage2 a").text(),$("#personalvname").text());
        }
    )
    $("#personalipage3 a").click(function(){        //数字页
            selectPersonalVideo($("#personalipage3 a").text(),$("#personalvname").text());
        }
    )
    $("#personalipage4 a").click(function(){        //数字页
            selectPersonalVideo($("#personalipage4 a").text(),$("#personalvname").text());
        }
    )
})

//进入个人中心是显示个人资料
function selectmanager() {
    $.ajax({
        url:"/manage/selectmanager",
        type:"POST",
        dataType:"JSON",
        success:function (data) {
            if (data.code==0){
                $("#mname").val(data.data.mname);
                $("#email").val(data.data.email);
                $("#cellphone").val(data.data.cellphone);
            }
        }
    })
}
//修改资料
$(document).ready(function() {
    $('#modifiedData').click(function(){
        $(this).parents('form')
            .find('#alldata input')
            .each(function(i) {
                $(this).prop("readonly",false);
            });
        $(this).css('display','none');
        $("#modified1").css('display','inline');
        $("#cancel1").css('display','inline');
    });
});
//取消资料修改
$(document).ready(function() {
    $('#cancel1').click(function(){
        $(this).parents('form')
            .find('#alldata input')
            .each(function(i) {
                $(this).prop("readonly",true);
            });
        $("#modifiedData").css('display','inline');
        $("#modified1").css('display','none');
        $(this).css('display','none');
        selectmanager();   //恢复数据
    });
});
//修改资料确认
$(document).ready(function() {
    $("form[name='alldata']").bind("submit",
        function(){
            $.ajax({
                url:"/manage/updatemanager",
                type:"PUT",
                dataType:"JSON",
                data:{
                    mname:$("#mname").val(),
                    email:$("#email").val(),
                    cellphone:$("#cellphone").val()
                },
                success:function (data) {
                    if (data.code==0){
                        $("#alldata input").prop("readonly",true);
                        $("#modifiedData").css('display','inline');
                        $("#modified1").css('display','none');
                        $("#cancel1").css('display','none');
                        alert('个人资料修改成功！');
                        selectmanager();   //恢复数据
                    }
                    if(data.code==-1){
                        alert(data.data);
                    }
                },
                error:function () {
                    alert("请求失败");
                }
            })
            return false;
        });
});
//修改密码
$(document).ready(function() {
    $('#modifiedPassword').click(function(){
        $(this).parents('form')
            .find('#allpassword input')
            .each(function(i) {
                $(this).prop("readonly",false);
            });
        $("#modified2").css('display','inline');
        $("#cancel2").css('display','inline');
        $(this).css('display','none');
    });
});
//取消密码修改
$(document).ready(function() {
    $('#cancel2').click(function(){
        $(this).parents('form')
            .find('#allpassword input')
            .each(function(i) {
                $(this).prop("readonly",true);
            });
        $("#modified2").css('display','none');
        $(this).css('display','none');
        $("#modifiedPassword").css('display','inline');
        $("input[name='password']").val("");  //清空三个密码输入框
    });
});
//修改密码确认
$(document).ready(function() {
    $("form[name='allpassword']").bind("submit",
        function(){
            $.ajax({
                url:"/manage/updatepassword",
                type:"POST",
                dataType:"JSON",
                data:{
                    oldPassword:$("#oldPassword").val(),    //旧密码
                    newPassword:$("#newPassword").val(),    //新密码
                    newagPassword:$("#newagPassword").val()     //确认新密码
                },
                success:function (data) {
                    if (data.code==0){
                        $("#allpassword input").prop("readonly",true);
                        $("#modified2").css('display','none');
                        $("#cancel2").css('display','none');
                        $("#modifiedPassword").css('display','inline');
                        $("input[name='password']").val("");  //清空三个密码输入框
                        alert('密码修改成功！');
                    }
                    if(data.code==-1){
                        alert(data.data);
                    }
                },
                error:function () {
                    alert("请求失败");
                }
            })
            return false;
        });
});

//视频审核查出视频，分页
function selectPersonalVideo(pageNum,vname) {
    for(var y=0;y<5;y++){
        $("#personalipage"+y).css('display','inline');  //恢复页码
    }

    var url="/manage/selectpersonalvideo?pageNum="+pageNum+"&vname="+vname;
    $.ajax({
        url:url,
        type:"GET",
        dataType:"JSON",
        success:function (data) {
            if(data.code==0){
                var videohtml;
                $.each(data.data.list,function(i,val) {  //两个参数，第一个参数表示遍历的数组的下标(0开始)，第二个参数表示下标对应的值
                    videohtml=videohtml+"<tr style='background:#ffffff'>  <td>"+val.vid+"</td> <td> <a href='/download/"+val.vid+"'>" +val.vname+
                        "</a></td> <td><textarea class='form-control' style='font-size: 12px;resize: none;height: 150px' readonly='readonly'>"
                        +val.introduce+"</textarea></td>  <td>"+val.user.username+"</td>  <td>"+val.date+"</td>  <td>"+val.vtag+
                        "</td> <td style='font-size: 12px'><div>封面：<input type='file' id='image"+i+"' style='width: 200px;float: right'></div>" +
                        "<div><br>视频：<input type='file' id='video"+i+"' style='width: 200px;float: right'></div>"+
                        "<button style='height: 25px;font-size: 12px' onclick='manageVideo("+val.vid+","+i+","+1+")' type='button' class='btn btn-danger'>通过并上传封面</button>"+
                        "<button style='height: 25px;font-size: 12px' onclick='manageVideo("+val.vid+","+i+","+2+")' type='button' class='btn btn-danger'>通过并上传视频和封面</button>"+
                        "<button style='height: 25px;font-size: 12px' onclick='manageVideo("+val.vid+","+i+","+-1+")' type='button' class='btn btn-danger'>不通过：视频涉及非法内容</button>"+
                        "<button style='height: 25px;font-size: 12px' onclick='manageVideo("+val.vid+","+i+","+-2+")' type='button' class='btn btn-danger'>不通过：文件格式不规范</button></td></tr>";
                })
                $("#personalvideo").html(videohtml);

                //把数据放到html隐藏标签内，方便分页栏下次调用
                $("#personalvname").text(vname); //搜索视频的内容
                $("#personalallpages").text(data.data.pages);   //总页数
                $("#personalnowpage").text(data.data.pageNum);     //当前页数
                if (data.data.prePage<1){   //上一页
                    $("#personalapage").text(1);
                }else {
                    $("#personalapage").text(data.data.prePage);
                }
                if(data.data.nextPage==0){ //下一页
                    $("#personalzpage").text(data.data.pages);
                }else{
                    $("#personalzpage").text(data.data.nextPage);
                }

                //---------分页栏处理  中间数字块---------
                if(data.data.pages>5){   //如果全部总页数大于5
                    if(data.data.pageNum<4){      //当前页数小于等于3
                        $("#personalipage0 a").text(1);
                        $("#personalipage1 a").text(2);
                        $("#personalipage2 a").text(3);
                        $("#personalipage3 a").text(4);
                        $("#personalipage4 a").text(5);
                    }else{
                        if(data.data.pageNum>(data.data.pages-2)){  //如果页数大于等于总页数-2时
                            $("#personalipage0 a").text(data.data.pages-4);
                            $("#personalipage1 a").text(data.data.pages-3);
                            $("#personalipage2 a").text(data.data.pages-2);
                            $("#personalipage3 a").text(data.data.pages-1);
                            $("#personalipage4 a").text(data.data.pages);
                        }else{
                            $("#personalipage0 a").text(data.data.pageNum-2);
                            $("#personalipage1 a").text(data.data.pageNum-1);
                            $("#personalipage2 a").text(data.data.pageNum);
                            $("#personalipage3 a").text(data.data.pageNum+1);
                            $("#personalipage4 a").text(data.data.pageNum+2);
                        }
                    }
                }
                if(data.data.pages<5){   //如果全部总页数小于5
                    for(var i=0;i<data.data.pages;i++){
                        $("#personalipage"+i+" a").text(i+1);
                    }
                    for(var y=data.data.pages;y<5;y++){
                        $("#personalipage"+y).css('display','none');
                    }
                }

                //显示出当前页面对应的按钮
                for(var z=0;z<5;z++)
                {
                    var personalipage=$("#personalipage"+z+" a").text();
                    if(personalipage==data.data.pageNum){
                        $("#personalipage"+z+" a").css({"background-color":"red","color":"white"});     //让当前页的按钮显示红色
                    }else{
                        $("#personalipage"+z+" a").css({"background-color":"white","color":"black"});       //恢复样式
                    }
                }
            }
        }
    })
}
//视频审核操作  i对应那一行的数据，方便取到该行上传的数据（全部0~4共5行）
// z对应哪个操作（1通过上传封面  2通过上传视频和封面   -1非法内容 -2文件不规范）
function manageVideo(vid,i,z){
    if ((z==1||z==2)&&($("#image"+i)[0].files[0]==null))
        alert('视频封面不能为空，请选择上传的视频封面！');
    else
        if((z==1)&&($("#video"+i)[0].files[0]!=null))
        alert("无需上传视频的情况时，请勿选择视频文件！");
    else
        if((z==2)&&($("#video"+i)[0].files[0]==null))
            alert('视频内容不能为空，请选择上传的视频！');
    else
        if (confirm("确定对该视频进行此操作吗?")) {
            var formdata = new FormData();      //FormData（） 文件上传https://developer.mozilla.org/zh-CN/docs/Web/API/FormData/FormData
            formdata.append('vid',vid);
            if (z==1||z==2)
                formdata.append('file1',$("#image"+i)[0].files[0]); //封面
            if(z==2)
                formdata.append('file2',$("#video"+i)[0].files[0]); //视频
            $.ajax({
                url:"/auditVideo/"+z,
                type:"PUT",
                dataType:"JSON",
                // contentType默认的值为：'application/x-www-form-urlencoded; charset=UTF-8，
                // 而文件上传一个是multipart/form-data，但是请求内容不只是文件上传。所有使用contentType:false
                contentType:false,
                //processData参数默认的值为true，会转数据格式，上传不需要转，所有使用processData: false
                processData:false,
                data:formdata,
                success:function (data) {
                    if (data.code==0){
                        alert('操作成功！');
                        selectPersonalVideo($("#personalnowpage").text(),"");
                    }else{
                        alert(data.data);
                    }
                },
                error:function () {
                    alert('操作失败，异常！')
                }
            })
        }
}
//视频审核 显示全部视频
$(document).ready(function() {
    $('#allpersonalvideo').click(function(){
        selectPersonalVideo(1,"");
        $('#allpersonalvideo').css('display','none');
    });
});
//视频审核搜索视频
$(document).ready(function() {
    $("form[name='personalvideosearch']").bind("submit",
        function(){
            $("#personalvideo").html("");   //清空表格,不清空会导致搜索不到时信息重叠
            selectPersonalVideo(1,$("input[name=searchvname]").val());
            $('#allpersonalvideo').css('display','inline');
            return false;
        });
});