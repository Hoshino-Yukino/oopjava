<%@page import="web.jpa.cashbook.Data"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="web.jpa.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>家計簿 | ホーム</title>
    <link rel="stylesheet" href="/oopjava/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url(/oopjava/img/bg.jpg);
            background-repeat: repeat;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            opacity: 0.8;
            position: relative;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        #search_bar {
            width: 400px;
            height: 25px;
            font-size: 20px;
            padding: 10px;
            box-sizing: border-box;
        }

        .logout-button {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 10px 20px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .logout-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
<% String username = (String) session.getAttribute("username"); 
SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
List<Data> dataList = (List<Data>) session.getAttribute("results"); 
Date date = new Date();
%>
    <div class="container">
        <button class="logout-button" onclick="window.location.href='logout'">ログアウト</button>
        <h1>家計簿</h1>
        <form action="home" method="post">
            <input id="search_bar" type="text" name="search" placeholder="検索内容">
            <button>検索</button>
        </form>
        <br>
        <table>
            <thead>
                <tr>
                    <th>日付</th>
                    <th>項目</th>
                    <th>収入/支出</th>
                    <th>金額</th>
                    <th>残高</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int allDataPrice = 0;
                    for (Data data : dataList) {
                        String category;
                        if (data.getCategory() == 0) {
                            category = "支出";
                            allDataPrice = allDataPrice - data.getPrice();
                        } else {
                            category = "収入";
                            allDataPrice = allDataPrice + data.getPrice();
                        }
                    }
                %>
                 <tr>
                    <form action="adddata" method="post">
                        <td><input type="date" value="<%=f.format(date) %>" name="time" ></td>
                        <td><input type="text" name="subject" id="subject" required></td>
                        <td>
                            <select name="category">
                                <option value="0" selected>支出</option>
                                <option value="1">収入</option>
                            </select>
                        </td>
                        <td><input type="text" name="price" id="price" required></td>
                        <td><%=allDataPrice %></td>
                        <td><input type="submit" value="追加"></td>
                    </form>
                </tr>
            
                <%
                    for (Data data : dataList) {
                        String category;
                        if (data.getCategory() == 0) {
                            category = "支出";
                        } else {
                            category = "収入";
                        }
                %>
                <tr>
                    <td><%= f.format(data.getRecordTime()) %></td>
                    <td><%= data.getSubject() %></td>
                    <td><%= category %></td>
                    <td><%= data.getPrice() %></td>
                    <td></td>
                    <td>
                        <a href="modify?id=<%= data.getId() %>">修正</a><br>
                        <a href="delete?id=<%= data.getId() %>">削除</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
