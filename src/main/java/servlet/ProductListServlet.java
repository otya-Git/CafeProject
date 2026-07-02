package servlet;

import java.io.IOException;
<<<<<<< HEAD
import java.util.List;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            ProductDAO dao = new ProductDAO();

            List<Product> list = dao.selectAll();

            request.setAttribute("list", list);

            request.getRequestDispatcher("/main/productList.jsp")
            .forward(request, response);

        } catch (Exception e) {
=======
import java.util.ArrayList;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            ProductDAO dao = new ProductDAO();

            ArrayList<Product> productList =
                    dao.selectAll();

            request.setAttribute("productList", productList);

            request.getRequestDispatcher(
                    "/product/productList.jsp")
                    .forward(request, response);

        } catch(Exception e) {
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
            throw new ServletException(e);
        }
    }
}