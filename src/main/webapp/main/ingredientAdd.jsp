<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="../header.jsp"%>

<h2>🧂 材料登録</h2>

<div class="form-box">

<form action="${pageContext.request.contextPath}/IngredientInsertServlet"
      method="post">

    <div class="form-group">

        <label>材料名</label>

        <input type="text"
               name="ingredient_name"
               required>

    </div>

    <div class="form-group">

        <label>単位</label>

        <input type="text"
               name="unit"
               placeholder="g・ml・個・本"
               required>

    </div>

    <div class="button-area">

        <input type="submit"
               value="登録"
               class="btn-register">

        <a href="${pageContext.request.contextPath}/IngredientListServlet"
           class="btn-back">

            戻る

        </a>

    </div>

</form>

</div>

<%@include file="../footer.jsp"%>