<%@page import="web.jpa.cashbook.Data"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.jpa.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>家計簿 | 修正入力</title>
    <link rel="stylesheet" href="/oopjava/css/styles.css">
</head>
<body>
<% String username = (String) session.getAttribute("username"); 
SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
List<Data> dataList = (List<Data>) session.getAttribute("results"); 
%>
    <div class="container">
        <h1>家計簿 - 修正入力</h1>
        <table>
            <thead>
                <tr>
                    <th>日付</th>
                    <th>項目</th>
                    <th>収入/支出</th>
                    <th>金額</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Data data : dataList) {
                    	String category;
                %>
                <tr>
                	<form action="modify" method="post">
                	<input value="<%= data.getId()%>" name="id" type="hidden">
	                    <td><input type="date" value="<%= f.format(data.getRecordTime()) %>" name="time"></td>
	                    <td><input type="text" value="<%= data.getSubject() %>" name="subject"></td>
	                    <td><select name="category">
	                    	<%if (data.getCategory()==0) {%>
	               					<option value="0" selected>支出</option>
	               					<option value="1">収入</option>
	               					<%}else{ %>
	               					<option value="0" >支出</option>
	               					<option value="1" selected>収入</option>
	               					<%} %>
	               				</select>
	               		</td>
	                    <td><input type="text" value="<%= data.getPrice()%>" name="price"></td>
	                    <td><button type="button" onclick="window.location.href='home'">取消</button><input type="submit" value="提出"></td>
	                 </form>
                </tr>
                <%
                    }
                %>            
            </tbody>
        </table>
    </div>
</body>
</html>
