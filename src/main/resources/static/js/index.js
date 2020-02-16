$(document).ready(function () {
    indexright();        //进入页面时自动调用
    indexleft();
    for (var i=0;i<13;i++){
        siftvideo(i);
    }
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

//左边模块：热门视频+热门视频
function indexleft(){
    $.ajax({
        url:"/indexleft",
        type:"GET",
        dataType:"JSON",
        success:function (data) {
            if(data.code==0){
                //热门视频
                $.each(data.data[1].data, function(i,val) {  //两个参数，第一个参数表示遍历的数组的下标(0开始)，第二个参数表示下标对应的值
                    var lookid='#look'+i;   //id号
                    $(lookid+" .looktag").text(val.vtag); //标签
                    $(lookid+" .lookvname").text(val.vname);    //视频名
                    $(lookid+" .lookusername").text(val.user.username);     //上传人
                    $(lookid+" .lookimage").attr("src",val.imageurl);      //封面
                    $(lookid+" .lookurl").attr("href",'/single/'+val.vid);      //路径
                    var lookdate="<i class='fa fa-calendar'></i>"+val.date;
                    $(lookid+" .lookdate").html(lookdate);      //上传时间
                    var countlikes="<i class='fa fa-heart'></i>"+val.countlikes;
                    $(lookid+" .countlikes").html(countlikes);      //喜欢数
                    var countlooks="<i class='fa fa-eye'></i>"+val.countlooks;
                    $(lookid+" .countlooks").html(countlooks);      //观看数
                    $(lookid+" .lookintroduce").html(val.introduce);      //视频介绍
                    var countall="<i class='fa fa-heart'></i>"+val.countlikes+"&nbsp;&nbsp;&nbsp;<i class='fa fa-eye'></i>"+val.countlooks;
                    $(lookid+" .countall").html(countall);      //数据
                })
                //随机视频
                $.each(data.data[2].data, function(i,val) {  //两个参数，第一个参数表示遍历的数组的下标(0开始)，第二个参数表示下标对应的值
                    var randid='#rand'+i;   //id号
                    $(randid+" .randtag").text(val.vtag); //标签
                    $(randid+" .randvname").text(val.vname);    //视频名
                    $(randid+" .randusername").text(val.user.username);     //上传人
                    $(randid+" .randimage").attr("src",val.imageurl);      //封面
                    $(randid+" .randurl").attr("href",'/single/'+val.vid);      //路径
                    var randdate="<i class='fa fa-calendar'></i>"+val.date;
                    $(randid+" .randdate").html(randdate);      //上传时间
                    var countlikes="<i class='fa fa-heart'></i>"+val.countlikes;
                    $(randid+" .countlikes").html(countlikes);      //喜欢数
                    var countlooks="<i class='fa fa-eye'></i>"+val.countlooks;
                    $(randid+" .countlooks").html(countlooks);      //观看数
                })
            }
        }
    })
}

//精选视频  不能在同个方法内写for循环ajax，如上要把for循环写到另一个方法内。https://www.cnblogs.com/destinyruru/p/5716517.html
function siftvideo(i){
        var vid='#vid'+i;   //id号
        var url="/siftvideo/"+$(vid+" .vid").text();    //路径
        $.ajax({
            url:url,
            type:"GET",
            dataType:"JSON",
            success:function (data) {
                if(data.code==0){
                        $(vid+" .sifttag").text(data.data.vtag); //标签
                        $(vid+" .siftvname").text(data.data.vname);    //视频名
                        $(vid+" .siftusername").text(data.data.user.username);     //上传人
                        $(vid+" .siftimage").attr("src",data.data.imageurl);      //封面
                        $(vid+" .sifturl").attr("href",'/single/'+data.data.vid);      //路径
                        var siftdate="<i class='fa fa-calendar'></i>"+data.data.date;
                        $(vid+" .siftdate").html(siftdate);      //上传时间
                        var countlikes="<i class='fa fa-heart'></i>"+data.data.countlikes;
                        $(vid+" .countlikes").html(countlikes);      //喜欢数
                        var countlooks="<i class='fa fa-eye'></i>"+data.data.countlooks;
                        $(vid+" .countlooks").html(countlooks);      //观看数
                        $(vid+" .siftintroduce").html(data.data.introduce);      //视频介绍
                        var countall="<i class='fa fa-heart'></i>"+data.data.countlikes+"&nbsp;&nbsp;&nbsp;<i class='fa fa-eye'></i>"+data.data.countlooks;
                        $(vid+" .countall").html(countall);      //数据
                }
            }
        })
}