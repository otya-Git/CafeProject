<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="../css/users.css">

<%@ include file="../header.jsp" %>

<h2>ユーザー</h2>

<table class="user-table">
	<tr>
		<th>利用者ID</th>
		<th>氏名</th>
		<th>登録日時</th>
		<th>　</th> 
	</tr>
	
	<c:forEach var="user" items="${userList}">
	<tr>
		<td><c:out value="${ user.user_id }"/></td>
		<td><c:out value="${ user.user_name }"/></td>
		<td><c:out value="${ user.formattedCreatedAt }"/></td>
		<td>
			<!-- 💡 style="..." を直接指定して、他のCSSの干渉をすべて無視して強制適用します -->
			<a href="${pageContext.request.contextPath}/UserUpdateServlet?user_id=${user.user_id}" 
			   style="display: inline-block; padding: 6px 16px; background-color: #8d6e63; color: #ffffff; text-decoration: none; border-radius: 8px; font-size: 14px; font-weight: bold; border: none;">
			   編集
			</a>
		</td>
	</tr>
	</c:forEach>
</table>

<%@ include file="../footer.jsp" %>
