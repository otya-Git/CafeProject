package servlet;


import java.io.IOException;

import dao.CafeTableDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/TableAddServlet")
public class TableAddServlet extends HttpServlet {


@Override
protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {


    try {


        CafeTableDAO dao =
                new CafeTableDAO();



        dao.insert();



        response.sendRedirect(
            request.getContextPath()
            + "/TableListServlet"
        );



    }catch(Exception e){

        throw new ServletException(e);

    }


}

}