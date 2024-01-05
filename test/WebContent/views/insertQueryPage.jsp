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
	$(function(){
		$("#action").on("change", function(){
			if($("#action").val() == "select"){
				
				var str = "<select id='typeKey' name='typeKey' required>"
				        + "<option>반환 형태</option>"
						+ "<option value='resultType'>resultType</option>"
						+ "<option value='resultMap'>resultMap</option>"
						+ "</select> : <input type='text' name='typeValue' placeholder='반환할 타입'>"
				$("#typeSelect").html(str);
			}else{
				$("#typeSelect").html("");
			}
		});
	});
</script>
<body>
	<div id="outer">
		<form action="/insertQuery" method="post">
			<table>
				
				<tr>
					<th width="50">매핑 주소 : </th>
					<td width="50">
						<input type="text" name="mappingId" required>
					</td>
				</tr>
				
				<tr>
					<td>
						쿼리문
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="10" cols="50" style="resize: none;" name="sql" required></textarea>
					<td>
				</tr>
				
				<tr>
					<td>
						설명
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="5" cols="50" style="resize: none;" name="description" required></textarea>
					<td>
				</tr>
				
			</table>
			<div>
				<button type="submit">생성</button>
			</div>
		</form>
	</div>
</body>
</html>