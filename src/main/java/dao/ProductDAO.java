package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Product;

public class ProductDAO extends DAO {

    // 商品一覧取得
    public ArrayList<Product> selectAll() throws Exception {

        ArrayList<Product> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = "SELECT * FROM product ORDER BY product_id";

        PreparedStatement st = con.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Product p = new Product();

            p.setProductId(rs.getInt("product_id"));
            p.setProductName(rs.getString("product_name"));
            p.setCostPrice(rs.getInt("cost_price"));
            p.setPrice(rs.getInt("price"));
            p.setDescription(rs.getString("description"));
            p.setCategoryName(rs.getString("category_name"));

            list.add(p);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

}