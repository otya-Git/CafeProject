package servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // 商品IDと数量を取得
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // 商品情報を取得
            ProductDAO dao = new ProductDAO();
            Product product = dao.selectById(productId);

            // セッション取得
            HttpSession session = request.getSession();

            // カート取得
            List<Order_Item> cart =
                    (List<Order_Item>) session.getAttribute("cart");

            if (cart == null) {
                cart = new ArrayList<>();
            }

            // 注文商品作成
            Order_Item item = new Order_Item();
            item.setProduct_id(product.getProductId());
            item.setProduct_name(product.getProductName());
            item.setPrice(product.getPrice());
            item.setQuantity(quantity);

            // カートへ追加
            cart.add(item);

            // セッションへ保存
            session.setAttribute("cart", cart);

            // 注文画面へ戻る
            response.sendRedirect(request.getContextPath() + "/OrderServlet");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}