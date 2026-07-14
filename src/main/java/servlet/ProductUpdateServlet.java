package servlet;

import java.io.IOException;

import bean.Product;
import bean.Recipe;
import dao.ProductDAO;
import dao.RecipeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int cost = Integer.parseInt(request.getParameter("cost"));
            int price = Integer.parseInt(request.getParameter("price"));
            String desc = request.getParameter("description");
            String category = request.getParameter("category");

            Product p = new Product();
            p.setProductId(id);
            p.setProductName(name);
            p.setCostPrice(cost);
            p.setPrice(price);
            p.setDescription(desc);
            p.setCategoryName(category);

            ProductDAO productDao = new ProductDAO();
            productDao.update(p);

            RecipeDAO recipeDao = new RecipeDAO();

         // 一旦全部削除
         recipeDao.deleteByProductId(id);

         // 再登録
         String[] ingredientIds =
                 request.getParameterValues("ingredientId");

         String[] quantities =
                 request.getParameterValues("quantity");

         if (ingredientIds != null && quantities != null) {

             for (int i = 0; i < ingredientIds.length; i++) {

                 if (ingredientIds[i].isEmpty() ||
                     quantities[i].isEmpty()) {
                     continue;
                 }

                 Recipe recipe = new Recipe();

                 recipe.setProductId(id);
                 recipe.setIngredientId(
                         Integer.parseInt(ingredientIds[i]));
                 recipe.setQuantity(
                         Double.parseDouble(quantities[i]));

                 recipeDao.insert(recipe);

             }

         }

            response.sendRedirect(
                request.getContextPath() + "/ProductListServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}