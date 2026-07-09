package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import bean.Ingredient;
import bean.Inventory;
import bean.Supplier;
import dao.IngredientDAO;
import dao.InventoryDAO;
import dao.SupplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/InventoryInsertServlet")
public class InventoryInsertServlet extends HttpServlet {


    // 在庫登録画面表示
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // 材料一覧取得
            IngredientDAO ingredientDAO = new IngredientDAO();

            List<Ingredient> ingredientList =
                    ingredientDAO.selectAll();

            request.setAttribute(
                    "ingredientList",
                    ingredientList);


            // 仕入先一覧取得
            SupplierDAO supplierDAO = new SupplierDAO();

            List<Supplier> supplierList =
                    supplierDAO.selectAll();

            request.setAttribute(
                    "supplierList",
                    supplierList);


            // JSPへ移動
            request.getRequestDispatcher(
                    "/main2/inventory_insert.jsp")
                    .forward(request, response);


        } catch (Exception e) {

            throw new ServletException(e);

        }
    }



    // 在庫登録処理
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");


        try {


            long ingredientId =
                    Long.parseLong(
                    request.getParameter("ingredient_id"));


            long supplierId =
                    Long.parseLong(
                    request.getParameter("supplier_id"));


            double stockQuantity =
                    Double.parseDouble(
                    request.getParameter("stock_quantity"));


            String unit =
                    request.getParameter("unit");


            double reorderPoint =
                    Double.parseDouble(
                    request.getParameter("reorder_point"));


            Date expiryDate = null;

            String date =
                    request.getParameter("expiry_date");


            if(date != null && !date.equals("")) {

                expiryDate = Date.valueOf(date);

            }



            Inventory inventory = new Inventory();


            inventory.setIngredientId(ingredientId);

            inventory.setSupplierId(supplierId);

            inventory.setStockQuantity(stockQuantity);

            inventory.setUnit(unit);

            inventory.setReorderPoint(reorderPoint);

            inventory.setExpiryDate(expiryDate);



            InventoryDAO dao = new InventoryDAO();


            dao.insert(inventory);



            response.sendRedirect(
                    request.getContextPath()
                    + "/InventoryListServlet");



        } catch(Exception e) {


            throw new ServletException(e);


        }

    }

}

//package servlet;
//
//import java.io.IOException;
//import java.sql.Date;
//
//import bean.Inventory;
//import dao.InventoryDAO;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/InventoryInsertServlet")
//public class InventoryInsertServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request,
//            HttpServletResponse response)
//            throws ServletException, IOException {
//
//        request.setCharacterEncoding("UTF-8");
//
//        try {
//
//            long productId =
//                    Long.parseLong(request.getParameter("product_id"));
//
//            long supplierId =
//                    Long.parseLong(request.getParameter("supplier_id"));
//
//            double stockQuantity =
//                    Double.parseDouble(request.getParameter("stock_quantity"));
//
//            String unit =
//                    request.getParameter("unit");
//
//            double reorderPoint =
//                    Double.parseDouble(request.getParameter("reorder_point"));
//
//            Date expiryDate =
//                    Date.valueOf(request.getParameter("expiry_date"));
//
//            Inventory inventory = new Inventory();
//
//            inventory.setIngredientId(productId);
//            inventory.setSupplierId(supplierId);
//            inventory.setStockQuantity(stockQuantity);
//            inventory.setUnit(unit);
//            inventory.setReorderPoint(reorderPoint);
//            inventory.setExpiryDate(expiryDate);
//
//            InventoryDAO dao = new InventoryDAO();
//
//            dao.insert(inventory);
//
//            response.sendRedirect(
//                    request.getContextPath() + "/InventoryListServlet");
//
//        } catch (Exception e) {
//
//            throw new ServletException(e);
//
//        }
//
//    }
//
//}