package servlet;

import java.io.IOException;
import java.util.List;

import bean.Product;
import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String category = request.getParameter("category");

            ProductDAO productDao = new ProductDAO();
            CategoryDAO categoryDao = new CategoryDAO();

            List<Product> list;

            if (category == null || category.isEmpty()) {

                list = productDao.selectAll();

            } else {

                list = productDao.selectCategory(category);

            }

            request.setAttribute("list", list);
            request.setAttribute("categoryList", categoryDao.selectAll());

            request.getRequestDispatcher("/main/productList.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}