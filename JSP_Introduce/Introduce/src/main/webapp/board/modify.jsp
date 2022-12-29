<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 수정 페이지</title>
</head>
<body>
<div class="cotnainer">
	<div class="image"><img alt="" src=""></div>
	<div class="content">
		<div class="title">Post</div>
			<div class="subcontent">
				<form action="/brd/edit" method="post" enctype="multipart/form-data">
				<input type="hidden" name="bNo" value="${bvo.bNo }">
				<div class="writer">
				 	<span>Writer: </span><input type=text id="writer" name="writer" value="${bvo.writer }" readonly>
				</div>
				<div class="image_file">
				 	<span>File: </span>
				  	<input type="hidden" name="image_file" value="${bvo.image_file }"><br>
 			 		<input type="file" name="new_file" accept="image/png, image/jpg, image/jpeg, image/gif">
				</div>			
				<div class="content">
				 	<span>Content</span><textarea rows="3" cols="30" name="content" id="content">${bvo.content }</textarea>
				</div>
				
				<div class="buttoncollection">
					<button type="submit">SUBMIT</button>
					<a href="/brd/list"><button type="button">CANCEL</button></a>
				</div>
				</form>
			</div>					
	</div>
</div>
</body>
</html>