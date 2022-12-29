<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 포스팅</title>
</head>
<body>
<div class="cotnainer">
	<div class="image"><img alt="" src=""></div>
	<div class="content">
		<div class="title">Post</div>
			<div class="subcontent">
				<form action="/brd/insert" method="post" enctype="multipart/form-data">
				<div class="writer">
				 	<span>Writer: </span><input type=text id="writer" name="writer" value="${ses.nickname }" readonly>
				</div>
				<div class="image_file">
				 	<span>File: </span><input type="file" id="file" name="image_file" accept="image/png,image/jpg,image/jpeg,image/gif">
				</div>			
				<div class="content">
				 	<span>Content</span><textarea rows="3" cols="30" name="content" id="content"></textarea>
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