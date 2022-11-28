<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" />
	<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
	
	<style type="text/css">
	.imageSpace {
		width: 200px;
		height: 300px;
	}
	</style>
	
	<title>movie chart</title>
</head>

<body class="sb-nav-fixed">
	<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
	<%String adminSession = (String) session.getAttribute("adminSession");%>
	<!-- Head -->
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-3" href="/project/movie/main">&emsp;<i class="fa-solid fa-video"></i>&nbsp;CGVIP</a>
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><svg class="svg-inline--fa fa-bars" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="bars" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M0 96C0 78.33 14.33 64 32 64H416C433.7 64 448 78.33 448 96C448 113.7 433.7 128 416 128H32C14.33 128 0 113.7 0 96zM0 256C0 238.3 14.33 224 32 224H416C433.7 224 448 238.3 448 256C448 273.7 433.7 288 416 288H32C14.33 288 0 273.7 0 256zM416 448H32C14.33 448 0 433.7 0 416C0 398.3 14.33 384 32 384H416C433.7 384 448 398.3 448 416C448 433.7 433.7 448 416 448z"></path></svg><!-- <i class="fas fa-bars"></i> Font Awesome fontawesome.com --></button>
		<!-- Navbar-->
		<div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"></div>
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<c:if test="${empty mmbIdSession }">
				<li class="nav-item dropdown"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="/project/member/login"><i class="fa-solid fa-user"></i>&nbsp;Login</a></li>
				<li class="nav-item dropdown"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="/project/member/register">&nbsp;<i class="fa-solid fa-user-pen"></i>&nbsp;Register</a></li>
			</c:if>
			<c:if test="${not empty mmbIdSession && empty adminSession}">
				<li class="nav-item dropdown"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="/project/member/logout">Logout&nbsp;<i class="fa-solid fa-user"></i>&nbsp;[ <%=mmbIdSession %> ]</a></li>
			</c:if>
			<c:if test="${not empty adminSession }">
				<li class="nav-item dropdown"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="/project/member/logout">Logout&nbsp;<i class="fa-solid fa-crown"></i>&nbsp;[ <%=mmbIdSession %> ]</a></li>
			</c:if>
		</ul>
	</nav>
	
	<!-- SideBar + Content -->
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
		    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
		        <div class="sb-sidenav-menu">
		            <div class="nav">
		            <!-- 계정에 따라 조건문으로 사이드바 출력  -->
			            <c:if test="${empty mmbIdSession }"><!-- 비회원 -->
			            	<br>
			                <a class="nav-link" href="/project/movie/main">&emsp;<i class="fa-solid fa-clapperboard"></i>&emsp;Movie</a><br>
			                <a class="nav-link" href="/project/branch/list">&emsp;<i class="fa-solid fa-building-flag"></i>&emsp;Branch</a><br>
			                <a class="nav-link" href="/project/schedule/list">&emsp;<i class="fa-regular fa-calendar-days"></i>&emsp;Schedule</a><br>
			            </c:if>
						<c:if test="${not empty mmbIdSession && empty adminSession}"><!-- 일반회원  -->
							<br>
							<a class="nav-link" href="/project/movie/main">&emsp;<i class="fa-solid fa-clapperboard"></i>&emsp;Movie</a><br>
			                <a class="nav-link" href="/project/branch/list">&emsp;<i class="fa-solid fa-building-flag"></i>&emsp;Branch</a><br>
			                <a class="nav-link" href="/project/schedule/list">&emsp;<i class="fa-regular fa-calendar-days"></i>&emsp;Schedule</a><br>
			                
			                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
			                  &emsp;<i class="fa-solid fa-house-user"></i>&emsp;Mypage<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
			                </a>
			                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
			                    <nav class="sb-sidenav-menu-nested nav">
			                        <a class="nav-link" href="/project/ticket/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;My Ticket</a>
			                        <a class="nav-link" href="/project/review/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;My Review</a>
			                        <a class="nav-link" href="/project/member/update?mmbId=<%=mmbIdSession%>">&emsp;<i class="fa-solid fa-minus"></i>&emsp;My Info</a>
			                    </nav>
			                </div>
						</c:if>
						<c:if test="${not empty adminSession }"><!-- 관리자 -->
							<br>
			                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
			                  &emsp;<i class="fa-solid fa-clapperboard"></i>&emsp;Movie<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
			                </a>
			                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
			                    <nav class="sb-sidenav-menu-nested nav">
			                        <a class="nav-link" href="/project/movie/admin/register">&emsp;<i class="fa-solid fa-minus"></i>&emsp;Register</a>
			                        <a class="nav-link" href="/project/movie/admin/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;List</a>
			                    </nav>
			                </div><br>
			                
			                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
			                  &emsp;<i class="fa-solid fa-building-flag"></i>&emsp;Branch<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
			                </a>
			                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
			                    <nav class="sb-sidenav-menu-nested nav">
			                        <a class="nav-link" href="/project/branch/admin/register">&emsp;<i class="fa-solid fa-minus"></i>&emsp;Register</a>
			                        <a class="nav-link" href="/project/branch/admin/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;List</a>
			                    </nav>
			                </div><br>  
			                
			                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
			                  &emsp;<i class="fa-solid fa-calendar-days"></i>&emsp;Schedule<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
			                </a>
			                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
			                    <nav class="sb-sidenav-menu-nested nav">
									<a class="nav-link" href="/project/schedule/admin/register">&emsp;<i class="fa-solid fa-minus"></i>&emsp;Register</a>
			                        <a class="nav-link" href="/project/schedule/admin/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;List</a>
			                    </nav>
			                </div><br>
			                
			                <a class="nav-link" href="/project/ticket/admin/list">&emsp;<i class="fa-solid fa-ticket"></i>&emsp;Ticket</a><br>
			                <a class="nav-link" href="/project/member/admin/list">&emsp;<i class="fa-solid fa-user"></i>&emsp;Member</a><br>
			                <a class="nav-link" href="/project/member/update?mmbId=<%=mmbIdSession%>">&emsp;<i class="fa-solid fa-house-user"></i>&emsp;My Info</a><br>
			                  
						</c:if>
		            </div>
		        </div>
		        <div class="sb-sidenav-footer">
		            <div class="small">&emsp;CGVIP<br>(Come and See Us)</div>
		        </div>
		    </nav>
		</div>
		<div id="layoutSidenav_content">
		    <!-- 영화 정렬 -->
			<select id="orderChoice">
				<option value="ticketSales">예매율순</option><!-- 조건절로 바꾸기 -->
				<option value="reviewAvg">평점순</option>			
			</select>
			<a id="orderUrl" href=""><input type="button" value="Go"></a>
			<br><br>
			
			<!-- 영화 검색 -->
			<input id="searchText" type="text"><a id="searchUrl" href=""><input id="searchBtn" type="button" value="검색"></a>
			
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
			<!-- layoutSidenav_content_footer  -->
		    <footer class="py-4 bg-light mt-auto">
		        <div class="container-fluid px-4">
		            <div class="d-flex align-items-center justify-content-between small">
		                <div class="text-muted">서울시 구로구 구트아카데미!  &emsp; contact us : cgvipMaster@cjvip.com
			                <br>
			                대표이사 P&S &emsp; 사업자등록번호 102-222-2222 &emsp; 통신판매업신고번호 2017-서울금천-0000
							<br>
			                Copyright &copy; CJ CGVIP. All Rights Reserved
		                </div>
		                <div>
		                    <a href="#">Terms &amp; Conditions</a>
		                </div>
		            </div>
		        </div>
		    </footer>
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
	<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/datatables-simple-demo.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/demo/chart-bar-demo.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/demo/chart-area-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
</body>
</html>