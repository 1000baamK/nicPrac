<%@page import="test.common.Client"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="test.member.model.vo.Member"%>
<% String contextPath = request.getContextPath(); %>
<% Member loginMember = (Member)session.getAttribute("loginMember"); %>
<% String msg = (String)session.getAttribute("msg"); %>
<% String ip = Client.getClientIP(request); %>
<!-- log4j -->
<%! static Logger log = LogManager.getLogger("include.jsp"); %>
<%
	log.debug("[{}] 접속 - {}", ip, request.getRequestURI());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<style>
/* @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
body{
	font-family: 'Roboto', sans-serif;
}
 */
#outer{
	margin: auto;
	height:700px;
	width: 1000px;
}
</style>
<script>
	//자동완성기 끄기
	$(function(){
		$("input").attr("autocomplete", "off");
		
	});
	
	//메세지 출력
	$(function(){
		<%if(msg != null){%>
			//msg가 있다면
			alert("<%=msg%>");
			<% session.removeAttribute("msg");%>
			location.reload();
		<%}%>
	});
	
</script>
<body>

</body>
</html>