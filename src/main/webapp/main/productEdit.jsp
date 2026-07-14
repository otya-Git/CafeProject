<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<h2>☕ 商品編集</h2>

<div class="form-box">

<form action="${pageContext.request.contextPath}/ProductUpdateServlet"
      method="post">

    <input type="hidden"
           name="id"
           value="${product.productId}">

    <div class="form-group">
        <label>商品名</label>
        <input type="text"
               name="name"
               value="${product.productName}"
               required>
    </div>

    <div class="form-group">
        <label>カテゴリ</label>

        <select name="category">

            <c:forEach var="c" items="${categoryList}">

                <option value="${c.categoryName}"
                    <c:if test="${c.categoryName == product.categoryName}">
                        selected
                    </c:if>>

                    ${c.categoryName}

                </option>

            </c:forEach>

        </select>

    </div>

    <div class="form-group">
        <label>原価</label>

        <input type="number"
               name="cost"
               value="${product.costPrice}"
               required>
    </div>

    <div class="form-group">
        <label>販売価格</label>

        <input type="number"
               name="price"
               value="${product.price}"
               required>
    </div>

    <div class="form-group">
        <label>商品説明</label>

        <textarea name="description"
                  rows="4">${product.description}</textarea>
    </div>

    <div class="form-group">

        <label>使用材料</label>

        <div id="ingredientArea">

            <c:forEach var="r" items="${recipeList}">

                <div class="ingredient-row">

                    <select name="ingredientId">

                        <c:forEach var="i" items="${ingredientList}">

                            <option value="${i.ingredientId}"

                                <c:if test="${i.ingredientId == r.ingredientId}">
                                    selected
                                </c:if>>

                                ${i.ingredientName} (${i.unit})

                            </option>

                        </c:forEach>

                    </select>

                    <input
                        type="number"
                        step="0.01"
                        name="quantity"
                        value="${r.quantity}"
                        placeholder="使用量">

                    <button
                        type="button"
                        class="removeIngredient">

                        削除
                    </button>

                </div>

            </c:forEach>

        </div>

        <button
            type="button"
            id="addIngredient"
            class="btn-add">

            ＋ 材料追加

        </button>

    </div>

    <div class="button-area">

        <input
            type="submit"
            value="更新"
            class="btn-register">

        <a href="${pageContext.request.contextPath}/ProductListServlet"
           class="btn-back">

            一覧へ戻る

        </a>

    </div>

</form>

</div>

<script>

const addButton = document.getElementById("addIngredient");
const area = document.getElementById("ingredientArea");

addButton.onclick = function(){

    const row = document.querySelector(".ingredient-row");

    const copy = row.cloneNode(true);

    copy.querySelector("input").value = "";

    copy.querySelector("select").selectedIndex = 0;

    area.appendChild(copy);

};

// 削除
area.addEventListener("click", function(e){

    if(e.target.classList.contains("removeIngredient")){

        const rows =
        document.querySelectorAll(".ingredient-row");

        if(rows.length > 1){

            e.target.parentElement.remove();

        }

    }

});

</script>

<%@include file="../footer.jsp"%>