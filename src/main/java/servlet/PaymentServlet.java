package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Order;
import bean.Order_Item;
import bean.Recipe;
import dao.CafeTableDAO;
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


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {


    // ==========================
    // 会計画面表示
    // ==========================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {


            HttpSession session =
                    request.getSession();


            Integer tableId =
                (Integer)session.getAttribute(
                        "tableId"
                );



            List<Order_Item> cart =
                    new ArrayList<>();



            Order order = null;



            if(tableId != null){


                OrderDAO orderDAO =
                        new OrderDAO();


                OrderItemDAO itemDAO =
                        new OrderItemDAO();



                // テーブルの未会計注文を全部取得

                ArrayList<Order> orders =
                        orderDAO.selectActiveOrders(
                                tableId
                        );



                int total = 0;



                for(Order o : orders){



                    int orderId =
                        Integer.parseInt(
                            o.getOrder_id()
                        );



                    List<Order_Item> items =
                        itemDAO.selectByOrderId(
                                orderId
                        );



                    cart.addAll(items);



                    total +=
                        o.getTotal_amount();

                }



                // 表示用

                request.setAttribute(
                        "cart",
                        cart
                );


                request.setAttribute(
                        "total",
                        total
                );

            }



            request.getRequestDispatcher(
                    "/main/Payment.jsp"
            )
            .forward(
                    request,
                    response
            );



        }catch(Exception e){

            throw new ServletException(e);

        }

    }





    // ==========================
    // 会計確定
    // ==========================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        try {


            HttpSession session =
                    request.getSession();



            Integer tableId =
                (Integer)session.getAttribute(
                        "tableId"
                );



            String paymentMethod =
                request.getParameter(
                        "payment_method"
                );




            if(tableId != null){



                OrderDAO orderDAO =
                        new OrderDAO();


                OrderItemDAO itemDAO =
                        new OrderItemDAO();



                // 未会計注文を全部取得

                ArrayList<Order> orders =
                        orderDAO.selectActiveOrders(
                                tableId
                        );



                RecipeDAO recipeDAO =
                        new RecipeDAO();


                InventoryDAO inventoryDAO =
                        new InventoryDAO();




                // ======================
                // 在庫減少
                // ======================

                for(Order order : orders){



                    int orderId =
                        Integer.parseInt(
                            order.getOrder_id()
                        );



                    List<Order_Item> items =
                        itemDAO.selectByOrderId(
                                orderId
                        );



                    for(Order_Item item : items){



                        List<Recipe> recipes =
                            recipeDAO.selectByProductId(
                                    item.getProduct_id()
                            );



                        for(Recipe recipe : recipes){



                            double quantity =
                                recipe.getQuantity()
                                *
                                item.getQuantity();



                            inventoryDAO.decrease(
                                    recipe.getIngredientId(),
                                    quantity
                            );

                        }

                    }



                }




                // ======================
                // 全注文を会計済みに変更
                // ======================

                orderDAO.updatePayment(
                        tableId,
                        "会計済み",
                        paymentMethod
                );




                // 席を空席へ

                CafeTableDAO tableDAO =
                        new CafeTableDAO();


                tableDAO.updateStatus(
                        tableId,
                        "空席"
                );



                session.removeAttribute(
                        "tableId"
                );


            }



            response.sendRedirect(
                    request.getContextPath()
                    + "/main/main.jsp"
            );



        }catch(Exception e){

            e.printStackTrace();

            throw new ServletException(e);

        }


    }

}