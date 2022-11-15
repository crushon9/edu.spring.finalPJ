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

</style>
</head>
<body>

	<h2>관리자 영화 목록보기</h2>
	<c:forEach var="vo" items="${mvList }">
		<ol class="mvItem">						
			<li style="list-style-type: none">						
				<a href="movieDetail?mvId=${vo.mvId}"><img class="imageSpace" src="/project/img/display?fileName=${vo.mvImage}"/></a>
				<Strong class="mvTitle" >${vo.mvTitle }</Strong><br>
				<a href="update?mvId=${vo.mvId}"><input type="button" value="영화정보수정"></a>	
			</li>				
		</ol>											
	</c:forEach>
	
</body>
</html>