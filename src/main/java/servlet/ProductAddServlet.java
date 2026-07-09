package servlet;

import java.io.IOException;
import java.util.List;

import bean.Category;
import bean.Ingredient;
import dao.CategoryDAO;
import dao.IngredientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductAddServlet")
public class ProductAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            CategoryDAO categoryDao = new CategoryDAO();
            IngredientDAO ingredientDao = new IngredientDAO();

            List<Category> categoryList = categoryDao.selectAll();
            List<Ingredient> ingredientList = ingredientDao.selectAll();

            request.setAttribute("categoryList", categoryList);
            request.setAttribute("ingredientList", ingredientList);

            request.getRequestDispatcher("/main/productAdd.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}