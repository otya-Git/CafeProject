<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>テーブル追加</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/tableadd.css">

</head>

<body>

<div class="container">

    <h2>テーブル追加</h2>

    <form action="${pageContext.request.contextPath}/TableAddServlet"
          method="post">

        <input type="submit"
               value="テーブルを追加">

    </form>

    <div class="button-area">

        <a href="${pageContext.request.contextPath}/main/main.jsp">
            戻る
        </a>

    </div>

</div>

</body>
</html>