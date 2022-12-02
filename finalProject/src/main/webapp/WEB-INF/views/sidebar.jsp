<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
<%String adminSession = (String) session.getAttribute("adminSession");%>

<!-- Head -->
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
	<!-- Navbar Brand-->
	<a class="navbar-brand ps-3" href="/project/movie/main">&emsp;<i class="fa-solid fa-video"></i>&nbsp;CGVIP</a>
	<!-- Sidebar Toggle-->
	<button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle"><i class="fas fa-bars"></i></button>
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

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
	           	<nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
	            <!-- 계정에 따라 조건문으로 사이드바 출력  -->
	            	<!-- 비회원 -->
		            <c:if test="${empty mmbIdSession }">
		            	<br><br><br>
		                <a class="nav-link" href="/project/movie/main"><i class="fa-solid fa-clapperboard"></i>&emsp;Movie</a><br>
		                <a class="nav-link" href="/project/branch/list"><i class="fa-solid fa-building-flag"></i>&emsp;Branch</a><br>
		                <a class="nav-link" href="/project/schedule/list?mvId=0&brcId=0&scdDate=none"><i class="fa-regular fa-calendar-days"></i>&emsp;Schedule</a><br>
		            </c:if>
		            
		            <!-- 일반회원  -->
					<c:if test="${not empty mmbIdSession && empty adminSession}">
						<br><br><br>
						<a class="nav-link" href="/project/movie/main"><i class="fa-solid fa-clapperboard"></i>&emsp;Movie</a><br>
		                <a class="nav-link" href="/project/branch/list"><i class="fa-solid fa-building-flag"></i>&emsp;Branch</a><br>
		                <a class="nav-link" href="/project/schedule/list?mvId=0&brcId=0&scdDate=none"><i class="fa-regular fa-calendar-days"></i>&emsp;Schedule</a><br>
		                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#mypage" aria-expanded="false" aria-controls="collapseLayouts">
                           <i class="fa-solid fa-clapperboard"></i>&emsp;MyPage
                           <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
		                <div class="collapse" id="mypage" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion" style="">
		                    <nav class="sb-sidenav-menu-nested nav">
		                        <a class="nav-link" href="/project/ticket/list"><i class="fa-solid fa-minus"></i>&emsp;My Ticket</a>
		                        <a class="nav-link" href="/project/review/list"><i class="fa-solid fa-minus"></i>&emsp;My Review</a>
		                        <a class="nav-link" href="/project/member/update?mmbId=${ mmbIdSession}"><i class="fa-solid fa-minus"></i>&emsp;My Info</a>
		                        <a class="nav-link" href="/project/member/resign_confirm"><i class="fa-solid fa-minus"></i>&emsp;Resign</a>
		                    </nav>
		                </div>
					</c:if>
					
					<!-- 관리자 -->
					<c:if test="${not empty adminSession }">
						<br><br><br>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#movie" aria-expanded="false" aria-controls="pagesCollapseAuth">
	                        <i class="fa-solid fa-clapperboard"></i>&emsp;Movie
	                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="movie" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages" style="">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="/project/movie/admin/register">&emsp;<i class="fa-solid fa-minus"></i>&emsp;Register</a>
                       			<a class="nav-link" href="/project/movie/admin/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;List</a>
                            </nav>
                        </div><br>
					
		                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#branch" aria-expanded="false" aria-controls="pagesCollapseAuth">
			                <i class="fa-solid fa-building-flag"></i>&emsp;Branch
			                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
		                </a>
		                <div class="collapse" id="branch" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages" style="">
		                    <nav class="sb-sidenav-menu-nested nav">
		                        <a class="nav-link" href="/project/branch/admin/register">&emsp;<i class="fa-solid fa-minus"></i>&emsp;Register</a>
		                        <a class="nav-link" href="/project/branch/admin/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;List</a>
		                    </nav>
		                </div><br>
		                
		                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#schedule" aria-expanded="false" aria-controls="pagesCollapseAuth">
			                <i class="fa-solid fa-calendar-days"></i>&emsp;Schedule
			                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
		                </a>
		                <div class="collapse" id="schedule" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages" style="">
		                    <nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="/project/schedule/admin/register">&emsp;<i class="fa-solid fa-minus"></i>&emsp;Register</a>
		                        <a class="nav-link" href="/project/schedule/admin/list">&emsp;<i class="fa-solid fa-minus"></i>&emsp;List</a>
		                    </nav>
		                </div><br>
		                <a class="nav-link" href="/project/ticket/admin/list"><i class="fa-solid fa-ticket"></i>&emsp;Ticket</a><br>
		                <a class="nav-link" href="/project/member/admin/list"><i class="fa-solid fa-user"></i>&emsp;Member</a><br>
		                <a class="nav-link" href="/project/member/update?mmbId=${ mmbIdSession}"><i class="fa-solid fa-house-user"></i>&emsp;My Info</a><br>
					</c:if>
				</nav>
            </div>
        </div>
        
        <div class="sb-sidenav-footer">
            <div class="small">&emsp;CGVIP<br>(Come and See Us)</div>
        </div>
    </nav>
</div>
<input type="hidden" id="alertMessage" value="${alertMessage}"/>
