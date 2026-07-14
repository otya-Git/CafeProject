<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Users" %>

<%@include file="../header.jsp" %>

<link rel="stylesheet" href="../css/main.css">

<%
Users loginUser = (Users) session.getAttribute("user");
%>
<%--aiu --%>
<div>


    <h2>☕ メニュー</h2><br>

    <div class="main">

        <div class="box">
            <a href="${pageContext.request.contextPath}/InventoryListServlet">在庫管理</a>
        </div>
        
        <div class="box">
            <a href="${pageContext.request.contextPath}/IngredientListServlet">材料一覧</a>
        </div>
        
        <div class="box">
            <a href="${pageContext.request.contextPath}/ProductListServlet">商品管理</a>
        </div>
        
        <div class="box">
	        <a href="${pageContext.request.contextPath}/SupplierListServlet">仕入先管理</a>
        </div>
        
        <div class="box">
        	<a href="${pageContext.request.contextPath}/SupplierInsertServlet">仕入先登録</a>
        </div>
        
        <div class="box">
            <a href="${pageContext.request.contextPath}/TableListServlet">テーブル</a>   
        </div>
        


        <%-- 🟡 管理者だけ表示 --%>
        <%
        if (loginUser != null && "ADMIN".equals(loginUser.getRole())) {
        %>
        <div class="box">
            <a href="${pageContext.request.contextPath}/SalesServlet">売り上げ一覧</a>
        </div>
        
        <div class="box">
            <a href="${pageContext.request.contextPath}/UsersServlet">ユーザー管理</a>
        </div>

        <div class="box">
            <a href="/">シフト</a>
        </div>

        <%
        }
        %>

    </div>
</div>

<%@include file="../footer.jsp" %>