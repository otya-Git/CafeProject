package servlet;

import java.io.IOException;
import java.util.List;

import bean.Order_Item;
import bean.Product;
import dao.CafeTableDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();


        try {


            // ==========================
            // テーブル番号取得
            // ==========================

            String tableParam =
                    request.getParameter("table_id");



            if(tableParam != null &&
               !tableParam.isEmpty()) {


                int tableId =
                        Integer.parseInt(tableParam);



                // 席状態変更

                CafeTableDAO tableDAO =
                        new CafeTableDAO();


                tableDAO.updateStatus(
                        tableId,
                        "利用中"
                );



                // セッション保存

                session.setAttribute(
                        "tableId",
                        tableId
                );


                System.out.println(
                    "保存したテーブル番号：" + tableId
                );

            }





            // ==========================
            // カート取得
            // ==========================


            List<Order_Item> cart =
                    (List<Order_Item>)
                    session.getAttribute("cart");



            request.setAttribute(
                    "cart",
                    cart
            );






            // ==========================
            // 商品取得
            // ==========================


            ProductDAO productDAO =
                    new ProductDAO();



            String keyword =
                    request.getParameter("keyword");



            List<Product> list;



            if(keyword != null &&
               !keyword.trim().isEmpty()) {



                list =
                    productDAO.search(keyword);



            } else {



                list =
                    productDAO.selectAll();

            }




            request.setAttribute(
                    "list",
                    list
            );



            request.setAttribute(
                    "keyword",
                    keyword
            );







            // ==========================
            // 注文履歴取得
            // ==========================


            OrderItemDAO orderItemDAO =
                    new OrderItemDAO();



            List<Order_Item> historyList =
                    orderItemDAO.selectHistory();




            request.setAttribute(
                    "historyList",
                    historyList
            );







            // ==========================
            // 注文画面へ
            // ==========================


            request.getRequestDispatcher(
                    "/main/Order.jsp"
            ).forward(request, response);




        } catch(Exception e) {


            throw new ServletException(e);


        }

    }

}