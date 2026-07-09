<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.Users" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カフェDX</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/product.css">

<script src="${pageContext.request.contextPath}/js/script.js"></script>

<%
Users user = (Users) session.getAttribute("user");
%>

</head>

<header>
	<button class="hamburger-btn" id="js-hamburger">
	    <span></span>
	    <span></span>
	    <span></span>
	</button>
	
	<!-- スライドして出てくるメニュー中身 -->
	<nav class="slide-menu" id="js-nav">
	    <ul>
	    	<li><a href="/CafeProject/main/main.jsp">メニュー</a></li>
	        <li><a href="${pageContext.request.contextPath}/InventoryListServlet">在庫管理</a></li>
	        <li><a href="${pageContext.request.contextPath}/IngredientListServlet">材料一覧</a></li>
	        <li><a href="${pageContext.request.contextPath}/ProductListServlet">商品管理</a></li>
	        <li><a href="${pageContext.request.contextPath}/OrderServlet">注文</a></li>
	        <li><a href="${pageContext.request.contextPath}/SalesServlet">売り上げ一覧</a></li>
	        <% if (user != null && "ADMIN".equals(user.getRole())){ %>
	        <li><a href="${pageContext.request.contextPath}/UsersServlet">ユーザー管理</a></li>
	        <li><a href="/">シフト管理</a></li>
	        <%} %>
	    </ul>
	</nav>
</header>