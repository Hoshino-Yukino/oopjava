<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家計簿 | 登録</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-image: url(/oopjava/img/bg.jpg);
        background-repeat: repeat;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
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
    input[type="password"],
    input[type="email"] {
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
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
    }
    .register-button {
        background-color: #4CAF50;
    }
    .login-button {
        background-color: #008CBA;
    }
    button:hover {
        opacity: 0.8;
    }
</style>
<script>
    function validateForm() {
        var password = document.forms["registerForm"]["password"].value;
        var confirmPassword = document.forms["registerForm"]["confirmPassword"].value;
        if (password !== confirmPassword) {
            alert("パスワードが一致しません。もう一度確認してください。");
            return false;
        }else{
        	alert("アカウント登録を完了しました。");
        	return true;
        }
        return true;
    }
</script>
</head>
<body>
    <form name="registerForm" action="register" method="POST" onsubmit="return validateForm()">
        <label>ユーザーID <input name="userId" type="text" required></label><br>
        <% String message = (String) session.getAttribute("message"); %>
        <% if (message != null) { %>
            <p>ユーザーIDは既に登録されています。修正して再試行してください。</p>
        <% } %>
        <label>お名前 <input name="name" type="text" required></label><br>
        <label>パスワード <input name="password" type="password" required></label><br>
        <label>パスワード確認 <input name="confirmPassword" type="password" required></label><br>
        <div class="button-container">
            <button type="button" class="login-button" onclick="window.location.href='login'">ログインへ戻る</button>
            <button type="submit" class="register-button">登録</button>
        </div>
    </form>
</body>
</html>
