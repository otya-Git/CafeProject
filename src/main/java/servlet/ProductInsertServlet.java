package servlet;

import java.io.IOException;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductInsertServlet")
public class ProductInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            String productName = request.getParameter("product_name");
            String categoryName = request.getParameter("category_name");
            int costPrice = Integer.parseInt(request.getParameter("cost_price"));
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");

            Product product = new Product();

            product.setProductName(productName);
            product.setCategoryName(categoryName);
            product.setCostPrice(costPrice);
            product.setPrice(price);
            product.setDescription(description);

            ProductDAO dao = new ProductDAO();

            dao.insert(product);

            response.sendRedirect("ProductListServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}