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
			<a id="orderUrl" href=""><input id="orderBtn" type="button" value="Go"></a>&emsp;&emsp;
			<!-- 영화 검색 -->
			<input id="searchText" type="text" placeholder="영화 제목을 입력하세요" required>&nbsp;<a id="searchUrl" href=""><input id="searchBtn" type="button" value="Search"></a>
			<hr>
			
			<!-- 영화 목록 출력 -->
			<c:if test="${empty mvList}">
				검색 결과가 없습니다
			</c:if>
			<c:if test="${not empty mvList}">
				<ul>						
					<c:forEach var="vo" items="${mvList }" varStatus="status">
						<li style="list-style-type: none; display: inline-block; text-align: center; margin: 20px; border: solid gray 1px;">
							<Strong>NO. ${status.count}</Strong><br>
							<a href="detail?mvId=${vo.mvId}"><img class="imageSpace" src="/project/img/display?fileName=${vo.mvImage}"/></a>
							<br><Strong>${vo.mvTitle }</Strong>
							<!-- 예매율 : 해당영화판매/영화전체판매 -->
							<br><Strong>예매율 <fmt:formatNumber value="${vo.mvTicketSales / mvTicketSalesTotal * 100}" pattern="0.0"/> %</Strong>
							<c:if test="${vo.mvRatingAvg != 0}">
								<br><Strong>평점 <fmt:formatNumber value="${vo.mvRatingAvg }" pattern="0.00"/> / 5.00</Strong>
							</c:if>
							<!-- 평점이 0이면 미등록 표기 -->
							<c:if test="${vo.mvRatingAvg == 0}">
								<br><Strong>평점 미등록</Strong>
							</c:if>
							<br>개봉일 ${vo.mvDateStarted }<br>
							<a href="detail?mvId=${vo.mvId}"><input id="mvDetail" type="button" value="상세정보"></a>
							<a href="/project/schedule/list?mvId=${vo.mvId }&brcId=0&scdDate=none"><input id="mvTicket" type="button" value="상영스케줄"></a>							
						</li>
						<!-- 영화 4개 마다 공백으로 띄우기 -->
						<c:if test="${(status.count % 4) == 0}">
							<br> 
				        </c:if>
					</c:forEach>
				</ul>
			</c:if>										
			</div>
			<%@include file="/WEB-INF/views/footer.jsp" %>
		</div>
	</div>
	
	<input type="hidden" id="mmbId" value="<%=mmbIdSession %>">
	<script type="text/javascript">
		// document가 생성되기 전에 view에서 생성한 sessionStorage를 통해서 targetURL 리다이렉트
		if (sessionStorage.getItem('targetURL') !== null && $('#mmbId').val() != 'null') {
			var targetURL = sessionStorage.getItem('targetURL');
			sessionStorage.removeItem('targetURL');
			location.href = targetURL;
		}
		
		$(document).ready(function() {
			// 정렬
			$('#orderChoice, #orderBtn').click(function() {
				var orderChoice = $('#orderChoice').val();
				var orderUrl = 'main?orderChoice=' + orderChoice;
				console.log(orderChoice);
				console.log(orderUrl);
				$('#orderUrl').prop("href", orderUrl);
			});//end orderChoice_click();
			
			// 검색
			$('#searchBtn').click(function() {
				var searchText = $('#searchText').val();
				// space 제거
				if (searchText == null || searchText == " ") {
					alert("검색어를 확인해주세요");
				}
				var searchUrl = 'main?searchText=' + searchText;
				$('#searchUrl').prop("href", searchUrl);
					
			});//end searchBtn_click();
		});
	</script>
	
</body>
</html>