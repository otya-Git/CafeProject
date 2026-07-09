<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/productlist.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>

<h2>☕ 商品一覧</h2>

<div class="list-area">

    <!-- 商品登録ボタン -->
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

        <label>カテゴリ</label>

        <select name="category">

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
				
				</td>

            </tr>
            

        </c:forEach>
    </table>
	<a href="${pageContext.request.contextPath}/main/main.jsp">戻る</a>
</div>

</body>
</html>
