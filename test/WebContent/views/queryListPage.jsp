<%@page import="test.query.model.vo.Query"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Query> qList = (ArrayList)request.getAttribute("qList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<%@include file="include.jsp" %>
<style>
outer{
	width: 1400px;
}
table {
  border-collapse: collapse;
  width: 100%;
  margin: 1rem auto;
  border: 1px solid #ddd;
  background-color: white;
}

/* 테이블 행 */
th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  text-align: center;
}

th {
  background-color: #499ED8;
  color: #ddd;
}

td{
	font-size: 14px;
}

/* 테이블 올렸을 때 */
tbody tr:hover {
  background-color: #d3d3d3;
  opacity: 0.9;
  cursor: pointer;
}

/* 테이블 비율 */
th:nth-child(1),
td:nth-child(1) {
  width: 15%;
}

th:nth-child(2),
td:nth-child(2) {
  width: 60%;
}

th:nth-child(3),
td:nth-child(3) {
  width: 25%;
}

/* 모달창 */
#modalContainer {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgba(0, 0, 0, 0.5);
}

#modalContent {
  position: absolute;
  background-color: #ffffff;
  width: 300px;
  height: 150px;
  padding: 15px;
}

#modalContainer.hidden {
  display: none;
}
</style>

<body>
	<div id="outer">
		<table id="">
			<thead>
				<tr>
					<th colspan="3">Query List</th>
				</tr>
				<tr>
					<th>매핑값</th>
					<th>SQL</th>
					<th>설명</th>
				</tr>
			</thead>
			
			<tbody>
				<%if(qList.isEmpty()){ %>
					<tr>
						<td colspan="3">현재 등록된 쿼리문이 없습니다.</td>
					</tr>
				<%}else{ %>
					<%for(int i=0; i<qList.size(); i++){ %>
						<tr>
							<td><%=qList.get(i).getMappingId() %></td>
							<td style="text-align: left"><%=qList.get(i).getSql() %></td>
							<td><%=qList.get(i).getDescription() %></td>
						</tr>
					<%} %>
				<%} %>
			</tbody>
		</table>
	
		<button type="button" id="insertBtn" style="float: right;">추가</button>
	</div>
	
	<div id="modalContainer" class="hidden">
		<div id="modalContent">
			<button type="button" id="updateBtn">수정</button>
			<button type="button" id="deleteBtn">삭제</button>
			<button id="modalClose">닫기</button>
		</div>
	</div>
    
</body>
<script>
	$(function(){
		//추가
		$("#insertBtn").on("click", function(){
			location.href = "<%=contextPath%>/insertQuery";
		});
		
		
		//모달관련
		$("tbody>tr").on("click", function(){
			
			var mappingId = $(this).children().eq(0).text();
			
			$("#modalContainer").removeClass("hidden");
			
			//수정
			$("#updateBtn").on("click", function(){
				location.href = "<%=contextPath%>/updateQuery?mappingId="+mappingId;
			})
			
			//삭제
			$("#deleteBtn").on("click", function(){
				location.href = "<%=contextPath%>/deleteQuery?mappingId="+mappingId;
			});
			
			
		});
		
		//모달닫기
		$("#modalClose").on("click", function(){
			$("#modalContainer").addClass("hidden");
		});
	});
</script>
</html>