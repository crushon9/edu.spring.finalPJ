<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>branch list for user</title>
</head>
<body>
	
	<h2>branch list for user</h2>
	
	지점명 : <input id="searchText" type="text"><a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="지점명 검색"></a><br>

	지역 : 
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
	
	<c:forEach var="vo" items="${list }">
		<ul>						
			<li style="list-style-type: none">						
				<Strong>${vo.brcName }</Strong><br>
				<a href="detail?brcId=${vo.brcId}"><input type="button" value="상세보기"></a>	
			</li>				
		</ul>											
	</c:forEach>
	
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
				var searchAreaUrl = 'list?brcArea=' + brcArea;
				$('#searchAreaUrl').prop("href", searchAreaUrl);
			});	
		});	
	 </script>
	  
</body>
</html>