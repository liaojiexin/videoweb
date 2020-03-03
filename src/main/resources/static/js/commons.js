$(document).ready(function () {     //进入页面时自动调用
    var vtags=["动漫","教育","娱乐","影视","广告","搞笑","音乐","生活","运动","科技","游戏"];
    for (var i=0;i<vtags.length;i++)
        rollvideo(vtags[i],i);
})
//公共区滚动视频
function rollvideo(vtag,i){
        var rollid="#roll"+i;
        var url="/rollvideo/"+vtag;    //路径
        $.ajax({
            url:url,
            type:"GET",
            dataType:"JSON",
            success:function (data) {
                if(data.code==0&&data.data!=null){
                    $(rollid+" span").text(vtag);         //标签
                    $(rollid+" a").attr("href",'/single/'+data.data.vid);   //路径
                    $(rollid+" p").text(data.data.vname);  //视频名
                    $(rollid+" img").attr("src",data.data.imageurl);      //封面
                }
                else{
                    $(rollid).remove();    //删除
                }
            }
        })
}