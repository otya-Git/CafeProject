package servlet;

import java.io.IOException;
import java.util.List;

import bean.Product;
import bean.Recipe;
import dao.ProductDAO;
import dao.RecipeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int productId =
                    Integer.parseInt(
                    request.getParameter("id"));

            ProductDAO productDao =
                    new ProductDAO();

            RecipeDAO recipeDao =
                    new RecipeDAO();

            Product product =
                    productDao.selectById(productId);

            List<Recipe> recipeList =
                    recipeDao.selectByProductId(productId);

            request.setAttribute("product", product);
            request.setAttribute("recipeList", recipeList);

            request.getRequestDispatcher(
                    "/main/productDetail.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}