package servlet;

import java.io.IOException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/OrderStatusServlet")
public class OrderStatusServlet extends HttpServlet {


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {


            // 注文ID取得

            int orderId =
                Integer.parseInt(
                    request.getParameter("order_id")
                );



            // 状態取得

            String status =
                request.getParameter("status");



            // 更新

            OrderDAO dao =
                    new OrderDAO();


            dao.updateOrderStatus(
                    orderId,
                    status
            );



            // 注文画面へ戻る

            response.sendRedirect(
                request.getContextPath()
                + "/OrderServlet"
            );



        }catch(Exception e){


            throw new ServletException(e);


        }

    }

}