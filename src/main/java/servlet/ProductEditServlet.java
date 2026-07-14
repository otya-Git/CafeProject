package servlet;

import java.io.IOException;
import java.util.List;

import bean.Category;
import bean.Ingredient;
import bean.Product;
import bean.Recipe;
import dao.CategoryDAO;
import dao.IngredientDAO;
import dao.ProductDAO;
import dao.RecipeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductEditServlet")
public class ProductEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id =
                    Integer.parseInt(
                    request.getParameter("id"));

            ProductDAO productDao =
                    new ProductDAO();

            CategoryDAO categoryDao =
                    new CategoryDAO();

            IngredientDAO ingredientDao =
                    new IngredientDAO();

            RecipeDAO recipeDao =
                    new RecipeDAO();

            Product product =
                    productDao.selectById(id);

            List<Category> categoryList =
                    categoryDao.selectAll();

            List<Ingredient> ingredientList =
                    ingredientDao.selectAll();

            List<Recipe> recipeList =
                    recipeDao.selectByProductId(id);

            request.setAttribute(
                    "product", product);

            request.setAttribute(
                    "categoryList", categoryList);

            request.setAttribute(
                    "ingredientList", ingredientList);

            request.setAttribute(
                    "recipeList", recipeList);

            request.getRequestDispatcher(
                    "/main/productEdit.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }
    }
}

//package servlet;
//
//import java.io.IOException;
//
//import bean.Product;
//import dao.ProductDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/ProductEditServlet")
//public class ProductEditServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        try {
//
//            int id = Integer.parseInt(request.getParameter("id"));
//
//            ProductDAO dao = new ProductDAO();
//
//            Product product = dao.selectById(id);
//
//            request.setAttribute("product", product);
//
//            request.getRequestDispatcher("/main/productEdit.jsp")
//                   .forward(request, response);
//
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }
//}