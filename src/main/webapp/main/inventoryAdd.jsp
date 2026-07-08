<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/product.css">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫登録</title>
</head>

<body>

<h2>📦 在庫登録</h2>

<div class="form-box">

<form action="${pageContext.request.contextPath}/InventoryInsertServlet"
      method="post">

    <!-- カテゴリ -->
    <div class="form-group">

        <label>カテゴリ</label>

        <select id="category">

            <option value="">選択してください</option>

            <c:forEach var="c" items="${categoryList}">

                <option value="${c.categoryName}">
                    ${c.categoryName}
                </option>

            </c:forEach>

        </select>

    </div>

    <!-- 商品 -->
    <div class="form-group">

        <label>商品</label>

        <select name="product_id" id="product">

            <c:forEach var="p" items="${productList}">

                <option
                    value="${p.productId}"
                    data-category="${p.categoryName}">

                    ${p.productName}

                </option>

            </c:forEach>

        </select>

    </div>

    <!-- 仕入先 -->
    <div class="form-group">

        <label>仕入先</label>

        <select name="supplier_id">

            <c:forEach var="s" items="${supplierList}">

                <option value="${s.supplierId}">
                    ${s.supplierName}
                </option>

            </c:forEach>

        </select>

    </div>

    <!-- 在庫数 -->
    <div class="form-group">

        <label>在庫数</label>

        <input type="number"
               step="0.01"
               name="stock_quantity"
               required>

    </div>

    <!-- 単位 -->
    <div class="form-group">

        <label>単位</label>

        <input type="text"
               name="unit"
               placeholder="g・個・本など"
               required>

    </div>

    <!-- 発注点 -->
    <div class="form-group">

        <label>発注点</label>

        <input type="number"
               step="0.01"
               name="reorder_point"
               required>

    </div>

    <!-- 賞味期限 -->
    <div class="form-group">

        <label>賞味期限</label>

        <input type="date"
               name="expiry_date">

    </div>

    <div class="button-area">

        <input type="submit"
               value="登録"
               class="btn-register">

        <a href="${pageContext.request.contextPath}/InventoryListServlet"
           class="btn-back">
            戻る
        </a>

    </div>

</form>

</div>

<!-- カテゴリで商品を絞り込む -->
<script>

const category = document.getElementById("category");
const product = document.getElementById("product");

category.addEventListener("change", function(){

    const selected = this.value;

    for (const option of product.options) {

        if (selected === "" ||
            option.dataset.category === selected) {

            option.hidden = false;

        } else {

            option.hidden = true;

        }

    }

    product.selectedIndex = 0;

});

</script>

</body>
</html>