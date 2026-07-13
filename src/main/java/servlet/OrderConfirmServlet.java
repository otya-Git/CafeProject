package servlet;

import java.io.IOException;
import java.util.List;

import bean.Order_Item;
import bean.Recipe;
import dao.InventoryDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.RecipeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order/confirm")
public class OrderConfirmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {

            HttpSession session = request.getSession();


            // カート取得
            List<Order_Item> cart =
                    (List<Order_Item>) session.getAttribute("cart");


            if (cart != null && !cart.isEmpty()) {


                // 合計金額計算
                int total = 0;


                for (Order_Item item : cart) {

                    total += item.getPrice()
                           * item.getQuantity();

                }



                // テーブル番号取得
                Integer tableId =
                        (Integer) session.getAttribute("tableId");



                // order登録
                OrderDAO orderDAO = new OrderDAO();


                int orderId = orderDAO.insert(
                        tableId,
                        "注文中",
                        total,
                        "未払い"
                );



                // order_item登録
                OrderItemDAO itemDAO = new OrderItemDAO();

                RecipeDAO recipeDAO = new RecipeDAO();

                InventoryDAO inventoryDAO = new InventoryDAO();



                for (Order_Item item : cart) {


                    // 注文明細登録
                    itemDAO.insert(
                            orderId,
                            item
                    );



                    // レシピ取得
                    List<Recipe> recipes =
                            recipeDAO.selectByProductId(
                                    item.getProduct_id()
                            );



                    // 材料在庫減少
                    for (Recipe recipe : recipes) {


                        double useQuantity =
                                recipe.getQuantity()
                                * item.getQuantity();



                        inventoryDAO.decrease(
                                recipe.getIngredientId(),
                                useQuantity
                        );

                    }

                }



                // カート削除
                session.removeAttribute("cart");

            }



            // 注文画面へ戻る
            response.sendRedirect(
                    request.getContextPath()
                    + "/OrderServlet"
            );


        } catch(Exception e) {

            e.printStackTrace();
            throw new ServletException(e);

        }

    }
}