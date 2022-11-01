<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Movie Poster</title>
</head>
<body>
	<h1>fileupload form</h1>
	
	<h2>single file uploading</h2>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="upload">
	
	</form>
	
	<h2>multi file uploading</h2>
	<form action="upload2" method="post" enctype="multipart/form-data">
		<input type="file" name="files" multiple="multiple"><br>
		<input type="submit" value="upload">
	
	</form>
	
	<!-- http://localhost:8080/spring/upload2 -->
</body>
</html>