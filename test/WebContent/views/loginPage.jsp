<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<%@include file="include.jsp" %>
<style>
body{
    font-size: 14px;
}
.login-wrapper{
    width: 400px;
    height: 350px;
    padding: 40px;
    box-sizing: border-box;
    margin: auto;
}

.login-wrapper > div{
	width: 40%;
    display: inline-block;
}

.login-wrapper > div > h2{
    font-size: 24px;
    color: #499ED8;
    margin-bottom: 20px;
    cursor: default;
}

.login-wrapper > div > img{
	width: 60%;
	height: 60%;
	float: right;
}


#login-form > input{
    width: 100%;
    height: 48px;
    padding: 0 10px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    border: 1px solid #499ED8;
    background-color: #F8F8F8;
}

#login-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
    background-color: #499ED8;
    margin-top: 20px;
    cursor: pointer;
}

#login-form > input[type="submit"]:hover{
	background-color: #138dde;
	color: white;
}

</style>
<script>
	$(function(){
		$("#img").on("click", function(){
			location.href = "/";
		});
	});
</script>
<body>
	<div id="outer">
		<div class="login-wrapper">
	        <div>
	        	<h2>Login</h2>
	        </div>
	        <div style="float: right;">
	        	<img id="img" alt="" src="../image/nic_sign.png">
	        </div>
	        <form method="post" action="<%=contextPath %>/login" id="login-form">
	            <input type="text" name="memberId" placeholder="Id">
	            <input type="password" name="memberPwd" placeholder="Password">
	            <input type="submit" value="로그인">
	        </form>
	    </div>
	</div>
</body>
</html>