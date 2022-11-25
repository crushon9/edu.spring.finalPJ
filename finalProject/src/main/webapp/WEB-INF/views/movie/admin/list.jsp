<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Movie list</title>
<style type="text/css">

.imageSpace {
	width: 100px;
	height: 150px;
}

</style>
</head>
<body>

	<h2>관리자 영화 목록보기</h2>
	<!-- 영화 검색 -->
	제목<input id="searchText" type="text"><a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="검색"></a><br>
	시작일<input id="inputDateStarted" type="date">
	종료일<input id="inputDateEnded" type="date">
	<a id="searchPeriodUrl" href=""><input id="searchPeriodBtn" type="button" value="검색"></a>
	
	<!-- 영화 목록 출력 -->
	<c:forEach var="vo" items="${mvList }">
		<ol>						
			<li style="list-style-type: none">						
				<img class="imageSpace" src="/project/img/display?fileName=${vo.mvImage}"/>
				<br>
				<Strong class="mvTitle" >${vo.mvTitle }</Strong><br>
				<a href="update?mvId=${vo.mvId}"><input type="button" value="정보수정"></a>	
				<a href="delete?mvId=${vo.mvId}"><input type="button" value="정보삭제"></a>	
			</li>				
		</ol>											
	</c:forEach>
	
	<script type="text/javascript">			
		$(document).ready(function() {
			// 문자열 검색
			$('#searchTextBtn').click(function() {
				var searchText = $('#searchText').val();
				var searchTextUrl = 'list?searchText=' + searchText;
				$('#searchTextUrl').prop("href", searchTextUrl);
			});
			// 기간 검색
			$('#searchPeriodBtn').click(function() {
				var inputDateStarted = $('#inputDateStarted').val();
				var inputDateEnded = $('#inputDateEnded').val();
				var searchPeriodUrl = 'list?inputDateStarted=' + inputDateStarted + '&inputDateEnded=' + inputDateEnded;
				$('#searchPeriodUrl').prop("href", searchPeriodUrl);
			});
			
		});	
	</script>
	
</body>
</html>