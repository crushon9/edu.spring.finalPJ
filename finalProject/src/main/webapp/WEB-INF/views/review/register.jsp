<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" />
	<meta charset="UTF-8">
	<title>review Register</title>
    <style>
      div.all {
        width: 100%;
      }
      div.left {
        width: 50%;
        float: left;
        box-sizing: border-box;
      }
      div.right {
        width: 50%;
        float: right;
        box-sizing: border-box;
      }
    </style>
</head>

<body onload='resizeWindow(this)' style="margin-left : 20px">
<%String mmbIdSession = (String) session.getAttribute("mmbIdSession");%>
    <div class="all">
	  <div style="text-align:center; font-weight:bolder; font-size: 40px">Review</div>
	  <br>
	  <form action="register" method="post">
		<input type="hidden" name="mvId" value="${mvId }">
		<input type="hidden" name="mmbId" value="<%=mmbIdSession %>">
		<div class="left">
		  	<img src="/project/img/display?fileName=${mvImage }" width="200px"/><br>
			<Strong>${mvTitle }</Strong>
		</div>
		<div class="right">
			<br>
			<p>평점 <select name="rvRating">                               
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
		       </select></p>
			<textarea rows="4" cols="25" name="rvContent" placeholder="관람평" required></textarea>
			<br><input type="submit" value="등록">
		</div>
	  </form>
	</div>
	<input type="hidden" id="alertMassage" value="${alertMassage}"/>
	<script type="text/javascript">
		function resizeWindow(win) {
			var width = 550;
			var heigh = 500;
			win.resizeTo(width,heigh);
		}
		var alertMassage = $('#alertMassage').val();
		if (alertMassage == 'reviewRegisterSuccess'){
			alert("리뷰 등록 성공");
			opener.document.location.reload();
			self.close();
		} else if (alertMassage == 'reviewRegisterFail'){
			alert("리뷰 등록 실패");
			opener.document.location.reload();
			self.close();
		}
	</script>

</body>
</html>