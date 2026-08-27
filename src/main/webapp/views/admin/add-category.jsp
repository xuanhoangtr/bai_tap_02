<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Them Category</title>
</head>
<body>

    <h2>Them danh muc moi</h2>

    <form role="form" action="<c:url value='/admin/category/add'/>" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label>Ten danh muc:</label>
            <input class="form-control" placeholder="please enter category Name" name="name" required />
        </div>
        <br/>
        <div class="form-group">
            <label>Anh dai dien</label>
            <input type="file" name="icon" />
        </div>
        <br/>
        <button type="submit" class="btn btn-default">Them</button>
        <button type="reset" class="btn btn-primary">Huy</button>
    </form>

    <br/>
    <p><a href="<c:url value='/admin/category/list'/>">Quay lai danh sach</a></p>

</body>
</html>
