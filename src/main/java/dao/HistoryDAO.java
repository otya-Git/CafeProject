package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.History;

public class HistoryDAO extends DAO {

    public List<History> selectAll() throws Exception {

        List<History> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT o.order_id, o.table_id, " +
            "p.product_name, oi.quantity, " +
            "o.total_amount, o.payment_method, o.ordered_at " +
            "FROM \"order\" o " +
            "JOIN order_item oi ON o.order_id = oi.order_id " +
            "JOIN product p ON oi.product_id = p.product_id " +
            "WHERE o.status='会計済み' " +
            "ORDER BY o.ordered_at DESC";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            History h = new History();

            h.setOrderId(rs.getInt("order_id"));
            h.setTableId(rs.getInt("table_id"));
            h.setProductName(rs.getString("product_name"));
            h.setQuantity(rs.getInt("quantity"));
            h.setTotalAmount(rs.getInt("total_amount"));
            h.setPaymentMethod(rs.getString("payment_method"));
            h.setOrderedAt(rs.getTimestamp("ordered_at"));

            list.add(h);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
}