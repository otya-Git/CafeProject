<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/UserEdit.css">

<h2>ユーザー情報の編集</h2>

<form action="${pageContext.request.contextPath}/UserUpdateServlet" method="post">
	
	<input type="hidden" name="user_id" value="${user.user_id}">

	<table class="edit-table" border="1" cellpadding="10" style="border-collapse: collapse; margin-bottom: 20px;">
		<tr>
			<th>利用者ID</th>
			<td><c:out value="${user.user_id}"/> (変更不可)</td>
		</tr>
		<tr>
			<th>氏名</th>
			<td><input type="text" name="user_name" value="<c:out value='${user.user_name}'/>" required></td>
		</tr>
		<tr>
			<th>ログインID</th>
			<td><input type="text" name="login_id" value="<c:out value='${user.login_id}'/>" required></td>
		</tr>
		<tr>
			<th>新しいパスワード</th>
			<td>
				<input type="password" name="password" placeholder="新しいパスワードを入力" required><br>
				<small style="color: gray;">※セキュリティのためハッシュ化されて保存されます</small>
			</td>
		</tr>
		<tr>
			<th>権限 (Role)</th>
			<td>
			
				<select name="role">
					<option value="admin" ${user.role == 'admin' ? 'selected' : ''}>管理者 (admin)</option>
					<option value="user" ${user.role == 'user' ? 'selected' : ''}>一般ユーザー (user)</option>
				</select>
			</td>
		</tr>
	</table>

	<button type="submit" style="padding: 10px 20px; font-weight: bold;">変更を保存する</button>
	<a href="${pageContext.request.contextPath}/UsersServlet" style="margin-left: 10px;">キャンセル</a>
</form>

<%@ include file="../footer.jsp" %>
