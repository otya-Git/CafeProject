<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Product"%>

<%
ArrayList<Product> productList =
(ArrayList<Product>)request.getAttribute("productList");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文画面</title>

<style>

.container{
    display:flex;
    gap:30px;
}

.left{
    width:60%;
}

.right{
    width:40%;
}

</style>

</head>


<body>


<h2>注文</h2>


<br><br>



<div class="container">



<!-- 左側 商品一覧 -->

<div class="left">


<h3>商品一覧</h3>


<table border="1">


<tr>

<th>商品名</th>
<th>価格</th>
<th>数量</th>
<th>注文</th>
<th>管理</th>

</tr>



<%

if(productList != null){


    for(Product p : productList){

%>


<tr>



<td>

<%= p.getProductName() %>

</td>



<td>

<%= p.getPrice() %>円

</td>




<td>


<form action="${pageContext.request.contextPath}/cart/add"
      method="post">



<input type="hidden"
       name="product_id"
       value="<%= p.getProductId() %>">



<input type="number"
       name="quantity"
       value="1"
       min="1">


</td>





<td>


<input type="submit"
       value="カートに追加">



</form>


</td>





<!-- 商品管理 -->

<td>


<a href="${pageContext.request.contextPath}/product/edit?id=<%=p.getProductId()%>">

編集

</a>


<br>


<a href="${pageContext.request.contextPath}/product/delete?id=<%=p.getProductId()%>"
onclick="return confirm('削除しますか？');">

削除

</a>



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
</div>

<!-- 右側 カート -->

<div class="right">


<h3>現在の注文</h3>



<table border="1">


<tr>

<th>商品名</th>
<th>数量</th>
<th>金額</th>

</tr>


<!-- 後でカート内容表示 -->



</table>



</div>




</div>



</body>


</html>