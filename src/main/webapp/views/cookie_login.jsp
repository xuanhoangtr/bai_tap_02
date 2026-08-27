<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dang Nhap Bang Cookie</title>
</head>
<body>

    <h2>Dang Nhap Bang Cookie</h2>
    <hr/>

    <c:if test="${param.error eq '1'}">
        <p style="color: red;">Tai khoan hoac mat khau khong dung!</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/cookie-login" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="username" placeholder="xuan" required /></td>
            </tr>
            <tr>
                <td>Pass:</td>
                <td><input type="password" name="password" placeholder="123" required /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login" /></td>
            </tr>
        </table>
    </form>

    <br/>
    <p>Tai khoan mau: <b>xuan</b> / mat khau: <b>123</b></p>
    <p><a href="${pageContext.request.contextPath}/home">Ve trang chu</a></p>

</body>
</html>
