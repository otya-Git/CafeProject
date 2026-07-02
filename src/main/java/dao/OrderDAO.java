package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Order;

public class OrderDAO extends DAO {

    // 全件取得
    public ArrayList<Order> selectAll() throws Exception {

        ArrayList<Order> list = new ArrayList<>();

        Connection con = getConnection();

        // 「order」は予約語なのでバッククォートで囲む
        String sql = "SELECT * FROM `order` ORDER BY order_id";

        PreparedStatement st = con.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Order o = new Order();

            o.setOrder_id(String.valueOf(rs.getInt("order_id")));
            o.setTable_id(String.valueOf(rs.getInt("table_id")));
            o.setStatus(rs.getString("status"));
            o.setPayment_method(rs.getString("payment_method"));

            if (rs.getTimestamp("ordered_at") != null) {
                o.setOrdered_at(
                    rs.getTimestamp("ordered_at").toLocalDateTime()
                );
            }

            list.add(o);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}