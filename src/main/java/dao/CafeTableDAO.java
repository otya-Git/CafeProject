package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.CafeTable;

public class CafeTableDAO extends DAO {

    public List<CafeTable> selectAll() throws Exception {

        List<CafeTable> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
                "SELECT * FROM cafe_table ORDER BY table_id";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            CafeTable c = new CafeTable();

            c.setTableId(
                    rs.getInt("table_id"));

            c.setStatus(
                    rs.getString("status"));

            list.add(c);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }
    public void updateStatus(int tableId, String status)
            throws Exception {


        Connection con = getConnection();


        String sql =
            "UPDATE cafe_table "
          + "SET status=? "
          + "WHERE table_id=?";


        PreparedStatement ps =
            con.prepareStatement(sql);


        ps.setString(1, status);
        ps.setInt(2, tableId);


        ps.executeUpdate();


        ps.close();
        con.close();

    }
 // テーブル追加
    public void insert() throws Exception {

        Connection con = getConnection();

        String sql =
            "INSERT INTO cafe_table(status) "
          + "VALUES('空席')";


        PreparedStatement st =
                con.prepareStatement(sql);


        st.executeUpdate();


        st.close();
        con.close();

    }
}