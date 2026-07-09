package servlet;

import java.io.IOException;

import dao.IngredientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IngredientDeleteServlet")
public class IngredientDeleteServlet extends HttpServlet {

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                request.getParameter("id")
            );


            IngredientDAO dao = new IngredientDAO();

            dao.delete(id);


            response.sendRedirect(
                request.getContextPath()
                + "/IngredientListServlet"
            );


        } catch(Exception e) {

            throw new ServletException(e);

        }

    }

}