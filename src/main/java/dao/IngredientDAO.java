package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Ingredient;

public class IngredientDAO extends DAO {

    public List<Ingredient> selectAll() throws Exception {

        List<Ingredient> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement ps =
                con.prepareStatement(
                		"SELECT * FROM ingredient ORDER BY ingredient_id ASC");

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Ingredient ingredient = new Ingredient();

            ingredient.setIngredientId(
                    rs.getLong("ingredient_id"));

            ingredient.setIngredientName(
                    rs.getString("ingredient_name"));

            ingredient.setUnit(
                    rs.getString("unit"));

            list.add(ingredient);

        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
    
	  // 材料登録
	    public void insert(Ingredient ingredient) throws Exception {
	
	        Connection con = getConnection();
	
	        String sql =
	            "INSERT INTO ingredient " +
	            "(ingredient_name, unit) " +
	            "VALUES (?, ?)";
	
	        PreparedStatement ps =
	                con.prepareStatement(sql);
	
	        ps.setString(1,
	                ingredient.getIngredientName());
	
	        ps.setString(2,
	                ingredient.getUnit());
	
	        ps.executeUpdate();
	
	        ps.close();
	        con.close();

    }
	    
	 // 材料取得
	    public Ingredient selectById(int ingredientId)
	            throws Exception {

	        Ingredient ingredient = null;

	        Connection con = getConnection();

	        String sql =
	            "SELECT * FROM ingredient " +
	            "WHERE ingredient_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1, ingredientId);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()){

	            ingredient = new Ingredient();

	            ingredient.setIngredientId(
	                    rs.getInt("ingredient_id"));

	            ingredient.setIngredientName(
	                    rs.getString("ingredient_name"));

	            ingredient.setUnit(
	                    rs.getString("unit"));

	        }

	        rs.close();
	        ps.close();
	        con.close();

	        return ingredient;

	    }
	    
	 // 材料更新
	    public void update(Ingredient ingredient)
	            throws Exception {

	        Connection con = getConnection();

	        String sql =
	            "UPDATE ingredient " +
	            "SET ingredient_name=?, unit=? " +
	            "WHERE ingredient_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1,
	                ingredient.getIngredientName());

	        ps.setString(2,
	                ingredient.getUnit());

	        ps.setLong(3,
	                ingredient.getIngredientId());

	        ps.executeUpdate();

	        ps.close();
	        con.close();

	    }
	    
	 // 材料削除
	    public void delete(int ingredientId)
	            throws Exception {

	        Connection con = getConnection();

	        String sql =
	            "DELETE FROM ingredient " +
	            "WHERE ingredient_id=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setInt(1, ingredientId);

	        ps.executeUpdate();

	        ps.close();
	        con.close();

	    }

}