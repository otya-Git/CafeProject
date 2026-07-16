<%@page contentType="text/html; charset=UTF-8" %>
<!-- 変更点: admin_check.css を読み込むタグを追加 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_check.css">

<h1>管理者認証</h1>

<div class="login-box">
    <h2>管理者ページへ進む</h2>
    
    <form id="login-form" action="../login_admin" method="post">
        <div class="login-input-group">
            <label for="admin-pass">パスワードを入力してください</label>
            <input type="password" id="admin-pass" name="password" placeholder="管理者パスワード">
        </div>

        <div class="logbut">
            <button type="submit">認証する</button>
        </div>
    </form>

    <!-- 変更点: 一般ユーザー用のログイン画面に戻るリンクを追加 -->
    <div class="back-area">
        <a class="back-link" href="${pageContext.request.contextPath}/login/login.jsp">
            ログイン画面に戻る
        </a>
    </div>
</div>

<%@include file="../loginfooter.jsp" %>