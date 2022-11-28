<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Movie list</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>관리자 영화 목록보기</h1>
			<div>
			<!-- 영화 검색 -->
			<strong>제목 </strong><input id="searchText" type="text">&nbsp;<a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="검색"></a>
			&emsp;&emsp;
			<strong>시작일 </strong><input id="inputDateStarted" type="date">
			<strong>종료일 </strong><input id="inputDateEnded" type="date">
			<a id="searchPeriodUrl" href=""><input id="searchPeriodBtn" type="button" value="검색"></a>
			<hr>
			
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
			</div>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
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