package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO extends DAO {

    // 商品一覧
    public List<Product> selectAll() throws Exception {

        List<Product> list = new ArrayList<>();

        Connection con = getConnection();

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

    // 商品削除
    public void delete(int productId) throws Exception {

        Connection con = getConnection();

        String sql = "DELETE FROM product WHERE product_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, productId);

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    // 商品1件取得（編集用）
    public Product selectById(int productId) throws Exception {

        Product product = null;

        Connection con = getConnection();

        String sql = "SELECT * FROM product WHERE product_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            product = new Product();

            product.setProductId(rs.getInt("product_id"));
            product.setProductName(rs.getString("product_name"));
            product.setCostPrice(rs.getInt("cost_price"));
            product.setPrice(rs.getInt("price"));
            product.setDescription(rs.getString("description"));
            product.setCategoryName(rs.getString("category_name"));
        }

        rs.close();
        ps.close();
        con.close();

        return product;
    }

    // 商品更新（編集確定）
    public void update(Product p) throws Exception {

        Connection con = getConnection();

        String sql = "UPDATE product SET product_name=?, cost_price=?, price=?, description=?, category_name=? WHERE product_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, p.getProductName());
        ps.setInt(2, p.getCostPrice());
        ps.setInt(3, p.getPrice());
        ps.setString(4, p.getDescription());
        ps.setString(5, p.getCategoryName());
        ps.setInt(6, p.getProductId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }
    public int insert(Product product) throws Exception {

        Connection con = getConnection();

        String sql = "INSERT INTO product (product_name, cost_price, price, description, category_name) VALUES (?, ?, ?, ?, ?)";

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
}