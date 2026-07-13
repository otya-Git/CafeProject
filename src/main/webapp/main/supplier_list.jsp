<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/productlist.css">

<h2>🚚 仕入先一覧</h2>

<div class="list-area">

    <table class="product-table">

        <tr>
            <th>ID</th>
            <th>仕入先名</th>
            <th>電話番号</th>
            <th>住所</th>
        </tr>

        <c:forEach var="s" items="${supplierList}">

            <tr>

                <td>${s.supplierId}</td>

                <td>${s.supplierName}</td>

                <td>${s.phone}</td>

                <td>${s.address}</td>

            </tr>

        </c:forEach>

    </table>

</div>

<%@include file="../footer.jsp"%>