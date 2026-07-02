package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/order/list")
public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            OrderDAO dao = new OrderDAO();

            ArrayList<Order> orderList = dao.selectAll();

            request.setAttribute("orderList", orderList);

            request.getRequestDispatcher("/order/orderList.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}