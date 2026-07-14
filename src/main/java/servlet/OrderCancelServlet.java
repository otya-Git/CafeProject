package servlet;

import java.io.IOException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/OrderCancelServlet")
public class OrderCancelServlet extends HttpServlet {


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




            OrderDAO dao =
                    new OrderDAO();



            // キャンセル状態へ変更

            dao.updateOrderStatus(
                    orderId,
                    "キャンセル"
            );




            response.sendRedirect(
                request.getContextPath()
                + "/OrderServlet"
            );



        }catch(Exception e){


            throw new ServletException(e);


        }


    }

}