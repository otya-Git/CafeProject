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
	</tr>
	<c:forEach var="user" items="${list}">
	<tr>
		<td><c:out value="${ user.user_id }"/></td>
		<td><c:out value="${ user.user_name }"/></td>
		<td><c:out value="${ user.created_at }"/></td>
	</tr>
	</c:forEach>
</table>

<%@ include file="../footer.jsp" %>