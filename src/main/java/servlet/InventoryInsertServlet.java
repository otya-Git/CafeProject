package servlet;

import java.io.IOException;
import java.sql.Date;

import bean.Inventory;
import dao.InventoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InventoryInsertServlet")
public class InventoryInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            long productId =
                    Long.parseLong(request.getParameter("product_id"));

            long supplierId =
                    Long.parseLong(request.getParameter("supplier_id"));

            double stockQuantity =
                    Double.parseDouble(request.getParameter("stock_quantity"));

            String unit =
                    request.getParameter("unit");

            double reorderPoint =
                    Double.parseDouble(request.getParameter("reorder_point"));

            Date expiryDate =
                    Date.valueOf(request.getParameter("expiry_date"));

            Inventory inventory = new Inventory();

            inventory.setIngredientId(productId);
            inventory.setSupplierId(supplierId);
            inventory.setStockQuantity(stockQuantity);
            inventory.setUnit(unit);
            inventory.setReorderPoint(reorderPoint);
            inventory.setExpiryDate(expiryDate);

            InventoryDAO dao = new InventoryDAO();

            dao.insert(inventory);

            response.sendRedirect(
                    request.getContextPath() + "/InventoryListServlet");

        } catch (Exception e) {

            throw new ServletException(e);

        }

    }

}