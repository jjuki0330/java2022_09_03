<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME PAGE</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="/resources/header.css">
<link rel="stylesheet" href="/resources/index.css">
</head>
<body>
<header>
 <ul>
 	<li><div class="nav one"></div></li>
 	<li><div class="nav two"></div></li>
 	<li><div class="nav three"></div></li>
 	<li><div class="nav four"></div></li>
 </ul>
</header>
<!-- menu bar -->
<div class="menubar">
	<c:if test="${ses.email eq null || ses.email eq '' }">
	<a href="/mem/login"><button type="button">Log in</button></a>
	<a href="/mem/join"><button type="button">Sign up</button></a>
	</c:if>
	<c:if test="${ses.email ne null && ses.email ne ''}">
	<a href="/mem/log_out"><button type="button">Log out</button></a>
	<a href="/mem/detail"><button type="button">My page</button></a>
	</c:if>
	<a href="/brd/list"><button type="button">Guest book</button></a>
</div>
<!-- main화면  -->
<div class="main">
	<div class="maincontainer ">
		<div class="img">
			<img alt="사진없음" src="/_fileUpload/mainImage.jpg">
		</div>
		<div class="maincontent">
			<h1>WELCOME JJuKi WORLD!</h1>
			<p>안녕하세요!<br>열정 넘치는 신입 개발자 쭈키입니다.<br>만나서 반가워요!</p>
			<p>Hello! I'm jjuki, junior developer with passion<br>
			Thank you for visiting my introduction page</p>
		</div>
	</div>
	<div>참고 앨범</div>
</div>
<!-- about me 화면 -->
<div class="aboutme">
	<div class="titleContainer tFirst">
		<div class="title">ABOUT ME</div>
	</div>
	<div class="contentContainer">
		<div class="contentimage">
			<img alt="" src="/_fileUpload/girl.png">
		</div>
		<div class="content">
			<div class="text">
				<div class="job">Web developer</div>
				<div class="intro">언제나 최선을 다하고 열정을 다하는 신입 개발자 홍준희입니다.
				하아…할말이 없구난… 링딩동 링딩동 딩딩동 리기디읻이ㅣㅇ동
				</div>
				<div class="myinfo">
					<ul>
						<li>이름: 홍준희</li>
						<li>생년월일: 1999.03.30</li>
						<li>MBTI: ISFP</li>
						<li>거주지: 인천</li>
						<li>최종학력: 경기대학교 신소재공학과 학사</li>
						<li>메일: qnvuddurh211@gmail.com</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!--skill  -->
<div class="skill">
	<div class="titleContainer tSecond">
		<div class="title">SKILL</div>
	</div>
	<table class="skillList">
		<tr>
			<td class="list java"><div class="listTitle1"><div class="listCover java"></div><span>국비지원교육</span></div></td>
			<td class="list certi"><div class="listTitle2"><div class="listCover certi"></div><span>정보처리기사</span></div></div></td>
			<td class="list none1"><div class="listTitle3"><div class="listCover none1"></div><span>없졍...</span></div></td>
		</tr>
	</table>
</div>
<!-- skill 국비지원 -->
<div class="skillOne skillFst">
	<div class="specContainer">
		<div class="skillImg"></div>
		<div class="skillSpec">
			<div class="skillTitle">자바 웹개발 프로그래밍 국비지원교육 수료</div>
			<div class="skillTerm">2022.09.03 ~ 2023.1.26(5개월)</div>
			<div class="skillAdd">이젠아카데미컴퓨터학원 인천</div>
			<div class="skillInfo"> Backend: java, mySQL <br> Frontend: javascript, HTML, CSS <br> Tool: notion, vscode, eclipse 
				<br> 프로그래밍 언어인 java의 기초부터 java를 활용한 여러가지 프로젝트 수행까지 진행하는 교육으로써 숭구리당당 구리구리뽕뽕
			    <br> 가나다라마바사 아자차카타파하 치키차카초콬초코초초초</div>
		</div>	
	</div>
	<div class="goback">X</div>
</div>
<!-- skill 정처기 -->
<div class="skillOne skillSec">
	<div class="specContainer">
		<div class="skillImg"></div>
		<div class="skillSpec">
			<div class="skillTitle">정보처리기사</div>
			<div class="skillTerm">2022.09.03 ~ 2023.1.26(5개월)</div>
			<div class="skillAdd">이젠아카데미컴퓨터학원 인천</div>
			<div class="skillInfo"> Backend: java, mySQL <br> Frontend: javascript, HTML, CSS <br> Tool: notion, vscode, eclipse 
				<br> 프로그래밍 언어인 java의 기초부터 java를 활용한 다양한 프로젝트 수행까지 진행하는 교육으로써 숭구리당당 구리구리뽕뽕
			    <br> 가나다라마바사 아자차카타파하 치키차카초콬초코초초초</div>
		</div>	
	</div>
	<div class="goback">X</div>
</div>
<!-- skill 없져... -->
<div class="skillOne skillTrd">
	<div class="specContainer">
		<div class="skillImg"></div>
		<div class="skillSpec">
			<div class="skillTitle">없져...</div>
			<div class="skillTerm">2022.09.03 ~ 2023.1.26(5개월)</div>
			<div class="skillAdd">이젠아카데미컴퓨터학원 인천</div>
			<div class="skillInfo"> Backend: java, mySQL <br> Frontend: javascript, HTML, CSS <br> Tool: notion, vscode, eclipse 
				<br> 프로그래밍 언어인 java의 기초부터 java를 활용한 다양한 프로젝트 수행까지 진행하는 교육으로써 숭구리당당 구리구리뽕뽕
			    <br> 가나다라마바사 아자차카타파하 치키차카초콬초코초초초</div>
		</div>	
	</div>
	<div class="goback">X</div>
</div>
<!-- project  -->
<div class="project">
	<div class="titleContainer tThird">
		<div class="title">PROJECT</div>
	</div>
	<table class="projectList">
		<tr>
			<td class="list"><div id="listTitle1"><div class="listCover music"></div><span>KORINIMusicPlayer</span></div></td>
			<td class="list"><div id="listTitle2"><div class="listCover korini"></div><span>KORINI</span></div></td>
			<td class="list"><div id="listTitle3"><div class="listCover none2"></div><span>없져....</span></div></td>
		</tr>
	</table>
</div>
<!-- project 뮤직플레이어 -->
<div class="skillOne pFst">
	<div class="specContainer">
		<div class="skillImg"></div>
		<div class="skillSpec">
			<div class="skillTitle">KORINIMusicPlayer</div>
			<div class="skillTerm">2022.09.03 ~ 2023.1.26(5개월)</div>
			<div class="skillAdd">이젠아카데미컴퓨터학원 인천</div>
			<div class="skillInfo"> Backend: java, mySQL <br> Frontend: javascript, HTML, CSS <br> Tool: notion, vscode, eclipse 
				<br> 프로그래밍 언어인 java의 기초부터 java를 활용한 다양한 프로젝트 수행까지 진행하는 교육으로써 숭구리당당 구리구리뽕뽕
			    <br> 가나다라마바사 아자차카타파하 치키차카초콬초코초초초</div>
		</div>	
	</div>
	<div class="goback">X</div>
</div>
<!-- project 코리니 -->
<div class="skillOne pSec">
	<div class="specContainer">
		<div class="skillImg"></div>
		<div class="skillSpec">
			<div class="skillTitle">KORINI(약속잡기 어플)</div>
			<div class="skillTerm">2022.09.03 ~ 2023.1.26(5개월)</div>
			<div class="skillAdd">이젠아카데미컴퓨터학원 인천</div>
			<div class="skillInfo"> Backend: java, mySQL <br> Frontend: javascript, HTML, CSS <br> Tool: notion, vscode, eclipse 
				<br> 프로그래밍 언어인 java의 기초부터 java를 활용한 다양한 프로젝트 수행까지 진행하는 교육으로써 숭구리당당 구리구리뽕뽕
			    <br> 가나다라마바사 아자차카타파하 치키차카초콬초코초초초</div>
		</div>	
	</div>
	<div class="goback">X</div>
</div>
<!-- project 없져 -->
<div class="skillOne pTrd">
	<div class="specContainer">
		<div class="skillImg"></div>
		<div class="skillSpec">
			<div class="skillTitle">없졍...</div>
			<div class="skillTerm">2022.09.03 ~ 2023.1.26(5개월)</div>
			<div class="skillAdd">이젠아카데미컴퓨터학원 인천</div>
			<div class="skillInfo"> Backend: java, mySQL <br> Frontend: javascript, HTML, CSS <br> Tool: notion, vscode, eclipse 
				<br> 프로그래밍 언어인 java의 기초부터 java를 활용한 여러가지 프로젝트 수행까지 진행하는 교육으로써 숭구리당당 구리구리뽕뽕
			    <br> 가나다라마바사 아자차카타파하 치키차카초콬초코초초초</div>
		</div>	
	</div>
	<div class="goback">X</div>
</div>
<!-- contact -->
<div class="contact">
	<div class="contactTitle">CONTACT</div>
	<div class="contactContainer">
		<ul class="contactwayList">
			<li>
				<span class="material-symbols-outlined">
				person
				</span>
				<span class="contactway">Hong junhee</span>
			</li>
			<li>
				<span class="material-symbols-outlined">
				phone_iphone
				</span>
				<span class="contactway">010-4110-9810</span>
			</li>
			<li>
				<span class="material-symbols-outlined">
				mail
				</span>
				<span class="contactway">qnvuddurh211@gmail.com</span>
			</li>
			<li>
				<span class="material-symbols-outlined">
					note_alt
				</span>
				<span class="contactway">notion</span>
			</li>
		</ul>
		<div>
			<ul class="contactSNS">
				<li><img alt="사진 없음" src="/_fileUpload/facebook.png"></li>
				<li><img alt="사진 없음" src="/_fileUpload/instagram.png"></li>
				<li><img alt="사진 없음" src="/_fileUpload/twitter.png"></li>
			</ul>
		</div>
	</div>
</div>
<script>
document.querySelectorAll(".listCover").forEach((e)=>{
	let i = e.classList[1];
	e.addEventListener('click',function(){
		switch(i){
			case "java": document.querySelector('.skillFst').style.display='flex';break;
			case "certi": document.querySelector('.skillSec').style.display='flex';break;
			case "none1": document.querySelector('.skillTrd').style.display='flex';break;
			case "music": document.querySelector('.pFst').style.display='flex';break;
			case "korini": document.querySelector('.pSec').style.display='flex';break;
			case "none2": document.querySelector('.pTrd').style.display='flex';break;
		}
	})
})
document.querySelectorAll(".goback").forEach((e)=>{
	e.addEventListener('click',function(){
		e.parentElement.style.display='none';
	})

})
</script>
</body>
</html>