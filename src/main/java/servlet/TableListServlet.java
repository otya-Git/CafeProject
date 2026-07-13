package servlet;

import java.io.IOException;
import java.util.List;

import bean.CafeTable;
import dao.CafeTableDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TableListServlet")
public class TableListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CafeTableDAO dao = new CafeTableDAO();

            List<CafeTable> list = dao.selectAll();

            // JSPへ渡す
            request.setAttribute("tableList", list);

            // JSPへフォワード
            request.getRequestDispatcher("/main/tableList.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}