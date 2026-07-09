<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.Product"%>
<%@ page import="bean.Order_Item"%>


<%
List<Product> list =
    (List<Product>)request.getAttribute("list");

List<Order_Item> cart =
    (List<Order_Item>)request.getAttribute("cart");
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


<!-- 左側 商品一覧 -->
<div class="left">


<h3>商品一覧</h3>


<table>


<tr>

<th>商品名</th>
<th>商品説明</th>
<th>価格</th>
<th>数量</th>
<th>注文</th>

</tr>



<%

if(list != null && !list.isEmpty()){


    for(Product p : list){

%>



<tr>


<td>
<%= p.getProductName() %>
</td>


<td>
<%= p.getDescription() %>
</td>


<td>
<%= p.getPrice() %>円
</td>


<td>


<input form="cartForm<%=p.getProductId()%>"
       type="number"
       name="quantity"
       value="1"
       min="1">


</td>



<td>


<form id="cartForm<%=p.getProductId()%>"
      action="${pageContext.request.contextPath}/cart/add"
      method="post">


<input type="hidden"
       name="product_id"
       value="<%= p.getProductId() %>">


<input type="submit"
       value="カートに追加">


</form>


</td>


</tr>



<%

    }

}else{

%>


<tr>

<td colspan="5">

商品がありません

</td>

</tr>


<%

}

%>


</table>



<div class="box">

<a href="${pageContext.request.contextPath}/main/main.jsp">
戻る
</a>

</div>



</div>





<!-- 右側 現在の注文 -->

<div class="right">


<h3>現在の注文</h3>



<table>


<tr>

<th>商品名</th>
<th>数量</th>
<th>金額</th>

</tr>



<%

if(cart != null && !cart.isEmpty()){


    for(Order_Item item : cart){

%>



<tr>


<td>

<%= item.getProduct_name() %>

</td>


<td>

<%= item.getQuantity() %>

</td>


<td>

<%= item.getPrice() * item.getQuantity() %>円

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


<script>
document.getElementById("myset").addEventListener("submit",function(){
	alert("注文を確定しました。");
});
</script>

</div>


</div>



</div>



</body>

</html>