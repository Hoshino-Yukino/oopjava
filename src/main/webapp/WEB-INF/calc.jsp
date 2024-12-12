<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="calc" method="post">
		数値を二つ入力:<br/>
		<input name="x">
		<input name="y">
		<button>送信</button>
	</form>
	<%
	double[] results = (double[])request.getAttribute("results");
	if(results != null){
	%>
	入力: <%=results[0] %>,<%=results[1] %>;
	<table border="1">
		<tr><td>加</td><td><%= results[2] %></td></tr>
		<tr><td>减</td><td><%= results[3] %></td></tr>
		<tr><td>乘</td><td><%= results[4] %></td></tr>
		<tr><td>除</td><td><%= results[5] %></td></tr>
	</table>
	<%}else{ %>
	<p>処理しない</p>
	<%} %>
</body>
</html>