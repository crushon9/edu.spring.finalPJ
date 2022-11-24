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
	지점명<input id="searchText" type="text"><a id="searchTextUrl" href=""><input id="searchTextBtn" type="button" value="지점명검색"></a><br>
	지점Area<input id="brcArea" type="number"><a id="searchAreaUrl" href=""><input id="searchAreaBtn" type="button" value="지점Area검색"></a>
	
	
	<!-- 지점 목록 출력, br con에서 model.addAttribute(list)-->
	<c:forEach var="vo" items="${list }">
		<ol>						
			<li style="list-style-type: none">						
			
				<Strong class="mvTitle" >${vo.mvTitle }</Strong><br>
				<a href="update?brcId=${vo.brcId}"><input type="button" value="지점수정"></a>	
				<a href="delete?brcId=${vo.brcId}"><input type="button" value="지점삭제"></a>	
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
				// Area 검색
				$('#searchAreaBtn').click(function() {
					var brcArea = $('#brcArea').val();
					var searchAreaUrl = 'list?brcArea=' + brcArea;
					$('#searchAreaUrl').prop("href", searchAreaUrl);
				});
			  
		   });
	  </script>
	
</body>
</html>