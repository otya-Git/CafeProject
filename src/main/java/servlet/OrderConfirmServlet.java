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


            HttpSession session =
                    request.getSession();



            // テーブル番号取得

            Integer tableId =
                    (Integer) session.getAttribute("tableId");



            if(tableId == null){

                response.sendRedirect(
                    request.getContextPath()
                    + "/main/tableList.jsp"
                );

                return;
            }





            // テーブルごとのカート取得

            List<Order_Item> cart =
                (List<Order_Item>)
                session.getAttribute(
                    "cart_" + tableId
                );




            if(cart != null && !cart.isEmpty()) {



                // 合計金額

                int total = 0;


                for(Order_Item item : cart){


                    total +=
                    item.getPrice()
                    * item.getQuantity();

                }




                // 注文登録

                OrderDAO orderDAO =
                        new OrderDAO();


                int orderId =
                    orderDAO.insert(
                        tableId,
                        "注文中",
                        total,
                        "未払い"
                    );





                // 注文明細登録

                OrderItemDAO itemDAO =
                        new OrderItemDAO();


                RecipeDAO recipeDAO =
                        new RecipeDAO();


                InventoryDAO inventoryDAO =
                        new InventoryDAO();




                for(Order_Item item : cart){



                    itemDAO.insert(
                            orderId,
                            item
                    );





                    // レシピ取得

                    List<Recipe> recipes =
                        recipeDAO.selectByProductId(
                            item.getProduct_id()
                        );





                    // 在庫減少

                    for(Recipe recipe : recipes){



                        double useQuantity =
                            recipe.getQuantity()
                            * item.getQuantity();



                        inventoryDAO.decrease(
                            recipe.getIngredientId(),
                            useQuantity
                        );


                    }


                }




                // テーブル専用カート削除

                session.removeAttribute(
                    "cart_" + tableId
                );


            }




            response.sendRedirect(
                request.getContextPath()
                + "/OrderServlet"
            );



        }catch(Exception e){


            e.printStackTrace();

            throw new ServletException(e);


        }


    }

}