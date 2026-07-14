package servlet;

import java.io.IOException;

import dao.CafeTableDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {


    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session =
                request.getSession();


        try {


            Integer tableId =
                (Integer)session.getAttribute("tableId");



            if(tableId != null){


                // 注文を会計済みに変更

                OrderDAO orderDAO =
                        new OrderDAO();


                orderDAO.updateStatus(
                        tableId,
                        "会計済み"
                );



                // 席を空席へ

                CafeTableDAO tableDAO =
                        new CafeTableDAO();


                tableDAO.updateStatus(
                        tableId,
                        "空席"
                );



                // カート削除

                session.removeAttribute("cart_" + tableId);

            }



            response.sendRedirect(
                request.getContextPath()
                + "/main/main.jsp"
            );



        }catch(Exception e){

            throw new ServletException(e);

        }

    }

}