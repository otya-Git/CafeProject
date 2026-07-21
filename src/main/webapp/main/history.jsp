<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%> 

<%@include file="../header.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/history.css">

<h2>💰 会計履歴</h2>

<div class="list-area history-page">

    <div class="search-container">
        <form action="HistoryServlet" method="GET">
            <label for="searchDate">日付で絞り込み：</label>
            <input type="date" id="searchDate" name="date" value="${selectedDate}">
            <button type="submit" class="search-btn">表示</button>
            <a href="HistoryServlet" class="clear-link">全件表示に戻す</a>
        </form>
    </div>

    <table class="history-table">
    <thead>
        <tr>
            <th>注文ID</th>
            <th>テーブル</th>
            <th>商品名</th>
            <th>数量</th>
            <th>合計金額</th>
            <th>決済方法</th>
            <th>日時</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="h" items="${historyList}">
        <tr>
            <td>${h.orderId}</td>
            <td>${h.tableId}</td>
            <td>${h.productName}</td>
            <td>${h.quantity}</td>
            <td class="price">¥${h.totalAmount}</td>
            <td class="payment">${h.paymentMethod}</td>
            <td><fmt:formatDate value="${h.orderedAt}" pattern="yyyy-MM-dd HH:mm" /></td>
        </tr>
        </c:forEach>
    </tbody>
    </table>

</div>

<%@include file="../footer.jsp"%>
