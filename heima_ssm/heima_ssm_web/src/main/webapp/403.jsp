<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>

<html>
<head>
    <title>倒计时自动关闭/跳转页面</title>
    <meta charset="utf-8" />
    <script>
        //定义函数myClose关闭当前窗口
        function myClose(){
            //将id为time的元素的内容转为整数，保存在变量n中
            var n=parseInt(time.innerHTML);
            n--;//将n-1
            //如果n==0,关闭页面
            //否则, 将n+秒钟后自动关闭，再保存回time的内容中
            if(n>0){
                time.innerHTML=n+"秒钟后自动跳转到首页！";
                timer=setTimeout(myClose,1000);
            }else{
                location.href='<%= request.getContextPath()%>/';
            }
        }
        var timer=null;
        //当页面加载后，启动周期性定时器，每个1秒执行myClose
        window.onload=function(){
            timer=setTimeout(myClose,1000);
        }
    </script>
</head>
<body>
<h1>权限不足,请联系管理员</h1>
<span id="time">5秒钟后自动跳转到首页！</span><br>
<!-- <a href="javascript:clearTimeout(timer)">留在本页</a> -->
</body>
</html>