<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="../header.jsp" %>

<link rel="stylesheet" href="../css/main.css">

<h2>テーブル一覧</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>テーブル</th>
        <th>状態</th>
        <th>操作</th>
    </tr>

    <c:forEach var="table" items="${tableList}">
        <tr>

            <td>
                ${table.tableId}番席
            </td>

            <td>
                ${table.status}
            </td>

            <td>

                <c:choose>

                    <c:when test="${table.status == '空席'}">

                        <form action="${pageContext.request.contextPath}/OrderServlet"
                              method="get">

                            <input type="hidden"
                                   name="table_id"
                                   value="${table.tableId}">

                            <input type="submit"
                                   value="注文開始">

                        </form>

                    </c:when>

                    <c:otherwise>

                        <form action="${pageContext.request.contextPath}/OrderServlet"
                              method="get">

                            <input type="hidden"
                                   name="table_id"
                                   value="${table.tableId}">

                            <input type="submit"
                                   value="注文を見る">

                        </form>

                    </c:otherwise>

                </c:choose>

            </td>

        </tr>

    </c:forEach>

</table>

<%@ include file="../footer.jsp" %>