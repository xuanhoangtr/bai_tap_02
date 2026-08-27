<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sach Category</title>
</head>
<body>

    <h2>Danh sach Category</h2>
    <p><a href="<c:url value='/admin/category/add'/>">Them danh muc</a> | <a href="<c:url value='/home'/>">Ve trang chu</a></p>

    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
            <tr>
                <th>STT</th>
                <th>Hinh anh</th>
                <th>Ten danh muc</th>
                <th>Chuc nang</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cateList}" var="cate" varStatus="STT">
                <tr class="odd gradeX">
                    <td>${STT.index + 1}</td>
                    <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                    <td><img height="150" width="200" src="${imgUrl}" onerror="this.src='https://via.placeholder.com/200x150?text=No+Image'" /></td>
                    <td>${cate.name}</td>
                    <td>
                        <a href="<c:url value='/admin/category/edit?id=${cate.id}'/>" class="center">Sua</a>
                        | 
                        <a href="<c:url value='/admin/category/delete?id=${cate.id}'/>" class="center" onclick="return confirm('Ban co chac muon xoa?');">Xoa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
