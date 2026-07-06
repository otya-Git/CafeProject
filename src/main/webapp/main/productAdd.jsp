<%@ page contentType="text/html; charset=UTF-8"%>

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
        <input type="text" name="category_name">
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

<%@include file="../footer.jsp"%>