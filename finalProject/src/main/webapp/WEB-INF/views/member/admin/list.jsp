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
	
	<!-- 
	<% String mmbId = (String) session.getAttribute("mmbIdSession"); %>
	<input type = "hidden" id="mmbId" value="<%=mmbId %>">
	 -->
	 
	아이디 : 
	<input id="searchMmbId" type="text">
	<a id="searchMmbIdUrl" href=""><input id="searchMmbIdBtn" type="button" value="search"></a>
	
	<!-- 회원 목록 출력 -->
		
	<c:forEach var="vo" items="${list }">
		<ul>						
			<li>
				<Strong>Member ID : ${vo.mmbId }</Strong><br>
				<input type="hidden" class="brcId" value="${vo.brcId }">
			</li>
		</ul>											
	</c:forEach>


	<script type="text/javascript">
		$(document).ready(function() {
			$('#searchMmbIdBtn').click(function() {
				var searchMmbId = $('#searchMmbId').val();
				var searchMmbIdUrl = 'list?searchMmbId=' + searchMmbId;
				$('#searchMmbIdUrl').prop("href", searchMmbIdUrl);
				
			});			
			
		});
	
	</script>

</body>
</html>