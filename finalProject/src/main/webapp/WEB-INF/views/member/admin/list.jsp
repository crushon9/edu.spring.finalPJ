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
		<h1>회원 목록 보기 (관리자용)</h1>
		<div>
		<input id="searchMmbId" type="text" placeholder="회원 아이디 검색">
		<a id="searchMmbIdUrl" href=""><input id="searchMmbIdBtn" type="button" value="Search"></a>
		<hr>
		<!-- 회원 목록 출력 -->
		<c:if test="${empty list}">
			검색 결과가 없습니다
		</c:if>
		<c:if test="${not empty list}">
			<table>
				<thead>
					<tr>
						<th style="width: 150px">아이디</th>
						<th style="width: 100px"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${list }">
						<tr>						
							<td><Strong> ${vo.mmbId }</Strong></td>
							<td><a href="detail?mmbId=${vo.mmbId }"><input type="button" value="상세조회"></a></td>
						</tr>											
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		</div>
		<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>

	<script type="text/javascript">
	$(document).ready(function() {
		// 문자열 검색
		$('#searchMmbIdBtn').click(function() {
			var searchMmbId = $('#searchMmbId').val();
			var searchMmbIdUrl = 'list?searchMmbId=' + searchMmbId;
			$('#searchMmbIdUrl').prop("href", searchMmbIdUrl);
			
		});			
	});
	</script>

</body>
</html>