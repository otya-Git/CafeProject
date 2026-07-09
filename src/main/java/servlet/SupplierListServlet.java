package servlet;

import java.io.IOException;
import java.util.List;

import bean.Supplier;
import dao.SupplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/SupplierListServlet")
public class SupplierListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {

            SupplierDAO dao = new SupplierDAO();


            List<Supplier> supplierList =
                    dao.selectAll();


            request.setAttribute(
                    "supplierList",
                    supplierList);


            request.getRequestDispatcher(
                    "/main/supplier_list.jsp")
                    .forward(request, response);



        } catch(Exception e) {

            throw new ServletException(e);

        }

    }

}