<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<h2>☕ 商品登録</h2>

<div class="form-box">

<form action="${pageContext.request.contextPath}/ProductInsertServlet"
      method="post">

    <div class="form-group">
        <label>商品名</label>
        <input type="text" name="product_name">
    </div>

	<div class="form-group">
	    <label>カテゴリ</label>
	
	    <select name="category_name">
	
	        <c:forEach var="c" items="${categoryList}">
	
	            <option value="${c.categoryName}">
	                ${c.categoryName}
	            </option>
	
	        </c:forEach>
	
	    </select>
	
	</div>

    <div class="form-group">
        <label>原価</label>
        <input type="number" name="cost_price">
    </div>

    <div class="form-group">
        <label>販売価格</label>
        <input type="number" name="price">
    </div>

    <div class="form-group">
        <label>商品説明</label>
        <textarea name="description"></textarea>
    </div>
    <div class="form-group">

    <label>使用材料</label>

    <div id="ingredientArea">

        <div class="ingredient-row">

            <select name="ingredientId">

                <c:forEach var="i" items="${ingredientList}">

                    <option value="${i.ingredientId}">
                        ${i.ingredientName} (${i.unit})
                    </option>

                </c:forEach>

            </select>

            <input
                type="number"
                step="0.01"
                name="quantity"
                placeholder="使用量">
                
             <div class="ingredient-row">

		    

</div>

        </div>

    </div>

    <br>

    <button
        type="button"
        id="addIngredient">
        ＋材料追加
    </button>

</div>

    <div class="button-area">

        <input type="submit"
               value="登録"
               class="btn-register">

        <a href="${pageContext.request.contextPath}/ProductListServlet"
           class="btn-back">

            一覧へ戻る

        </a>

    </div>

</form>

</div>
<script>

const addButton =
document.getElementById("addIngredient");

const area =
document.getElementById("ingredientArea");

addButton.onclick = function(){

    const row =
    document.querySelector(".ingredient-row");

    const copy =
    row.cloneNode(true);

    copy.querySelector("input").value = "";

    area.appendChild(copy);

};
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