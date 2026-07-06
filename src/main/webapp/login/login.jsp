<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/login.css">
<%@ include file="../header.jsp" %>
<h1>カフェDX始めました</h1>

<div class="login-box">

    <h2>ログイン</h2>
    <%
String message =
    (String)request.getAttribute("message");

if(message != null){
%>

<p style="color:red;">
    <%= message %>
</p>

<%
}
%>

    <form action="${pageContext.request.contextPath}/login"
          method="post">

        <div class="login">

            <input
                type="text"
                name="login_id"
                placeholder="ログインID"
            ><br>

            <input
                type="password"
                name="password_hash"
                id="pass"
                placeholder="パスワード"
            >

        </div>

        <div>
            <label>
                <input type="checkbox" id="check">
                パスワードを表示
            </label>
        </div>

        <div class="logbut">
            <button type="submit">
                ログイン
            </button>
        </div>

    </form>
    <h4>はじめての方はこちら</h4>
<a class="btn-link"
   href="${pageContext.request.contextPath}/login/insert.jsp">
    新規登録
</a>
    <a href="${pageContext.request.contextPath}/login/admin_check.jsp">
        管理者ページへ
    </a>

</div>

<script>
    const pass = document.getElementById("pass");
    const check = document.getElementById("check");

    check.addEventListener("change", () => {
        pass.type = check.checked ? "text" : "password";
    });
</script>

<%@ include file="../footer.jsp" %>