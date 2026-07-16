<%@page contentType="text/html; charset=UTF-8" %>
<!-- 変更点: admin_insert.css を読み込むように指定 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_insert.css">


<h1>カフェDX始めました</h1>

<div class="login-box">
    <h2>新規登録</h2>

    <!-- 画面全体向けのエラーメッセージ -->
    <% if (request.getAttribute("error") != null) { %>
        <p class="error-message">${error}</p>
    <% } %>

    <form class="login-form" action="UserInsert" method="post">
        
        <div class="login-input-group">
            <label>ログインID</label>
            <input type="text" name="login_id" placeholder="ログインID">
            <% if (request.getAttribute("error1") != null) { %>
                <p class="field-error">${error1}</p>
            <% } %>
        </div>

        <div class="login-input-group">
            <label>パスワード</label>
            <input type="password" name="password_hash" placeholder="パスワードを入力">
        </div>

        <div class="login-input-group">
            <label>名前</label>
            <input type="text" name="user_name" placeholder="名前">
            <% if (request.getAttribute("error2") != null) { %>
                <p class="field-error">${error2}</p>
            <% } %>
        </div>

        <input type="hidden" name="role" value="ADMIN">

        <div class="logbut">
            <button type="submit">登録する</button>
        </div>
    </form>

    <!-- ログイン画面に戻るリンク -->
    <div class="back-area">
        <a class="back-link" href="${pageContext.request.contextPath}/login/login.jsp">
            ログイン画面に戻る
        </a>
    </div>
</div>

<%@include file="../loginfooter.jsp" %>