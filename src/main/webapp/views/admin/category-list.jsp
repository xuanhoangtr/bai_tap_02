<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sach Category JPA</title>
</head>
<body>

    <h2>Danh sach Category (JPA)</h2>
    <p>
        <a href="<c:url value='/admin/category/add'/>">Add Category</a>
        | <a href="<c:url value='/home'/>">Ve trang chu</a>
    </p>
    <hr>
    <table border="1" width="100%" cellpadding="6" cellspacing="0">
        <tr>
            <th>STT</th>
            <th>Images</th>
            <th>Category name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listcate}" var="cate" varStatus="STT">
            <tr>
                <td align="center">${STT.index + 1}</td>
                <c:choose>
                    <c:when test="${not empty cate.images and cate.images.startsWith('http')}">
                        <c:url value="${cate.images}" var="imgUrl"></c:url>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
                    </c:otherwise>
                </c:choose>

                <td align="center"><img height="150" width="200" src="${imgUrl}" onerror="this.src='https://via.placeholder.com/200x150?text=No+Image'" /></td>
                <td>${cate.categoryname}</td>
                <td align="center">
                    <c:if test="${cate.status == 1}">Hoat dong</c:if>
                    <c:if test="${cate.status != 1}">Khoa</c:if>
                </td>
                <td align="center">
                    <a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Sua</a>
                    | <a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>" onclick="return confirm('Ban co chac muon xoa?');">Xoa</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
