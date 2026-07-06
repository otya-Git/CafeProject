package servlet;

import java.io.IOException;
import java.util.List;

import bean.Order_Item;
import bean.Product;
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

    	List<Order_Item> cart =
    	        (List<Order_Item>) session.getAttribute("cart");

    	request.setAttribute("cart", cart);
        try {

            ProductDAO dao = new ProductDAO();

            // 商品一覧を取得
            List<Product> list = dao.selectAll();

            // JSPへ渡す
            request.setAttribute("list", list);

            // Order.jspへ遷移
            request.getRequestDispatcher("/main/Order.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}