<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<h2>☕ 商品詳細</h2>

<div class="form-box">

    <table class="product-table">

        <tr>
            <th>商品名</th>
            <td>${product.productName}</td>
        </tr>

        <tr>
            <th>カテゴリ</th>
            <td>${product.categoryName}</td>
        </tr>

        <tr>
            <th>原価</th>
            <td>¥${product.costPrice}</td>
        </tr>

        <tr>
            <th>販売価格</th>
            <td>¥${product.price}</td>
        </tr>

        <tr>
            <th>商品説明</th>
            <td>${product.description}</td>
        </tr>

    </table>

    <br>

    <h3>🧂 使用材料</h3>

    <table class="product-table">

        <tr>
            <th>材料名</th>
            <th>使用量</th>
            <th>単位</th>
        </tr>

        <c:forEach var="r" items="${recipeList}">

            <tr>

                <td>${r.ingredientName}</td>

                <td>${r.quantity}</td>

                <td>${r.unit}</td>

            </tr>

        </c:forEach>

    </table>

    <br>

    <div class="button-area">

        <a href="${pageContext.request.contextPath}/ProductListServlet"
           class="btn-back">
            一覧へ戻る
        </a>

    </div>

</div>

<%@include file="../footer.jsp"%>