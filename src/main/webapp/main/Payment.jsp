<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.Order_Item"%>


<%

List<Order_Item> cart =
    (List<Order_Item>)request.getAttribute("cart");


Integer tableId =
    (Integer)session.getAttribute("tableId");


Integer total =
    (Integer)request.getAttribute("total");


if(total == null){

    total = 0;

}

%>



<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>会計</title>


<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/payment.css">


</head>

<body>


<h2>会計</h2>



<div class="payment-box">



<h3>
テーブル <%=tableId%>番
</h3>



<div class="total-price">
    合計金額：
    <%=total%>円
</div>




<h3>
注文内容
</h3>



<table border="1">


<tr>

<th>
商品名
</th>

<th>
数量
</th>

<th>
金額
</th>


</tr>



<%

if(cart != null && !cart.isEmpty()){


for(Order_Item item : cart){

%>


<tr>


<td>
<%=item.getProduct_name()%>
</td>


<td>
<%=item.getQuantity()%>
</td>


<td>
<%=item.getPrice()
    *
    item.getQuantity()%>円
</td>


</tr>


<%

}

}else{

%>


<tr>

<td colspan="3">

注文がありません

</td>

</tr>


<%

}

%>


</table>





<form action="${pageContext.request.contextPath}/PaymentServlet"
      method="post">



<h3>
支払方法
</h3>



<select name="payment_method">


<option value="現金">
現金
</option>


<option value="カード">
カード
</option>


<option value="QR決済">
QR決済
</option>


</select>



<br><br>



<input type="submit"
       class="payment-btn"
       value="会計確定">



</form>





<br>



<a href="${pageContext.request.contextPath}/OrderServlet">

戻る

</a>



</div>



</body>


</html>