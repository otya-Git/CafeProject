package servlet;

import java.io.IOException;
import java.util.List;

import bean.Order_Item;
import dao.OrderDAO;
import dao.OrderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order/confirm")
public class OrderConfirmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {

            HttpSession session = request.getSession();


            // カート取得
            List<Order_Item> cart =
                    (List<Order_Item>) session.getAttribute("cart");


            if (cart != null && !cart.isEmpty()) {


                // 合計金額計算
                int total = 0;

                for (Order_Item item : cart) {

                    total += item.getPrice()
                           * item.getQuantity();

                }


                // order登録
                OrderDAO orderDAO = new OrderDAO();

                int orderId = orderDAO.insert(total);



                // order_item登録
                OrderItemDAO itemDAO = new OrderItemDAO();


                for (Order_Item item : cart) {

                    itemDAO.insert(orderId, item);

                }



                // カート削除
                session.removeAttribute("cart");

            }


            // 売上一覧へ
            response.sendRedirect(
                    request.getContextPath()
                    + "/OrderServlet"
            );


        } catch(Exception e) {

            throw new ServletException(e);

        }

    }
}