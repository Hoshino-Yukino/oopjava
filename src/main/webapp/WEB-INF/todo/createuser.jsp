<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="todousercreate" method="POST">
	<table border="1">
		<tr><th>userId</th><td> <input name="userId" type="text"></td></tr>
		<tr><th>password</th><td> <input name="password" type="password"></td></tr>
		<tr><th>name</th><td> <input name="name" type="text"></td></tr>
	</table>
	<button>追加</button>
	</form>
    <% 
        Object idAttribute = request.getAttribute("id");
        if (idAttribute != null) {
            String value = idAttribute.toString();
    %>
    ユーザを追加しました。id: <%= value %>
    <% 
        } 
    %>
</body>
</html>