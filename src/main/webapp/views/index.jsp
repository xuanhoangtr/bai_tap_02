<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chu</title>
</head>
<body>

    <h2>TRANG CHU (HOME)</h2>
    <hr/>

    <div>
        <c:choose>
            <c:when test="${empty sessionScope.account}">
                <p>Ban chua dang nhap. <a href="${pageContext.request.contextPath}/login"><b>Dang nhap (Login)</b></a></p>
            </c:when>
            <c:otherwise>
                <p>Xin chao, <b>${sessionScope.account.fullName}</b> | <a href="${pageContext.request.contextPath}/logout">Dang xuat</a></p>
            </c:otherwise>
        </c:choose>
    </div>

    <hr/>
    <h3>Danh muc cac bai tap:</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/login">Trang Login (Kien truc 3 tang)</a></li>
        <li><a href="${pageContext.request.contextPath}/cookie-login">Bai tap Login voi Cookie</a></li>
        <li><a href="${pageContext.request.contextPath}/session-login">Bai tap Login voi Session</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/categories"><b>Bai tap CRUD Category bang JPA API (/admin/categories)</b></a></li>
    </ul>

    <br/>
    <p>Tai khoan dang nhap: <b>xuan</b> / mat khau: <b>123</b></p>

</body>
</html>
