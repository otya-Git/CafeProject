<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<form action="${pageContext.request.contextPath}/ProductUpdateServlet" method="post">

    <input type="hidden" name="id" value="${product.productId}">

    商品名：<input type="text" name="name" value="${product.productName}"><br>

    原価：<input type="text" name="cost" value="${product.costPrice}"><br>

    販売価格：<input type="text" name="price" value="${product.price}"><br>

    説明：
    <textarea name="description">${product.description}</textarea><br>

    カテゴリ：<input type="text" name="category" value="${product.categoryName}"><br>

    <button type="submit">更新</button>

</form>