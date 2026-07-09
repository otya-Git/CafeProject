<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="bean.Sales"%>

<%
List<Sales> list =
    (List<Sales>)request.getAttribute("list");
%>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/sales.css">

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>売上管理</title>
</head>

<body>

<h2>売上管理</h2>


<div class="container">


<!-- 左側 売上一覧 -->
<div class="left">

<h3>売上一覧</h3>


<table>

<tr>
    <th>商品名</th>
    <th>単価</th>
    <th>販売個数</th>
    <th>売上金額</th>
</tr>


<%
if(list != null && !list.isEmpty()){

    for(Sales s : list){
%>


<tr>

    <td>
        <%= s.getProductName() %>
    </td>


    <td>
        <%= s.getPrice() %>円
    </td>


    <td>
        <%= s.getQuantity() %>個
    </td>


    <td>
        <%= s.getSalesAmount() %>円
    </td>

</tr>


<%
    }

}else{
%>


<tr>
    <td colspan="4">
        売上データがありません
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



<!-- 右側 人気商品 -->
<div class="right">


<h3>人気商品 TOP3</h3>


<table>


<tr>
    <th>順位</th>
    <th>商品名</th>
    <th>販売個数</th>
</tr>


<%
if(list != null && !list.isEmpty()){

    int rank = 1;


    for(Sales s : list){


        if(rank > 3){
            break;
        }

%>


<tr>

    <td>
        <%= rank %>位
    </td>


    <td>
        <%= s.getProductName() %>
    </td>


    <td>
        <%= s.getQuantity() %>個
    </td>


</tr>


<%

        rank++;

    }


}else{

%>


<tr>

    <td colspan="3">
        データがありません
    </td>

</tr>


<%
}
%>


</table>


</div>


</div>


</body>

</html>

