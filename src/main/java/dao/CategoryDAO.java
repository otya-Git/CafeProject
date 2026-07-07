package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Category;

public class CategoryDAO extends DAO {

    public List<Category> selectAll() throws Exception {

        List<Category> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT * FROM category ORDER BY category_id";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Category c = new Category();

            c.setCategoryId(
                rs.getInt("category_id"));

            c.setCategoryName(
                rs.getString("category_name"));

            list.add(c);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

}