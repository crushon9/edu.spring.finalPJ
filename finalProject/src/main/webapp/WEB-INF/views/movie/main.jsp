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
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}
ul {
	list-style-type: none;
}
li {
	display: inline-block;
}
.main{
	padding : 100px 100px 200px 200px;
	margin-right : 100px;
	margin-left : 100px;	
}

.chart-rank{
	display : inline-flex;
	justify-content : right; 
}

.rank{
	margin-right : 20px;
	margin-left : 20px;
	margin-bottom : 5px;
	background-color : red;
	width : 204px;
	height : 20px;
	text-align : center;
}

.imagespace{
	margin-right : 20px;
	margin-left : 20px;
	width : 204px; 
	height : 300px; 
}

.box-content{
	margin-right : 20px;
	margin-left : 20px;
	width : 200px;
	height : 65px;	
}

.movieTitle2{	
	font-size : 12px;
}

.common{
	margin-top : 1px;
	margin-left : 20px;
	line-height : 20px;
	width:80px; 
	height:20px; 
	color:white; 
	background-color:red; 
	border:none;
	border-radius: 5px; 
	cursor:pointer;
	text-align:center;
	font-size: 14px;
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
			<div id="standardArea" style=" float: right; margin-right: 255px;">
				<select id="viewStandard">
					<option>예매순</option>
					<option>개봉일순</option>			
				</select>
				<input type="button" id="execute" onclick="execute()" value="go!">
			</div>
			<br><br>
			<div>
			<c:forEach var="vo" items="${movieList }" varStatus="status">
				<ol class="chart-rank">						
					<li style="list-style-type: none">
						<div class="rank">
							<Strong style="color : white">NO.<c:out value="${status.count}"/></Strong>
						</div>
						<div>
							<a href="movieDetail?movieNumber=${vo.movieNumber}"><img class="imagespace" src="display?fileName=/${vo.movieImage}"/></a>
						</div>
						<div class="box-content">
							<Strong class="movieTitle" >${vo.movieName }</Strong><br>
							<Strong class="movieTitle2">예매수 : ${vo.movieCount }<br></Strong>
							<Strong class="movieTitle2">개봉일 : ${vo.movieOpenDate }</Strong>
						</div>
						<div >
							<a href="../movieSchedule/reserve1"><input type="button" value="예매하기" class="common"></a>							
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