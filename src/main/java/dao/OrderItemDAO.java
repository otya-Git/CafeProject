package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Order_Item;

public class OrderItemDAO extends DAO {


    // 注文登録
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



    // 注文履歴取得
 // 注文履歴取得（テーブル別）
    public List<Order_Item> selectHistory(int tableId) throws Exception {


        List<Order_Item> list =
                new ArrayList<>();


        Connection con =
                getConnection();



        String sql =
              "SELECT "
            + "oi.order_id, "
            + "p.product_name, "
            + "oi.quantity, "
            + "oi.price, "
            + "o.status "
            + "FROM order_item oi "
            + "JOIN product p "
            + "ON oi.product_id = p.product_id "
            + "JOIN \"order\" o "
            + "ON oi.order_id = o.order_id "
            + "WHERE o.table_id = ? "
            + "AND o.status != '会計済み' "
            + "ORDER BY oi.order_id DESC";



        PreparedStatement st =
                con.prepareStatement(sql);



        st.setInt(1, tableId);



        ResultSet rs =
                st.executeQuery();



        while(rs.next()) {


            Order_Item item =
                    new Order_Item();


            item.setOrder_id(
                String.valueOf(
                    rs.getInt("order_id")
                )
            );


            item.setProduct_name(
                rs.getString("product_name")
            );


            item.setQuantity(
                rs.getInt("quantity")
            );


            item.setPrice(
                rs.getInt("price")
            );


            item.setStatus(
                rs.getString("status")
            );



            list.add(item);

        }



        rs.close();

        st.close();

        con.close();



        return list;

    }

}