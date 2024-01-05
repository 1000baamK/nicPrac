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
table {
  border-collapse: collapse;
  width: 800px;
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
  width: 55%;
}

th:nth-child(3),
td:nth-child(3) {
  width: 30%;
}


#navi{
	backgournd-color: #68b5e8;
	list-style-type: none;
}

#navi>li{
	float: left;
}

#navi>li>a{
	display: block;
	background-color: #68b5e8;
	padding: 8px;
	color: #000000;
	text-decoration: none;
	text-align: center;
	font-weight: bold;
}

#navi>li>a:hover{
	background-color: #138dde;
	color: white;
	cursor: pointer;
}
</style>

<script>
	//은행 코드별 은행이름 가져오는 펑션
	function getBankName(value){
		var bank = "";
		
		switch(value){
		case 2 : bank = "IBK기업은행";
		break
		case 3 : bank = "KDB산업은행";
		break
		case 39 : bank = "경남은행";
		break
		case 34 : bank = "광주은행";
		break
		case 12 : bank = "단위농협(지역농축협)";
		break
		case 32 : bank = "부산은행";
		break
		case 45 : bank = "새마을금고";
		break
		case 64 : bank = "산림조합";
		break
		case 88 : bank = "신한은행";
		break
		case 48 : bank = "신협";
		break
		case 27 : bank = "씨티은행";
		break
		case 20 : bank = "우리은행";
		break
		case 71 : bank = "우체국예금보험";
		break
		case 50 : bank = "저축은행중앙회";
		break
		case 37 : bank = "전북은행";
		break
		case 35 : bank = "제주은행";
		break
		case 90 : bank = "카카오뱅크";
		break
		case 92 : bank = "토스뱅크";
		break
		case 81 : bank = "하나은행";
		break
		case 54 : bank = "홍콩상하이은행";
		break
		case 31 : bank = "DGB대구은행";
		break
		case 11 : bank = "NH농협은행";
		break
		case 23 : bank = "SC제일은행";
		break
		case 7 : bank = "SH수협은행";
		break
		}
		
		return bank;
	}
	
	//숫자 쪼개기
	function toMoneyForm(value){
		var money = String(value);
		
		//숫자3개씩마다 , 를 찍는 정규표현식
		money = money.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		
		return money+"원";
	}

	//로그인이 되어있을때
	<%if(loginMember != null){ %>
	$(function(){
		$("#balanceCheck").on("click", function(){
			
			$.ajax({
				url:"<%=contextPath%>/balCheck",
				method:"POST",
				data:{
					memberNo : "<%=loginMember.getMemberNo()%>"
				},
				success:function(list){
					//객체리스트
					if(list != ""){// list != null로 했더니 공백이 존재해서 그런가 있다고 판정됨
						//console.log("롱메");
						//넘어온 객체리스트가 있다면
						
						//동적으로 테이블 행 추가를 위해 str초기화
						var str = "";
						
						var bank = "";
						var money = "";
						
						$.each(list, function(index, item){
							
							bank = getBankName(item.bankCode);
							money = toMoneyForm(item.balance);
							
							str += "<tr><td>"+bank+"</td>"+
										"<td>"+item.accountNumber+"</td>"+
										"<td>"+money+"</td><tr>";
						});
						
						$("#acInfo>tbody").html(str);
						
						//마지막으로 표보여주기
						$("#acInfo").fadeToggle();
					}else{
						//없다면
						//console.log("메롱");
						var str = "<tr><td  colspan='3'>등록된 계좌가 존재하지 않습니다.</td></tr>";
						
						$("#acInfo>tbody").html(str);
						$("#acInfo").fadeToggle();
					}
					
				},
				error:function(){
					alert("계좌 불러오기 통신 오류");
				}
			});
		});
		
		//로그아웃
		$("#logoutBtn").on("click", function(){
			location.href = "<%=contextPath%>/logout";
		});
		
		
		//쿼리문 만들기페이지
		$("#queryList").on("click", function(){
			location.href = "<%=contextPath%>/queryList";
			console.log("hi");
		});
		
	});
	<%}%>
	
	
</script>
<body>
	<div id="outer">
	<%if(loginMember != null){ %>
		<ul id="navi">
			<li><a id="logoutBtn">로그아웃</a></li>
			<li><a id="balanceCheck">계좌 잔액 조회</a></li>
			<li><a id="queryList">Query List</a></li>
		</ul><br><br>
		<hr>
		<span><%=loginMember.getMemberId() %>님 환영합니다.</span>
		<div>
			<table id="acInfo" style="display: none;">
				<thead>
					<tr>
						<th>은행</th>
						<th>계좌번호</th>
						<th>잔액</th>
					</tr>
				</thead>
				<tbody>
					<!-- 동적으로 행 추가 -->
				</tbody>
 			</table>
		</div>
	<%}else{ %>
	<ul id="navi">
		<li><a href="<%=contextPath %>/login">로그인</a></li>
		<li><a href="<%=contextPath %>/join">회원가입</a></li>
	</ul>
	<%} %>
	</div>
</body>
</html>