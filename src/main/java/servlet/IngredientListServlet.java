package servlet;

import java.io.IOException;
import java.util.List;

import bean.Ingredient;
import dao.IngredientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IngredientListServlet")
public class IngredientListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            IngredientDAO dao = new IngredientDAO();

            List<Ingredient> list = dao.selectAll();

            request.setAttribute("list", list);

            request.getRequestDispatcher("/main/ingredientList.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}