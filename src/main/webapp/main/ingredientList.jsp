<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>


<h2>🧂 材料一覧</h2>


<div class="list-area">


    <div class="button-area">

        <a href="${pageContext.request.contextPath}/IngredientAddServlet"
           class="add-btn">

            ＋ 材料登録

        </a>

    </div>



    <table class="product-table">


        <tr>

            <th>ID</th>
            <th>材料名</th>
            <th>単位</th>
            <th>操作</th>

        </tr>

        <c:forEach var="i" items="${list}">

            <tr>

                <td>
                    ${i.ingredientId}
                </td>

                <td>
                    ${i.ingredientName}
                </td>

                <td>
                    ${i.unit}
                </td>

                <td>

                    <a href="${pageContext.request.contextPath}/IngredientEditServlet?id=${i.ingredientId}"
                       class="edit-btn">
                        編集
                    </a>

                    <a href="${pageContext.request.contextPath}/IngredientDeleteServlet?id=${i.ingredientId}"
                       class="delete-btn"
                       onclick="return confirm('この材料を削除しますか？');">
                        削除
                    </a>

                </td>

            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="../footer.jsp"%>