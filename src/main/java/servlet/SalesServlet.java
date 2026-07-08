package servlet;

import java.io.IOException;
import java.util.List;

import bean.Sales;
import dao.SalesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SalesServlet")
public class SalesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {

            SalesDAO dao = new SalesDAO();

            List<Sales> list = dao.getSalesList();

            request.setAttribute("list", list);

            request.getRequestDispatcher("/main/Sales.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}