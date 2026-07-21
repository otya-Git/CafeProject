<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.Users"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カフェDX</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/product.css">

<script src="${pageContext.request.contextPath}/js/script.js"></script>

<%
Users menuUser = (Users) session.getAttribute("user");
%>


</head>

<header>

    <button class="hamburger-btn" id="js-hamburger">
        <span></span>
        <span></span>
        <span></span>
    </button>

    <%
    Users user = (Users) session.getAttribute("user");
    %>

    <nav class="slide-menu" id="js-nav">

        <div class="menu-user">

            <%
            if (user != null) {
            %>

                <div class="user-name">
                    ☺ <%= user.getUser_name() %> さん
                </div>

                <div class="user-role">
                    <%= "ADMIN".equals(user.getRole()) ? "管理者" : "スタッフ" %>
                </div>

            <%
            }
            %>

        </div>

        <!-- メニュー -->

    <ul>

        <li><a href="/CafeProject/main/main.jsp">メニュー</a></li>

        <li><a href="${pageContext.request.contextPath}/InventoryListServlet">在庫管理</a></li>

        <li><a href="${pageContext.request.contextPath}/IngredientListServlet">材料一覧</a></li>

        <li><a href="${pageContext.request.contextPath}/ProductListServlet">商品管理</a></li>
        
        <li><a href="${pageContext.request.contextPath}/SupplierListServlet">仕入先管理</a></li>

        <li><a href="${pageContext.request.contextPath}/TableListServlet">座席</a></li>
        
		<%
        if (menuUser != null && "ADMIN".equals(menuUser.getRole())) {
        %>
        <li><a href="${pageContext.request.contextPath}/SalesServlet">売り上げ一覧</a></li>

        <li><a href="${pageContext.request.contextPath}/UsersServlet">ユーザー管理</a></li>
        
        <li><a href="${pageContext.request.contextPath}/HistoryServlet">会計履歴</a></li>
        <%
        }
        %>
        

        <li><a href="${pageContext.request.contextPath}/ShiftServlet">シフト</a></li>

        <li class="logout">
            <a href="${pageContext.request.contextPath}/logout">
                🚪 ログアウト
            </a>
        </li>

    </ul>

</nav>
</header>