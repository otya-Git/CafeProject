package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Order_Item;
import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {


@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {


try{


int productId =
Integer.parseInt(request.getParameter("product_id"));


int quantity =
Integer.parseInt(request.getParameter("quantity"));



HttpSession session =
request.getSession();



Integer tableId =
(Integer)session.getAttribute("tableId");



if(tableId == null){

response.sendRedirect(
request.getContextPath()
+"/main/tableList.jsp");

return;

}



ProductDAO dao =
new ProductDAO();


Product product =
dao.selectById(productId);





List<Order_Item> cart =
(List<Order_Item>)
session.getAttribute(
"cart_"+tableId);



if(cart == null){

cart =
new ArrayList<>();

}





boolean exists=false;


for(Order_Item item:cart){


if(item.getProduct_id()
        == product.getProductId()){


item.setQuantity(
item.getQuantity()+quantity
);


exists=true;

break;

}

}





if(!exists){


Order_Item item =
new Order_Item();


item.setProduct_id(
product.getProductId());


item.setProduct_name(
product.getProductName());


item.setPrice(
product.getPrice());


item.setQuantity(
quantity);


cart.add(item);

}





session.setAttribute(
"cart_"+tableId,
cart
);





response.sendRedirect(
request.getContextPath()
+"/OrderServlet");



}catch(Exception e){

throw new ServletException(e);

}


}

}