package servlet;

import java.io.IOException;
import java.util.List;

import bean.Category;
import bean.Ingredient; // IngredientListServletと同じインポート
import bean.Supplier;
import dao.CategoryDAO;
import dao.IngredientDAO; // IngredientListServletと同じインポート
import dao.SupplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InventoryAddServlet")
public class InventoryAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            IngredientDAO ingredientDao = new IngredientDAO(); // ProductDAOから変更
            SupplierDAO supplierDao = new SupplierDAO();
            CategoryDAO categoryDao = new CategoryDAO();

            List<Ingredient> ingredientList = ingredientDao.selectAll(); // 正しくselectAll()で取得
            List<Supplier> supplierList = supplierDao.selectAll();
            List<Category> categoryList = categoryDao.selectAll();

            request.setAttribute("ingredientList", ingredientList); // JSPへデータをパス
            request.setAttribute("supplierList", supplierList);
            request.setAttribute("categoryList", categoryList);

            request.getRequestDispatcher("/main/inventoryAdd.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            throw new ServletException(e);

        }
    }
}
