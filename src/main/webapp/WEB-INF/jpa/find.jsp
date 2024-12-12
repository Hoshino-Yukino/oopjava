<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	検査結果：<br>
    <% 
        Object id = request.getAttribute("id");
        if (id!=null) {
        	String userId = (String)request.getAttribute("userId").toString();
        	String name = (String)request.getAttribute("name").toString();
    %>
    	<table border="1">
    	<tr><th>id</th><td><%= id %></td></tr>
		<tr><th>userId</th><td> <%= userId %></td></tr>
		<tr><th>name</th><td> <%= name %></td></tr>
		</table>
	<% 
        } else{
    %>
    見つかりませんでした
    <%} %>
</body>
</html>