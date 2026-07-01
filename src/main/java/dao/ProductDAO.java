package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO {

    // 商品登録
    public int insert(Product product) throws Exception {

        Connection con = new DAO().getConnection();

        String sql = "INSERT INTO product "
                + "(product_name, cost_price, price, description, category_name) "
                + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, product.getProductName());
        ps.setInt(2, product.getCostPrice());
        ps.setInt(3, product.getPrice());
        ps.setString(4, product.getDescription());
        ps.setString(5, product.getCategoryName());

        int result = ps.executeUpdate();

        ps.close();
        con.close();

        return result;
    }

    // 商品一覧
    public List<Product> selectAll() throws Exception {

        List<Product> list = new ArrayList<>();

        Connection con = new DAO().getConnection();

        String sql = "SELECT * FROM product ORDER BY product_id";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Product product = new Product();

            product.setProductId(rs.getInt("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setCostPrice(rs.getInt("cost_price"));
            product.setPrice(rs.getInt("price"));
            product.setDescription(rs.getString("description"));
            product.setCategoryName(rs.getString("category_name"));

            list.add(product);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
}