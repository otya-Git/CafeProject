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
   

    <!-- 商品 -->
    <div class="form-group">

        <label>材料</label>

        <select name="ingredient_id" id="ingredient">

		<c:forEach var="i" items="${ingredientList}">

		<option value="${i.ingredientId}"
		        data-unit="${i.unit}">
		    ${i.ingredientName}
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
	       id="unit"
	       readonly>

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

const ingredient = document.getElementById("ingredient");
const unit = document.getElementById("unit");


ingredient.addEventListener("change", function(){

    const selected = this.options[this.selectedIndex];

    unit.value = selected.dataset.unit;

});

</script>

</body>
</html>