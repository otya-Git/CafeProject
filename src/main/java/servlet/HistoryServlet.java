package servlet;

import java.io.IOException;
import java.util.List;

import bean.History;
import dao.HistoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HistoryDAO dao = new HistoryDAO();

            List<History> list = dao.selectAll();

            request.setAttribute("historyList", list);

            request.getRequestDispatcher("/main/history.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }
    }
}