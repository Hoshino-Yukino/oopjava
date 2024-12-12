<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="web.jpa.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	検査結果：<br>
    <% 
        List<User> userList = (List<User>) request.getAttribute("users");
    %>
    <table border="1">
		<thead><tr><th>id</th><th>userId</th><th>name</th></tr></thead>
		<tbody>
		<%
			for(User user : userList){
		%>
			<tr><td><%= user.getId() %></td><td><%= user.getUserId() %></td><td><%= user.getName() %></td></tr>
		<% 
			}
		%>
		</tbody>
	</table>
</body>
</html>
