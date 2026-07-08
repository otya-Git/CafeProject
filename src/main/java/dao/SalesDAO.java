package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Sales;

public class SalesDAO extends DAO {


    public List<Sales> getSalesList() throws Exception {


        List<Sales> list = new ArrayList<>();

        Connection con = getConnection();


        String sql =
            """
            SELECT
                p.product_name,
                oi.price,
                SUM(oi.quantity) AS quantity,
                SUM(oi.price * oi.quantity) AS sales_amount
            FROM order_item oi
            JOIN product p
            ON oi.product_id = p.product_id
            GROUP BY
                p.product_name,
                oi.price
            ORDER BY quantity DESC
            """;


        PreparedStatement st = con.prepareStatement(sql);

        ResultSet rs = st.executeQuery();


        while(rs.next()) {

            Sales sales = new Sales();

            sales.setProductName(
                rs.getString("product_name")
            );

            sales.setPrice(
                rs.getInt("price")
            );

            sales.setQuantity(
                rs.getInt("quantity")
            );

            sales.setSalesAmount(
                rs.getInt("sales_amount")
            );


            list.add(sales);
        }


        rs.close();
        st.close();
        con.close();


        return list;
    }
}