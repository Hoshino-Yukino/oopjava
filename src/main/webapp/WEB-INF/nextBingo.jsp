<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String value = (String)request.getAttribute("message").toString(); %>
	生成された数: <%=value %>
	<p><a href="nextBingo">次の数</a></p>
	<p><a href="startBingo">最初から</a></p>
</body>
</html>