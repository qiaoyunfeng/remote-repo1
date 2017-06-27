<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<div id="loginDialog" title="用户登录" style="display: none;">
	<form id="loginForm" method="post">
	    <div>
	        <label for="phone">账户名称:</label>
	        <input class="easyui-validatebox" type="text" name="phone" data-options="required:true" />
	    </div>

		<div>
	        <label for="password">账户密码:</label>
	        <input class="easyui-validatebox" type="password" name="password"
	        data-options="required:true,validType:'length[6,18]'" />   
	    </div> 
	    <div>
	    	<label>验证码:</label>
	    	<input  class="easyui-validatebox"  data-options="required:true"   type="text" name="imgcode" id="imgcode" style="width:60px"/>
	    	<img src="<%=request.getContextPath()%>/imageCode" id="imgcoode" onclick="getImageCode()">
	   		<span id="yan"></span>
	   		<a class="easyui-linkbutton"  href="javascript:getImageCode()" >看不清换一个</a>
	   	</div>
	    </form> 
	 </div>
	 <script type="text/javascript">
	 function loginUser(){
	      	$.ajax({
            		url:sys.contextPath+'/user/checkSysUserLogin.do',
            		type:'post',
            		//同步
            		async:false,
            		data:$("#loginForm").serialize(),
            		success:function(data){
            			//用户不存在返回true
            			if (data.success) {
            				//alert(data.success);
            				window.location.href=sys.contextPath+"/user/toitemListjsp.do";
        				}else{
        					$.messager.alert('警告',data.msg,"error");    
        				}
            		}
            	})
	 }
	 	$(function(){
	 		$('#loginDialog').dialog({    
	 		    title: '登录',    
		    width: 400,    
	 		    height: 200,     
	 		    //不能关闭窗口
	 		//    closed: true,    
	 		    cache: false,    
	 		    //模态窗口
	 		    modal: true,
	 		    top:250, 
	 		    content : $("#loginForm"),
	 		    buttons:[
	 		             {
	 		            	 text:'登录',
	 		            	 iconCls:'',
	 		            	 handler:function(){
	 		            		loginUser()
	 		              }
	 		             },
	 		             {
	 		            	 text:'请注册',
	 		            	 iconCls:'',
	 		            	 handler:function(){
	 		            		window.location.href="<%=request.getContextPath()%>/register.jsp"
	 		            	 }
	 		             }
	 		           ],
	 		  		onOpen : function() {
	 					$('form :input:first').focus();
	 					$('form :input').keyup(function(event) {
	 						if (event.keyCode == 13) {
	 							loginUser();
	 						}
	 					});
	 				}
	 		});    
	 	})
	 	//随机生成验证码图片
	 	function getImageCode(){
			var thisDate =  new Date();
			//区分当前请求和上一次请求
			document.getElementById("imgcoode").src="<%=request.getContextPath()%>/imageCode?flags="+thisDate.getTime();
	}
	 </script>
</body>
</html>