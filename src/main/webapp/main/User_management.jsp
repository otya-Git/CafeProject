<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="../css/users.css">

<h2>ユーザー</h2>

<table class="user-table">
	<tr>
		<th>利用者ID</th>
		<th>氏名</th>
		<th>登録日時</th>
		<!-- 💡 【追加】ヘッダーに操作列を追加 -->
		<th></th> 
	</tr>
	
	<!-- サーブレットから受け取った「userList」をループ処理 -->
	<c:forEach var="user" items="${userList}">
	<tr>
		<td><c:out value="${ user.user_id }"/></td>
		<td><c:out value="${ user.user_name }"/></td>
		<td><c:out value="${ user.formattedCreatedAt }"/></td>
		<td>
			<!-- 💡 【追加】編集サーブレット（/userUpdate）に対象の「user_id」をくっつけて送るリンク -->
			<a href="${pageContext.request.contextPath}/UserUpdateServlet?user_id=${user.user_id}">編集</a>
		</td>
	</tr>
	</c:forEach>
</table>


<%@ include file="../footer.jsp" %>
