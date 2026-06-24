<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../loginheader.jsp" %>

<form class ="login-form" action="/webapp/login/Insert.action" method="post">
<h4>新規登録</h4>
<p>ログイン名：<input type="text" name="teacher_id"></p>
<p class="error">${error1}</p>
<p>パスワード：<input type="password" name="password"></p>
<p>名前：<input type="text" name="teacher_name"></p>
<p>：<input type="text" name="school_id"></p>
<p class="error">${error2}</p>
<p class="error">${error}</p>
<p><input type="submit" value="登録"  class="reserve-btn"></p>
</form>

<%@include file="../loginfooter.jsp" %>