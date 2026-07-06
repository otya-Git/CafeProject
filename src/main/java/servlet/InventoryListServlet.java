package servlet;

import java.io.IOException;
import java.util.List;

import bean.Inventory;
import dao.InventoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InventoryListServlet")
public class InventoryListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            InventoryDAO dao = new InventoryDAO();

            List<Inventory> list = dao.selectAll();

            request.setAttribute("list", list);

            request.getRequestDispatcher("/main/inventoryList.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }
    }
}