package servlet;

import java.io.IOException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

        	int productId = Integer.parseInt(request.getParameter("id"));

        	ProductDAO dao = new ProductDAO();
        	dao.delete(productId);

            response.sendRedirect(
                request.getContextPath() + "/OrderServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}