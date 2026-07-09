package servlet;

import java.io.IOException;

import bean.Ingredient;
import dao.IngredientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IngredientEditServlet")
public class IngredientEditServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {

            int ingredientId =
                Integer.parseInt(
                    request.getParameter("id")
                );


            IngredientDAO dao =
                new IngredientDAO();


            Ingredient ingredient =
                dao.selectById(ingredientId);


            request.setAttribute(
                "ingredient",
                ingredient
            );


            request.getRequestDispatcher(
                "/main/ingredientEdit.jsp"
            )
            .forward(request,response);


        } catch(Exception e){

            throw new ServletException(e);

        }

    }

}