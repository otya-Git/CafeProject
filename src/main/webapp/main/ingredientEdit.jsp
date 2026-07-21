<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/ingredientEdit.css">
<h2>🧂 材料編集</h2>



<div class="form-area">


<form action="${pageContext.request.contextPath}/IngredientUpdateServlet"
      method="post">



<input type="hidden"
       name="ingredientId"
       value="${ingredient.ingredientId}">



<p>
    <label>材料名：</label>
    <input type="text"
           name="ingredientName"
           value="${ingredient.ingredientName}"
           required>
</p>

<p>
    <label>単位：</label>
    <input type="text"
           name="unit"
           value="${ingredient.unit}"
           required>
</p>



</form>


<div class="button-area">
    <input type="submit"
           value="更新"
           class="edit-btn">

    <a href="${pageContext.request.contextPath}/IngredientListServlet"
       class="back-btn">
        戻る
    </a>
</div>

</div>



<%@include file="../footer.jsp"%>