package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Inventory;

public class InventoryDAO extends DAO {

    // 在庫一覧
    public List<Inventory> selectAll() throws Exception {

        List<Inventory> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
        	    "SELECT i.inventory_id, i.product_id, i.supplier_id, " +
        	    "i.stock_quantity, i.unit, i.reorder_point, " +
        	    "i.expiry_date, i.updated_at, " +
        	    "p.product_name, s.supplier_name " +
        	    "FROM inventory i " +
        	    "JOIN product p ON i.product_id = p.product_id " +
        	    "JOIN supplier s ON i.supplier_id = s.supplier_id " +
        	    "ORDER BY i.inventory_id";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Inventory inventory = new Inventory();

            inventory.setInventoryId(rs.getLong("inventory_id"));
            inventory.setProductId(rs.getLong("product_id"));
            inventory.setSupplierId(rs.getLong("supplier_id"));
            inventory.setStockQuantity(rs.getDouble("stock_quantity"));
            inventory.setUnit(rs.getString("unit"));
            inventory.setReorderPoint(rs.getDouble("reorder_point"));
            inventory.setExpiryDate(rs.getDate("expiry_date"));
            inventory.setUpdatedAt(rs.getTimestamp("updated_at"));
            inventory.setProductName(rs.getString("product_name"));
            inventory.setSupplierName(rs.getString("supplier_name"));
            

            
            list.add(inventory);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

}