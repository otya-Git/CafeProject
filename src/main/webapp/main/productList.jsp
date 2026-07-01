<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@include file="../header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>

<style>
table{
    border-collapse: collapse;
    width:80%;
    margin:auto;
}

th,td{
    border:1px solid #999;
    padding:10px;
    text-align:center;
}

th{
    background:#f3e5c8;
}
</style>

</head>
<body>

<h2 align="center">商品一覧</h2>

<table>

<tr>
    <th>ID</th>
    <th>商品名</th>
    <th>カテゴリ</th>
    <th>原価</th>
    <th>販売価格</th>
    <th>説明</th>
</tr>

<c:forEach var="p" items="${list}">

<tr>

<td>${p.productId}</td>
<td>${p.productName}</td>
<td>${p.categoryName}</td>
<td>${p.costPrice}</td>
<td>${p.price}</td>
<td>${p.description}</td>

</tr>

</c:forEach>

</table>

</body>
</html>