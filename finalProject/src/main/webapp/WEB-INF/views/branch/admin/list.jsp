<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Branch list</title>
</head>
<body>
	
	<h2>관리자 지점 목록보기</h2>
	 <!-- 영화 검색 -->
	지점 : 
	<input id="searchText" type="text"> 
	<a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="검색"></a><br>
	지역 : 
	<select id="brcArea" name="brcArea">
		<option>지역선택</option>
		<option value="1">서울</option>
		<option value="2">경기/강원</option>
		<option value="3">부산/경상</option>
		<option value="4">대전/충청</option>
		<option value="5">광주/전라</option>
		<option value="6">제주</option>
	</select>
	<a id="searchAreaUrl" href=""><input id="searchAreaBtn" type="button" value="검색"></a>
	
	
	<!-- 지점 목록 출력, br con에서 model.addAttribute(list)-->
	<c:forEach var="vo" items="${list }">
		<ul>						
			<li>						
				<Strong>${vo.brcName } (지점번호 : ${vo.brcId })</Strong><br>
				<input type="hidden" class="brcArea" value="${vo.brcArea }">
				<div class="brcAreaName"></div> (지역번호 : ${vo.brcArea })<br>
	   			극장 수 : ${vo.brcTheaterNumbers } (좌석 수 : ${vo.brcTheaterSeats })<br>
				<a href="update?brcId=${vo.brcId}"><input type="button" value="지점수정"></a>	
				<form action="delete" method="post">
					<input type="hidden" name="brcId" value="${vo.brcId }">
					<input type="submit" value="지점삭제">
				</form>
			</li>				
		</ul>											
	</c:forEach>
	 
	  <script type="text/javascript">
		  $(document).ready(function() {
				setBrcAreaName();
				// 문자열 검색
				$('#searchTextBtn').click(function() {
					var searchText = $('#searchText').val();
					var searchTextUrl = 'list?searchText=' + searchText;
					$('#searchTextUrl').prop("href", searchTextUrl);
				});
				// Area 검색
				$('#searchAreaBtn').click(function() {
					var brcArea = $('#brcArea').val();
					var searchAreaUrl = 'list?brcArea=' + brcArea;
					$('#searchAreaUrl').prop("href", searchAreaUrl);
				});					
		  });
		  
		  function setBrcAreaName() {
			  console.log('setBrcAreaName() 호출');
			  var brcArea = $('.brcArea').val();
			  console.log(brcArea);
			  switch (brcArea) {
			  case 1:
				$('.brcAreaName').html('서울');
			    break;
			  case 2:
				$('.brcAreaName').html('경기/강원');
				break;
			  case 3:
				$('.brcAreaName').html('부산/경상');
			    break;
			  case 4:
				$('.brcAreaName').html('대전/충청');
				break;
			  case 5:
				$('.brcAreaName').html('광주/전라');
				break;
			  case 6:
				$('.brcAreaName').html('제주');
				break;
			  }
		  };
	  </script>
	
</body>
</html>