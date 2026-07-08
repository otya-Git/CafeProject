package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Supplier;

public class SupplierDAO extends DAO {

    // 仕入先一覧取得
    public List<Supplier> selectAll() throws Exception {

        List<Supplier> list = new ArrayList<>();

        Connection con = getConnection();

        String sql = "SELECT * FROM supplier ORDER BY supplier_id";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Supplier supplier = new Supplier();

            supplier.setSupplierId(rs.getLong("supplier_id"));
            supplier.setSupplierName(rs.getString("supplier_name"));
            supplier.setPhone(rs.getString("phone"));
            supplier.setAddress(rs.getString("address"));

            list.add(supplier);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
}