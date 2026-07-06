<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<h2>☕ 商品一覧</h2>

<div class="list-area">

    <div class="button-area">
        <a href="${pageContext.request.contextPath}/main/productAdd.jsp"
            class="add-btn">
            ＋ 商品を登録
        </a>
    </div>

    <table class="product-table">

        <tr>
            <th>ID</th>
            <th>商品名</th>
            <th>カテゴリ</th>
            <th>原価</th>
            <th>販売価格</th>
            <th>説明</th>
            <th>操作</th>
        </tr>

<c:forEach var="p" items="${list}">
<tr>
    <td>${p.productId}</td>
    <td>${p.productName}</td>
    <td>${p.categoryName}</td>
    <td>¥${p.costPrice}</td>
    <td>¥${p.price}</td>
    <td>${p.description}</td>
    <td>
        <a href="${pageContext.request.contextPath}/ProductEditServlet?id=${p.productId}"
           class="edit-btn">
            編集
        </a>

        <a href="${pageContext.request.contextPath}/ProductDeleteServlet?id=${p.productId}"
           class="delete-btn"
           onclick="return confirm('この商品を削除しますか？');">
            削除
        </a>
    </td>
</tr>
</c:forEach>

    </table>

</div>

