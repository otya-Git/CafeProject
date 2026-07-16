<%@page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_check.css">


<h1>管理者認証</h1>


<div class="login-box">

    <h2>管理者ページへ進む</h2>


    <!-- エラー表示 -->
    <%
    String error = (String)request.getAttribute("error");

    if(error != null){
    %>

    <p class="error-message">
        <%=error%>
    </p>

    <%
    }
    %>


    <form id="login-form"
          action="${pageContext.request.contextPath}/login_admin"
          method="post">


        <div class="login-input-group">

            <label for="admin-pass">
                パスワードを入力してください
            </label>


            <input type="password"
                   id="admin-pass"
                   name="password"
                   placeholder="管理者パスワード">

        </div>



        <div class="logbut">

            <button type="submit">
                認証する
            </button>

        </div>


    </form>



    <div class="back-area">

        <a class="back-link"
           href="${pageContext.request.contextPath}/login/login.jsp">

            ログイン画面に戻る

        </a>

    </div>


</div>


<%@include file="../loginfooter.jsp" %>