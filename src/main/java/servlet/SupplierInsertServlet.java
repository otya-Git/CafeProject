package servlet;

import java.io.IOException;

import bean.Supplier;
import dao.SupplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/SupplierInsertServlet")
public class SupplierInsertServlet extends HttpServlet {


    // 登録画面表示
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        request.getRequestDispatcher(
                "/main/supplier_insert.jsp")
                .forward(request, response);

    }



    // 登録処理
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");


        try {


            String supplierName =
                    request.getParameter("supplier_name");


            String phone =
                    request.getParameter("phone");


            String address =
                    request.getParameter("address");



            Supplier supplier = new Supplier();


            supplier.setSupplierName(supplierName);

            supplier.setPhone(phone);

            supplier.setAddress(address);



            SupplierDAO dao =
                    new SupplierDAO();


            dao.insert(supplier);



            response.sendRedirect(
                    request.getContextPath()
                    + "/SupplierListServlet");



        } catch(Exception e) {


            throw new ServletException(e);

        }

    }

}