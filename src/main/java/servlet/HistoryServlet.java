package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
            String dateParam = request.getParameter("date");
            String tableIdParam = request.getParameter("tableId");

            int recordsPerPage = 10;
            int currentPage = 1;
            if (request.getParameter("page") != null) {
                currentPage = Integer.parseInt(request.getParameter("page"));
            }

            HistoryDAO dao = new HistoryDAO();
            List<History> allList = dao.search(dateParam, tableIdParam);
            List<Integer> tableIds = dao.selectTableIds();

            int totalRecords = allList.size();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            if (totalPages == 0) {
                totalPages = 1;
            }

            int fromIndex = (currentPage - 1) * recordsPerPage;
            int toIndex = Math.min(fromIndex + recordsPerPage, totalRecords);

            List<History> pagedList = new ArrayList<>();
            if (fromIndex < totalRecords) {
                pagedList = allList.subList(fromIndex, toIndex);
            }

            request.setAttribute("historyList", pagedList);
            request.setAttribute("tableIds", tableIds);
            request.setAttribute("selectedDate", dateParam);
            request.setAttribute("selectedTableId", tableIdParam);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/main/history.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
