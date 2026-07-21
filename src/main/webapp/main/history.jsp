<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@include file="../header.jsp"%>

<h2>💰 会計履歴</h2>

<table border="1">

<tr>
    <th>注文ID</th>
    <th>テーブル</th>
    <th>商品名</th>
    <th>数量</th>
    <th>合計金額</th>
    <th>決済方法</th>
    <th>日時</th>
</tr>

<c:forEach var="h" items="${historyList}">

<tr>

    <td>${h.orderId}</td>

    <td>${h.tableId}</td>

    <td>${h.productName}</td>

    <td>${h.quantity}</td>

    <td>¥${h.totalAmount}</td>

    <td>${h.paymentMethod}</td>

    <td>${h.orderedAt}</td>

</tr>

</c:forEach>

</table>

<%@include file="../footer.jsp"%>