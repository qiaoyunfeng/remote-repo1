<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>XXX系统首页</title>
<%--
	com.qyf.pojo.SessionInfo sessionInfo = (com.qyf.pojo.SessionInfo) session.getAttribute("sessionInfo");
	if (sessionInfo != null) {//说明 登录成功
		//后台 主页面 
		response.sendRedirect(request.getContextPath()+"/main/main.do");
	} else {//说明 用户没有登录
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
--%>
</head>
<body>

<input id="testid">
<button  onclick="test1()">回写</button>
<div id="divid"></div>
<script>
    function test1(){
        var id=  $("#testid").val();
        $.ajax({
            url:sys.contextPath+'/httpsss/httphuixie.do',
            type:'Post',
            async:true,
            data:{"id": id},
            dataType:'json',
            success:function(data){
                alert(data);
            },
            error:function(){
                $.messager.alert('信息','ajax失败','error');
            }
        });
    }

</script>

</body>
</html>













