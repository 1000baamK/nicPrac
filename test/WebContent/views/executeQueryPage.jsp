<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="include.jsp" %>
<style>
	form{
		height: 80%;
		width: 100%;
		margin: auto;
	}
</style>
<script>
	
</script>
<body>
	<div id="outer">
		<form action="/executeQuery" method="post">
			
			<textarea rows="10" cols="50" style="resize: none;" name="inputText" required placeholder="실행문 입력"></textarea>
			
			<div>
				<button type="submit">실행</button>
			</div>
		</form>
	</div>
</body>
</html>