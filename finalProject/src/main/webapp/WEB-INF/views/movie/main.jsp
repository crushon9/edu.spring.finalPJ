<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>movie chart</title>
</head>

<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>
	
		<div id="layoutSidenav_content">
			<h1>무비 차트</h1>
			<div>
		    <!-- 영화 정렬 -->
			<select id="orderChoice">
				<option value="ticketSales">예매율순</option><!-- 조건절로 바꾸기 -->
				<option value="reviewAvg">평점순</option>			
			</select>
			<a id="orderUrl" href=""><input type="button" value="Go"></a>&emsp;&emsp;
			<!-- 영화 검색 -->
			<input id="searchText" type="text" placeholder="영화 제목을 입력하세요">&nbsp;<a id="searchUrl" href=""><input id="searchBtn" type="button" value="Search"></a>
			<hr>
			
			<!-- 영화 목록 출력 -->
			<c:forEach var="vo" items="${mvList }">
				<ol class="mvItem">						
					<li style="list-style-type: none">
						<div>
							<a href="movieDetail?mvId=${vo.mvId}"><img class="imageSpace" src="/project/img/display?fileName=${vo.mvImage}"/></a>
						</div>
						<div class="mvTitle">
							<Strong class="mvTitle" >${vo.mvTitle }</Strong><br>
						</div>
						<a href="/project/schedule/list"><input id="mvTicket" type="button" value="예매하기"></a>							
						<a href="detail?mvId=${vo.mvId}"><input id="mvDetail" type="button" value="영화상세정보"></a>	
					</li>				
				</ol>											
			</c:forEach>
			</div>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
	<script type="text/javascript">			
		$(document).ready(function() {
			// 정렬
			$('#orderChoice').click(function() {
				var orderChoice = $('#orderChoice').val();
				var orderUrl = 'main?orderChoice=' + orderChoice;
				console.log(orderChoice);
				console.log(orderUrl);
				$('#orderUrl').prop("href", orderUrl);
			});//end orderChoice_click();
			
			// 검색
			$('#searchBtn').click(function() {
				var searchText = $('#searchText').val();
				var searchUrl = 'main?searchText=' + searchText;
				$('#searchUrl').prop("href", searchUrl);
			});//end searchBtn_click();
		
		});	
	</script>
	
</body>
</html>