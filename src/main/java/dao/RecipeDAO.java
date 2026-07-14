package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Recipe;

public class RecipeDAO extends DAO {

    // レシピ登録
    public void insert(Recipe recipe) throws Exception {

        Connection con = getConnection();

        String sql =
            "INSERT INTO recipe " +
            "(product_id, ingredient_id, quantity) " +
            "VALUES (?, ?, ?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, recipe.getProductId());
        ps.setInt(2, recipe.getIngredientId());
        ps.setDouble(3, recipe.getQuantity());

        ps.executeUpdate();

        ps.close();
        con.close();

    }

    // 商品ごとのレシピ取得
    public List<Recipe> selectByProductId(long productId) throws Exception {

        List<Recipe> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "SELECT r.*, i.ingredient_name, i.unit " +
            "FROM recipe r " +
            "JOIN ingredient i " +
            "ON r.ingredient_id = i.ingredient_id " +
            "WHERE r.product_id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, productId);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Recipe recipe = new Recipe();

            recipe.setRecipeId(rs.getLong("recipe_id"));
            recipe.setProductId(rs.getLong("product_id"));
            recipe.setIngredientId(rs.getInt("ingredient_id"));
            recipe.setQuantity(rs.getDouble("quantity"));

            recipe.setIngredientName(
                    rs.getString("ingredient_name"));

            recipe.setUnit(
                    rs.getString("unit"));

            list.add(recipe);

        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }
    
	 // 商品のレシピ削除
	    public void deleteByProductId(long productId) throws Exception {
	
	        Connection con = getConnection();
	
	        String sql =
	            "DELETE FROM recipe WHERE product_id=?";
	
	        PreparedStatement ps =
	                con.prepareStatement(sql);
	
	        ps.setLong(1, productId);
	
	        ps.executeUpdate();
	
	        ps.close();
	        con.close();
	
	    }

}