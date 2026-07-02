<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Order" %>

<%
ArrayList<Order> orderList = (ArrayList<Order>)request.getAttribute("orderList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文一覧</title>
</head>
<body>

<h2>注文一覧</h2>

<table border="1">
    <tr>
        <th>注文ID</th>
        <th>テーブルID</th>
        <th>ステータス</th>
        <th>決済方法</th>
        <th>注文日時</th>
        <th>合計金額</th>
    </tr>

<%
for(Order order : orderList){
%>

<tr>
    <td><%=order.getOrder_id()%></td>
    <td><%=order.getTable_id()%></td>
    <td><%=order.getStatus()%></td>
    <td><%=order.getPayment_method()%></td>
    <td><%=order.getOrdered_at()%></td>
    <td><%=order.getTotal_amount()%>円</td>
</tr>

<%
}
%>

</table>

</body>
</html>