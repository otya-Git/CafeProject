<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.Product"%>
<%@ page import="bean.Order_Item"%>


<%
List<Product> list =
    (List<Product>)request.getAttribute("list");


List<Order_Item> cart =
    (List<Order_Item>)request.getAttribute("cart");


List<Order_Item> historyList =
    (List<Order_Item>)request.getAttribute("historyList");

%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>注文</title>


<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/order.css">


</head>


<body>


<h2>注文</h2>


<div class="container">



<!-- =====================
 商品一覧
===================== -->

<div class="left">


<div class="title-area">

    <h2>商品一覧</h2>

    <form action="${pageContext.request.contextPath}/OrderServlet"
          method="get">

        <input type="text"
               name="keyword"
               placeholder="商品名検索">

        <input type="submit"
               value="検索"
               class="search-btn">

    </form>

</div>


<table>


<tr>
<th>商品名</th>
<th>説明</th>
<th>価格</th>
<th>数量</th>
<th>操作</th>
</tr>



<%
if(list != null){

for(Product p : list){

%>


<tr>


<td>
<%=p.getProductName()%>
</td>


<td>
<%=p.getDescription()%>
</td>


<td>
<%=p.getPrice()%>円
</td>


<td>


<form action="${pageContext.request.contextPath}/cart/add"
      method="post">


<input type="number"
       name="quantity"
       value="1"
       min="1">


<input type="hidden"
       name="product_id"
       value="<%=p.getProductId()%>">


</td>


<td>

<input type="submit"
       value="追加">


</form>


</td>


</tr>



<%

}

}

%>


</table>



<br>

<div class="button-area">

    <a href="${pageContext.request.contextPath}/main/main.jsp"
       class="btn-back">
        戻る
    </a>

</div>



</div>





<!-- =====================
 右側
===================== -->


<div class="right">



<h3>現在の注文</h3>


<table>


<tr>

<th>商品</th>
<th>数量</th>
<th>金額</th>

</tr>



<%

if(cart != null){

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
商品がありません
</td>
</tr>


<%

}

%>


</table>





<form action="${pageContext.request.contextPath}/order/confirm"
      method="post">


<input type="submit"
       class="confirm-btn"
       value="注文確定">


</form>







<!-- =====================
 注文履歴
===================== -->


<h3>注文履歴</h3>



<table class="history-table">


<tr>

<th>注文番号</th>
<th>商品名</th>
<th>数量</th>
<th>状態</th>
<th>操作</th>

</tr>



<%

if(historyList != null){


for(Order_Item item : historyList){


%>



<tr>


<td>

<%=item.getOrder_id()%>

</td>



<td>

<%=item.getProduct_name()%>

</td>



<td>

<%=item.getQuantity()%>

</td>




<td>


<form action="${pageContext.request.contextPath}/OrderStatusServlet"
      method="post">


<input type="hidden"
       name="order_item_id"
       value="<%=item.getOrder_item_id()%>">



<select name="status">


<option value="注文中"
<%= "注文中".equals(item.getStatus())?"selected":"" %>>
注文中
</option>


<option value="提供完了"
<%= "提供完了".equals(item.getStatus())?"selected":"" %>>
提供完了
</option>


</select>



<input type="submit"
       value="変更">


</form>



</td>






<td>


<form action="${pageContext.request.contextPath}/OrderCancelServlet"
      method="post">


<input type="hidden"
       name="order_item_id"
       value="<%=item.getOrder_item_id()%>">



<input type="submit"
       value="キャンセル"
       onclick="return confirm('この商品をキャンセルしますか？');">


</form>


</td>



</tr>




<%

}

}

%>


</table>




<br>


<!-- 会計 -->

<a href="${pageContext.request.contextPath}/PaymentServlet"
class="payment-btn">

会計

</a>



</div>


</div>



</body>

</html>