package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Order_Item;

public class OrderItemDAO extends DAO {

    public void insert(int orderId, Order_Item item) throws Exception {

        Connection con = getConnection();

        String sql = "INSERT INTO order_item "
                   + "(order_id, product_id, quantity, price) "
                   + "VALUES (?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        st.setInt(1, orderId);
        st.setInt(2, item.getProduct_id());
        st.setInt(3, item.getQuantity());
        st.setInt(4, item.getPrice());

        st.executeUpdate();

        st.close();
        con.close();
    }
}