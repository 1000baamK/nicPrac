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

#outer>form{
	height: 80%;
	width: 80%;
	padding: 20px 20px;
	border-radius: 8px;
	margin: auto;
	background-color: #499ED8;
	
}
#input_area{
	height: 60%;
	width: 90%;
	padding: 10px;
}

input{
	width: 300px;
	height: 30px;
	border: 1px solid #499ED8;
	border-radius: 6px;
}

.hide{
	display: none;
}

th{
	color: white;
	text-align: left;
}

#input_area td>span{
	font-size: 15px;
	color: white;
}

#btn_area{
text-align: center;

}

.button {
	background: #3D4C53;
	margin : 100px auto;
	width : 200px;
	height : 50px;
	overflow: hidden;
	text-align : center;
	transition : .2s;
	cursor : pointer;
	border-radius: 3px;
	box-shadow: 0px 1px 2px rgba(0,0,0,.2);
	display: inline-block;
}
.btnTwo {
	position : relative;
	width : 200px;
	height : 100px;
	margin-top: -100px;
	padding-top: 2px;
	background : #26A69A;
	left : -250px;
	transition : .3s;
}
.btnText {
	color : white;
	transition : .3s;
}
.btnText2 {
	margin-top : 63px;
	margin-right : -130px;
	color : #FFF;
}
.button:hover .btnTwo{ /*When hovering over .button change .btnTwo*/
	left: -130px;
}
.button:hover .btnText{ /*When hovering over .button change .btnText*/
	margin-left : 65px;
}
.button:active { /*Clicked and held*/
	box-shadow: 0px 5px 6px rgba(0,0,0,0.3);
}


</style>
<body>
	<div id="outer">
		<h1 align="center">회 원 가 입</h1>
		<form action="<%=contextPath %>/join" method="post" id="input_form">
			<table align="center" id="input_area">
				<tr>
					<th width="110"><label for="memberId">아이디</label></th>
					
					<td>
						<input type="text" id="memberId" name="memberId" placeholder="(영문,숫자 5~14자리)" maxlength="14" required style="ime-mode: inactive">
						<span class="hide" id="useId">이미 사용중인 아이디입니다.</span>
						<span class="hide" id="noFormId">양식에 맞게 작성해주세요.</span>
					</td>
				</tr>
				<tr>
					<th><label for="memberPwd">비밀번호</label></th>
					<td>
						<input type="password" id="memberPwd" name="memberPwd" placeholder="(영문,숫자,특수문자  8~16자리)" maxlength="16" required>
						<span class="hide" id="noFormPwd">양식에 맞게 작성해주세요.</span>
					</td>
				</tr>
				<tr>
					<th><label for="memberPwd">비밀번호 확인</label></th>
					<td>
						<input type="password" id="checkPwd" placeholder="사용하실 비밀번호를 다시 입력해주세요." maxlength="16" required>
						<span class="hide" id="noEqualPwd">비밀번호가 일치하지 않습니다.</span>
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" id="email" name="email" placeholder="이메일의 양식에 따라 입력해주세요.(@포함)" required>
						<span class="hide" id="noFormEmail">양식에 맞게 작성해주세요.</span>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" id="name" name="name" placeholder="성함을 입력해주세요." required>
					</td>
				</tr>
			</table>
			
			<div id="btn_area">
				<div class="button" id="submitBtn">
					<p class="btnText">JOIN?</p>
					<div class="btnTwo">
						<p class="btnText2">GO!</p>
					</div>
				</div>
					
				<div class="button" id="cancleBtn">
					<p class="btnText">CANCEL</p>
					<div class="btnTwo">
						<p class="btnText2">X</p>
					</div>
				</div>
				
			</div>
		</form>
	</div>
<script>

	//유효성
	var idExp = /^[a-zA-Z0-9]{5,14}$/; //영문, 숫자 5~14자리
	var pwdExp = /^[a-zA-Z0-9!@#$%^&*()]{8,16}$/; //영문,숫자,특수문자 8~16자리
	var emailExp = /^[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*@[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*\.[a-zA-Z]{2,3}$/i;
	var nameExp = /^[가-힣]{2,4}$/; //한글 2~4자리
	
	//회원가입버튼
	var idPass = false;
	var pwdPass = false;
	var emailPass = false;
	var namePass = false;

	$(function(){
		//회원가입 취소 버튼
		$("#cancleBtn").on("click", function(){
			location.href = "/";
		});
		
		
		//아이디체크
		$("#memberId").on("focusout", function(){
			if($("#memberId").val() != ""){
				//값이 있다면 작업 수행
				if(idExp.test($("#memberId").val())){
					//유효성통과
					$.ajax({
						url:"<%=contextPath%>/idCheck?memberId="+$("#memberId").val(),
						method:"GET",
						success:function(result){
							console.log(result);
							if(result>0){
								//중복
								$("#noFormId").addClass("hide");
								$("#useId").removeClass("hide");
								idPass = false;
							}else{
								//사용가능
								$("#useId").addClass("hide");
								$("#noFormId").addClass("hide");
								idPass = true;
							}
						},
						error:function(){
							alert("아이디 검사 통신 오류");
						}
					});
				}else{
					//유효성 걸리면
					$("#useId").addClass("hide");
					$("#noFormId").removeClass("hide");
					idPass = false;
				}
			}else{
				//값이 없다면
				$("#noFormId").addClass("hide");
				$("#useId").addClass("hide");
				idPass = false;
			}
			
		});
		
		
		//패스워드 유효성 체크
		$("#memberPwd").on("focusout", function(){
			if($("#memberPwd").val() != ""){
				
				if(pwdExp.test($("#memberPwd").val())){
					//통과
					$("#noFormPwd").addClass("hide");
					pwdPass = false;
					
					////////////////////////////////////////////////////
					//비밀번호 재확인이 먼저 입력되었다면 값비교해서 pwdPass 지정
					if($("#memberPwd").val() != ""){
						if($("#memberPwd").val() != $("#checkPwd").val()){
							//같지않음
							$("#noEqualPwd").removeClass("hide");
						}else{
							//같음
							$("#noEqualPwd").addClass("hide");
						}
					}
					////////////////////////////////////////////////////
				}else{
					//실패
					$("#noFormPwd").removeClass("hide");
					$("#checkPwd").val("");
					pwdPass = false;
				}
			}else{
				//비어있음
				$("#noFormPwd").addClass("hide");
				pwdPass = false;
			}
		});
		
		
		//비밀번호 재확인
		$("#checkPwd").on("focusout", function(){
			
			if(($("#memberPwd").val() != "") && ($("#checkPwd").val() != "")){
				console.log(1);
				//비밀번호칸과 비밀번호 확인칸이 채워져 있다면
				if($("#memberPwd").val() != $("#checkPwd").val()){ //그후 같지 않다면
					console.log("같지않음");
					$("#noEqualPwd").removeClass("hide");
					pwdPass = false;
				}else{
					//같다면
					console.log("같음");
					$("#noEqualPwd").addClass("hide");
					pwdPass = true;
				}
			}else{
				pwdPass = false;
			}
		});
		
		//이메일 유효성
		$("#email").on("focusout", function(){
			if($("#email").val() != ""){
				//비어있지않다면
				if(emailExp.test($("#email").val())){
					//통과
					$("#noFormEmail").addClass("hide");
					emailPass = true;
				}else{
					//실패
					emailPass = false;
					$("#noFormEmail").removeClass("hide");
				}
			}else{
				//비어있음
				$("#noFormEmail").addClass("hide");
				emailPass = false;
			}
		});
		
		//이름 유효성
		$("#name").on("focusout", function(){
			if($("#name").val() != ""){
				//작성이 되어있다면 실행
				if(nameExp.test($("#name").val())){
					//통과
					namePass = true;
				}else{
					if(confirm("이름이 확실한가요?")){
						//true
						namePass = true;
						alert("알겠습니다..");
					}else{
						//false
						namePass = false;
						$("#name").val("");
						alert("역시나.. 다시 입력해주세요.");
						$("#name").focus();
					}
				}
			}else{
				namePass = false;
			}
		});
		
		$("#submitBtn").on("click", function(){
			console.log("idPass"+idPass);
			console.log("pwdPass"+pwdPass);
			console.log("emailPass"+emailPass);
			console.log("namePass"+namePass);
			if(idPass && pwdPass && emailPass && namePass){
				//다 true면 submit
				$("#input_form").submit();
			}else{
				alert("양식을 다시한번 확인해주세요.");
			}
		});
		
	});
	
	
</script>
</body>
</html>