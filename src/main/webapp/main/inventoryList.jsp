<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<h2>📦 在庫一覧</h2>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/inventorylist.css">
<div class="list-area">

    <div class="button-area">
        <a href="${pageContext.request.contextPath}/InventoryAddServlet"
		   class="add-btn">
		    ＋ 在庫を登録
		</a>
    </div>

    <table class="product-table">

        <tr>
            <th>商品名</th>
            <th>仕入先</th>
            <th>在庫数</th>
            <th>単位</th>
            <th>発注点</th>
            <th>賞味期限</th>
            <th>状態</th>
        </tr>

        <c:forEach var="i" items="${list}">

            <tr>

                <td>${i.productName}</td>

                <td>${i.supplierName}</td>

                <td>

                    <c:choose>

                        <c:when test="${i.stockQuantity <= i.reorderPoint}">

                            <span style="color:red;font-weight:bold;">
                                ${i.stockQuantity}
                            </span>

                        </c:when>

                        <c:otherwise>

                            ${i.stockQuantity}

                        </c:otherwise>

                    </c:choose>

                </td>

                <td>${i.unit}</td>

                <td>${i.reorderPoint}</td>

                <td>${i.expiryDate}</td>

                <td>

                    <c:choose>

                        <c:when test="${i.stockQuantity <= i.reorderPoint}">

                            🔴 発注

                        </c:when>

                        <c:otherwise>

                            🟢 正常

                        </c:otherwise>

                    </c:choose>

                </td>

            </tr>

        </c:forEach>

    </table>
    <div class="back-area">
        <a href="${pageContext.request.contextPath}/main/main.jsp"
           class="back-btn">
            戻る
        </a>
    </div>
</div>

<%@include file="../footer.jsp"%>