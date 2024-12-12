<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="web.jpa.User" %>
<%@ page import="web.jpa.todo.Todo" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%String username = (String)session.getAttribute("name"); %>
	<p>ようこそ<%= username %>さん。</p>
	<p><a href="todologout">ログアウト</a></p>
	検索結果:
	<table border="1">
	<tr><th>やること</th><th>期限</th><th>優先度</th><th>やる人</th></tr>
	<% if(request.getAttribute("results")!=null ){
	// あれば、結果を取り出してfor文を開始する
	List<Todo> results = (List<Todo>)request.getAttribute("results");
	SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	for(Todo t : results){
	%>
	<tr><td><%= t.getSubject() %></td>
	<td><%= f.format(t.getDeadline()) %></td>
	<td><%= t.getPriority() %></td>
	<td><%= t.getUser().getName() %></td>
	</tr>
	<% } } /* for文とif文の閉じ括弧 */ %>
	</table>
	<hr>
	<% /*追加フォームと検索フォームもここに追加する */ %>
	<form action="searchtodo" method="POST">
	<label>subject <input name="subject" type="text"></label><br>
	<button>検索</button>
	</form>
	<hr>
	<form action="addtodo" method="POST">
	<label>subject <input name="subject" type="text"></label><br>
	<label>deadline <input name="deadline" type="text" placeholder="2019/10/1 00:00"></label><br>
	<label>priority <input name="priority" type="number" min=1 max=5></label><br>
	<button>追加</button>
	</form>
	
	
</body>
</html>