<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dang Nhap</title>
</head>
<body>

    <h2>Dang Nhap (Login)</h2>
    <hr/>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" value="${not empty rememberUser ? rememberUser : ''}" placeholder="xuan" required autofocus /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" placeholder="123" required /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="checkbox" name="remember" id="remember" /> <label for="remember">Remember me</label></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login" /></td>
            </tr>
        </table>
    </form>

    <br/>
    <p>Tai khoan kiem tra: <b>xuan</b> / mat khau: <b>123</b></p>
    <p><a href="${pageContext.request.contextPath}/home">Ve trang chu</a></p>

</body>
</html>
