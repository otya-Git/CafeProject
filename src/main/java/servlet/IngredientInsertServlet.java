package servlet;

import java.io.IOException;

import bean.Ingredient;
import dao.IngredientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IngredientInsertServlet")
public class IngredientInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            Ingredient ingredient = new Ingredient();

            ingredient.setIngredientName(
                    request.getParameter("ingredient_name"));

            ingredient.setUnit(
                    request.getParameter("unit"));

            IngredientDAO dao = new IngredientDAO();

            dao.insert(ingredient);

            response.sendRedirect(
                    request.getContextPath()
                    + "/IngredientListServlet");

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}