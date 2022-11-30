<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>Movie List for admin</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
			<h1>영화 목록 보기 (관리자용)</h1>
			<div>
			<!-- 영화 검색 -->
			<strong>시작일 </strong><input id="inputDateStarted" type="date">
			<strong>종료일 </strong><input id="inputDateEnded" type="date">
			<a id="searchPeriodUrl" href=""><input id="searchPeriodBtn" type="button" value="Go"></a>
			&emsp;&emsp;
			<input id="searchText" type="text" placeholder="제목을 입력해주세요.">&nbsp;<a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="Search"></a>
			<hr>			
			<!-- 영화 목록 출력 -->
			<table>
				<thead>
					<tr>
						<th>이미지</th>
						<th>제목</th>
						<th>개봉일</th>
						<th>장르</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
			
				<tbody>
					<c:forEach var="vo" items="${mvList }">
						<tr>				
							<td><img src="/project/img/display?fileName=thumbnail_${vo.mvImage}"/></td>
							<td>${vo.mvTitle }</td>
							<td>${vo.mvDateStarted }</td>
							<td>${vo.mvGenre }</td>
							<td><a href="update?mvId=${vo.mvId}"><input type="button" value="수정"></a></td>
							<td><a href="delete?mvId=${vo.mvId}"><input type="button" value="삭제"></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
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