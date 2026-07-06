package servlet;

import java.io.IOException;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int cost = Integer.parseInt(request.getParameter("cost"));
            int price = Integer.parseInt(request.getParameter("price"));
            String desc = request.getParameter("description");
            String category = request.getParameter("category");

            Product p = new Product();
            p.setProductId(id);
            p.setProductName(name);
            p.setCostPrice(cost);
            p.setPrice(price);
            p.setDescription(desc);
            p.setCategoryName(category);

            ProductDAO dao = new ProductDAO();
            dao.update(p);

            response.sendRedirect(
                request.getContextPath() + "/ProductListServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}