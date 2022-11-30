<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
<title>admin member list</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>회원 리스트(관리자용 페이지)</h1>
		<div>
		아이디 <input id="searchMmbId" type="text">
		<a id="searchMmbIdUrl" href=""><input id="searchMmbIdBtn" type="button" value="Search"></a>
		<hr>
		<!-- 회원 목록 출력 -->
		<c:forEach var="vo" items="${list }">
			<ul>						
				<li>
					<Strong>Member ID : ${vo.mmbId }</Strong><br>
					<a href="detail?mmbId=${vo.mmbId }"><input type="button" value="상세조회"></a>	
				</li>
			</ul>											
		</c:forEach>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

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