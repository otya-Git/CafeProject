package servlet;

import java.io.IOException;

import dao.OrderItemDAO;
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


            int orderItemId =
                Integer.parseInt(
                    request.getParameter("order_item_id")
                );



            String status =
                request.getParameter("status");




            OrderItemDAO dao =
                    new OrderItemDAO();



            dao.updateStatus(
                    orderItemId,
                    status
            );




            response.sendRedirect(
                request.getContextPath()
                + "/OrderServlet"
            );



        }catch(Exception e){


            e.printStackTrace();

            throw new ServletException(e);

        }


    }

}