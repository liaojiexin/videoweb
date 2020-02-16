$(document).ready(function () {
    indexright();  //右边模块：受欢迎的视频+最新视频     //进入页面时自动调用
    selectcomments(1);  //开始进页面时第一页，查询评论功能

    islike();   //判断当前是否有投票
    //投票
    $("#like button").click(function () {
        addlike();
    })
    //取消投票
    $("#nolike button").click(function () {
        deletelike();
    })

    //插入评论
    $('form[name="form1"]').bind("submit",
        function () {
            insertcomments();
            return false;
        }
    );

    //---------分页栏处理 按钮点击处理---------
        $("#firstpage").click(function () {        //首页
                selectcomments(1);
            }
        );
        $("#prepage").click(function(){        //上一页
                selectcomments($("#apage").text());
            }
        );
        $("#nextpage").click(function(){        //下一页
                selectcomments($("#zpage").text());
            }
        );
        $("#endpage").click(function () {        //末页
                selectcomments($("#allpages").text());
            }
        );
        $("#ipage0 a").click(function(){        //数字页
                selectcomments($("#ipage0 a").text());
            }
        )
        $("#ipage1 a").click(function(){        //数字页
                selectcomments($("#ipage1 a").text());
            }
        )
        $("#ipage2 a").click(function(){        //数字页
                selectcomments($("#ipage2 a").text());
            }
        )
        $("#ipage3 a").click(function(){        //数字页
                selectcomments($("#ipage3 a").text());
            }
        )
        $("#ipage4 a").click(function(){        //数字页
                selectcomments($("#ipage4 a").text());
            }
        )
})
//右边模块：受欢迎的视频+最新视频
function indexright(){
    $.ajax({
        url:"/indexright",
        type:"GET",
        dataType:"JSON",
        success:function (data) {
            if(data.code==0){
                //最受欢迎的视频
                $.each(data.data[1].data, function(i,val) {  //两个参数，第一个参数表示遍历的数组的下标(0开始)，第二个参数表示下标对应的值
                    var likeid='#like'+i;   //id号
                    $(likeid+" .likevtag").text(val.vtag); //标签
                    $(likeid+" .likevname").text(val.vname);    //视频名
                    var likedate="<i class='fa fa-calendar'></i>"+val.date;
                    $(likeid+" .likedate").html(likedate);      //上传时间
                    $(likeid+" .likeusername").text(val.user.username);     //上传人
                    $(likeid+" .likeimage").attr("src",val.imageurl);      //封面
                    $(likeid+" .likeurl").attr("href",'/single/'+val.vid);      //路径
                    var countall="<i class='fa fa-heart'></i>"+val.countlikes+"&nbsp;&nbsp;&nbsp;<i class='fa fa-eye'></i>"+val.countlooks;
                    $(likeid+" .countall").html(countall);      //数据
                })
                //最新视频
                $.each(data.data[2].data, function(i,val) {  //两个参数，第一个参数表示遍历的数组的下标(0开始)，第二个参数表示下标对应的值
                    var newid='#new'+i;   //id号
                    $(newid+" .newtag").text(val.vtag); //标签
                    $(newid+" .newvname").text(val.vname);    //视频名
                    $(newid+" .newusername").text(val.user.username);     //上传人
                    $(newid+" .newimage").attr("src",val.imageurl);      //封面
                    $(newid+" .newurl").attr("href",'/single/'+val.vid);      //路径
                    var newdate="<i class='fa fa-calendar'></i>"+val.date;
                    $(newid+" .newdate").html(newdate);      //上传时间
                    var countlikes="<i class='fa fa-heart'></i>"+val.countlikes;
                    $(newid+" .countlikes").html(countlikes);      //喜欢数
                    var countlooks="<i class='fa fa-eye'></i>"+val.countlooks;
                    $(newid+" .countlooks").html(countlooks);      //观看数
                })
                // var jsonData=JSON.stringify(data.data);      //把对象变成字符串
                // alert(jsonData);
            }
        }
    })
}
//查询相关视频所有的评论
function selectcomments(pageNum) {
    $("#comments blockquote").css('display','block')    //恢复评论框
    for(var y=0;y<5;y++){
        $("#ipage"+y).css('display','inline');  //恢复页码
    }

    var vid=$("#vid").text();
    var url="/selectcomments/"+vid+"?pageNum="+pageNum;
    $.ajax({
        url:url,
        type:"GET",
        dataType:"JSON",
        success:function (data) {
            if(data.code==0){
                //把多余的评论框隐藏起来
                for(var l=6;l>=data.data.list.length;l--){  //data.data.list.length当前一页中评论的数量
                    $("#comments"+l).css('display','none'); //隐藏
                }
                //把评论放到指定位置
                $.each(data.data.list, function(i,val) {  //两个参数，第一个参数表示遍历的数组的下标(0开始)，第二个参数表示下标对应的值
                    var commentsid="#comments"+i;
                    $(commentsid+" textarea").text(val.comment);    //评论
                    var small="  <i class='fa fa-user'></i>"+val.user.username+"&nbsp;&nbsp;&nbsp;<i class='fa fa-calendar'></i>"+val.commenttime;
                    $(commentsid+" small").html(small); //用户名+评论时间
                })

                //把数据放到html隐藏标签内，方便分页栏下次调用
                $("#allpages").text(data.data.pages);   //总页数
                $("#nowpage").text(data.data.pageNum);     //当前页数
                if (data.data.prePage<1){   //上一页
                    $("#apage").text(1);
                }else {
                    $("#apage").text(data.data.prePage);
                }
                if(data.data.nextPage==0){ //下一页
                    $("#zpage").text(data.data.pages);
                }else{
                    $("#zpage").text(data.data.nextPage);
                }

                //---------分页栏处理  中间数字块---------
                if(data.data.pages>5){   //如果全部总页数大于5
                    if(data.data.pageNum<4){      //当前页数小于等于3
                        $("#ipage0 a").text(1);
                        $("#ipage1 a").text(2);
                        $("#ipage2 a").text(3);
                        $("#ipage3 a").text(4);
                        $("#ipage4 a").text(5);
                    }else{
                        if(data.data.pageNum>(data.data.pages-2)){  //如果页数大于等于总页数-2时
                            $("#ipage0 a").text(data.data.pages-4);
                            $("#ipage1 a").text(data.data.pages-3);
                            $("#ipage2 a").text(data.data.pages-2);
                            $("#ipage3 a").text(data.data.pages-1);
                            $("#ipage4 a").text(data.data.pages);
                        }else{
                            $("#ipage0 a").text(data.data.pageNum-2);
                            $("#ipage1 a").text(data.data.pageNum-1);
                            $("#ipage2 a").text(data.data.pageNum);
                            $("#ipage3 a").text(data.data.pageNum+1);
                            $("#ipage4 a").text(data.data.pageNum+2);
                        }
                    }
                }
                if(data.data.pages<5){   //如果全部总页数小于5
                    for(var i=0;i<data.data.pages;i++){
                        $("#ipage"+i+" a").text(i+1);
                    }
                    for(var y=data.data.pages;y<5;y++){
                        $("#ipage"+y).css('display','none');
                    }
                }

                //显示出当前页面对应的按钮
                for(var z=0;z<5;z++)
                {
                    var ipage=$("#ipage"+z+" a").text();
                    if(ipage==data.data.pageNum){
                        $("#ipage"+z+" a").css({"background-color":"red","color":"white"});     //让当前页的按钮显示红色
                    }else{
                        $("#ipage"+z+" a").css({"background-color":"white","color":"black"});       //恢复样式
                    }
                }
            }
        }
    })
}
//评论功能
function insertcomments() {
    $.ajax({
        url:"/insertcomments",
        contentType: 'application/x-www-form-urlencoded',
        type:"POST",
        dataType:"JSON",
        data:{
            comment:$('textarea[name="message"]').val(),
            vid:$("#vid").text(),
        },
        success:function (data) {
            if (data.code==0){      //评论成功
                $("#message").val("");  //清空评论框
                alert('评论成功！');
                selectcomments(1);
            }
            if (data.code==-1){     //失败
                alert('请先登录，再评论！');
            }
        },
        error:function () {
            alert("请求失败");
        }
    });
}
//判断是否投票了
function islike() {
    $.ajax({
        url:"/islike",
        type:"GET",
        dataType:"JSON",
        data:{
            vid:$("#vid").text(),
        },
        success:function (data) {
            if (data.code==0){      //已经有投票了
                $("#nolike").css('display','inline');
                $("#like").css('display','none');
            }
            if (data.code==-1){     //没有投票或者没有登录
                $("#like").css('display','inline');
                $("#nolike").css('display','none');
            }
        }
    })
}
//喜欢视频投票
function addlike() {
    $.ajax({
        url:"/addlike",
        type:"POST",
        dataType:"JSON",
        data:{
            vid:$("#vid").text(),
        },
        success:function (data) {
            if(data.code==0){
                $("#nolike").css('display','inline');
                $("#like").css('display','none');
                alert('感谢你的投票！');
            }
            if (data.code==-1){
                alert('请先登录，在投票！');
            }
        }
    })
}
//取消视频投票
function deletelike() {
    $.ajax({
        url:"/deletelike",
        type:"DELETE",
        dataType:"JSON",
        data:{
            vid:$("#vid").text(),
        },
        success:function (data) {
            if(data.code==0){   //取消投票成功
                $("#like").css('display','inline');
                $("#nolike").css('display','none');
                alert('投票已取消！');
            }
            if (data.code==-1){
                alert('异常！！！');
            }
        }
    })
}