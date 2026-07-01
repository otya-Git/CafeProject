<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="../header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
</head>
<body>

<h2>商品登録</h2>

<form action="${pageContext.request.contextPath}/ProductInsertServlet" method="post">

<p>商品名</p>
<input type="text" name="product_name">

<p>カテゴリ</p>
<input type="text" name="category_name">

<p>原価</p>
<input type="number" name="cost_price">

<p>販売価格</p>
<input type="number" name="price">

<p>商品説明</p>
<textarea name="description"></textarea>

<br><br>

<input type="submit" value="登録">

</form>

</body>
</html>