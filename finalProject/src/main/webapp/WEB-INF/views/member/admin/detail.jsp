<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Member Admin detail page</title>
</head>
<body>
	
	<h2>Member Admin detail page</h2>
	 
	    <p>아이디 : ${vo.mmbId }</p>
	    <p>패스워드 : ${vo.mmbPassword }</p>
	    <p>이메일 : ${vo.mmbEmail }</p>
	 	<p>전화번호 : ${vo.mmbPhone }</p>
		<p>생년월일 : ${vo.mmbBirthday }</p>
		<p>선호지점 : ${vo.brcName }</p>
		
</body>
</html>