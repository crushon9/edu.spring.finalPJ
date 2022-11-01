<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>upload movie poster</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
.file-drop {
width : 100;
height : 100px;
border : 1px solid grey;
}

</style>
</head>
<body>
	<h1>Ajax Fileupload</h1>
	<div class="file-drop"></div>
	<div class="upload-list"></div>

	<script type="text/javascript">
		$(document).ready(function() {
			// prohibit 'file image show'(when drag & drop) 
			$('.file-drop').on('dragenter dragover', function(event) {
				event.preventDefault();
			}); // function()
			
			$('.file-drop').on('drop', function(event) {
				event.preventDefault();
				console.log('drop test');
				
				// Ajax를 이용하여 서버로 파일 업로드
				// multipart/form-data 타입으로 파일 업로드하는 객체
				var formData = new FormData();
				
				// 드래그한 파일 정보들을 갖고 있는 객체
				var files = event.originalEvent.dataTransfer.files;
				var i = 0;
				for(i = 0; i < files.length; i++) {
					console.log(files[i]);
					// parameter 이름과 일치 files fileup con
					formData.append("files", files[i]); // 배열로 넘어감										
				}	
				
				$.ajax({
					type : 'post',
					url : '/spring/upload-ajax',
					data : formData,
					processData : false, // 일반적으로 true 사용함
					contentType : false, //  datatype이 내포되어 있음
					success : function(data) {
						console.log(data);
					
					}
				}); //end ajax()
				
			});					
		});//end docu	
	
	</script>

</body>
</html>