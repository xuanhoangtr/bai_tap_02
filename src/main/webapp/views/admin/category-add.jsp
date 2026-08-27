<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Category</title>
</head>
<body>

    <h2>Add Category</h2>
    <hr/>

    <form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">
        <label for="categoryname">Category name:</label><br>
        <input type="text" id="categoryname" name="categoryname" required><br><br>

        <label for="images">Link images:</label><br>
        <input type="text" id="images" name="images"><br><br>

        <label for="images1">Upload images:</label><br>
        <input type="file" id="images1" name="images1"><br><br>

        <label>Status:</label><br>
        <input type="radio" id="ston" name="status" value="1" checked>
        <label for="ston">Hoat dong</label><br>
        <input type="radio" id="stoff" name="status" value="0">
        <label for="stoff">Khoa</label>

        <br><br>
        <input type="submit" value="Insert">
    </form>

    <br/>
    <p><a href="<c:url value='/admin/categories'/>">Quay lai danh sach</a></p>

</body>
</html>
