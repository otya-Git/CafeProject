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

材料名：

<input type="text"
       name="ingredientName"
       value="${ingredient.ingredientName}"
       required>

</p>



<p>

単位：

<input type="text"
       name="unit"
       value="${ingredient.unit}"
       required>

</p>




<input type="submit"
       value="更新"
       class="edit-btn">



</form>



</div>



<%@include file="../footer.jsp"%>