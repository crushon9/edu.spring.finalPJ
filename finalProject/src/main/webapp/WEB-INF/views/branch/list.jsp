<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/headTag.jsp" %>
	<title>branch list for user</title>
</head>
<body class="sb-nav-fixed">
	<div id="layoutSidenav">
		<%@include file="/WEB-INF/views/sidebar.jsp" %>

		<div id="layoutSidenav_content">
		<h1>지점 정보 목록</h1>
		<div>
		<!-- 지역검색 -->
		<select id="brcArea" name="brcArea">
			<option value="0">지역선택</option>
			<option value="-1">전체</option>
			<option value="1">서울</option>
			<option value="2">경기/강원</option>
			<option value="3">부산/경상</option>
			<option value="4">대전/충청</option>
			<option value="5">광주/전라</option>
			<option value="6">제주</option>
		</select>
		<a id="searchAreaUrl" href=""><input id="searchAreaBtn" type="button" value="Go"></a>
		&emsp;&emsp;
		<!-- 지점이름검색 -->
		<input id="searchBrcName" type="text" placeholder="지점을 입력하세요">&nbsp;
		<a id="searchBrcNameUrl" href=""><input id="searchBrcNameBtn" type="button" value="Search"></a>
		<hr>
		<!-- 지점 반복문 출력 -->
		<c:if test="${empty list}">
			검색 결과가 없습니다
		</c:if>
		<c:if test="${not empty list}">
			<table>
				<thead>
					<tr>
						<th>지역</th>
						<th>지점</th>
						<th></th>
					</tr>
				</thead>
				<tbody>						
				<c:forEach var="vo" items="${list }" varStatus="status">
					<tr id="brc${status.index}">						
						<td class="brcAreaTd"><input type="hidden" class="brcArea" value="${vo.brcArea }">
						<div style="display: inline-block;" class="brcAreaName"></div>(${vo.brcArea })</td>
						<td><Strong>${vo.brcName }점</Strong></td>
						<td><a href="detail?brcId=${vo.brcId}"><input type="button" value="상세보기"></a></td>
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
			setBrcAreaName();
			// 문자열 검색
			$('#searchBrcNameBtn').click(function() {
				var searchBrcName = $('#searchBrcName').val();
				var searchBrcNameUrl = 'list?searchBrcName=' + searchBrcName;
				$('#searchBrcNameUrl').prop("href", searchBrcNameUrl);
			});
			// Area 검색
			$('#searchAreaBtn').click(function() {
				var brcArea = $('#brcArea').val();
				var searchAreaUrl = '';
				if (brcArea == -1) {
					searchAreaUrl = 'list';
				} else {
					searchAreaUrl = 'list?brcArea=' + brcArea;
				}
				
				$('#searchAreaUrl').prop("href", searchAreaUrl);
			});	
		});	
		
		function setBrcAreaName() {
			  console.log('setBrcAreaName() 호출');
			  var brcAreaArray = ['서울', '경기/강원', '부산/경상', '대전/충청', '광주/전라', '제주'];
			  for (var i = 0; i < $('td').length; i++){
			  	var brcArea = $('#brc' + i).children('.brcAreaTd').children('.brcArea').val();
			  	$('#brc' + i).children('.brcAreaTd').children('.brcAreaName').html(brcAreaArray[brcArea - 1]);
			  }
		};
	</script>
	  
</body>
</html>