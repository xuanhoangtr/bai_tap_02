<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Category</title>
</head>
<body>

    <h2>Edit Category</h2>
    <hr/>

    <form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="categoryid" value="${cate.categoryid}">

        <label for="categoryname">Category name:</label><br>
        <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}" required><br><br>

        <label for="images">Link images:</label><br>
        <input type="text" id="images" name="images" value="${cate.images}"><br><br>

        <c:choose>
            <c:when test="${not empty cate.images and cate.images.startsWith('http')}">
                <c:url value="${cate.images}" var="imgUrl"></c:url>
            </c:when>
            <c:otherwise>
                <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
            </c:otherwise>
        </c:choose>

        <img height="150" width="200" src="${imgUrl}" onerror="this.src='https://via.placeholder.com/200x150?text=No+Image'" /><br><br>

        <label for="images1">Upload images:</label><br>
        <input type="file" id="images1" name="images1"><br><br>

        <label>Status:</label><br>
        <input type="radio" id="ston" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}>
        <label for="ston">Hoat dong</label><br>
        <input type="radio" id="stoff" name="status" value="0" ${cate.status != 1 ? 'checked' : ''}>
        <label for="stoff">Khoa</label>

        <br><br>
        <input type="submit" value="Update">
    </form>

    <br/>
    <p><a href="<c:url value='/admin/categories'/>">Quay lai danh sach</a></p>

</body>
</html>
