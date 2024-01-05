<%@page import="test.query.model.vo.Query"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Query query = (Query)request.getAttribute("query");
%>
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
	$(function(){
		
		//취소버튼 뒤로가기
		$(function(){
			$("#cancleBtn").on("click", function(){
				history.back();
			});
		});
	});
</script>
<body>
	<div id="outer">
		<form action="/updateQuery" method="post">
			<table>
				
				<tr>
					<th width="50">매핑 주소 : </th>
					<td width="50">
						<input type="text" name="mappingId" value="<%=query.getMappingId()%>" readonly required>
					</td>
				</tr>
				
				<tr>
					<td>
						쿼리문
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="10" cols="50" style="resize: none;" name="sql" required><%=query.getSql() %></textarea>
					<td>
				</tr>
				
				<tr>
					<td>
						설명
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="5" cols="50" style="resize: none;" name="description" required><%=query.getDescription() %></textarea>
					<td>
				</tr>
				
			</table>
			<div>
				<button type="submit">수정</button>
				<button type="button" id="cancleBtn">취소</button>
			</div>
		</form>
	</div>
</body>
</html>