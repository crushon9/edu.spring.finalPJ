<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<style type="text/css">


.main{
	padding : 100px 100px 200px 200px;
	margin-right : 100px;
	margin-left : 100px;	
}


</style>

<meta charset="UTF-8">
<title>movie chart</title>
</head>

<body>
	<h1>movie chart</h1>
	<a href="detail"><input type="button" value="영화상세go"></a>	
	
	<img class="image" src="display?fileName=/${vo.mvImage}"/>
	
	<br>
	<div class="main">
		<br><br><br>
		<h1>무비 차트</h1>
		<hr style="width : 85%; margin-left : 0;"><br>
			<div id="chart" style=" float: right; margin-right: 255px;"><!--  접근 -->
				<select id="viewStandard">
					<option>기본</option>
					<option>개봉일순</option>			
				</select>
				<input type="button" id="execute" onclick="execute()" value="go!">
			</div>
			<br><br>
			<div>
			<c:forEach var="vo" items="${mvList }" varStatus="status">
				<ol class="mvItem">						
					<li style="list-style-type: none">
						<div class="rank">
							<Strong style="color : white">NO.<c:out value="${status.count}"/></Strong>
						</div>
						<div>
							<a href="movieDetail?mvId=${vo.mvId}"><img class="imagespace" src="/project/img/display?fileName=${vo.mvImage}"/></a>
						</div>
						<div class="mvTitle">
							<Strong class="mvTitle" >${vo.mvTitle }</Strong><br>
					
						</div>
						<div >
							<a href="/project/schedule/list"><input type="button" value="예매하기"></a>							
						</div>
					</li>				
				</ol>											
			</c:forEach>
		</div>
	</div>
	
	<script type="text/javascript">
			
			var st = document.getElementById('viewStandard');
	
		function execute() {
			var standard = st.value;
			
			location.href = "chart?movieClosing=Y&standard=" + standard;
			console.log("a");
		}
	
	</script>
	
	
</body>
</html>