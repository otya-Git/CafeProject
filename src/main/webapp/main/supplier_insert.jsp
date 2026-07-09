<%@ page contentType="text/html; charset=UTF-8"%>

<%@include file="../header.jsp"%>


<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>仕入先登録</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/css/product.css">

</head>


<body>


<h2>🚚 仕入先登録</h2>


<div class="form-box">


<form action="${pageContext.request.contextPath}/SupplierInsertServlet"
      method="post">


<div class="form-group">

<label>仕入先名</label>

<input type="text"
       name="supplier_name"
       required>

</div>



<div class="form-group">

<label>電話番号</label>

<input type="text"
       name="phone">

</div>



<div class="form-group">

<label>住所</label>

<input type="text"
       name="address">

</div>



<div class="button-area">


<input type="submit"
       value="登録"
       class="btn-register">


<a href="${pageContext.request.contextPath}/SupplierListServlet"
   class="btn-back">

戻る

</a>


</div>


</form>


</div>


</body>

</html>