<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>member list</title>
</head>
<body>
	<h2>admin member list show up</h2>

	<!-- 영화 목록 출력 -->
	<c:forEach var="vo" items="${list }">
		<ol>						
			<li style="list-style-type: none">						
				<Strong class="mmbId">${vo.mmbId }</Strong><br>
				<br>
				<a href="update?mmbId=${vo.mmbId}"><input type="button" value="회원정보 조작"></a>	
				<a href="delete?mmbId=${vo.mmbId}"><input type="button" value="회원 강제 탈퇴 ㅋㅋ"></a>	
			</li>				
		</ol>											
	</c:forEach>

</body>
</html>