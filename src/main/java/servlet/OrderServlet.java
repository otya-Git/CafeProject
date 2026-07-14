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


            // テーブル番号取得

            String tableParam =
                    request.getParameter("table_id");


            if(tableParam != null &&
               !tableParam.isEmpty()) {


                int tableId =
                        Integer.parseInt(tableParam);


                CafeTableDAO tableDAO =
                        new CafeTableDAO();


                tableDAO.updateStatus(
                        tableId,
                        "利用中"
                );


                session.setAttribute(
                        "tableId",
                        tableId
                );

            }



            Integer tableId =
                    (Integer)session.getAttribute("tableId");





            // カート取得（テーブル別）

            List<Order_Item> cart = null;


            if(tableId != null){

                cart =
                (List<Order_Item>)
                session.getAttribute(
                        "cart_" + tableId
                );

            }


            request.setAttribute(
                    "cart",
                    cart
            );





            // 商品取得

            ProductDAO productDAO =
                    new ProductDAO();


            String keyword =
                    request.getParameter("keyword");


            List<Product> list;


            if(keyword != null &&
               !keyword.isEmpty()){

                list =
                productDAO.search(keyword);

            }else{

                list =
                productDAO.selectAll();

            }


            request.setAttribute(
                    "list",
                    list
            );






            // 注文履歴（テーブル別）

            OrderItemDAO orderItemDAO =
                    new OrderItemDAO();


            List<Order_Item> historyList =
                    null;


            if(tableId != null){

                historyList =
                orderItemDAO.selectHistory(tableId);

            }


            request.setAttribute(
                    "historyList",
                    historyList
            );





            request.getRequestDispatcher(
                    "/main/Order.jsp"
            ).forward(request,response);



        }catch(Exception e){

            throw new ServletException(e);

        }

    }

}