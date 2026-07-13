package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Shift;

public class ShiftDAO extends DAO {

    // 全てのシフト情報を取得するメソッド
    public List<Shift> findAll() throws Exception {
        List<Shift> shiftList = new ArrayList<>();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = this.getConnection();

            String sql = "SELECT shift_id, user_id, work_date, start_time, end_time FROM shift ORDER BY work_date ASC, start_time ASC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Shift shift = new Shift();
                shift.setshift_id(rs.getInt("shift_id"));
                shift.setuser_id(rs.getInt("user_id"));
                
                if (rs.getDate("work_date") != null) {
                    shift.setwork_date(rs.getDate("work_date").toLocalDate());
                }
                if (rs.getTime("start_time") != null) {
                    shift.setstart_time(rs.getTime("start_time").toLocalTime());
                }
                if (rs.getTime("end_time") != null) {
                    shift.setend_time(rs.getTime("end_time").toLocalTime());
                }
                
                shiftList.add(shift);
            }
        } finally {
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) {} }
            if (con != null) { try { con.close(); } catch (SQLException e) {} }
        }
        return shiftList;
    }

    // 新しいシフトを登録するメソッド
    public int insert(Shift shift) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "INSERT INTO shift (user_id, work_date, start_time, end_time) VALUES (?, ?, ?, ?)";

        try {
            con = this.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, shift.getuser_id());
            pstmt.setDate(2, java.sql.Date.valueOf(shift.getWork_date()));
            pstmt.setTime(3, java.sql.Time.valueOf(shift.getstart_time()));
            pstmt.setTime(4, java.sql.Time.valueOf(shift.getend_time()));

            result = pstmt.executeUpdate();
        } finally {
            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) {} }
            if (con != null) { try { con.close(); } catch (SQLException e) {} }
        }
        return result;
    }
}
