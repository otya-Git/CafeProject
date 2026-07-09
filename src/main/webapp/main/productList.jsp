<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@ include file="../header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/productlist.css">

</head>
<body>

<h2>☕ 商品一覧</h2>

<div class="list-area">

    <!-- 商品登録 -->
    <div class="button-area">
        <a href="${pageContext.request.contextPath}/ProductAddServlet"
           class="add-btn">
            ＋ 商品を登録
        </a>
    </div>

    <!-- カテゴリ検索 -->
    <form action="${pageContext.request.contextPath}/ProductListServlet"
          method="get"
          class="search-form">

        <label for="category">カテゴリ</label>

        <select name="category" id="category">
            <option value="">全体</option>

            <c:forEach var="c" items="${categoryList}">
                <option value="${c.categoryName}"
                    <c:if test="${param.category == c.categoryName}">
                        selected
                    </c:if>>
                    ${c.categoryName}
                </option>
            </c:forEach>
        </select>

        <input type="submit"
               value="検索"
               class="search-btn">

    </form>

    <!-- 商品一覧 -->
    <table class="product-table">

        <thead>
            <tr>
                <th>ID</th>
                <th>商品名</th>
                <th>カテゴリ</th>
                <th>原価</th>
                <th>販売価格</th>
                <th>説明</th>
                <th>操作</th>
            </tr>
        </thead>

        <tbody>

            <c:choose>

                <c:when test="${empty list}">
                    <tr>
                        <td colspan="7" style="text-align:center;">
                            商品がありません
                        </td>
                    </tr>
                </c:when>

                <c:otherwise>

                    <c:forEach var="p" items="${list}">

                        <tr>

                            <td>${p.productId}</td>
                            <td>${p.productName}</td>
                            <td>${p.categoryName}</td>
                            <td>¥${p.costPrice}</td>
                            <td>¥${p.price}</td>
                            <td>${p.description}</td>

                            <td>
                                <div class="action-buttons">

                                    <a href="${pageContext.request.contextPath}/ProductDetailServlet?id=${p.productId}"
                                       class="detail-btn">
                                        詳細
                                    </a>

                                    <a href="${pageContext.request.contextPath}/ProductEditServlet?id=${p.productId}"
                                       class="edit-btn">
                                        編集
                                    </a>

                                    <a href="${pageContext.request.contextPath}/ProductDeleteServlet?id=${p.productId}"
                                       class="delete-btn"
                                       onclick="return confirm('この商品を削除しますか？');">
                                        削除
                                    </a>

                                </div>
                            </td>

                        </tr>

                    </c:forEach>

                </c:otherwise>

            </c:choose>

        </tbody>

    </table>

    <div class="back-area">
        <a href="${pageContext.request.contextPath}/main/main.jsp"
           class="back-btn">
            戻る
        </a>
    </div>

</div>

</body>
</html>