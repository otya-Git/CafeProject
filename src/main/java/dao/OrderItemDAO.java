package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Order_Item;


public class OrderItemDAO extends DAO {



    // 注文明細登録
    public void insert(
            int orderId,
            Order_Item item)
            throws Exception {


        Connection con = getConnection();


        String sql =
            "INSERT INTO order_item "
          + "(order_id, product_id, quantity, price, status) "
          + "VALUES (?, ?, ?, ?, ?)";


        PreparedStatement st =
                con.prepareStatement(sql);


        st.setInt(1, orderId);

        st.setInt(2, item.getProduct_id());

        st.setInt(3, item.getQuantity());

        st.setInt(4, item.getPrice());

        st.setString(5, "注文中");


        st.executeUpdate();


        st.close();
        con.close();

    }







    // 注文履歴取得

    public List<Order_Item> selectHistory(int tableId)
            throws Exception {


        List<Order_Item> list =
                new ArrayList<>();


        Connection con =
                getConnection();



        String sql =
            "SELECT "
          + "oi.order_item_id,"
          + "oi.order_id,"
          + "oi.product_id,"
          + "p.product_name,"
          + "oi.quantity,"
          + "oi.price,"
          + "oi.status "
          + "FROM order_item oi "
          + "JOIN product p "
          + "ON oi.product_id=p.product_id "
          + "JOIN \"order\" o "
          + "ON oi.order_id=o.order_id "
          + "WHERE o.table_id=? "
          + "AND o.status!='会計済み' "
          + "AND oi.status!='キャンセル' "
          + "ORDER BY oi.order_id DESC";



        PreparedStatement st =
                con.prepareStatement(sql);


        st.setInt(1, tableId);



        ResultSet rs =
                st.executeQuery();



        while(rs.next()){


            Order_Item item =
                    new Order_Item();



            item.setOrder_item_id(
                String.valueOf(
                    rs.getInt("order_item_id")
                )
            );


            item.setOrder_id(
                String.valueOf(
                    rs.getInt("order_id")
                )
            );


            item.setProduct_id(
                rs.getInt("product_id")
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








    // 注文IDから明細取得

    public List<Order_Item> selectByOrderId(int orderId)
            throws Exception {


        List<Order_Item> list =
                new ArrayList<>();


        Connection con =
                getConnection();



        String sql =
        	    "SELECT "
        	  + "oi.order_item_id,"
        	  + "oi.product_id,"
        	  + "p.product_name,"
        	  + "oi.quantity,"
        	  + "oi.price,"
        	  + "oi.status "
        	  + "FROM order_item oi "
        	  + "JOIN product p "
        	  + "ON oi.product_id=p.product_id "
        	  + "WHERE oi.order_id=? "
        	  + "AND oi.status!='キャンセル'";



        PreparedStatement st =
                con.prepareStatement(sql);


        st.setInt(1,orderId);



        ResultSet rs =
                st.executeQuery();



        while(rs.next()){


            Order_Item item =
                    new Order_Item();



            item.setOrder_item_id(
                String.valueOf(
                    rs.getInt("order_item_id")
                )
            );


            item.setProduct_id(
                rs.getInt("product_id")
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

 // 商品1件状態変更

    public void updateStatus(
            int orderItemId,
            String status)
            throws Exception {


        Connection con =
                getConnection();



        String sql =
            "UPDATE order_item "
          + "SET status=? "
          + "WHERE order_item_id=?";



        PreparedStatement st =
                con.prepareStatement(sql);



        st.setString(1,status);

        st.setInt(2,orderItemId);



        st.executeUpdate();



        st.close();

        con.close();

    }
 // 会計用 合計金額取得
    public int getPaymentTotal(int tableId)
            throws Exception {


        int total = 0;


        Connection con = getConnection();


        String sql =
            "SELECT "
          + "COALESCE(SUM(oi.price * oi.quantity),0) AS total "
          + "FROM order_item oi "
          + "JOIN \"order\" o "
          + "ON oi.order_id=o.order_id "
          + "WHERE o.table_id=? "
          + "AND o.status!='会計済み' "
          + "AND o.status!='キャンセル' "
          + "AND oi.status!='キャンセル'";


        PreparedStatement st =
                con.prepareStatement(sql);


        st.setInt(1, tableId);


        ResultSet rs =
                st.executeQuery();


        if(rs.next()){

            total =
                rs.getInt("total");

        }


        rs.close();
        st.close();
        con.close();


        return total;

    }
}