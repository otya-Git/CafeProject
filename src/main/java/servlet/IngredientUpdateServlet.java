package servlet;

import java.io.IOException;

import bean.Ingredient;
import dao.IngredientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IngredientUpdateServlet")
public class IngredientUpdateServlet extends HttpServlet {


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {


            request.setCharacterEncoding("UTF-8");



            Ingredient ingredient =
                    new Ingredient();



            ingredient.setIngredientId(
                Integer.parseInt(
                request.getParameter("ingredientId"))
            );



            ingredient.setIngredientName(
                request.getParameter("ingredientName")
            );



            ingredient.setUnit(
                request.getParameter("unit")
            );



            IngredientDAO dao =
                    new IngredientDAO();



            dao.update(ingredient);



            response.sendRedirect(
                request.getContextPath()
                + "/IngredientListServlet"
            );



        } catch(Exception e) {


            throw new ServletException(e);


        }

    }

}