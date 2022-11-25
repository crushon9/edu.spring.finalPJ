<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>member admin list http://localhost:8080/project/member/admin/list</title>
</head>
<body>
	<h2>admin member list</h2>

	<!-- 영화 목록 출력 -->
	<c:forEach var="vo" items="${list }">
		<ol>						
			<li style="list-style-type: none">						
				회원 아이디 : <Strong class="mmbId">${vo.mmbId }</Strong><br>
									
			</li>	
			<li style="list-style-type: none">						
				회원 아이디 : <Strong class="mmbId">${vo.mmbId }</Strong><br>
									
			</li>			
		</ol>											
	</c:forEach>

</body>
</html>