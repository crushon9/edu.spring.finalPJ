<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>branch list for user</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>지점 정보</h1>
		<div>
		<input id="searchText" type="text" placeholder="지점명">&nbsp;<a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="Search"></a>
		&emsp;&emsp;
		<select id="brcArea" name="brcArea">
			<option value="0">지역선택</option>
			<option value="-1">전체지역</option>
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
		<a id="searchAreaUrl" href=""><input id="searchAreaBtn" type="button" value="Go"></a>
		<hr>
		
		<c:forEach var="vo" items="${list }">
			<ul>						
				<li style="list-style-type: none">						
					<p>${vo.brcName }점</p>
					<a href="detail?brcId=${vo.brcId}"><input type="button" value="상세보기"></a>	
				</li>				
			</ul>											
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
		// area 검색
		$('#searchAreaBtn').click(function() {
			var brcArea = $('#brcArea').val();
			var searchAreaUrl = '';
			if (brcArea == -1) {
				searchAreaUrl = 'list';
			} else {
				searchAreaUrl = 'list?brcArea=' + brcArea;
			}
			$('#searchAreaUrl').prop("href", searchAreaUrl);
		});	
	});	
	</script>
	  
</body>
</html>