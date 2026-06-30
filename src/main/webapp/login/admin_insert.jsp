<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../loginheader.jsp" %>

<form class ="login-form" action="../UserInsert" method="post">
<h4>新規登録</h4>
<p>ログインID：<input type="text" name="login_id"></p>
<p class="error">${error1}</p>
<p>パスワード：<input type="password" name="password_hash"></p>
<p>名前：<input type="text" name="user_name"></p>
<p class="error">${error2}</p>
<p class="error">${error}</p>
<input type="hidden" name="role" value="ADMIN">
<p><input type="submit" value="登録"  class=""></p>
</form>

<%@include file="../loginfooter.jsp" %>