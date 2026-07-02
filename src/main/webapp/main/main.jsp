<%@page contentType="text/html; charset=UTF-8" %>

<%@include file="../header.jsp" %>

<link rel="stylesheet" href="../css/main.css">

<div class="">
<h2>☕ メニュー</h2><br>

<div class="main">

<div class="box">
<a href="/">在庫管理</a>
</div>

<div class="box">
<a href="/">商品管理</a>
</div>

<div class="box">
<a href="${pageContext.request.contextPath}/main/Order.jsp">注文管理</a>
</div>

<div class="box">
<a href="/">ユーザー管理</a>
</div>

<div class="box">
<a href="/Sales.jsp">売り上げ一覧</a>
</div>

<div class="box">
<a href="/">シフト一覧</a>
</div>

</div>

</div>

<%@include file="../footer.jsp" %>