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

@WebServlet("/ProductInsertServlet")
public class ProductInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            String productName = request.getParameter("product_name");
            String categoryName = request.getParameter("category_name");
            int costPrice = Integer.parseInt(request.getParameter("cost_price"));
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");

            Product product = new Product();
            product.setProductName(productName);
            product.setCategoryName(categoryName);
            product.setCostPrice(costPrice);
            product.setPrice(price);
            product.setDescription(description);

            ProductDAO productDao = new ProductDAO();

            int productId = productDao.insert(product);

            RecipeDAO recipeDao = new RecipeDAO();

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

                    recipe.setProductId(productId);
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