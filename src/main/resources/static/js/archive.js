$(document).ready(function () {     //让对应当前页数的按钮显示红色
    var pagenum='.pagenum'+$("#pagenums").text();
    $(pagenum).css({"background-color":"red","color":"white"});
});