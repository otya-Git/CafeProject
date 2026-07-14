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


            o.setTotal_amount(
                rs.getInt("total_amount")
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



        st.setInt(1, tableId);

        st.setString(2, status);

        st.setInt(3, totalAmount);

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






    // テーブル別注文取得
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






    // テーブル全注文状態変更
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







    // 会計処理
    // 注文中・提供完了を会計済みに変更
    public void updatePayment(
            int tableId,
            String status,
            String paymentMethod)
            throws Exception {


        Connection con = getConnection();



        String sql =
            "UPDATE \"order\" "
          + "SET status=?, payment_method=? "
          + "WHERE table_id=? "
          + "AND status!='会計済み' "
          + "AND status!='キャンセル'";



        PreparedStatement st =
                con.prepareStatement(sql);



        st.setString(1,status);

        st.setString(2,paymentMethod);

        st.setInt(3,tableId);



        st.executeUpdate();



        st.close();

        con.close();

    }








    // 1件状態変更
    public void updateOrderStatus(
            int orderId,
            String status)
            throws Exception {


        Connection con = getConnection();



        String sql =
            "UPDATE \"order\" "
          + "SET status=? "
          + "WHERE order_id=?";



        PreparedStatement st =
                con.prepareStatement(sql);



        st.setString(1,status);

        st.setInt(2,orderId);



        st.executeUpdate();



        st.close();

        con.close();

    }








    // 現在注文中の注文取得（1件）
    public Order selectActiveOrder(int tableId)
            throws Exception {


        Connection con = getConnection();



        String sql =
            "SELECT * FROM \"order\" "
          + "WHERE table_id=? "
          + "AND status!='会計済み' "
          + "AND status!='キャンセル' "
          + "ORDER BY order_id DESC "
          + "LIMIT 1";



        PreparedStatement st =
                con.prepareStatement(sql);



        st.setInt(1,tableId);



        ResultSet rs =
                st.executeQuery();



        Order order = null;



        if(rs.next()){


            order = new Order();


            order.setOrder_id(
                String.valueOf(
                    rs.getInt("order_id")
                )
            );


            order.setTotal_amount(
                rs.getInt("total_amount")
            );


        }



        rs.close();

        st.close();

        con.close();



        return order;

    }







    // 現在会計対象の注文取得（複数）
    public ArrayList<Order> selectActiveOrders(int tableId)
            throws Exception {


        ArrayList<Order> list =
                new ArrayList<>();


        Connection con = getConnection();



        String sql =
            "SELECT * FROM \"order\" "
          + "WHERE table_id=? "
          + "AND status!='会計済み' "
          + "AND status!='キャンセル' "
          + "ORDER BY order_id";



        PreparedStatement ps =
                con.prepareStatement(sql);



        ps.setInt(1,tableId);



        ResultSet rs =
                ps.executeQuery();



        while(rs.next()) {


            Order o = new Order();



            o.setOrder_id(
                String.valueOf(
                    rs.getInt("order_id")
                )
            );



            o.setTotal_amount(
                rs.getInt("total_amount")
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


}