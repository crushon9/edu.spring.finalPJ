<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Branch admin list</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>Branch admin list</h1>
			 <!-- 영화 검색 -->
			<div>
			지점 <input id="searchText" type="text">
			<a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="검색"></a>&emsp;
			
			지역
			<select id="brcArea" name="brcArea">
				<option value="0">지역선택</option>
				<option value="1">서울</option>
				<option value="2">경기/강원</option>
				<option value="3">부산/경상</option>
				<option value="4">대전/충청</option>
				<option value="5">광주/전라</option>
				<option value="6">제주</option>
			</select>
			
			
			<a id="searchAreaUrl" href=""><input id="searchAreaBtn" type="button" value="검색"></a>
			</div>
			<hr>
			
			<!-- 지점 목록 출력, br con에서 model.addAttribute(list)-->
			<c:forEach var="vo" items="${list }" varStatus="status">
				<ul>						
					<li id="brc${status.index}">						
						<Strong>${vo.brcName } (지점ID_${vo.brcId })</Strong><br>
						<input type="hidden" class="brcArea" value="${vo.brcArea }">
						지역_<div style="display: inline-block;" class="brcAreaName"></div>(${vo.brcArea })
			   			| 극장_${vo.brcTheaterNumbers }관 | 좌석 수_[${vo.brcTheaterSeats }]<br>
						<a href="update?brcId=${vo.brcId}"><input type="button" value="지점수정"></a>	
						<form action="delete" method="post">
							<input type="hidden" name="brcId" value="${vo.brcId }">
							<input type="submit" value="지점삭제">
						</form>
					</li>				
				</ul>											
			</c:forEach>
			
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>	
	 
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
		  var brcAreaArray = ['서울', '경기/강원', '부산/경상', '대전/충청', '광주/전라', '제주'];
		  for (var i = 0; i < $('li').length; i++){
		  	var brcArea = $('#brc' + i).children('.brcArea').val();
		  	$('#brc' + i).children('.brcAreaName').html(brcAreaArray[brcArea - 1]);
		  }
	  };
	 </script>
	
</body>
</html>