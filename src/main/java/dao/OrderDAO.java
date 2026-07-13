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


        String sql =
                "SELECT * FROM \"order\" ORDER BY order_id";


        PreparedStatement st =
                con.prepareStatement(sql);


        ResultSet rs =
                st.executeQuery();



        while (rs.next()) {

            Order o = new Order();


            o.setOrder_id(
                String.valueOf(
                    rs.getInt("order_id")
                )
            );


            o.setTable_id(
                String.valueOf(
                    rs.getInt("table_id")
                )
            );


            o.setStatus(
                rs.getString("status")
            );


            o.setPayment_method(
                rs.getString("payment_method")
            );



            if (rs.getTimestamp("ordered_at") != null) {

                o.setOrdered_at(
                    rs.getTimestamp("ordered_at")
                    .toLocalDateTime()
                );

            }


            list.add(o);

        }


        rs.close();
        st.close();
        con.close();


        return list;

    }





    // 注文登録
    public int insert(
            int tableId,
            String status,
            int totalAmount,
            String paymentMethod
    ) throws Exception {


        Connection con =
                getConnection();



        String sql =
            "INSERT INTO \"order\" "
          + "(table_id, status, total_amount, payment_method, ordered_at) "
          + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP) "
          + "RETURNING order_id";



        PreparedStatement st =
                con.prepareStatement(sql);



        // テーブル番号
        st.setInt(1, tableId);


        // 注文状態
        st.setString(2, status);


        // 合計金額
        st.setInt(3, totalAmount);


        // 支払方法
        st.setString(4, paymentMethod);



        ResultSet rs =
                st.executeQuery();



        int orderId = 0;



        if (rs.next()) {

            orderId =
                rs.getInt("order_id");

        }



        rs.close();
        st.close();
        con.close();



        return orderId;

    }
    
    public ArrayList<Order> selectByTableId(int tableId)
            throws Exception {


        ArrayList<Order> list =
                new ArrayList<>();


        Connection con = getConnection();


        String sql =
            "SELECT * FROM \"order\" "
          + "WHERE table_id=? "
          + "ORDER BY order_id DESC";


        PreparedStatement ps =
                con.prepareStatement(sql);


        ps.setInt(1, tableId);


        ResultSet rs =
                ps.executeQuery();


        while(rs.next()) {


            Order o = new Order();


            o.setOrder_id(
                String.valueOf(
                    rs.getInt("order_id")
                )
            );


            o.setStatus(
                rs.getString("status")
            );


            list.add(o);

        }


        rs.close();
        ps.close();
        con.close();


        return list;
    }
    
    public void updateStatus(
            int tableId,
            String status)
            throws Exception {


        Connection con = getConnection();


        String sql =
            "UPDATE \"order\" "
          + "SET status=? "
          + "WHERE table_id=? "
          + "AND status='注文中'";


        PreparedStatement st =
                con.prepareStatement(sql);


        st.setString(1,status);

        st.setInt(2,tableId);


        st.executeUpdate();


        st.close();

        con.close();

    }

}