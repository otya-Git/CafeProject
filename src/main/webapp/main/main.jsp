<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Users" %>

<%@include file="../header.jsp" %>

<link rel="stylesheet" href="../css/main.css">

<%
Users user = (Users) session.getAttribute("user");
%>

<div>

    <!-- 🔵 ログイン中ユーザー表示 -->
    <div style="margin-bottom:10px;">
        <%
        if (user != null) {
        %>
            ようこそ、<b><%= user.getUser_name() %></b> さん
        <%
        } else {
        %>
            未ログイン
        <%
        }
        %>
    </div>

    <h2>☕ メニュー</h2><br>

    <div class="main">

        <div class="box">
            <a href="/">在庫管理</a>
        </div>

        <div class="box">
            <a href="${pageContext.request.contextPath}/ProductListServlet">商品管理</a>
        </div>

        <div class="box">
            <a href="${pageContext.request.contextPath}/OrderServlet">注文</a>
        </div>

        <div class="box">
            <a href="/Sales.jsp">売り上げ一覧</a>
        </div>

        <%-- 🟡 管理者だけ表示 --%>
        <%
        if (user != null && "ADMIN".equals(user.getRole())) {
        %>

        <div class="box">
            <a href="/">ユーザー管理</a>
        </div>

        <div class="box">
            <a href="/">シフト一覧</a>
        </div>

        <%
        }
        %>

    </div>
</div>

<%@include file="../footer.jsp" %>