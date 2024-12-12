<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿 | ログイン</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-image: url(/oopjava/img/bg.jpg);
        background-repeat: repeat;
    }
    form {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 400px;
        box-sizing: border-box;
        opacity: 0.9;
    }
    label {
        display: block;
        margin-bottom: 15px;
        font-weight: bold;
    }
    input[type="text"],
    input[type="password"] {
        width: calc(100% - 20px);
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .button-container {
        display: flex;
        justify-content: space-between;
    }
    button {
        width: 48%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }
    .register-button {
        background-color: #008CBA;
    }
    button:hover {
        opacity: 0.8;
    }
</style>
</head>
<body>
    <form action="login" method="POST">
        <label>userId <input name="userId" type="text"></label><br>
        <label>password <input name="password" type="password"></label><br>
        <div class="button-container">
            <button type="button" class="register-button" onclick="window.location.href='register'">登録</button>
            <button type="submit">ログイン</button>
        </div>
    </form>
</body>
</html>
